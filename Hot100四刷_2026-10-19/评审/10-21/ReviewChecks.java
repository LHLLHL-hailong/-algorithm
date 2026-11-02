import java.util.*;
class TreeNode { int val; TreeNode left,right; TreeNode(int v) { val=v; } }

/**
 * start: 6:54
 * end: 7:08
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Q31 {

    void swap(int[]nums ,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }


    // 0 1 2
    // 1
    void reverse(int []nums,int from ,int to){
        //0---3
        //1
        //01  23
        //0----4
        //0 1 2 3 4
        int mid=(from+to)/2;
        for(int i=from;i<=mid;i++){
           // System.out.println(Arrays.toString(nums));
            //System.out.println(i);
            
            swap(nums,i ,from+to-i);
        }

        //System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {

        if(nums.length<=1) return ;

        for(int i=nums.length-2;i>=0;i--){

            if(nums[i]<nums[i+1]){
                int j=i+1;
                while(j+1<nums.length && nums[j+1]>nums[i]){
                    j++;
                }
                swap(nums,i,j);

                reverse(nums,i+1,nums.length-1);

                return ;

            }


        }

        //System.out.println("in");

        reverse(nums,0,nums.length-1);

        return ;
        
    }
}
/**
 * start: 19:11
 * end: 19:17
 * 独立完成
 * 时间： O(log(n))
 * 空间： O(1)
 */
class Q33 {
    public int search(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;
        int k=nums[0];

        while(left<=right){
            int mid=(left+right)/2;

            if(target>=k && nums[mid]<k){
                right=mid-1;
            }else if(target<k && nums[mid]>=k){
                left=mid+1;
            }else if(target>nums[mid]){
                left=mid+1;
            }else if(target<nums[mid]){
                right=mid-1;
            }else{
                return mid;
            }
        }

        return -1;
        
    }
}
/**
 * start: 19:32
 * end: 19:39
 * 独立完成
 * 时间：O(mn)
 * 空间：O(1)
 */
class Q48 {

    void swap(int[][]matrix,int x1,int y1,int x2,int y2){
        int tmp=matrix[x1][y1];
        matrix[x1][y1]=matrix[x2][y2];
        matrix[x2][y2]=tmp;
    }

    void reverse(int[][]matrix){

        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix[0].length;j++){
                swap(matrix,i,j,j,i);

            }
        }

    }

    void reverse2(int[][]matrix){

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length/2;j++){
                swap(matrix,i,j,i,matrix[i].length-1-j);

            }
        }
    }


    public void rotate(int[][] matrix) {

        reverse(matrix);
        reverse2(matrix);
        
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * strat:19:39
 * end:19:43
 * 独立完成
 * 时间：O(n) n为节点的数量
 * 空间 O(log(n)) 递归调用栈的占用
 */
class Q104 {

    int dfs(TreeNode root){
        if(root==null) return 0;
        return Math.max(dfs(root.left),dfs(root.right))+1;
    }

    public int maxDepth(TreeNode root) {

        return dfs(root);
        
    }
}
/**
 * start: 19:17
 * end:19:28
 * 独立完成
 * 时间：O(log(n))
 * 空间：O(1)
 * 
 */
/**
 * tip：又改限制了，这个只有左右边界用二分才能ac,找到一个后两边扫描不再被允许
 */
class Q34LogicCopy {
    public int[] searchRange(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;
        int l1=-1;
        int r1=-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{  
                right=mid-1;
                l1=mid;
                
            }

        }

        left=0;
        right=nums.length-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{
                left=mid+1;
                r1=mid;
                
            }

        }

        return new int[]{l1,r1};
        
    }
}

