import java.util.*;

class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }

/**
 * start:20:30 
 * end: 20:46
 * 独立完成
 * 时间: O(1)
 * 空间O(Math.min(插入的数据，capacity))
 */
class LRUCache {

    class Node{
        int value;
        int key;
        Node pre;
        Node next;

        Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    Node head;
    Node tail;
    int capacity=-1;
    int size=0;
    HashMap<Integer,Node>map;


    Node removeTail(){

        Node target=tail.pre;

        target.next.pre=target.pre;
        target.pre.next=target.next;


        return target;

    }


    Node removeByKey(Integer key){

        Node target=map.get(key);

        return removeNode(target);


    }

        Node removeNode(Node target){

        target.next.pre=target.pre;
        target.pre.next=target.next;


        return target;
    }

    void addFirst(Node target){


        target.next=head.next;
        head.next.pre=target;
        head.next=target;
        target.pre=head;
    }

    public LRUCache(int capacity) {

        this.capacity=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);


        head.next=tail;
        tail.pre=head;

        map=new HashMap<>();
        
    }
    
    public int get(int key) {

        if(map.containsKey(key)){

            Node target=removeByKey(key);

            addFirst(target);

            return target.value;

            
        }else{

            return -1;
        }
        
    }
    
    public void put(int key, int value) {

        Node target=map.get(key);

        if(target==null){

            target=new Node(key,value);

            map.put(key,target);
            addFirst(target);

            if(++size>capacity){
                Node now=removeTail();
                map.remove(now.key);
                size--;
            }


        }else{
            target.value=value;
            removeNode(target);

            addFirst(target);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * start: 20:48
 * end: 21:10
 * 独立完成
 * 时间O(nlog(n))
 * 空间：log(n) 
 * 
 */
class Q148 {

    ListNode merge(ListNode l1,ListNode l2){



        ListNode head=new ListNode();
        ListNode ptr=head;

        ListNode ptr1=l1;
        ListNode ptr2=l2;

        while(ptr1!=null && ptr2!=null){

           // System.out.println("in");

            if(ptr1.val<=ptr2.val){
                ptr.next=ptr1;
                ptr=ptr.next;
                ptr1=ptr1.next;
            }else{
                ptr.next=ptr2;
                ptr=ptr.next;
                ptr2=ptr2.next;

            }
        }

        ptr.next= ptr1 !=null?ptr1:ptr2;

        return head.next;

        
    }

    ListNode build(ListNode list){



        if(list==null) return null;
        if(list.next==null) return list;

        ListNode quick=list;
        ListNode slow=list;

        while(quick.next!=null && quick.next.next!=null){
            quick=quick.next.next;
            slow=slow.next;
        }
        
        ListNode ptr2=slow.next;
        slow.next=null;

        return merge(build(list),build(ptr2));
    }


    public ListNode sortList(ListNode head) {

        return build(head);
        
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/**
 * start: 21:10
 * end:21:18
 * 独立完成
 * 时间：O(n+m)
 * 空间：O(1)
 */
class Q160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA==null || headB==null) return null;

        ListNode ptr1=headA;
        int countA=0;

        ListNode ptr2=headB;
        int countB=0;

        while(true){

            
            if(ptr1==ptr2){//tip:要在开头判断而不是结尾
                return ptr1;
            }

            if(ptr1.next!=null){
                ptr1=ptr1.next;
            }else{
                if(++countA>=2){

                    return null;

                }else{
                    ptr1=headB;
                }
                
            }

            if(ptr2.next!=null){
                ptr2=ptr2.next;
            }else{
                if(++countB>=2){

                    return null;
                }else{
                    ptr2=headA;
                }
            }

        }
        
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * start: 21:18
 * end:21:22
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Q206 {
    public ListNode reverseList(ListNode head) {

        if(head==null || head.next==null) return head;

        ListNode pre=head;
        ListNode now=head.next;

        head.next=null;//tip:重点

        while(now!=null){
            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        return pre;
        
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * start:21:22
 * end:21:35
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Q234 {
    public boolean isPalindrome(ListNode head) {

        if(head==null || head.next==null) return true;

        ListNode quick=head;
        ListNode slow=head;

        while(quick.next!=null && quick.next.next!=null){
            quick=quick.next.next;
            slow=slow.next;
        }

        ListNode pre=slow;
        ListNode now=slow.next;

        slow.next=null;//tip

        while(now!=null){
            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        ListNode ptr1=head;
        ListNode ptr2=pre;

        boolean res=true;

        while(ptr1!=null && ptr2!=null){
           // System.out.println("in");
            if(ptr1.val!=ptr2.val){
                res=false;
                break;
            }

            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }

        now=pre.next;
        pre.next=null;//tip

        while(now!=null){


            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        return res;
        
    }
}

/**
 * start 22:25
 * end:22:28
 * 独立完成
 * 时间：O（n2）
 * 空间O(1)
 */
class Q48 {


    void swap(int[][] matrix , int fx,int fy ,int tx,int ty){

        int tmp=matrix[fx][fy];
        matrix[fx][fy]=matrix[tx][ty];
        matrix[tx][ty]=tmp;

    }


    void fix1(int[][] matrix){
        for(int i=0;i<matrix.length/2;i++){
            for(int j=0;j<matrix[i].length;j++){
                swap(matrix,i,j,matrix.length-1-i,j);
            }
            
        }

    }


    void fix2(int[][] matrix){


        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<=i;j++){
                swap(matrix,i,j,j,i);
            }
        }


    
    }
    
    
    public void rotate(int[][] matrix) {

        fix1(matrix);
        fix2(matrix);

    }


}

/**
 * strat:22:15
 * end:22:25
 * 独立完成
 * 时间: O(1)
 * 空间: O(30005)
 */
class MinStack {

    long[]stack=new long[30005];
    int ptr=0;

    long minValue=-1;

    public MinStack() {

        ptr=0;
        minValue=-1;
        
    }
    
    public void push(int value) {

        if(ptr==0){
            minValue=value;
            stack[ptr++]=0;
        }else{
            stack[ptr++]=(value-minValue);//tip 按照long计算
            if(stack[ptr-1]<0){
                minValue=value;
            }
        }
        
    }
    
    public void pop() {

        if(stack[ptr-1]<0){


            //minvalue-premin=stack[ptr-1];

            minValue=minValue-stack[ptr-1];
        }

        ptr--;
    }
    
    public int top() {

        if(stack[ptr-1]<0) return (int)minValue;//tip
        return(int) (stack[ptr-1]+minValue);
        
    }
    
    public int getMin() {

        return (int)minValue;
        
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

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
