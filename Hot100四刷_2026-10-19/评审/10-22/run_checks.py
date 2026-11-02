from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT=Path(__file__).resolve().parent
ROOT=OUT.parents[2]
SUBMISSION=ROOT/'homework'/'10-22'
BUILD=OUT/'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS=OUT/'提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids=[139,155,215,136,169]
hashes={}
parts=['import java.util.*;\n']
for qid in ids:
    path=SUBMISSION/f'LK{qid}.java'
    data=path.read_bytes(); hashes[str(qid)]=hashlib.sha256(data).hexdigest()
    if not (SNAPSHOTS/path.name).exists(): (SNAPSHOTS/path.name).write_bytes(data)
    source=data.decode('utf-8-sig')
    if qid==139:
        counter=[0]
        def rename(match):
            counter[0]+=1
            return f'class Q139V{counter[0]}'
        source=re.sub(r'\bclass\s+Solution\b',rename,source)
        assert counter[0]==2
    elif qid!=155:
        source,count=re.subn(r'\bclass\s+Solution\b',f'class Q{qid}',source,count=1)
        assert count==1
    parts.append(source)
parts.append(r'''
public class ReviewChecks {
    static Random random=new Random(1022);
    static Q139V1 first139=new Q139V1(); static Q139V2 q139=new Q139V2();
    static Q215 q215=new Q215(); static Q136 q136=new Q136(); static Q169 q169=new Q169();
    static int[] checks=new int[5]; static boolean first139Counterexample;
    static void require(boolean value,String message) { if(!value)throw new AssertionError(message); }
    static String word(int len) { StringBuilder b=new StringBuilder(); for(int i=0;i<len;i++)b.append((char)('a'+random.nextInt(2))); return b.toString(); }
    static void words(String s,List<String> dictionary) {
        boolean[] seen=new boolean[s.length()+1]; ArrayDeque<Integer> queue=new ArrayDeque<>(); queue.add(0); seen[0]=true;
        while(!queue.isEmpty()) {
            int index=queue.remove();
            for(String w:dictionary) if(s.startsWith(w,index)) {
                int next=index+w.length(); if(!seen[next]) { seen[next]=true; queue.add(next); }
            }
        }
        require(q139.wordBreak(s,dictionary)==seen[s.length()],"139 V2: "+s+" "+dictionary); checks[0]++;
    }
    static void stackStep(MinStack stack,List<Integer> reference,boolean push,int value) {
        if(push) { stack.push(value); reference.add(value); }
        else { stack.pop(); reference.remove(reference.size()-1); }
        if(!reference.isEmpty()) {
            require(stack.top()==reference.get(reference.size()-1),"155 top");
            require(stack.getMin()==Collections.min(reference),"155 min");
        }
        checks[1]++;
    }
    static void kth(int[] a,int k) {
        int[] sorted=a.clone(); Arrays.sort(sorted);
        require(q215.findKthLargest(a.clone(),k)==sorted[a.length-k],"215: "+Arrays.toString(a)+" k="+k); checks[2]++;
    }
    static void single(int[] a,int expected) {
        require(q136.singleNumber(a.clone())==expected,"136: "+Arrays.toString(a)); checks[3]++;
    }
    static void majority(int[] a,int expected) {
        int count=0; for(int v:a)if(v==expected)count++;
        require(count>a.length/2,"invalid reference majority");
        require(q169.majorityElement(a.clone())==expected,"169: "+Arrays.toString(a)); checks[4]++;
    }
    static void shuffle(int[] a) { for(int i=a.length-1;i>0;i--) { int j=random.nextInt(i+1),v=a[i];a[i]=a[j];a[j]=v; } }
    public static void main(String[] args) {
        first139Counterexample=!first139.wordBreak("ba",List.of("a","b"));
        require(first139Counterexample,"139 V1 expected counterexample disappeared; inspect changed source");
        require(q139.wordBreak("ba",List.of("a","b")),"139 V2 counterexample");
        words("ba",List.of("a","b")); words("leetcode",List.of("leet","code"));
        words("applepenapple",List.of("apple","pen")); words("catsandog",List.of("cats","dog","sand","and","cat"));
        words("aaaaaaa",List.of("aaaa","aaa")); words("a",List.of("b"));
        MinStack extreme=new MinStack(); List<Integer> reference=new ArrayList<>();
        for(int v:new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,0,Integer.MAX_VALUE})stackStep(extreme,reference,true,v);
        while(!reference.isEmpty())stackStep(extreme,reference,false,0);
        stackStep(extreme,reference,true,Integer.MIN_VALUE); stackStep(extreme,reference,false,0);
        for(int trial=0;trial<100;trial++) {
            MinStack stack=new MinStack(); List<Integer> values=new ArrayList<>();
            for(int step=0;step<100;step++)stackStep(stack,values,values.isEmpty()||random.nextInt(3)!=0,random.nextInt());
        }
        for(int[] a:new int[][]{{1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{2,2,2,2},{-10000,10000,0}})
            for(int k=1;k<=a.length;k++)kth(a,k);
        single(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},Integer.MIN_VALUE);
        single(new int[]{0},0); single(new int[]{2,2,1},1);
        majority(new int[]{1},1); majority(new int[]{2,2,1,1,1,2,2},2); majority(new int[]{-1,2,-1,3,-1},-1);
        for(int test=0;test<2000;test++) {
            Set<String> dictionary=new HashSet<>(); for(int i=0;i<1+random.nextInt(6);i++)dictionary.add(word(1+random.nextInt(4)));
            words(word(1+random.nextInt(16)),new ArrayList<>(dictionary));
            int[] a=new int[1+random.nextInt(60)]; for(int i=0;i<a.length;i++)a[i]=random.nextInt(21)-10; kth(a,1+random.nextInt(a.length));
            int pairs=random.nextInt(20),only=123456789; int[] paired=new int[2*pairs+1]; Set<Integer> used=new HashSet<>();
            paired[0]=only;
            for(int i=0;i<pairs;i++) { int value; do {value=random.nextInt(1000)-500;}while(!used.add(value)); paired[2*i+1]=paired[2*i+2]=value; }
            shuffle(paired);single(paired,only);
            int n=1+random.nextInt(100),m=random.nextInt(5)-2; int[] voters=new int[n];
            int major=n/2+1+random.nextInt(n-n/2); Arrays.fill(voters,0,major,m);
            for(int i=major;i<n;i++){int other; do {other=random.nextInt(5)-2;}while(other==m);voters[i]=other;}
            shuffle(voters);majority(voters,m);
        }
        int[] identical=new int[100000];Arrays.fill(identical,7);kth(identical,50000);
        int[] increasing=new int[3000];for(int i=0;i<increasing.length;i++)increasing[i]=i;kth(increasing,1);
        words("a".repeat(299)+"b",List.of("a","aa","aaa","aaaa","aaaaa"));
        System.out.printf("{\"139_v2\":%d,\"155_operations\":%d,\"215\":%d,\"136\":%d,\"169\":%d,\"139_v1_counterexample_confirmed\":%s}%n",checks[0],checks[1],checks[2],checks[3],checks[4],first139Counterexample);
    }
}
''')
source_path=OUT/'ReviewChecks.java'
source_path.write_text('\n'.join(parts),encoding='utf-8')
compiled=subprocess.run(['javac','-encoding','UTF-8','-d',str(BUILD),str(source_path)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(BUILD),'ReviewChecks'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
checks=json.loads(executed.stdout.strip())
assert all(hashlib.sha256((SUBMISSION/f'LK{qid}.java').read_bytes()).hexdigest()==hashes[str(qid)] for qid in ids)
result={'date':'2026-10-22','status':'139_first_version_incorrect_latest_version_passed_others_passed',
    'checks':checks,'code_passed_ids':[139,155,215,136,169],
    '139_variants':[{'version':1,'independence':'self_reported_independent','status':'incorrect',
        'counterexample':{'s':'ba','dictionary':['a','b'],'expected':True,'actual':False}},
        {'version':2,'independence':'self_reported_after_reading_answer','status':'passed','grade':'B'}],
    '215_performance_note':'固定首元素；升序N个数且k=1，扫描次数N(N-1)/2；全相等均分不解决有序退化。最好Theta(N)，最坏Theta(N^2)。本次3000升序功能检查通过，不作为最大约束下时限证明。',
    'normalization':'同文件的139两个练习版本分别更名、补java.util导入；所有方法体保持原样。重复类名本身不作为算法失败。',
    'method':'实际Java，BFS参考139，ArrayList参考155，排序参考215，合法配对生成136，严格多数输入169',
    'seed':1022,'submission_sha256':hashes,'submission_files_unchanged':True,
    'scope':'本地功能正确性不等于已确认力扣全量AC或最坏输入运行时合格'}
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':result['status'],'checks':checks,'source_unchanged':True}))
