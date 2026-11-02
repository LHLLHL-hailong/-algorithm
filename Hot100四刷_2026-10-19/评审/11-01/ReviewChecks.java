import java.util.*;

class TreeNode { int val; TreeNode left,right; TreeNode(){} TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;} }

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * start: 18:20
 * end: 18:23
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Q236 {


    TreeNode dfs(TreeNode root,TreeNode p,TreeNode q){

        if(root==null|| root==p || root==q){
            return root;
        }

        TreeNode l=dfs(root.left,p,q);
        TreeNode r=dfs(root.right,p,q);

        if(l!=null && r!=null){
            return root;
        }

        if(l!=null){
            return l;
        }else{
            return r;
        }


    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return dfs(root,p,q);
        
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
 * start: 18:25
 * end: 18:33
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Q437 {

    HashMap<Long,Integer>map=new HashMap<>();

    int res=0;

    void dfs(TreeNode root,int targetSum,long now){

        if(root==null) return;
        long prefix=now+root.val;

        Integer count=map.get(prefix-targetSum);

        if(count!=null){
            res+=count;
        }

        map.put(prefix,map.getOrDefault(prefix,0)+1);

        dfs(root.left,targetSum,prefix);
        dfs(root.right,targetSum,prefix);

        map.put(prefix,map.getOrDefault(prefix,0)-1);


    }

    public int pathSum(TreeNode root, int targetSum) {

        map.put(0L,1);

        dfs(root,targetSum,0L);

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
 * start: 18:35
 * end: 18:37
 * 独立完成
 * 时间： O(n)
 * 空间： O(h)
 */
class Q538 {

    int total=0;


    void dfs(TreeNode root){
        if(root==null) return;

        dfs(root.right);

        total+=root.val;

        root.val=total;


        dfs(root.left);
    }


    public TreeNode convertBST(TreeNode root) {

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
 * start: 18:39
 * end: 18:45
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Q617 {


    void merge(TreeNode root1,TreeNode root2){

        root1.val+=root2.val;

        if(root2.left!=null){
            if(root1.left==null){
                root1.left=root2.left;
            }else{
                merge(root1.left,root2.left);
            }
        }

        if(root2.right!=null){
            if(root1.right==null){
                root1.right=root2.right;
            }else{
                merge(root1.right,root2.right);
            }
        }
    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if(root1==null) return root2;
        if(root2==null) return root1;

        merge(root1,root2);

        return root1;


        
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * start: 18:45
 * end: 18:58
 * 独立完成
 * 时间：O(n)
 * 空间：O(h+n)
 */
class Q297 {

    void dfs(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("null,");

            return ;
        }else{
            sb.append(root.val+",");
        }

        dfs(root.left,sb);

        dfs(root.right,sb);

        
    }
    

    

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();

        dfs(root,sb);

        return sb.toString();

        //StringBuilder sb=dfs(root);
        
    }

    TreeNode build(String[]strs,int[]ptr){

        if(strs[ptr[0]].equals("null")){
            ptr[0]++;
            return null;
        }else{
            TreeNode root=new TreeNode(Integer.parseInt(strs[ptr[0]]));
            ptr[0]++;

            root.left=build(strs,ptr);
            root.right=build(strs,ptr);

            return root;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[]strs=data.split(",");

        return build(strs,new int[]{0});

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

public class ReviewChecks {
    static Random random=new Random(1101); static int[] checks=new int[5];
    static Q297 serializer=new Q297(), deserializer=new Q297();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> out=new ArrayList<>(); if(root==null)return out;
        ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"cycle/shared child in tree");out.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return out;
    }
    static String shape(TreeNode root){
        StringBuilder out=new StringBuilder();List<TreeNode> queue=new ArrayList<>();queue.add(root);
        for(int i=0;i<queue.size();i++){TreeNode n=queue.get(i);if(n==null){out.append("#, ");continue;}out.append(n.val).append(',');queue.add(n.left);queue.add(n.right);}return out.toString();
    }
    static TreeNode randomTree(int count,int bound){
        if(count==0)return null;TreeNode root=new TreeNode(random.nextInt(2*bound+1)-bound);
        List<TreeNode> open=new ArrayList<>();open.add(root);
        for(int i=1;i<count;i++){
            int pos=random.nextInt(open.size());TreeNode parent=open.get(pos),child=new TreeNode(random.nextInt(2*bound+1)-bound);
            if(parent.left==null&&parent.right==null){if(random.nextBoolean())parent.left=child;else parent.right=child;}else if(parent.left==null)parent.left=child;else parent.right=child;
            if(parent.left!=null&&parent.right!=null)open.remove(pos);open.add(child);
        }return root;
    }
    static TreeNode complete(int count){
        if(count==0)return null;TreeNode[] all=new TreeNode[count];for(int i=0;i<count;i++)all[i]=new TreeNode(i);
        for(int i=0;i<count;i++){if(2*i+1<count)all[i].left=all[2*i+1];if(2*i+2<count)all[i].right=all[2*i+2];}return all[0];
    }
    static TreeNode chain(int count,int value){TreeNode root=null;for(int i=0;i<count;i++)root=new TreeNode(value,root,null);return root;}
    static void lca(TreeNode root,int repeats){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,TreeNode> parents=new IdentityHashMap<>();parents.put(root,null);
        int unique=0;for(TreeNode n:all){n.val=unique++-50000;if(n.left!=null)parents.put(n.left,n);if(n.right!=null)parents.put(n.right,n);}
        String before=shape(root);Q236 solution=new Q236();
        for(int i=0;i<repeats;i++){
            TreeNode p=all.get(random.nextInt(all.size())),q=all.get(random.nextInt(all.size()));if(p==q){i--;continue;}
            Set<TreeNode> ancestors=Collections.newSetFromMap(new IdentityHashMap<>());for(TreeNode n=p;n!=null;n=parents.get(n))ancestors.add(n);
            TreeNode expected=q;while(!ancestors.contains(expected))expected=parents.get(expected);
            require(solution.lowestCommonAncestor(root,p,q)==expected,"236 LCA identity");checks[0]++;
        }require(before.equals(shape(root)),"236 changed tree");
    }
    static int pathOracle(TreeNode root,int target){
        int result=0;for(TreeNode start:nodes(root)){
            ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Long> sums=new ArrayDeque<>();todo.push(start);sums.push((long)start.val);
            while(!todo.isEmpty()){TreeNode n=todo.pop();long sum=sums.pop();if(sum==target)result++;if(n.left!=null){todo.push(n.left);sums.push(sum+n.left.val);}if(n.right!=null){todo.push(n.right);sums.push(sum+n.right.val);}}
        }return result;
    }
    static void path(TreeNode root,int target){
        String before=shape(root);require(new Q437().pathSum(root,target)==pathOracle(root,target),"437 target="+target);require(before.equals(shape(root)),"437 changed tree");checks[1]++;
    }
    static void greater(TreeNode root){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,Integer> expected=new IdentityHashMap<>();IdentityHashMap<TreeNode,TreeNode[]> links=new IdentityHashMap<>();
        for(TreeNode n:all){int sum=0;for(TreeNode other:all)if(other.val>=n.val)sum+=other.val;expected.put(n,sum);links.put(n,new TreeNode[]{n.left,n.right});}
        require(new Q538().convertBST(root)==root,"538 root identity");
        for(TreeNode n:all){require(n.val==expected.get(n),"538 greater sum");require(n.left==links.get(n)[0]&&n.right==links.get(n)[1],"538 changed links");}checks[2]++;
    }
    static TreeNode bst(int[] values,int from,int to){if(from>=to)return null;int mid=(from+to)/2;return new TreeNode(values[mid],bst(values,from,mid),bst(values,mid+1,to));}
    static List<TreeNode> inorder(TreeNode root){
        List<TreeNode> out=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){while(current!=null){stack.push(current);current=current.left;}current=stack.pop();out.add(current);current=current.right;}return out;
    }
    static void makeBst(TreeNode root){
        int value=-500;for(TreeNode n:inorder(root)){value+=1+random.nextInt(10);n.val=value;}
    }
    static TreeNode mergeOracle(TreeNode a,TreeNode b){
        if(a==null&&b==null)return null;
        return new TreeNode((a==null?0:a.val)+(b==null?0:b.val),mergeOracle(a==null?null:a.left,b==null?null:b.left),mergeOracle(a==null?null:a.right,b==null?null:b.right));
    }
    static void merge(TreeNode a,TreeNode b){
        String expected=shape(mergeOracle(a,b)),beforeB=shape(b);TreeNode actual=new Q617().mergeTrees(a,b);
        require(shape(actual).equals(expected),"617 full tree");require(actual==(a==null?b:a),"617 root reuse");require(shape(b).equals(beforeB),"617 changed second tree");nodes(actual);checks[3]++;
    }
    static void codec(TreeNode root){
        String expected=shape(root),encoded=serializer.serialize(root);
        require(expected.equals(shape(root)),"297 changed input");
        TreeNode decoded=deserializer.deserialize(encoded);require(expected.equals(shape(decoded)),"297 cross-codec full tree");nodes(decoded);
        TreeNode same=serializer.deserialize(encoded);require(expected.equals(shape(same)),"297 same-codec round trip");
        require(serializer.serialize(decoded).equals(encoded),"297 stable encoding");checks[4]++;
    }
    public static void main(String[] args){
        lca(new TreeNode(1,new TreeNode(2),null),2);
        for(int test=0;test<500;test++)lca(randomTree(2+random.nextInt(99),100),12);
        lca(complete(100000),8);lca(chain(1000,1),8);
        path(null,0);path(new TreeNode(1),1);path(new TreeNode(1),0);
        path(new TreeNode(0,new TreeNode(0),new TreeNode(0)),0);
        path(new TreeNode(10,new TreeNode(5,new TreeNode(3,new TreeNode(3),new TreeNode(-2)),new TreeNode(2,null,new TreeNode(1))),new TreeNode(-3,null,new TreeNode(11))),8);
        for(int test=0;test<1200;test++)path(randomTree(random.nextInt(101),20),random.nextInt(101)-50);
        TreeNode zeros=chain(1000,0);path(zeros,0);path(chain(1000,1000000000),0);
        path(new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(294967296),null),null),null),null),0);
        TreeNode uniquePrefix=complete(1000);ArrayDeque<TreeNode> work=new ArrayDeque<>();ArrayDeque<Integer> prefix=new ArrayDeque<>();work.add(uniquePrefix);prefix.add(0);
        while(!work.isEmpty()){TreeNode n=work.remove();int previous=prefix.remove(),now=n.val+1;n.val=now-previous;if(n.left!=null){work.add(n.left);prefix.add(now);}if(n.right!=null){work.add(n.right);prefix.add(now);}}
        Q437 observed=new Q437();require(observed.pathSum(uniquePrefix,0)==0,"437 positive paths");checks[1]++;
        System.out.println("RETAINED_KEYS_437="+observed.map.size());
        require(observed.map.size()==1001,"437 retained zero-key observation");
        Q437 reused437=new Q437();int first437=reused437.pathSum(new TreeNode(1),1),second437=reused437.pathSum(new TreeNode(1),1);
        System.out.println("REUSE437_FIRST="+first437);System.out.println("REUSE437_SECOND="+second437);
        greater(null);greater(new TreeNode(-10000));greater(new TreeNode(10000));
        for(int test=0;test<700;test++){TreeNode root=randomTree(random.nextInt(101),1);makeBst(root);greater(root);}
        int[] values=new int[10000];for(int i=0;i<values.length;i++)values[i]=i;TreeNode large=bst(values,0,values.length);List<TreeNode> all=inorder(large);new Q538().convertBST(large);
        int suffix=0;for(int i=9999;i>=0;i--){suffix+=i;require(all.get(i).val==suffix,"538 10000-node suffix oracle");}checks[2]++;
        TreeNode skew=chain(1000,0);makeBst(skew);greater(skew);
        Q538 reused538=new Q538();int first538=reused538.convertBST(new TreeNode(1)).val,second538=reused538.convertBST(new TreeNode(1)).val;
        System.out.println("REUSE538_FIRST="+first538);System.out.println("REUSE538_SECOND="+second538);
        merge(null,null);merge(new TreeNode(1),null);merge(null,new TreeNode(-1));
        for(int test=0;test<1000;test++)merge(randomTree(random.nextInt(101),10000),randomTree(random.nextInt(101),10000));
        merge(complete(2000),complete(2000));merge(chain(1000,10000),chain(1000,-10000));
        codec(null);codec(new TreeNode(-1000));codec(new TreeNode(1000));codec(new TreeNode(1,new TreeNode(2),null));codec(new TreeNode(1,null,new TreeNode(2)));
        for(int test=0;test<1000;test++)codec(randomTree(random.nextInt(151),1000));
        TreeNode largeCodec=complete(10000);for(TreeNode n:nodes(largeCodec))n.val=random.nextInt(2001)-1000;codec(largeCodec);codec(chain(1000,-1000));codec(null);
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
