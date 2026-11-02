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
 * start: 20:15
 * end: 20:30
 * 独立完成
 * 时间：O(n)
 * 空间：O(n+h)
 */
class Q105 {


    TreeNode build(int []preorder,int from1,int to1,int[]inorder,int from2,int to2,HashMap<Integer,Integer>map){


        if(from1>to1|| from2>to2) return null;

        TreeNode root=new TreeNode(preorder[from1]);

        int idx=map.get(preorder[from1]);

        //target-from1=idx-1-from2+1
        //target=idx-from2+from1

        //前序遍历
        //from1+1 idx-from2+from1
        //idx-from2+from1+1  to1

        //中序遍历
        //from2   idx-1
        //idx+1   to2

        root.left=build(preorder,from1+1,idx-from2+from1,inorder,from2, idx-1,map);
        root.right=build(preorder,idx-from2+from1+1,to1,inorder,idx+1,to2,map);

        return root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }

        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);


        
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
 * start: 20:30
 * end: 20:33
 * 独立完成
 * 时间：O(n)
 * 空间: O(h)
 */
class Q114 {

    TreeNode pre=null;


    void dfs(TreeNode root){
        if(root==null) return;

        TreeNode r=root.right;
        TreeNode l=root.left;

        root.left=null;
        root.right=null;

        if(pre==null){
            pre=root;
        }else{
            pre.right=root;
            pre=root;
        }

        dfs(l);
        dfs(r);

        
    }


