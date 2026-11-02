from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-25'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [146, 148, 160, 206, 234, 48, 155]
paths = {i: SUBMISSION / (f'review/LK{i}.java' if i in {48, 155} else f'LK{i}.java') for i in ids}
hashes = {}
parts = ['import java.util.*;\n', 'class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }\n']
for i, path in paths.items():
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.relative_to(SUBMISSION)
    target.parent.mkdir(parents=True, exist_ok=True)
    if target.exists():
        assert target.read_bytes() == data, 'Submission changed after snapshot'
    else:
        target.write_bytes(data)
    source = data.decode('utf-8-sig')
    if i not in {146, 155}:
        source, count = re.subn(r'\b(?:public\s+)?class\s+Solution\b', f'class Q{i}', source, count=1)
        assert count == 1
    parts.append(source)

parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1025);
    static long[] checks = new long[7];
    static Q148 q148 = new Q148(); static Q160 q160 = new Q160();
    static Q206 q206 = new Q206(); static Q234 q234 = new Q234(); static Q48 q48 = new Q48();
    static void require(boolean ok, String message) { if (!ok) throw new AssertionError(message); }
    static ListNode list(int[] a) { ListNode h=new ListNode(),t=h;for(int v:a){t.next=new ListNode(v);t=t.next;}return h.next; }
    static List<ListNode> nodes(ListNode h) {
        List<ListNode> a=new ArrayList<>(); Set<ListNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(h!=null){require(seen.add(h),"unexpected cycle");a.add(h);require(a.size()<=100001,"length overflow");h=h.next;}return a;
    }
    static int[] values(List<ListNode> a){return a.stream().mapToInt(x->x.val).toArray();}
    static IdentityHashMap<ListNode,ListNode> links(ListNode... heads){
        IdentityHashMap<ListNode,ListNode> before=new IdentityHashMap<>();
        for(ListNode h:heads)for(ListNode n:nodes(h))before.put(n,n.next);return before;
    }
    static void unchanged(IdentityHashMap<ListNode,ListNode> before){
        for(var e:before.entrySet())require(e.getKey().next==e.getValue(),"input links changed");
    }
    static void sort(int[] a){
        ListNode h=list(a);List<ListNode> original=nodes(h);int[] expected=a.clone();Arrays.sort(expected);
        List<ListNode> actual=nodes(q148.sortList(h));require(Arrays.equals(values(actual),expected),"148 sorted values");
        Set<ListNode> set=Collections.newSetFromMap(new IdentityHashMap<>());set.addAll(original);
        require(actual.size()==original.size()&&set.containsAll(actual),"148 node identity");checks[1]++;
    }
    static ListNode prefix(int length,ListNode tail){for(int i=0;i<length;i++)tail=new ListNode(i%3,tail);return tail;}
    static void intersection(int a,int b,int shared){
        ListNode common=prefix(shared,null),ha=prefix(a,common),hb=prefix(b,common);
        var before=links(ha,hb);require(q160.getIntersectionNode(ha,hb)==common,"160 a="+a+" b="+b+" shared="+shared);
        unchanged(before);checks[2]++;
    }
    static void reverse(int[] a){
        ListNode h=list(a);List<ListNode> expected=nodes(h);Collections.reverse(expected);
        require(nodes(q206.reverseList(h)).equals(expected),"206 identity/order");checks[3]++;
    }
    static void palindrome(int[] a){
        ListNode h=list(a);var before=links(h);boolean expected=true;
        for(int i=0;i<a.length/2;i++)if(a[i]!=a[a.length-1-i])expected=false;
        require(q234.isPalindrome(h)==expected,"234 "+Arrays.toString(a));unchanged(before);checks[4]++;
    }
    static void rotate(int n){
        int[][] input=new int[n][n],expected=new int[n][n];
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)input[i][j]=random.nextInt(2001)-1000;
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)expected[j][n-1-i]=input[i][j];
        q48.rotate(input);require(Arrays.deepEquals(input,expected),"48 n="+n);checks[5]++;
    }
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
            require(i==order.size()&&actual.tail.pre==prev,"146 tail");checks[0]++;
        }
    }
    static class StackCheck {
        MinStack actual=new MinStack();ArrayDeque<Integer> expected=new ArrayDeque<>();TreeMap<Integer,Integer> sorted=new TreeMap<>();
        void push(int v){actual.push(v);expected.push(v);sorted.merge(v,1,Integer::sum);checks[6]++;}
        void pop(){actual.pop();int v=expected.pop();sorted.compute(v,(k,c)->c==1?null:c-1);checks[6]++;}
        void query(){require(actual.top()==expected.peek(),"155 top");require(actual.getMin()==sorted.firstKey(),"155 min");checks[6]+=2;}
    }
    public static void main(String[] args){
        CacheCheck official=new CacheCheck(2);official.put(1,1);official.put(2,2);official.get(1);official.put(3,3);official.get(2);official.put(4,4);official.get(1);official.get(3);official.get(4);
        CacheCheck update=new CacheCheck(2);update.put(1,10);update.put(2,20);update.get(1);update.put(2,200);update.put(3,30);update.get(1);update.get(2);update.get(3);
        CacheCheck one=new CacheCheck(1);one.get(0);one.put(0,0);one.put(0,99);one.get(0);one.put(1,7);one.get(0);one.get(1);
        for(int cap=1;cap<=8;cap++){
            CacheCheck c=new CacheCheck(cap);
            for(int t=0;t<1200;t++){int key=random.nextInt(20);if(random.nextInt(3)==0)c.get(key);else c.put(key,random.nextInt(100001));}
        }
        CacheCheck largeCache=new CacheCheck(3000);for(int i=0;i<3003;i++)largeCache.put(i,i);largeCache.get(0);largeCache.get(3002);
        for(int[] a:new int[][]{{},{1},{2,1},{1,2},{3,2,1},{4,2,1,3},{-1,5,3,4,0},{7,7,7}})sort(a);
        for(int a=0;a<=8;a++)for(int b=0;b<=8;b++)for(int c=0;c<=8;c++)intersection(a,b,c);
        for(int[] a:new int[][]{{},{1},{1,2},{1,2,3,4,5},{0,0,0}})reverse(a);
        for(int n=0;n<=10;n++)for(int bits=0;bits<(1<<n);bits++){
            int[] a=new int[n];for(int i=0;i<n;i++)a[i]=(bits>>i)&1;palindrome(a);
        }
        for(int n=1;n<=20;n++)for(int t=0;t<5;t++)rotate(n);
        for(int t=0;t<500;t++){
            int[] a=new int[random.nextInt(301)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(200001)-100000;sort(a);
            int[] b=new int[random.nextInt(150)];for(int i=0;i<b.length;i++)b[i]=random.nextInt(20001)-10000;reverse(b);
            int[] c=new int[1+random.nextInt(200)];for(int i=0;i<c.length;i++)c[i]=random.nextInt(10);
            if(t%2==0)for(int i=0;i<c.length/2;i++)c[c.length-1-i]=c[i];palindrome(c);
            intersection(random.nextInt(100),random.nextInt(100),random.nextInt(100));
        }
        int[] longSort=new int[50000];for(int i=0;i<longSort.length;i++)longSort[i]=50000-i;sort(longSort);
        Arrays.fill(longSort,9);sort(longSort);
        intersection(29999,29999,1);intersection(30000,30000,0);intersection(0,10000,20000);
        int[] longReverse=new int[5000];for(int i=0;i<longReverse.length;i++)longReverse[i]=i;reverse(longReverse);
        int[] longPal=new int[100000];Arrays.fill(longPal,9);palindrome(longPal);longPal[99999]=8;palindrome(longPal);
        StackCheck extreme=new StackCheck();
        for(int v:new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,0,Integer.MAX_VALUE}){extreme.push(v);extreme.query();}
        while(!extreme.expected.isEmpty()){extreme.pop();if(!extreme.expected.isEmpty())extreme.query();}
        extreme.push(-7);extreme.query();extreme.pop();extreme.push(42);extreme.query();
        StackCheck varied=new StackCheck();
        for(int t=0;t<6000;t++){
            if(varied.expected.isEmpty()||random.nextInt(5)<3){int v=random.nextInt(5)==0?(random.nextBoolean()?Integer.MIN_VALUE:Integer.MAX_VALUE):random.nextInt();varied.push(v);}else varied.pop();
            if(!varied.expected.isEmpty())varied.query();
        }
        StackCheck deep=new StackCheck();for(int i=0;i<29995;i++)deep.push(i==0?Integer.MAX_VALUE:(i==29994?Integer.MIN_VALUE:30000-i));
        deep.query();deep.pop();deep.query();
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-25', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util导入及ListNode辅助类，调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['check_units'] = {str(i): '操作核验次数' if i in {146, 155} else '测试用例数' for i in ids}
        result['total_checks'] = sum(counts)
else:
    result['passed'] = False
for i, path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
