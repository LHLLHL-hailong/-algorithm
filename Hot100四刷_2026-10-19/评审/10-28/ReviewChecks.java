import java.util.*;

class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }


/**
 * start: 18:04;
 * end: 18:19
 * 独立完成
 * 时间: O(1)
 * 空间: O(1)
 */
class LRUCache {

    int capacity=-1;
    int size=0;
    Node head;
    Node tail;

    HashMap<Integer,Node>map=new HashMap<>();

    class Node{
        int key=0;
        int value=0;

        Node pre;
        Node next;

        Node(int key ,int value){

            this.key=key;
            this.value=value;
        }


    }

    void remove(Node now){
        now.next.pre=now.pre;
        now.pre.next=now.next;
    }
    

    Node RemoveTail(){
        Node target=tail.pre;

        remove(target);

        return target;
    }

    void addFirst(Node now){
        now.next=head.next;
        head.next.pre=now;
        head.next=now;
        now.pre=head;
    }

    

    public LRUCache(int capacity) {

        this.capacity=capacity;
        this.size=0;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);

        head.next=tail;
        tail.next=head;
        
    }
    
    public int get(int key) {

        Node target=map.get(key);
        if(target==null) return -1;

        remove(target);
        addFirst(target);

        return target.value;
        
    }
    
    public void put(int key, int value) {

        Node target=map.get(key);
        if(target!=null){

            target.value=value;
            remove(target);
            addFirst(target);

        }else{
            target=new Node(key,value);
            addFirst(target);
            map.put(key,target);
            if(++size>capacity){

                Node now= RemoveTail();
                map.remove(now.key);
                size--;
            }

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
 * start: 18:25
 * end: 18:38
 * 独立完成
 * 时间：O(nlogn)
 * 空间: O(1)
 */
/**3,4,6
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Q148 {
        ListNode merge(ListNode l1,ListNode l2){
            if(l1==null) return l2;
            if(l2==null) return l1;

            ListNode head=new ListNode(-1);

            ListNode ptr=head;

            while(l1!=null && l2!=null){
                if(l1.val<=l2.val){

                    ptr.next=l1;
                    l1=l1.next;

                }else{
                    ptr.next=l2;
                    l2=l2.next;

                }

                ptr=ptr.next;
            }

            ptr.next=l1==null?l2:l1;

            return head.next;
        }

        ListNode build(ListNode now){
            if(now==null || now.next==null) return now;

            ListNode quick=now;
            ListNode slow=now;

            while(quick.next!=null && quick.next.next!=null){
                    quick=quick.next.next;
                    slow=slow.next;
            }

            ListNode h2=slow.next;
            slow.next=null;

            return merge(build(now),build(h2));
        }


    public ListNode sortList(ListNode head) {

        return build(head);


        
    }
}
/**
 * start: 18:38
 * end: 18:49
 * 独立完成
 * 时间: O(n)
 * 空间: O(n)
 */
class Q239 {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[]res=new int[nums.length-k+1];

        Deque<Integer>queue=new ArrayDeque<>();

        for(int i=0;i<k;i++){
            while(!queue.isEmpty()&& nums[i]>=nums[queue.getLast()]){

                queue.removeLast();

            }
            queue.addLast(i);
        }

        res[0]=nums[queue.getFirst()];

        for(int i=k;i<nums.length;i++){
            while(!queue.isEmpty()&& nums[i]>=nums[queue.getLast()]){

                queue.removeLast();

            }

            queue.addLast(i);

            if(queue.getFirst()==i-k){
                queue.removeFirst();
            }

            res[i-k+1]=nums[queue.getFirst()];
            
        }

        return res;


        
    }
}

/**
 * start: 18:49
 * end: 19:12
 * 独立完成
 * 时间：O(nm)
 * 空间：O(m)
 */
class Q85 {
    public int maximalRectangle(char[][] matrix) {

        int[]hei=new int[matrix[0].length+2];

        int []stack=new int[hei.length+1];
        int ptr=0;

        int res=0;

        for(int i=0;i<matrix.length;i++){

            ptr=0;

            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]=='1'){
                   // System.out.println("in");
                    hei[j+1]++;
                }else{
                    hei[j+1]=0;
                }
            }

           // System.out.println(Arrays.toString(hei));

            for(int j=0;j<hei.length;j++){

                

                while(ptr!=0 && hei[j]<hei[stack[ptr-1]]){
                    int with=j-stack[ptr-2]-1;
                    int heigt=hei[stack[ptr-1]];

                    //System.out.println("in");

                    res=Math.max(res,with*heigt);

                    ptr--;

                }

                stack[ptr++]=j;
            }
        }

        return res;
        
    }
}

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
