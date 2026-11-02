from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT=Path(__file__).resolve().parent
ROOT=OUT.parents[2]
SUBMISSION=ROOT/'homework'/'10-23'
BUILD=OUT/'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS=OUT/'提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
parts=['import java.util.*;\n']
hashes={}
for qid in [139,76,15]:
    p=SUBMISSION/f'LK{qid}.java'
    data=p.read_bytes(); hashes[str(qid)]=hashlib.sha256(data).hexdigest()
    if not (SNAPSHOTS/p.name).exists(): (SNAPSHOTS/p.name).write_bytes(data)
    source,count=re.subn(r'\bclass\s+Solution\b',f'class Q{qid}',data.decode('utf-8-sig'),count=1)
    assert count==1
    parts.append(source)
parts.append(r'''
public class ReviewChecks {
    static Random random=new Random(1023);
    static Q139 q139=new Q139(); static Q76 q76=new Q76(); static Q15 q15=new Q15();
    static int[] checks=new int[3];
    static void require(boolean value,String message){if(!value)throw new AssertionError(message);}
    static String word(int n,String alphabet){StringBuilder b=new StringBuilder();for(int i=0;i<n;i++)b.append(alphabet.charAt(random.nextInt(alphabet.length())));return b.toString();}
    static void words(String s,List<String> dictionary){
        boolean[] seen=new boolean[s.length()+1];ArrayDeque<Integer> queue=new ArrayDeque<>();queue.add(0);seen[0]=true;
        while(!queue.isEmpty()){
            int index=queue.remove();
            for(String w:dictionary)if(s.startsWith(w,index)){
                int next=index+w.length();if(!seen[next]){seen[next]=true;queue.add(next);}
            }
        }
        require(q139.wordBreak(s,dictionary)==seen[s.length()],"139 "+s+" "+dictionary);checks[0]++;
    }
    static boolean covers(int[] a,int[] b){for(int i=0;i<128;i++)if(a[i]<b[i])return false;return true;}
    static void window(String s,String t){
        int[] target=new int[128];for(char c:t.toCharArray())target[c]++;
        int best=Integer.MAX_VALUE;
        for(int start=0;start<s.length();start++){
            int[] counts=new int[128];
            for(int end=start;end<s.length();end++){
                counts[s.charAt(end)]++;
                if(covers(counts,target)){best=Math.min(best,end-start+1);break;}
            }
        }
        String actual=q76.minWindow(s,t);int[] counts=new int[128];for(char c:actual.toCharArray())counts[c]++;
        require(best==Integer.MAX_VALUE?actual.isEmpty():actual.length()==best&&s.contains(actual)&&covers(counts,target),"76 "+s+" "+t+" got="+actual);checks[1]++;
    }
    static String tuple(int a,int b,int c){int[] values={a,b,c};Arrays.sort(values);return Arrays.toString(values);}
    static void sum(int[] a){
        Set<String> expected=new HashSet<>();
        for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++)for(int k=j+1;k<a.length;k++)if(a[i]+a[j]+a[k]==0)expected.add(tuple(a[i],a[j],a[k]));
        List<List<Integer>> result=q15.threeSum(a.clone());Set<String> actual=new HashSet<>();
        for(List<Integer> item:result){require(item.size()==3,"15 tuple length");actual.add(tuple(item.get(0),item.get(1),item.get(2)));}
        require(actual.equals(expected)&&actual.size()==result.size(),"15 "+Arrays.toString(a));checks[2]++;
    }
    public static void main(String[] args){
        words("ba",List.of("a","b"));words("ba",List.of("b","a"));words("leetcode",List.of("leet","code"));
        words("applepenapple",List.of("apple","pen"));words("catsandog",List.of("cats","dog","sand","and","cat"));words("aaaaaaa",List.of("aaaa","aaa"));
        for(String[] pair:new String[][]{{"ADOBECODEBANC","ABC"},{"a","aa"},{"a","a"},{"AAA","AA"},{"aaab","aab"},{"aA","A"},{"A","a"},{"ABBAAC","AAC"},{"abc","Z"}})window(pair[0],pair[1]);
        for(int[] a:new int[][]{{-1,0,1,2,-1,-4},{0,0,0,0},{-2,0,0,2,2},{1,2,3},{-4,-2,-2,0,2,2,4},{-100000,0,100000}})sum(a);
        for(int test=0;test<2000;test++){
            Set<String> dictionary=new HashSet<>();int size=1+random.nextInt(6);
            for(int i=0;i<size;i++)dictionary.add(word(1+random.nextInt(4),"ab"));
            words(word(1+random.nextInt(16),"ab"),new ArrayList<>(dictionary));
            window(word(random.nextInt(24),"abAB"),word(1+random.nextInt(8),"abAB"));
            int[] a=new int[3+random.nextInt(14)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(31)-15;sum(a);
        }
        words("a".repeat(299)+"b",List.of("a","aa","aaa","aaaa","aaaaa"));
        require(q76.minWindow("a".repeat(100000),"aa").equals("aa"),"76 large");checks[1]++;
        require(q15.threeSum(new int[3000]).equals(List.of(List.of(0,0,0))),"15 large repeats");checks[2]++;
        System.out.printf("{\"139\":%d,\"76\":%d,\"15\":%d}%n",checks[0],checks[1],checks[2]);
    }
}
''')
source=OUT/'ReviewChecks.java'
source.write_text('\n'.join(parts),encoding='utf-8')
compiled=subprocess.run(['javac','-encoding','UTF-8','-d',str(BUILD),str(source)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(BUILD),'ReviewChecks'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
counts=json.loads(executed.stdout.strip())
assert all(hashlib.sha256((SUBMISSION/f'LK{qid}.java').read_bytes()).hexdigest()==digest for qid,digest in hashes.items())
result={'date':'2026-10-23','status':'passed','test_cases_by_question':counts,'total_cases':sum(counts.values()),
        'seed':1023,'method':'实际Java方法；139与BFS对照、76与最短覆盖枚举对照、15与三下标枚举对照；每题2000组随机及边界用例',
        'normalization':'仅评审目录副本补java.util导入、为Solution更名；方法体不变',
        'submission_sha256':hashes,'submission_files_unchanged':True,
        'scope':'仅本次提交的139/76/15，不含缺交的215补正；本地检查不是力扣全量AC声明'}
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':'passed','counts':counts,'total':sum(counts.values()),'source_unchanged':True}))