public class ReviewChecks {
    static Random random = new Random(1021);
    static int[] checks = new int[5];
    static Q31 q31=new Q31(); static Q33 q33=new Q33(); static Q34LogicCopy q34=new Q34LogicCopy();
    static Q48 q48=new Q48(); static Q104 q104=new Q104();
    static void require(boolean value,String message) { if(!value) throw new AssertionError(message); }
    static int compare(int[] a,int[] b) {
        for(int i=0;i<a.length;i++) if(a[i]!=b[i]) return Integer.compare(a[i],b[i]); return 0;
    }
    static int[] bestPermutation;
    static void enumerate(int[] sorted,boolean[] used,int[] candidate,int depth,int[] original) {
        if(depth==sorted.length) {
            if(compare(candidate,original)>0 && (bestPermutation==null || compare(candidate,bestPermutation)<0))
                bestPermutation=candidate.clone();
            return;
        }
        for(int i=0;i<sorted.length;i++) {
            if(used[i] || (i>0 && sorted[i]==sorted[i-1] && !used[i-1])) continue;
            used[i]=true; candidate[depth]=sorted[i]; enumerate(sorted,used,candidate,depth+1,original); used[i]=false;
        }
    }
    static void permutation(int[] a) {
        int[] sorted=a.clone(); Arrays.sort(sorted); bestPermutation=null;
        enumerate(sorted,new boolean[a.length],new int[a.length],0,a);
        int[] expected=bestPermutation==null?sorted:bestPermutation;
        int[] actual=a.clone(); q31.nextPermutation(actual);
        require(Arrays.equals(actual,expected),"31: "+Arrays.toString(a)); checks[0]++;
    }
    static void search(int[] a,int target) {
        int expected=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { expected=i; break; }
        require(q33.search(a.clone(),target)==expected,"33: "+Arrays.toString(a)+" target="+target); checks[1]++;
    }
    static void range(int[] a,int target) {
        int lo=-1,hi=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { if(lo==-1)lo=i; hi=i; }
        require(Arrays.equals(q34.searchRange(a.clone(),target),new int[]{lo,hi}),"34-copy: "+Arrays.toString(a)); checks[2]++;
    }
    static void rotate(int[][] a) {
        int n=a.length; int[][] expected=new int[n][n]; int[][] actual=new int[n][];
        for(int i=0;i<n;i++) { actual[i]=a[i].clone(); for(int j=0;j<n;j++) expected[j][n-1-i]=a[i][j]; }
        q48.rotate(actual); require(Arrays.deepEquals(expected,actual),"48 size="+n); checks[3]++;
    }
    static void depth(TreeNode root) {
        int expected=0; ArrayDeque<TreeNode> queue=new ArrayDeque<>();
        if(root!=null) queue.add(root);
        while(!queue.isEmpty()) {
            int width=queue.size(); expected++;
            for(int i=0;i<width;i++) { TreeNode node=queue.remove(); if(node.left!=null)queue.add(node.left); if(node.right!=null)queue.add(node.right); }
        }
        require(q104.maxDepth(root)==expected,"104 expected="+expected); checks[4]++;
    }
    static TreeNode tree(int budget) {
        if(budget==0 || random.nextInt(5)==0) return null;
        TreeNode node=new TreeNode(random.nextInt()); int left=random.nextInt(budget);
        node.left=tree(left); node.right=tree(budget-1-left); return node;
    }
    public static void main(String[] args) {
        for(int[] a:new int[][]{{1},{1,2},{2,1},{1,3,2,2},{3,2,1},{1,1,5},{2,2,0,4,3,1},{0,0,0}}) permutation(a);
        for(int[] a:new int[][]{{1},{4,5,6,7,0,1,2},{1,3},{3,1},{0,1,2,3,4},{2,3,4,0,1}})
            for(int target=-1;target<=8;target++) search(a,target);
        for(int[] a:new int[][]{{},{1},{2,2,2},{1,2,2,2,3},{-1000000000,1000000000}})
            for(int target=-1;target<=3;target++) range(a,target);
        rotate(new int[][]{{1}}); rotate(new int[][]{{1,2},{3,4}}); rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        depth(null); depth(new TreeNode(1));
        TreeNode chain=new TreeNode(0),tail=chain;
        for(int i=0;i<999;i++) { tail.left=new TreeNode(i); tail=tail.left; } depth(chain);
        for(int i=0;i<1000;i++) {
            int[] p=new int[1+random.nextInt(7)]; for(int j=0;j<p.length;j++)p[j]=random.nextInt(5); permutation(p);
            int n=1+random.nextInt(50),shift=random.nextInt(n); int[] a=new int[n];
            for(int j=0;j<n;j++)a[j]=((j+shift)%n)*2-50;
            search(a,-51+random.nextInt(103)); search(a,a[random.nextInt(n)]);
            int[] b=new int[random.nextInt(50)]; for(int j=0;j<b.length;j++)b[j]=random.nextInt(11)-5;
            Arrays.sort(b); range(b,random.nextInt(15)-7);
            int m=1+random.nextInt(12); int[][] matrix=new int[m][m];
            for(int row=0;row<m;row++)for(int col=0;col<m;col++)matrix[row][col]=random.nextInt(); rotate(matrix);
            depth(tree(random.nextInt(150)));
        }
        int[] decreasing=new int[100]; for(int i=0;i<100;i++)decreasing[i]=99-i;
        q31.nextPermutation(decreasing); for(int i=0;i<100;i++)require(decreasing[i]==i,"31 size100"); checks[0]++;
        int[] repeats=new int[100000]; Arrays.fill(repeats,5); range(repeats,5); range(repeats,6);
        int[][] matrix=new int[20][20]; rotate(matrix);
        System.out.printf("{\"31\":%d,\"33\":%d,\"34_logic_copy\":%d,\"48\":%d,\"104\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4]);
    }
}
