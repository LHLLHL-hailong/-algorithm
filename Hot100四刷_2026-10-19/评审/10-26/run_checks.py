from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-26'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [20, 32, 239, 394, 739, 139, 215]
paths = {i: SUBMISSION / (f'review/LK{i}.java' if i in {139, 215} else f'LK{i}.java') for i in ids}
hashes = {}
parts = ['import java.util.*;\n']
for i, path in paths.items():
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.relative_to(SUBMISSION)
    target.parent.mkdir(parents=True, exist_ok=True)
    if target.exists():
        assert target.read_bytes() == data, 'Submission changed after snapshot'
    else:
        target.write_bytes(data)
    source, count = re.subn(r'\b(?:public\s+)?class\s+Solution\b', f'class Q{i}', data.decode('utf-8-sig'), count=1)
    assert count == 1
    parts.append(source)

parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1026);
    static int[] checks = new int[7];
    static int partitionChecks;
    static Q20 q20=new Q20();static Q32 q32=new Q32();static Q239 q239=new Q239();
    static Q394 q394=new Q394();static Q739 q739=new Q739();static Q139 q139=new Q139();static Q215 q215=new Q215();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void valid(String s){
        String reduced=s,old;
        do{old=reduced;reduced=reduced.replace("()","").replace("[]","").replace("{}","");}while(!old.equals(reduced));
        require(q20.isValid(s)==reduced.isEmpty(),"20 "+s);checks[0]++;
    }
    static int longestOracle(String s){
        int best=0;
        for(int i=0;i<s.length();i++){int balance=0;for(int j=i;j<s.length();j++){
            balance+=s.charAt(j)=='('?1:-1;if(balance<0)break;if(balance==0)best=Math.max(best,j-i+1);
        }}return best;
    }
    static void longest(String s){longest(s,longestOracle(s));}
    static void longest(String s,int expected){require(q32.longestValidParentheses(s)==expected,"32 "+s);checks[1]++;}
    static void window(int[] a,int k){
        int[] expected=new int[a.length-k+1];
        for(int i=0;i<expected.length;i++){int best=Integer.MIN_VALUE;for(int j=i;j<i+k;j++)best=Math.max(best,a[j]);expected[i]=best;}
        window(a,k,expected);
    }
    static void window(int[] a,int k,int[] expected){
        int[] input=a.clone();require(Arrays.equals(q239.maxSlidingWindow(input,k),expected),"239 k="+k+" n="+a.length);
        require(Arrays.equals(input,a),"239 input changed");checks[2]++;
    }
    static void largeWindow(int[] a,int k){
        int[] expected=new int[a.length-k+1];TreeMap<Integer,Integer> counts=new TreeMap<>();
        for(int i=0;i<a.length;i++){
            counts.merge(a[i],1,Integer::sum);
            if(i>=k){int v=a[i-k];counts.compute(v,(key,c)->c==1?null:c-1);}
            if(i>=k-1)expected[i-k+1]=counts.lastKey();
        }window(a,k,expected);
    }
    static void temperatures(int[] a){
        int[] expected=new int[a.length];
        for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++)if(a[j]>a[i]){expected[i]=j-i;break;}
        temperatures(a,expected);
    }
    static void temperatures(int[] a,int[] expected){
        int[] input=a.clone();require(Arrays.equals(q739.dailyTemperatures(input),expected),"739 n="+a.length);
        require(Arrays.equals(input,a),"739 input changed");checks[4]++;
    }
    record Encoding(String text,String plain){}
    static Encoding expression(int depth){
        StringBuilder text=new StringBuilder(),plain=new StringBuilder();
        int terms=1+random.nextInt(3);
        for(int i=0;i<terms;i++){
            if(depth>0&&random.nextBoolean()){
                Encoding child=expression(depth-1);int copies=1+random.nextInt(12);
                text.append(copies).append('[').append(child.text).append(']');plain.append(child.plain.repeat(copies));
            }else{
                int len=1+random.nextInt(3);for(int j=0;j<len;j++){char c=(char)('a'+random.nextInt(4));text.append(c);plain.append(c);}
            }
        }return new Encoding(text.toString(),plain.toString());
    }
    static void decode(String text,String expected){
        require(q394.decodeString(text).equals(expected),"394 "+text);checks[3]++;
    }
    static boolean wordOracle(String s,List<String> words){
        boolean[] seen=new boolean[s.length()+1];ArrayDeque<Integer> queue=new ArrayDeque<>();seen[0]=true;queue.add(0);
        while(!queue.isEmpty()){
            int start=queue.remove();if(start==s.length())return true;
            for(String word:words)if(s.startsWith(word,start)){
                int next=start+word.length();if(!seen[next]){seen[next]=true;queue.add(next);}
            }
        }return false;
    }
    static void wordBreak(String s,List<String> words){require(q139.wordBreak(s,words)==wordOracle(s,words),"139 "+s+" "+words);checks[5]++;}
    static void kth(int[] a,int k){
        int[] sorted=a.clone();Arrays.sort(sorted);int[] input=a.clone();
        require(q215.findKthLargest(input,k)==sorted[a.length-k],"215 k="+k+" n="+a.length);
        Arrays.sort(input);require(Arrays.equals(input,sorted),"215 multiset changed");checks[6]++;
    }
    static void partition(int[] a,int left,int right){
        int[] input=a.clone(),expected=a.clone();Arrays.sort(expected,left,right+1);
        int split=q215.search(input,left,right);require(split>=left&&split<=right,"215 split range");
        for(int i=left;i<split;i++)require(input[i]<=input[split],"215 left partition");
        for(int i=split+1;i<=right;i++)require(input[i]>=input[split],"215 right partition");
        Arrays.sort(input,left,right+1);require(Arrays.equals(input,expected),"215 interval multiset/outside changed");partitionChecks++;
    }
    static String letters(int n,int alphabet){StringBuilder b=new StringBuilder();for(int i=0;i<n;i++)b.append((char)('a'+random.nextInt(alphabet)));return b.toString();}
    public static void main(String[] args){
        char[] bracketChars={'(',')','[',']','{','}'};
        for(int n=1;n<=5;n++){int count=(int)Math.pow(6,n);for(int bits=0;bits<count;bits++){
            int v=bits;StringBuilder s=new StringBuilder();for(int i=0;i<n;i++){s.append(bracketChars[v%6]);v/=6;}valid(s.toString());
        }}
        valid("()[]{}");valid("([)]");valid("([])");valid("(]");valid("()".repeat(5000));valid("(".repeat(5000)+")".repeat(5000));
        for(int n=0;n<=14;n++)for(int bits=0;bits<(1<<n);bits++){
            StringBuilder s=new StringBuilder();for(int i=0;i<n;i++)s.append(((bits>>i)&1)==0?'(':')');longest(s.toString());
        }
        longest("(".repeat(30000),0);longest(")".repeat(30000),0);longest("()".repeat(15000),30000);
        longest("(".repeat(15000)+")".repeat(15000),30000);longest(")()())",4);
        for(int n=1;n<=7;n++){int count=(int)Math.pow(3,n);for(int bits=0;bits<count;bits++){
            int v=bits;int[] a=new int[n];for(int i=0;i<n;i++){a[i]=v%3-1;v/=3;}for(int k=1;k<=n;k++)window(a,k);
        }}
        window(new int[]{1,3,-1,-3,5,3,6,7},3);window(new int[]{1},1);window(new int[]{9,9,9},2);
        decode("a","a");decode("abc","abc");decode("3[a]2[bc]","aaabcbc");decode("3[a2[c]]","accaccacc");
        decode("2[abc]3[cd]ef","abcabccdcdcdef");decode("10[a]","a".repeat(10));decode("300[300[a]]","a".repeat(90000));
        decode("10[10[10[10[a]]]]","a".repeat(10000));decode("1[1[1[1[1[1[1[1[1[a]]]]]]]]]","a");
        int generated=0;while(generated<1000){Encoding e=expression(3);if(e.text.length()<=30&&e.plain.length()<=100000){decode(e.text,e.plain);generated++;}}
        for(int[] a:new int[][]{{30},{30,30},{100,100},{73,74,75,71,69,72,76,73},{100,90,80,30},{30,40,50,60}})temperatures(a);
        wordBreak("leetcode",List.of("leet","code"));wordBreak("applepenapple",List.of("apple","pen"));
        wordBreak("catsandog",List.of("cats","dog","sand","and","cat"));wordBreak("ba",List.of("a","b"));
        wordBreak("a",List.of("b"));wordBreak("aaaaaaa",List.of("aaaa","aaa"));
        List<String> aWords=new ArrayList<>();for(int i=1;i<=20;i++)aWords.add("a".repeat(i));
        wordBreak("a".repeat(300),aWords);wordBreak("a".repeat(299)+"b",aWords);
        for(int[] a:new int[][]{{1},{2,1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{0,0,0},{-10000,10000}})for(int k=1;k<=a.length;k++)kth(a,k);
        for(int test=0;test<1000;test++){
            int[] a=new int[1+random.nextInt(100)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(20001)-10000;window(a,1+random.nextInt(a.length));
            int[] ts=new int[1+random.nextInt(200)];for(int i=0;i<ts.length;i++)ts[i]=30+random.nextInt(71);temperatures(ts);
            StringBuilder s=new StringBuilder();for(int i=0,n=random.nextInt(100);i<n;i++)s.append(random.nextBoolean()?'(':')');longest(s.toString());
            String wordString=letters(1+random.nextInt(60),3);Set<String> dict=new HashSet<>();for(int i=0,n=1+random.nextInt(25);i<n;i++)dict.add(letters(1+random.nextInt(8),3));wordBreak(wordString,new ArrayList<>(dict));
            int[] kInput=new int[1+random.nextInt(200)];for(int i=0;i<kInput.length;i++)kInput[i]=random.nextInt(21)-10;
            kth(kInput,1+random.nextInt(kInput.length));int left=random.nextInt(kInput.length),right=left+random.nextInt(kInput.length-left);partition(kInput,left,right);
        }
        int[] large=new int[100000];for(int i=0;i<large.length;i++)large[i]=i/5-10000;
        for(int k:new int[]{1,50000,100000}){largeWindow(large,k);kth(large,k);}
        for(int i=0;i<large.length;i++)large[i]=9999-i/5;
        largeWindow(large,1000);for(int k:new int[]{1,50000,100000})kth(large,k);
        Arrays.fill(large,7);largeWindow(large,100000);for(int k:new int[]{1,50000,100000})kth(large,k);
        for(int i=0;i<large.length;i++)large[i]=random.nextInt(20001)-10000;largeWindow(large,1234);kth(large,54321);
        int[] hugeT=new int[100000],expectedT=new int[100000];Arrays.fill(hugeT,30);temperatures(hugeT,expectedT);
        for(int i=0;i<hugeT.length;i++)hugeT[i]=30+i%71;
        for(int i=0;i<hugeT.length;i++)expectedT[i]=(hugeT[i]<100&&i+1<hugeT.length)?1:0;temperatures(hugeT,expectedT);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("PARTITION_CHECKS="+partitionChecks);
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-26', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util导入、调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['total_cases'] = sum(counts)
    partition_match = re.search(r'PARTITION_CHECKS=(\d+)', execution.stdout)
    if partition_match:
        result['additional_partition_checks_215'] = int(partition_match.group(1))
else:
    result['passed'] = False
for i, path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