    public void flatten(TreeNode root) {

        dfs(root);
        
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
 * start: 20:36
 * end: 20:40
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Q124 {

    int minValue=Integer.MIN_VALUE/2;

    int dfs(TreeNode root){
        if(root==null) return 0;

        int l=dfs(root.left);
        int r=dfs(root.right);

        minValue=Math.max(minValue,l+r+root.val);

        int res=Math.max(l,r)+root.val;

        return res>0?res:0;

    }
    public int maxPathSum(TreeNode root) {

        dfs(root);

        return minValue;
        
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
 * start: 20:41
 * end: 20:43
 * 独立完成
 * 时间：O(n)
 * 空间: O(h)
 */
class Q226 {

    void dfs(TreeNode root){
        if(root==null) return ;

        dfs(root.left);
        dfs(root.right);

        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
    }

    public TreeNode invertTree(TreeNode root) {

        dfs(root);

        return root;
        
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
 * start: 20:46
 * end: 20:49
 * 独立完成
 * 时间：O(n)
 * 空间: O(h)
 */
class Q543 {

    int res=0;

    int dfs(TreeNode root){

        if(root==null) return 0;

        int l=dfs(root.left);
        int r=dfs(root.right);

        res=Math.max(res,l+r);

        return Math.max(l,r)+1;

    }
    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);

        return res;
        
    }
}
/**
 * start: 20:50
 * end: 21:02
 * 独立完成
 * 时间：log(n+m)
 * 空间: O(1)
 */
class Q4 {


    int dfs(int[]nums1,int[]nums2,int target){
        int ptr1=0;
        int ptr2=0;

        while(true){

            if(ptr1>=nums1.length){
                return nums2[ptr2+target-1];
            }

            if(ptr2>=nums2.length){
                return nums1[ptr1+target-1];
            }

            if(target==1){

                return Math.min(nums1[ptr1],nums2[ptr2]);

            }

            int piviot1=Math.min(ptr1+target/2-1,nums1.length-1);
            int piviot2=Math.min(ptr2+target/2-1,nums2.length-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                target-=(piviot1-ptr1+1);
                ptr1=piviot1+1;
            }else{
                target-=(piviot2-ptr2+1);
                ptr2=piviot2+1;
            }

        }
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;

        if(len%2==0){

            return (dfs(nums1,nums2,len/2)+dfs(nums1,nums2,len/2+1))*1.0/2;


        }else{
            return dfs(nums1,nums2,len/2+1)*1.0;//tip target是第几个数，不是下标是几
        }
        
    }
}

public class ReviewChecks {
    static Random random=new Random(1031);static int[] checks=new int[6];static int rankChecks;
    static Q4 q4=new Q4();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> result=new ArrayList<>();if(root==null)return result;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"tree cycle/alias");result.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return result;
    }
    static TreeNode copy(TreeNode root){if(root==null)return null;return new TreeNode(root.val,copy(root.left),copy(root.right));}
    static String shape(TreeNode root){
        StringBuilder b=new StringBuilder();LinkedList<TreeNode> stack=new LinkedList<>();stack.push(root);
        while(!stack.isEmpty()){TreeNode n=stack.pop();if(n==null){b.append("#,");continue;}b.append(n.val).append(',');stack.push(n.right);stack.push(n.left);}return b.toString();
    }
    static int[] inorder(TreeNode root){
        List<Integer> values=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){while(current!=null){stack.push(current);current=current.left;}current=stack.pop();values.add(current.val);current=current.right;}
        return values.stream().mapToInt(x->x).toArray();
    }
    static void construct(TreeNode expected){
        int[] pre=nodes(expected).stream().mapToInt(n->n.val).toArray(),in=inorder(expected);int[] preCopy=pre.clone(),inCopy=in.clone();
        TreeNode actual=new Q105().buildTree(preCopy,inCopy);
        require(shape(actual).equals(shape(expected)),"105 full shape/values");require(Arrays.equals(pre,preCopy)&&Arrays.equals(in,inCopy),"105 arrays changed");nodes(actual);checks[0]++;
    }
    static void flatten(TreeNode original){
        TreeNode root=copy(original);List<TreeNode> expected=nodes(root);new Q114().flatten(root);TreeNode current=root;
        for(TreeNode node:expected){require(current==node&&current.left==null,"114 original identity/preorder/left");current=current.right;}
        require(current==null,"114 tail/cycle");checks[1]++;
    }
    static void invert(TreeNode original){
        TreeNode root=copy(original);IdentityHashMap<TreeNode,TreeNode[]> links=new IdentityHashMap<>();for(TreeNode n:nodes(root))links.put(n,new TreeNode[]{n.left,n.right});
        require(new Q226().invertTree(root)==root,"226 root identity");
        for(var e:links.entrySet())require(e.getKey().left==e.getValue()[1]&&e.getKey().right==e.getValue()[0],"226 swapped edges");nodes(root);checks[3]++;
    }
    static List<List<Integer>> graph(List<TreeNode> all){
        IdentityHashMap<TreeNode,Integer> index=new IdentityHashMap<>();for(int i=0;i<all.size();i++)index.put(all.get(i),i);
        List<List<Integer>> edges=new ArrayList<>();for(int i=0;i<all.size();i++)edges.add(new ArrayList<>());
        for(int i=0;i<all.size();i++)for(TreeNode child:new TreeNode[]{all.get(i).left,all.get(i).right})if(child!=null){int j=index.get(child);edges.get(i).add(j);edges.get(j).add(i);}return edges;
    }
    static int maxPathOracle(TreeNode root){
        List<TreeNode> all=nodes(root);List<List<Integer>> edges=graph(all);int best=Integer.MIN_VALUE;
        for(int from=0;from<all.size();from++){
            ArrayDeque<int[]> stack=new ArrayDeque<>();stack.push(new int[]{from,-1,all.get(from).val});
            while(!stack.isEmpty()){int[] s=stack.pop();best=Math.max(best,s[2]);for(int next:edges.get(s[0]))if(next!=s[1])stack.push(new int[]{next,s[0],s[2]+all.get(next).val});}
        }return best;
    }
    static int[] farthest(List<List<Integer>> edges,int from){
        int[] dist=new int[edges.size()];Arrays.fill(dist,-1);dist[from]=0;ArrayDeque<Integer> queue=new ArrayDeque<>();queue.add(from);int last=from;
        while(!queue.isEmpty()){last=queue.remove();for(int next:edges.get(last))if(dist[next]<0){dist[next]=dist[last]+1;queue.add(next);}}return new int[]{last,dist[last]};
    }
    static int diameterOracle(TreeNode root){List<List<Integer>> edges=graph(nodes(root));return farthest(edges,farthest(edges,0)[0])[1];}
    static void paths(TreeNode root){paths(root,maxPathOracle(root));}
    static void paths(TreeNode root,int expected){String before=shape(root);require(new Q124().maxPathSum(root)==expected,"124 max node path");require(shape(root).equals(before),"124 changed tree");checks[2]++;}
    static void diameter(TreeNode root){String before=shape(root);require(new Q543().diameterOfBinaryTree(root)==diameterOracle(root),"543 diameter edges");require(shape(root).equals(before),"543 changed tree");checks[4]++;}
    static TreeNode randomTree(int n){
        if(n==0)return null;TreeNode root=new TreeNode(0);List<TreeNode> available=new ArrayList<>();available.add(root);
        for(int i=1;i<n;i++){
            int pick=random.nextInt(available.size());TreeNode p=available.get(pick),c=new TreeNode(i);
            if(p.left==null&&p.right==null){if(random.nextBoolean())p.left=c;else p.right=c;}else if(p.left==null)p.left=c;else p.right=c;
            if(p.left!=null&&p.right!=null)available.remove(pick);available.add(c);
        }return root;
    }
    static TreeNode complete(int n,int value){
        if(n==0)return null;TreeNode[] nodes=new TreeNode[n];for(int i=0;i<n;i++)nodes[i]=new TreeNode(value);
        for(int i=0;i<n;i++){if(2*i+1<n)nodes[i].left=nodes[2*i+1];if(2*i+2<n)nodes[i].right=nodes[2*i+2];}return nodes[0];
    }
    static void sortedArrays(List<int[]> out,int[] a,int pos,int minimum){if(pos==a.length){out.add(a.clone());return;}for(int v=minimum;v<=2;v++){a[pos]=v;sortedArrays(out,a,pos+1,v);}}
    static int[] combined(int[] a,int[] b){int[] all=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,all,a.length,b.length);Arrays.sort(all);return all;}
    static void median(int[] a,int[] b){
        int[] sorted=combined(a,b);int n=sorted.length;double expected=n%2==0?((long)sorted[n/2-1]+sorted[n/2])/2.0:sorted[n/2];
        int[] x=a.clone(),y=b.clone();require(Double.compare(q4.findMedianSortedArrays(x,y),expected)==0,"4 median");require(Arrays.equals(x,a)&&Arrays.equals(y,b),"4 arrays changed");checks[5]++;
    }
    static void rank(int[] a,int[] b,int k){require(q4.dfs(a.clone(),b.clone(),k)==combined(a,b)[k-1],"4 rank="+k);rankChecks++;}
    public static void main(String[] args){
        flatten(null);invert(null);
        for(int test=0;test<800;test++){
            TreeNode root=randomTree(1+random.nextInt(100));construct(root);flatten(root);invert(root);diameter(root);
            for(TreeNode node:nodes(root))node.val=random.nextInt(2001)-1000;paths(root);
        }
        for(TreeNode root:new TreeNode[]{new TreeNode(-3),new TreeNode(-10,new TreeNode(-20),new TreeNode(-5)),new TreeNode(-10,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7))),new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3))}){paths(root);diameter(root);flatten(root);invert(root);}
        TreeNode c3000=complete(3000,0);int index=-1500;for(TreeNode node:nodes(c3000))node.val=index++;construct(c3000);
        TreeNode skew=null;for(int i=999;i>=0;i--)skew=new TreeNode(i,null,skew);construct(skew);
        TreeNode c2000=complete(2000,3);flatten(c2000);
        TreeNode c10000=complete(10000,0);diameter(c10000);
        TreeNode positive=complete(30000,1000);paths(positive,(diameterOracle(positive)+1)*1000);
        for(TreeNode node:nodes(positive))node.val=-1000;paths(positive,-1000);
        TreeNode path=null;for(int i=0;i<1000;i++)path=new TreeNode(1,path,null);paths(path,1000);flatten(path);diameter(path);
        Q114 reused114=new Q114();TreeNode first=new TreeNode(1),second=new TreeNode(2);reused114.flatten(first);reused114.flatten(second);
        Q124 reused124=new Q124();int first124=reused124.maxPathSum(new TreeNode(5)),second124=reused124.maxPathSum(new TreeNode(-1));
        Q543 reused543=new Q543();int first543=reused543.diameterOfBinaryTree(new TreeNode(1,new TreeNode(2),new TreeNode(3))),second543=reused543.diameterOfBinaryTree(new TreeNode(0));
        System.out.println("REUSE114_CROSS_LINK="+(first.right==second));System.out.println("REUSE124_FIRST="+first124);System.out.println("REUSE124_SECOND="+second124);System.out.println("REUSE543_FIRST="+first543);System.out.println("REUSE543_SECOND="+second543);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=5;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        median(new int[]{1,3},new int[]{2});median(new int[]{1,2},new int[]{3,4});median(new int[]{},new int[]{1});median(new int[]{-1000000},new int[]{1000000});
        for(int test=0;test<1000;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];
            for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;
            Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        int[] a=new int[1000],b=new int[1000];for(int i=0;i<1000;i++){a[i]=i*2;b[i]=i*2+1;}median(a,b);median(new int[]{},b);median(a,new int[]{});for(int k=1;k<=2000;k++)rank(a,b,k);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
