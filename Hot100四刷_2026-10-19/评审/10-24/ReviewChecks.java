import java.util.*;
import java.io.*;
import java.math.BigInteger;

class ListNode { int val; ListNode next; ListNode(){} ListNode(int v){val=v;} ListNode(int v,ListNode n){val=v;next=n;} }

/**
 * start: 9:16
 * end: 9:25
 * 独立完成
 * 时间: O(max(m,n))
 * 空间: O(1)
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
class Q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1=new ListNode(-1);
        head1.next=l1;
        ListNode head2=new ListNode(-1);
        head2.next=l2;
        
        ListNode ptr1=head1;
        ListNode ptr2=head2;

        int cnt=0;

        while(ptr1.next!=null && ptr2.next!=null){
            cnt+=ptr1.next.val+ptr2.next.val;
            ptr1.next.val=cnt%10;
            cnt/=10;
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }

        if(ptr1.next==null){
            ptr1.next=ptr2.next;
        }

        while(ptr1.next!=null && cnt!=0){
            cnt+=ptr1.next.val;
            ptr1.next.val=cnt%10;
            cnt=cnt/10;
            ptr1=ptr1.next;
        }

        while(cnt!=0){
            ListNode tmp=new ListNode(cnt%10);
            cnt/=10;
            ptr1.next=tmp;
            ptr1=ptr1.next;
        }


        return head1.next;
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
 * start: 9:28
 * end: 9:32
 * 独立完成
 * 时间O(n)
 * 空间O(1)
 */
class Q19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode ptr1=head;
        ListNode ptr2=head;

        for(int i=1;i<n;i++){
            if(ptr1==null) return head;
            ptr1=ptr1.next;
        }
        
        ListNode pre=null;

        while(ptr1.next!=null){
            ptr1=ptr1.next;
            pre=ptr2;
            ptr2=ptr2.next;
        }

        if(pre==null){
            return head.next;
        }else{
            pre.next=pre.next.next;
        }

        return head;
        
    }
}

class Q23 {

    /**
 * start: 9:35
 * end: 9:47
 * 独立完成
 * n: lists的长度 m:平均每个链条的长度
 * 时间：（log(n) * nm）
 * 空间： log(n)
 * 
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

    if(l1!=null) ptr.next=l1;
    if(l2!=null) ptr.next=l2;

    return head.next;


}


ListNode build(ListNode[] lists,int l,int r){

    if(l==r){
        return lists[l];
    }else if(l>r){
        return null;
    }else if(l+1==r){
        return merge(lists[l],lists[r]);
    }else{

        int mid=(l+r)/2;

        return merge(build(lists,l,mid),build(lists,mid+1,r));
    }
}

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length==1){
            return lists[0];
        }
        
        return build(lists,0,lists.length-1);
    }
}
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/**
 * start: 10:17
 * end: 10:22
 * 独立完成
 * 时间 O(n)
 * 空间 O(1)
 */
class Q141 {
    public boolean hasCycle(ListNode head) {

        if(head==null || head.next==null) return false;
        if(head.next==head) return true;

        ListNode slow=head;
        ListNode quick=head.next;

        while(quick.next!=null && quick.next.next!=null ){
            quick=quick.next.next;
            slow=slow.next;

            if(quick==slow){

                return true;

            }
        }

        return false;
        
    }
}
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/**
 * start: 10:31
 * end: 10:38
 * 独立完成
 * 时间O（n）
 * 空间O(1)
 */
class Q142 {
    public ListNode detectCycle(ListNode head) {
        
        if(head==null || head.next==null) return null ;
        if(head.next==head) return head;

        ListNode slow=head;
        ListNode quick=head;

        while(quick.next!=null && quick.next.next!=null ){
            quick=quick.next.next;
            slow=slow.next;

            if(quick==slow){

                ListNode tmp=head;

                while(tmp!=slow){

                    //System.out.println(tmp.val);
                    System.out.println(slow.val);
                    

                    tmp=tmp.next;
                    slow=slow.next;



                }

                return slow;

            }
        }

        return null;

    }
}

/**
 * tip: 必须让slow 和quick走的步数完全是2倍关系（此时尤其需要注意初始化），否则会死归
 */
/**
 * strat: 10:56
 * end: 11:05
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Q31 {


    void swap(int[]nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    void reverse(int[]nums,int l,int r){

        for(int i=l;i<=(l+r)/2;i++){
            swap(nums,i,r+l-i);
        }
    }


    public void nextPermutation(int[] nums) {

        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                for(int j=nums.length-1;j>i;j--){
                    if(nums[j]>nums[i]){

                        swap(nums,i,j);
                        //i之后需要反转
                        reverse(nums,i+1,nums.length-1);
                        return ;
                    }
                }
            }
        }

        reverse(nums,0,nums.length-1);
        

        
    }
}
/**
 * start: 11:06
 * end: 11:14
 * 独立完成
 * 时间：log(n)
 * 空间 O(1)
 */
class Q33 {
    public int search(int[] nums, int target) {
        
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(nums[mid]>=nums[0] && target<nums[0]){
                left=mid+1;
            }else if(nums[mid]<nums[0] && target>=nums[0]){
                right=mid-1;

            }else if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                return mid;
            }
        }

        return -1;
        
    }
}

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
