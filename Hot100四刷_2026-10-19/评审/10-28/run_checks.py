from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-28'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [146, 148, 239, 85]
hashes = {}
parts = ['import java.util.*;\n', 'class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }\n']
for i in ids:
    path = SUBMISSION / f'LK{i}.java'
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.name
    if target.exists():
        assert target.read_bytes() == data, 'Submission changed after snapshot'
    else:
        target.write_bytes(data)
    source = data.decode('utf-8-sig')
    if i != 146:
        source, count = re.subn(r'\b(?:public\s+)?class\s+Solution\b', f'class Q{i}', source, count=1)
        assert count == 1
    parts.append(source)

parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1028);
    static int[] checks = new int[4];
    static Q148 q148=new Q148();static Q239 q239=new Q239();static Q85 q85=new Q85();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static class CacheCheck {
        LRUCache actual;LinkedHashMap<Integer,Integer> expected=new LinkedHashMap<>(16,.75f,true);int capacity;
        CacheCheck(int c){capacity=c;actual=new LRUCache(c);}
        void put(int k,int v){actual.put(k,v);expected.put(k,v);if(expected.size()>capacity)expected.remove(expected.keySet().iterator().next());verify();}
        void get(int k){Integer v=expected.get(k);require(actual.get(k)==(v==null?-1:v),"146 get key="+k);verify();}
        void verify(){
            require(actual.size==expected.size()&&actual.map.size()==expected.size()&&actual.size<=capacity,"146 size/map");
            List<Integer> order=new ArrayList<>(expected.keySet());Map<Integer,Integer> savedValues=new HashMap<>(expected);Collections.reverse(order);int i=0;
            LRUCache.Node prev=actual.head; Set<LRUCache.Node> seen=Collections.newSetFromMap(new IdentityHashMap<>());
            for(LRUCache.Node n=actual.head.next;n!=actual.tail;n=n.next){
                require(n!=null&&seen.add(n)&&i<order.size(),"146 cycle/boundary");
                require(n.pre==prev&&prev.next==n,"146 backlinks");
                require(n.key==order.get(i)&&n.value==savedValues.get(n.key)&&actual.map.get(n.key)==n,"146 order/map identity");
                prev=n;i++;
            }
            require(i==order.size(),"146 list length");
            // The empty constructor leaves tail.pre null; record separately instead
            // of treating an unused sentinel field as a public-API failure.
            if(!expected.isEmpty())require(actual.tail.pre==prev,"146 tail");checks[0]++;
        }
    }
    static ListNode list(int[] a){ListNode h=new ListNode(),t=h;for(int v:a){t.next=new ListNode(v);t=t.next;}return h.next;}
    static List<ListNode> nodes(ListNode h){
        List<ListNode> a=new ArrayList<>();Set<ListNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(h!=null){require(seen.add(h),"148 output cycle");a.add(h);require(a.size()<=50000,"148 excess length");h=h.next;}return a;
    }
    static void sort(int[] a){
        ListNode h=list(a);List<ListNode> original=nodes(h);int[] expected=a.clone();Arrays.sort(expected);
        List<ListNode> actual=nodes(q148.sortList(h));require(actual.size()==a.length,"148 length");
        Set<ListNode> originals=Collections.newSetFromMap(new IdentityHashMap<>());originals.addAll(original);
        for(int i=0;i<actual.size();i++)require(actual.get(i).val==expected[i]&&originals.contains(actual.get(i)),"148 values/identity");checks[1]++;
    }
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
    static int matrixOracle(char[][] a){
        int best=0;
        for(int top=0;top<a.length;top++){
            boolean[] allOne=new boolean[a[0].length];Arrays.fill(allOne,true);
            for(int bottom=top;bottom<a.length;bottom++){
                int run=0;for(int col=0;col<a[0].length;col++){
                    allOne[col]&=a[bottom][col]=='1';run=allOne[col]?run+1:0;best=Math.max(best,run*(bottom-top+1));
                }
            }
        }return best;
    }
    static void matrix(char[][] a){
        char[][] input=new char[a.length][];for(int i=0;i<a.length;i++)input[i]=a[i].clone();
        require(q85.maximalRectangle(input)==matrixOracle(a),"85 "+Arrays.deepToString(a));
        require(Arrays.deepEquals(input,a),"85 input changed");checks[3]++;
    }
    public static void main(String[] args){
        LRUCache empty=new LRUCache(1);
        System.out.println("CONSTRUCTOR_TAIL_PRE_NULL="+(empty.tail.pre==null));
        System.out.println("CONSTRUCTOR_TAIL_NEXT_HEAD="+(empty.tail.next==empty.head));
        empty.put(7,8);System.out.println("FIRST_PUT_FIXES_TAIL_PRE="+(empty.tail.pre!=null&&empty.tail.pre.key==7));
        CacheCheck official=new CacheCheck(2);official.put(1,1);official.put(2,2);official.get(1);official.put(3,3);official.get(2);official.put(4,4);official.get(1);official.get(3);official.get(4);
        CacheCheck update=new CacheCheck(2);update.put(1,10);update.put(2,20);update.get(1);update.put(2,200);update.put(3,30);update.get(1);update.get(2);update.get(3);
        CacheCheck one=new CacheCheck(1);one.get(0);one.get(999);one.put(0,0);one.put(0,99);one.get(0);one.put(1,7);one.get(0);one.get(1);
        for(int cap=1;cap<=8;cap++){
            CacheCheck c=new CacheCheck(cap);
            for(int t=0;t<1200;t++){int key=random.nextInt(20);if(random.nextInt(3)==0)c.get(key);else c.put(key,random.nextInt(100001));}
        }
        CacheCheck largeCache=new CacheCheck(3000);for(int i=0;i<3003;i++)largeCache.put(i,i);largeCache.get(0);largeCache.get(3002);
        for(int[] a:new int[][]{{},{1},{2,1},{1,2},{3,2,1},{4,2,1,3},{-1,5,3,4,0},{7,7,7}})sort(a);
        for(int n=1;n<=7;n++){int count=(int)Math.pow(3,n);for(int bits=0;bits<count;bits++){
            int v=bits;int[] a=new int[n];for(int i=0;i<n;i++){a[i]=v%3-1;v/=3;}for(int k=1;k<=n;k++)window(a,k);
        }}
        window(new int[]{1,3,-1,-3,5,3,6,7},3);window(new int[]{1},1);window(new int[]{9,9,9},2);
        for(int rows=1;rows<=3;rows++)for(int cols=1;cols<=4;cols++)for(int bits=0;bits<(1<<(rows*cols));bits++){
            char[][] a=new char[rows][cols];for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)a[i][j]=((bits>>(i*cols+j))&1)==0?'0':'1';matrix(a);
        }
        matrix(new char[][]{"10100".toCharArray(),"10111".toCharArray(),"11111".toCharArray(),"10010".toCharArray()});
        for(int test=0;test<1000;test++){
            int[] a=new int[random.nextInt(301)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(200001)-100000;sort(a);
            int[] w=new int[1+random.nextInt(100)];for(int i=0;i<w.length;i++)w[i]=random.nextInt(20001)-10000;window(w,1+random.nextInt(w.length));
            int rows=1+random.nextInt(12),cols=1+random.nextInt(12);char[][] m=new char[rows][cols];
            for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)m[i][j]=random.nextBoolean()?'0':'1';matrix(m);
        }
        int[] longSort=new int[50000];for(int i=0;i<longSort.length;i++)longSort[i]=50000-i;sort(longSort);
        Arrays.fill(longSort,9);sort(longSort);
        int[] large=new int[100000];for(int i=0;i<large.length;i++)large[i]=i/5-10000;
        for(int k:new int[]{1,50000,100000})largeWindow(large,k);
        for(int i=0;i<large.length;i++)large[i]=9999-i/5;largeWindow(large,1000);
        Arrays.fill(large,7);largeWindow(large,100000);
        for(int i=0;i<large.length;i++)large[i]=random.nextInt(20001)-10000;largeWindow(large,1234);
        char[][] big=new char[200][200];for(char[] row:big)Arrays.fill(row,'1');matrix(big);
        for(char[] row:big)Arrays.fill(row,'0');matrix(big);
        for(int i=0;i<200;i++)for(int j=0;j<200;j++)big[i][j]=((i+j)&1)==0?'0':'1';matrix(big);
        for(int i=0;i<200;i++)for(int j=0;j<200;j++)big[i][j]=(i>=50&&i<150&&j>=40&&j<160)?'1':'0';matrix(big);
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-28', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util导入、ListNode辅助类、调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['check_units'] = {str(i): '操作核验次数' if i == 146 else '测试用例数' for i in ids}
        result['total_checks'] = sum(counts)
    result['constructor_146_observations'] = {key: re.search(key + '=(true|false)', execution.stdout).group(1) == 'true' for key in ['CONSTRUCTOR_TAIL_PRE_NULL', 'CONSTRUCTOR_TAIL_NEXT_HEAD', 'FIRST_PUT_FIXES_TAIL_PRE']}
else:
    result['passed'] = False
for i in ids:
    assert hashlib.sha256((SUBMISSION / f'LK{i}.java').read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
