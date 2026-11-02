import java.util.*;

class TreeNode { int val; TreeNode left,right; TreeNode(){} TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;} }

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
 * start: 18:48
 * end: 18:51
 * 独立完成
 * 时间：O(n)
 * 空间：O(log(n))
 */
class Q94 {

    void dfs(TreeNode root){
        if(root==null) return ;
        dfs(root.left);

        res.add(root.val);

        dfs(root.right);
    }

    List<Integer>res=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {

        dfs(root);

        return res;


        
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
 * start: 18:52
 * end: 18:58
 * 独立完成
 * 时间: O(n)
 * 空间：O(log(n))
 */



class Q98 {
int pre=0;
boolean isFirst=true;

    boolean dfs(TreeNode root){

        if(root==null) return true;

        if(!dfs(root.left)) return false;

        if(isFirst){

            isFirst=false;
        }else{
            if(pre>=root.val){
                return false;
            }

        }
        pre=root.val;

        if(!dfs(root.right)) return false;

        return true;

    }


    


    public boolean isValidBST(TreeNode root) {


        return dfs(root);
        
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
 * start: 18:59
 * end: 19:02
 * 独立完成
 * 时间： O(n)
 * 空间：O(log(n))
 */
class Q101 {

    boolean dfs(TreeNode root1,TreeNode root2){

        if(root1==null && root2==null){
            return true;
        }  

        if(root1==null || root2==null){
            return false;
        }

        return root1.val==root2.val && dfs(root1.left,root2.right) && dfs(root1.right,root2.left);

    }

    public boolean isSymmetric(TreeNode root) {

        return dfs(root.left,root.right);
        
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
 * start: 19:14
 * end: 19:20
 * 独立完成
 * 时间:O(n)
 * 空间:O(max(m1+m2))//相邻两成节点数量最多的
 */
class Q102 {
    public List<List<Integer>> levelOrder(TreeNode root) {

        LinkedList<TreeNode>queue=new LinkedList<>();

        List<List<Integer>>list=new ArrayList<>();

        if(root==null) return list;

        queue.add(root);

        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer>res=new ArrayList<>();

            for(int i=0;i<size;i++){
                TreeNode now=queue.removeFirst();

                res.add(now.val);

                if(now.left!=null) queue.addLast(now.left);
                if(now.right!=null) queue.addLast(now.right);
            }

            list.add(res);

            
        }

        return list;

        
        
    }
}
/**
 * start: 19:39
 * end: 19:57
 * 忘了，看完答案后回忆出来
 * 时间：O(log(n+m))
 * 空间：O(1)
 */
class Q4 {


    int dfs(int[]nums1,int []nums2,int k){

        int ptr1=0;
        int ptr2=0;

        while(true){

             if(ptr1>=nums1.length){//tip：必须先于k==1的判断条件
                return nums2[ptr2+k-1];
            }
            if(ptr2>=nums2.length){
                return nums1[ptr1+k-1];
            }

            if(k==1){
                return Math.min(nums1[ptr1],nums2[ptr2]);
            }
            


            int piviot1=Math.min(ptr1+k/2-1,nums1.length-1);
            int piviot2=Math.min(ptr2+k/2-1,nums2.length-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                k-=(piviot1-ptr1+1);
                ptr1=piviot1+1;
            }else{
                k-=(piviot2-ptr2+1);
                ptr2=piviot2+1;
            }

        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;


        if(len%2==0){

            return (dfs(nums1,nums2,len/2+1)+dfs(nums1,nums2,len/2))*1.0/2;

        }else{
            

            return dfs(nums1,nums2,len/2+1);

        }



        
    }
}

public class ReviewChecks {
    static Random random=new Random(1030);
    static int[] checks=new int[5];static int rankChecks;
    static Q4 q4=new Q4();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> all=new ArrayList<>();if(root==null)return all;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"cycle/alias in tree");all.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return all;
    }
    static List<Integer> inorderOracle(TreeNode root){
        List<Integer> values=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){
            while(current!=null){stack.push(current);current=current.left;}
            current=stack.pop();values.add(current.val);current=current.right;
        }return values;
    }
    record Bound(TreeNode node,long low,long high){}
    static boolean bstOracle(TreeNode root){
        ArrayDeque<Bound> stack=new ArrayDeque<>();if(root!=null)stack.push(new Bound(root,Long.MIN_VALUE,Long.MAX_VALUE));
        while(!stack.isEmpty()){
            Bound b=stack.pop();TreeNode n=b.node;if(n.val<=b.low||n.val>=b.high)return false;
            if(n.left!=null)stack.push(new Bound(n.left,b.low,n.val));if(n.right!=null)stack.push(new Bound(n.right,n.val,b.high));
        }return true;
    }
    static String structure(TreeNode root,boolean mirror){
        StringBuilder b=new StringBuilder();LinkedList<TreeNode> stack=new LinkedList<>();stack.push(root);
        while(!stack.isEmpty()){
            TreeNode n=stack.pop();if(n==null){b.append("#,");continue;}b.append(n.val).append(',');
            if(mirror){stack.push(n.left);stack.push(n.right);}else{stack.push(n.right);stack.push(n.left);}
        }return b.toString();
    }
    record Depth(TreeNode node,int level){}
    static List<List<Integer>> levelsOracle(TreeNode root){
        List<List<Integer>> levels=new ArrayList<>();ArrayDeque<Depth> stack=new ArrayDeque<>();if(root!=null)stack.push(new Depth(root,0));
        while(!stack.isEmpty()){
            Depth d=stack.pop();if(d.level==levels.size())levels.add(new ArrayList<>());levels.get(d.level).add(d.node.val);
            if(d.node.right!=null)stack.push(new Depth(d.node.right,d.level+1));if(d.node.left!=null)stack.push(new Depth(d.node.left,d.level+1));
        }return levels;
    }
    static void tree(TreeNode root){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,TreeNode[]> before=new IdentityHashMap<>();
        boolean smallValues=true,mediumValues=true;
        for(TreeNode n:all){before.put(n,new TreeNode[]{n.left,n.right});smallValues&=n.val>=-100&&n.val<=100;mediumValues&=n.val>=-1000&&n.val<=1000;}
        // Fresh Solution per primary case, matching the ordinary problem runner.
        if(all.size()<=100&&smallValues){require(new Q94().inorderTraversal(root).equals(inorderOracle(root)),"94 inorder");checks[0]++;}
        if(!all.isEmpty()&&all.size()<=10000){require(new Q98().isValidBST(root)==bstOracle(root),"98 ancestor/strict bound");checks[1]++;}
        if(!all.isEmpty()&&all.size()<=1000&&smallValues){
            boolean expected=structure(root.left,false).equals(structure(root.right,true));require(new Q101().isSymmetric(root)==expected,"101 mirrored structure");checks[2]++;
        }
        if(all.size()<=2000&&mediumValues){require(new Q102().levelOrder(root).equals(levelsOracle(root)),"102 DFS grouped levels");checks[3]++;}
        for(var e:before.entrySet())require(e.getKey().left==e.getValue()[0]&&e.getKey().right==e.getValue()[1],"tree input changed");
    }
    static TreeNode randomTree(int size){
        if(size==0)return null;TreeNode root=new TreeNode(random.nextInt(11)-5);List<TreeNode> available=new ArrayList<>();available.add(root);
        for(int i=1;i<size;i++){
            int pick=random.nextInt(available.size());TreeNode parent=available.get(pick),child=new TreeNode(random.nextInt(11)-5);
            if(parent.left==null&&parent.right==null){if(random.nextBoolean())parent.left=child;else parent.right=child;}
            else if(parent.left==null)parent.left=child;else parent.right=child;
            if(parent.left!=null&&parent.right!=null)available.remove(pick);available.add(child);
        }return root;
    }
    static TreeNode mirrored(TreeNode root){if(root==null)return null;return new TreeNode(root.val,mirrored(root.right),mirrored(root.left));}
    static TreeNode balanced(int from,int to){if(from>to)return null;int mid=(from+to)>>>1;return new TreeNode(mid,balanced(from,mid-1),balanced(mid+1,to));}
    static int[] combined(int[] a,int[] b){int[] all=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,all,a.length,b.length);Arrays.sort(all);return all;}
    static void median(int[] a,int[] b){
        int[] sorted=combined(a,b);require(sorted.length>0,"invalid empty pair");int n=sorted.length;
        double expected=n%2==0?((long)sorted[n/2-1]+sorted[n/2])/2.0:sorted[n/2];
        int[] x=a.clone(),y=b.clone();require(Double.compare(q4.findMedianSortedArrays(x,y),expected)==0,"4 "+Arrays.toString(a)+" "+Arrays.toString(b));
        require(Arrays.equals(a,x)&&Arrays.equals(b,y),"4 input changed");checks[4]++;
    }
    static void rank(int[] a,int[] b,int k){int[] sorted=combined(a,b);require(q4.dfs(a.clone(),b.clone(),k)==sorted[k-1],"4 rank k="+k);rankChecks++;}
    static void sortedArrays(List<int[]> result,int[] prefix,int position,int minimum){
        if(position==prefix.length){result.add(prefix.clone());return;}
        for(int v=minimum;v<=2;v++){prefix[position]=v;sortedArrays(result,prefix,position+1,v);}
    }
    public static void main(String[] args){
        tree(null);tree(new TreeNode(0));tree(new TreeNode(-1));
        tree(new TreeNode(2,new TreeNode(1),new TreeNode(3)));tree(new TreeNode(1,new TreeNode(1),null));
        tree(new TreeNode(5,new TreeNode(1),new TreeNode(7,new TreeNode(4),new TreeNode(8))));
        tree(new TreeNode(0,new TreeNode(Integer.MIN_VALUE),new TreeNode(Integer.MAX_VALUE)));
        tree(new TreeNode(Integer.MIN_VALUE));tree(new TreeNode(Integer.MAX_VALUE));
        tree(new TreeNode(1,new TreeNode(2,new TreeNode(3),null),new TreeNode(2,new TreeNode(3),null)));
        tree(new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(2,new TreeNode(4),new TreeNode(3))));
        for(int test=0;test<1000;test++)tree(randomTree(random.nextInt(101)));
        for(int test=0;test<300;test++){
            TreeNode left=randomTree(random.nextInt(500));TreeNode root=new TreeNode(0,left,mirrored(left));tree(root);
            if(root.right!=null){root.right.val++;tree(root);}
        }
        TreeNode rightChain=null,leftChain=null;
        for(int i=99;i>=0;i--){rightChain=new TreeNode(i,null,rightChain);leftChain=new TreeNode(99-i,leftChain,null);}tree(rightChain);tree(leftChain);
        TreeNode deep=null;for(int i=1999;i>=0;i--)deep=new TreeNode(i,null,deep);tree(deep);
        TreeNode longLeft=null;for(int i=0;i<499;i++)longLeft=new TreeNode(i%3,longLeft,null);tree(new TreeNode(0,longLeft,mirrored(longLeft)));
        tree(balanced(0,1999));tree(balanced(0,9999));
        Q94 repeat94=new Q94();List<Integer> first94=new ArrayList<>(repeat94.inorderTraversal(new TreeNode(1)));List<Integer> second94=new ArrayList<>(repeat94.inorderTraversal(new TreeNode(2)));
        Q98 repeat98=new Q98();boolean first98=repeat98.isValidBST(new TreeNode(1)),second98=repeat98.isValidBST(new TreeNode(1));
        System.out.println("REUSE94_FIRST="+first94);System.out.println("REUSE94_SECOND="+second94);System.out.println("REUSE98_FIRST="+first98);System.out.println("REUSE98_SECOND="+second98);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=5;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        median(new int[]{1,3},new int[]{2});median(new int[]{1,2},new int[]{3,4});median(new int[]{},new int[]{1});
        median(new int[]{-1000000},new int[]{1000000});median(new int[]{1000000},new int[]{1000000});
        for(int test=0;test<1000;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];
            for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;
            Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        int[] longA=new int[1000],longB=new int[1000];for(int i=0;i<1000;i++){longA[i]=i*2;longB[i]=i*2+1;}
        median(longA,longB);median(new int[]{},longB);median(longA,new int[]{});median(new int[]{-1000000},longB);median(longA,new int[]{1000000});
        for(int k=1;k<=2000;k++)rank(longA,longB,k);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
