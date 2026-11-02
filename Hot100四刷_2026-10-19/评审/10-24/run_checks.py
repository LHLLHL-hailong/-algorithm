from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT=Path(__file__).resolve().parent
ROOT=OUT.parents[2]
SUBMISSION=ROOT/'homework'/'10-24'
BUILD=OUT/'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS=OUT/'提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
paths={qid:SUBMISSION/(f'review/LK{qid}.java' if qid in {31,33} else f'LK{qid}.java') for qid in [2,19,23,141,142,31,33]}
hashes={}
parts=['import java.util.*;\nimport java.io.*;\nimport java.math.BigInteger;\n',
       'class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }\n']
for qid,p in paths.items():
    data=p.read_bytes();hashes[str(qid)]=hashlib.sha256(data).hexdigest()
    dest=SNAPSHOTS/p.relative_to(SUBMISSION)
    dest.parent.mkdir(parents=True,exist_ok=True)
    if not dest.exists():dest.write_bytes(data)
    source,count=re.subn(r'\b(?:public\s+)?class\s+Solution\b',f'class Q{qid}',data.decode('utf-8-sig'),count=1)
    assert count==1
    parts.append(source)
parts.append(r'''
public class ReviewChecks {
    static Random random=new Random(1024);
    static int[] checks=new int[7];
    static Q2 q2=new Q2();static Q19 q19=new Q19();static Q23 q23=new Q23();
    static Q141 q141=new Q141();static Q142 q142=new Q142();static Q31 q31=new Q31();static Q33 q33=new Q33();
    static class CapturedOutput extends OutputStream {long bytes;public void write(int b){bytes++;}public void write(byte[] b,int o,int n){bytes+=n;}}
    static CapturedOutput captured=new CapturedOutput();
    static void require(boolean v,String msg){if(!v)throw new AssertionError(msg);}
    static ListNode list(int[] a){ListNode head=new ListNode(),tail=head;for(int v:a){tail.next=new ListNode(v);tail=tail.next;}return head.next;}
    static List<ListNode> nodes(ListNode head){
        List<ListNode> result=new ArrayList<>();Set<ListNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(head!=null){require(seen.add(head),"unexpected output cycle");result.add(head);require(result.size()<=100001,"unexpected length");head=head.next;}
        return result;
    }
    static int[] values(ListNode head){return nodes(head).stream().mapToInt(n->n.val).toArray();}
    static BigInteger number(int[] reversed){StringBuilder b=new StringBuilder();for(int i=reversed.length-1;i>=0;i--)b.append(reversed[i]);return new BigInteger(b.toString());}
    static void add(int[] a,int[] b){
        String expected=number(a).add(number(b)).toString();
        int[] output=values(q2.addTwoNumbers(list(a),list(b)));StringBuilder actual=new StringBuilder();
        for(int i=output.length-1;i>=0;i--){require(output[i]>=0&&output[i]<=9,"2 invalid digit");actual.append(output[i]);}
        require(actual.toString().equals(expected),"2: "+Arrays.toString(a)+" + "+Arrays.toString(b));checks[0]++;
    }
    static void remove(int[] a,int n){
        ListNode head=list(a);List<ListNode> before=nodes(head);
        List<ListNode> expected=new ArrayList<>(before);expected.remove(a.length-n);
        List<ListNode> actual=nodes(q19.removeNthFromEnd(head,n));
        require(actual.equals(expected),"19: "+Arrays.toString(a)+" n="+n);checks[1]++;
    }
    static void merge(int[][] groups){
        ListNode[] heads=new ListNode[groups.length];List<Integer> expected=new ArrayList<>();
        Set<ListNode> original=Collections.newSetFromMap(new IdentityHashMap<>());
        for(int i=0;i<groups.length;i++){
            heads[i]=list(groups[i]);original.addAll(nodes(heads[i]));for(int v:groups[i])expected.add(v);
        }
        Collections.sort(expected);List<ListNode> actual=nodes(q23.mergeKLists(heads));
        require(actual.size()==expected.size(),"23 output length");
        for(int i=0;i<actual.size();i++)require(actual.get(i).val==expected.get(i),"23 sorted values");
        Set<ListNode> actualNodes=Collections.newSetFromMap(new IdentityHashMap<>());actualNodes.addAll(actual);
        require(actualNodes.equals(original),"23 lost/duplicated/created data nodes");checks[2]++;
    }
    static void cycle(int n,int entry){
        ListNode[] a=new ListNode[n];for(int i=0;i<n;i++)a[i]=new ListNode(i%3);
        for(int i=1;i<n;i++)a[i-1].next=a[i];if(n>0&&entry>=0)a[n-1].next=a[entry];
        ListNode head=n==0?null:a[0];
        require(q141.hasCycle(head)==(entry>=0),"141 n="+n+" entry="+entry);checks[3]++;
        PrintStream previous=System.out;ListNode actual;
        try{System.setOut(new PrintStream(captured));actual=q142.detectCycle(head);}finally{System.setOut(previous);}
        require(actual==(entry<0?null:a[entry]),"142 n="+n+" entry="+entry);checks[4]++;
        for(int i=0;i<n;i++)require(a[i].next==(i+1<n?a[i+1]:(entry<0?null:a[entry])),"cycle input changed");
    }
    static int cmp(int[] a,int[] b){for(int i=0;i<a.length;i++)if(a[i]!=b[i])return Integer.compare(a[i],b[i]);return 0;}
    static int[] best;
    static void enumerate(int[] sorted,boolean[] used,int[] candidate,int depth,int[] original){
        if(depth==sorted.length){if(cmp(candidate,original)>0&&(best==null||cmp(candidate,best)<0))best=candidate.clone();return;}
        for(int i=0;i<sorted.length;i++){
            if(used[i]||(i>0&&sorted[i]==sorted[i-1]&&!used[i-1]))continue;
            used[i]=true;candidate[depth]=sorted[i];enumerate(sorted,used,candidate,depth+1,original);used[i]=false;
        }
    }
    static void permutation(int[] a){
        int[] sorted=a.clone();Arrays.sort(sorted);best=null;enumerate(sorted,new boolean[a.length],new int[a.length],0,a);
        int[] expected=best==null?sorted:best;int[] actual=a.clone();q31.nextPermutation(actual);
        require(Arrays.equals(actual,expected),"31 "+Arrays.toString(a));checks[5]++;
    }
    static void search(int[] a,int target){int expected=-1;for(int i=0;i<a.length;i++)if(a[i]==target){expected=i;break;}
        require(q33.search(a.clone(),target)==expected,"33 "+Arrays.toString(a)+" target="+target);checks[6]++;}
    static int[] digits(int n){int[] a=new int[n];for(int i=0;i<n;i++)a[i]=random.nextInt(10);if(n>1&&a[n-1]==0)a[n-1]=1;return a;}
    public static void main(String[] args){
        add(new int[]{0},new int[]{0});add(new int[]{9},new int[]{1});add(new int[]{1},new int[]{9,9,9});add(new int[]{9,9,9},new int[]{1});
        add(new int[]{2,4,3},new int[]{5,6,4});add(new int[]{9,9,9,9,9,9,9},new int[]{9,9,9,9});
        remove(new int[]{1},1);remove(new int[]{1,2},1);remove(new int[]{1,2},2);remove(new int[]{1,2,3,4,5},2);
        merge(new int[][]{});merge(new int[][]{{}});merge(new int[][]{{},{},{}});merge(new int[][]{{1,4,5},{1,3,4},{2,6}});
        merge(new int[][]{{Integer.MIN_VALUE,0},{Integer.MAX_VALUE}});
        for(int n=0;n<=40;n++)for(int entry=-1;entry<n;entry++)cycle(n,entry);
        for(int[] a:new int[][]{{1},{1,2},{2,1},{1,3,2,2},{3,2,1},{1,1,5},{0,0,0}})permutation(a);
        for(int[] a:new int[][]{{1},{4,5,6,7,0,1,2},{1,3},{3,1},{0,1,2,3,4}})for(int target=-1;target<=8;target++)search(a,target);
        for(int test=0;test<1000;test++){
            add(digits(1+random.nextInt(100)),digits(1+random.nextInt(100)));
            int[] a=new int[1+random.nextInt(30)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(200)-100;remove(a,1+random.nextInt(a.length));
            int[][] groups=new int[random.nextInt(20)][];
            for(int i=0;i<groups.length;i++){groups[i]=new int[random.nextInt(16)];for(int j=0;j<groups[i].length;j++)groups[i][j]=random.nextInt(21)-10;Arrays.sort(groups[i]);}merge(groups);
            int count=1+random.nextInt(100);cycle(count,random.nextInt(count+1)-1);
            int[] p=new int[1+random.nextInt(7)];for(int i=0;i<p.length;i++)p[i]=random.nextInt(5);permutation(p);
            int size=1+random.nextInt(50),shift=random.nextInt(size);int[] rotated=new int[size];for(int i=0;i<size;i++)rotated[i]=((i+shift)%size)*2-50;
            search(rotated,rotated[random.nextInt(size)]);search(rotated,random.nextInt(103)-51);
        }
        int[][] manyEmpty=new int[10000][];for(int i=0;i<manyEmpty.length;i++)manyEmpty[i]=new int[0];merge(manyEmpty);
        int[][] many=new int[64][128];for(int i=0;i<many.length;i++)for(int j=0;j<many[i].length;j++)many[i][j]=j;merge(many);
        int[] nines=new int[100];Arrays.fill(nines,9);add(nines,new int[]{1});
        System.out.printf("{\"2\":%d,\"19\":%d,\"23\":%d,\"141\":%d,\"142\":%d,\"31\":%d,\"33\":%d,\"142_debug_bytes\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4],checks[5],checks[6],captured.bytes);
    }
}
''')
java=OUT/'ReviewChecks.java'
java.write_text('\n'.join(parts),encoding='utf-8')
compiled=subprocess.run(['javac','-encoding','UTF-8','-d',str(BUILD),str(java)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(BUILD),'ReviewChecks'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
counts=json.loads(executed.stdout.strip())
assert all(hashlib.sha256(paths[int(qid)].read_bytes()).hexdigest()==digest for qid,digest in hashes.items())
cases={qid:count for qid,count in counts.items() if qid!='142_debug_bytes'}
result={'date':'2026-10-24','status':'passed','test_cases_by_question':cases,'total_cases':sum(cases.values()),
    'debug_output':{'question':142,'captured_bytes':counts['142_debug_bytes'],'line':39,'source':'homework/10-24/LK142.java'},
    'seed':1024,'method':'实际Java：BigInteger参考两数相加；链表节点身份及排序参考；环入口按节点身份校验；31穷举排列参考；33线性参考；随机与边界',
    'normalization':'仅新目录副本补导入与ListNode、为Solution更名并去public以同文件编译；方法体不变。142调试输出在测试驱动中捕获计数，未删除。',
    'submission_paths':{str(qid):p.relative_to(ROOT).as_posix() for qid,p in paths.items()},
    'submission_sha256':hashes,'submission_files_unchanged':True,
    'scope':'合法题目约束；两数相加允许复用独立输入链表；本地检查不是力扣全量AC证明'}
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':'passed','counts':cases,'total':sum(cases.values()),'142_debug_bytes':counts['142_debug_bytes'],'source_unchanged':True}))
