import java.util.*;

class TreeNode {int val; TreeNode left,right; TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;}}

/**
 * start: 15:23
 * end: 15:31
 * 独立完成
 * 时间: O(n3)
 * 空间: O(n2)
 */
class Q312 {
    public int maxCoins(int[] nums) {

        int[]numss=new int[nums.length+2];
        for(int i=0;i<nums.length;i++){
            numss[i+1]=nums[i];
        }
        numss[0]=1;
        numss[numss.length-1]=1;

        int[][]dp=new int[numss.length][numss.length];

        //dp[i][j]=dp[i][k]+dp[k][j]+numss[i]*numss[k]*numss[j];

        for(int i=numss.length-1;i>=0;i--){
            for(int j=i+2;j<=numss.length-1;j++){
                for(int k=i+1;k<=j-1;k++){

                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+numss[i]*numss[k]*numss[j]);

                }
            }
        }        

        return dp[0][numss.length-1];
    }
}
/**
 * start: 15:31
 * end: 15:54
 * 独立完成
 * 时间：O(log(n+m))
 * 空间：O(1)
 */
class Q4 {

    int getNum(int[]nums1,int[]nums2,int target){

        int ptr0=0;
        int ptr1=0;

        while(true){

            if(ptr0>=nums1.length){
                return nums2[ptr1+target-1];
            }

            if(ptr1>=nums2.length){
                return nums1[ptr0+target-1];
            }

            if(target==1){
                return Math.min(nums1[ptr0],nums2[ptr1]);
            }

            int piviot1=Math.min(nums1.length-1,ptr0+target/2-1);
            int piviot2=Math.min(nums2.length-1,ptr1+target/2-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                target-=(piviot1-ptr0+1);
                ptr0=piviot1+1;

            }else{
                target-=(piviot2-ptr1+1);
                ptr1=piviot2+1;

            }
        }
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;

        if(len%2==0){

            return (getNum(nums1,nums2,len/2)+getNum(nums1,nums2,len/2+1))*1.0/2;
        }else{

            return getNum(nums1,nums2,len/2+1)*1.0;
        }
        
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
 * strat: 15:56
 * end: 16:06
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Q437 {

    HashMap<Long,Integer>map=new HashMap<>();

    int res=0;

    void dfs(TreeNode root,long prefix,int target){
        if(root==null){
            return ;
        }
        prefix+=root.val;
        int count=map.getOrDefault(prefix-target,0);
        res+=count;

        map.put(prefix,map.getOrDefault(prefix,0)+1);
        dfs(root.left,prefix,target);
        dfs(root.right,prefix,target);

        map.put(prefix,map.getOrDefault(prefix,0)-1);


    }



    public int pathSum(TreeNode root, int targetSum) {

        map.put(0L,1);

        dfs(root,0L,targetSum);

        return res;

        
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
 * start: 16:06
 * end: 16:19
 * 独立完成
 * 时间：O(n)
 * 空间：O(n)
 */
class Q297 {


    void dfs(StringBuilder sb,TreeNode root){
        if(root==null){
            sb.append("null,");
            return;
        }else{
            sb.append(root.val);
            sb.append(",");

            dfs(sb,root.left);
            dfs(sb,root.right);
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();

        dfs(sb,root);

        //System.out.println(sb.toString());
        
        return sb.toString();
    }

    TreeNode build(String[]strs,int[]ptr){
        if(strs[ptr[0]].equals("null")){
            ptr[0]++;
            return null;
        }else{

            int value=Integer.parseInt(strs[ptr[0]]);
            ptr[0]++;
            TreeNode root=new TreeNode (value);

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
    static Random random=new Random(1102); static int[] checks=new int[4]; static int rankChecks=0;
    static Q312 q312=new Q312();static Q4 q4=new Q4();static Q297 ser=new Q297(),deser=new Q297();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static int burstOracle(int[] a,int mask,int[] memo){
        if(mask==0)return 0;if(memo[mask]>=0)return memo[mask];int best=0;
        for(int i=0;i<a.length;i++)if((mask&(1<<i))!=0){
            int left=i-1,right=i+1;while(left>=0&&(mask&(1<<left))==0)left--;while(right<a.length&&(mask&(1<<right))==0)right++;
            int score=(left<0?1:a[left])*a[i]*(right==a.length?1:a[right]);best=Math.max(best,score+burstOracle(a,mask^(1<<i),memo));
        }return memo[mask]=best;
    }
    static void burst(int[] a){int[] memo=new int[1<<a.length];Arrays.fill(memo,-1);burst(a,burstOracle(a,(1<<a.length)-1,memo));}
    static void burst(int[] a,int expected){int[] input=a.clone();require(q312.maxCoins(input)==expected,"312 "+Arrays.toString(a));require(Arrays.equals(a,input),"312 input mutation");checks[0]++;}
    static int[] sorted(int[] a,int[] b){int[] out=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,out,a.length,b.length);Arrays.sort(out);return out;}
    static void median(int[] a,int[] b){
        int[] all=sorted(a,b),x=a.clone(),y=b.clone();int n=all.length;
        double expected=n%2==0?((long)all[n/2-1]+all[n/2])/2.0:all[n/2];require(q4.findMedianSortedArrays(x,y)==expected,"4 median");require(Arrays.equals(x,a)&&Arrays.equals(y,b),"4 mutation");checks[1]++;
    }
    static void rank(int[] a,int[] b,int k){require(q4.getNum(a,b,k)==sorted(a,b)[k-1],"4 rank k="+k);rankChecks++;}
    static void sortedArrays(List<int[]> out,int[] a,int pos,int minimum){if(pos==a.length){out.add(a.clone());return;}for(int v=minimum;v<=2;v++){a[pos]=v;sortedArrays(out,a,pos+1,v);}}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> out=new ArrayList<>();if(root==null)return out;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"tree alias/cycle");out.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return out;
    }
    static String shape(TreeNode root){StringBuilder s=new StringBuilder();List<TreeNode> q=new ArrayList<>();q.add(root);for(int i=0;i<q.size();i++){TreeNode n=q.get(i);if(n==null){s.append("#, ");continue;}s.append(n.val).append(',');q.add(n.left);q.add(n.right);}return s.toString();}
    static TreeNode tree(int count,int bound){
        if(count==0)return null;TreeNode root=new TreeNode(random.nextInt(2*bound+1)-bound);List<TreeNode> open=new ArrayList<>();open.add(root);
        for(int i=1;i<count;i++){int pos=random.nextInt(open.size());TreeNode p=open.get(pos),n=new TreeNode(random.nextInt(2*bound+1)-bound);if(p.left==null&&p.right==null){if(random.nextBoolean())p.left=n;else p.right=n;}else if(p.left==null)p.left=n;else p.right=n;if(p.left!=null&&p.right!=null)open.remove(pos);open.add(n);}return root;
    }
    static TreeNode complete(int count){if(count==0)return null;TreeNode[] a=new TreeNode[count];for(int i=0;i<count;i++)a[i]=new TreeNode(i);for(int i=0;i<count;i++){if(2*i+1<count)a[i].left=a[2*i+1];if(2*i+2<count)a[i].right=a[2*i+2];}return a[0];}
    static TreeNode chain(int count,int value){TreeNode root=null;for(int i=0;i<count;i++)root=new TreeNode(value,root,null);return root;}
    static int pathOracle(TreeNode root,int target){
        int result=0;for(TreeNode start:nodes(root)){ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Long> sums=new ArrayDeque<>();todo.push(start);sums.push((long)start.val);while(!todo.isEmpty()){TreeNode n=todo.pop();long sum=sums.pop();if(sum==target)result++;if(n.left!=null){todo.push(n.left);sums.push(sum+n.left.val);}if(n.right!=null){todo.push(n.right);sums.push(sum+n.right.val);}}}return result;
    }
    static void path(TreeNode root,int target){String before=shape(root);require(new Q437().pathSum(root,target)==pathOracle(root,target),"437 target="+target);require(before.equals(shape(root)),"437 mutation");checks[2]++;}
    static void codec(TreeNode root){
        String before=shape(root),encoded=ser.serialize(root);TreeNode decoded=deser.deserialize(encoded);
        require(before.equals(shape(decoded)),"297 cross codec structure/values");require(before.equals(shape(ser.deserialize(encoded))),"297 self codec");require(before.equals(shape(root)),"297 source changed");require(ser.serialize(decoded).equals(encoded),"297 stable encoding");nodes(decoded);checks[3]++;
    }
    public static void main(String[] args){
        for(int n=1;n<=5;n++)for(int code=0;code<(1<<(2*n));code++){int[] a=new int[n];int value=code;for(int i=0;i<n;i++){a[i]=value&3;value>>=2;}burst(a);}
        burst(new int[]{3,1,5,8});burst(new int[]{1,5});burst(new int[]{100});burst(new int[]{0,2,0});
        for(int test=0;test<200;test++){int[] a=new int[1+random.nextInt(9)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(101);burst(a);}
        int[] full=new int[300];burst(full,0);Arrays.fill(full,1);burst(full,300);Arrays.fill(full,100);burst(full,298010100);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=4;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        for(int test=0;test<600;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        for(int test=0;test<100;test++){int[] a=new int[random.nextInt(31)],b=new int[random.nextInt(31)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(5)-2;for(int i=0;i<b.length;i++)b[i]=random.nextInt(5)-2;Arrays.sort(a);Arrays.sort(b);if(a.length+b.length>0){median(a,b);for(int k=1;k<=a.length+b.length;k++)rank(a,b,k);}}
        int[] a=new int[1000],b=new int[1000];for(int i=0;i<1000;i++){a[i]=2*i;b[i]=2*i+1;}median(a,b);median(new int[]{},a);median(b,new int[]{});for(int k=1;k<=2000;k++)rank(a,b,k);median(new int[]{-1000000},new int[]{1000000});median(new int[]{1000000},new int[]{1000000});
        path(null,0);path(new TreeNode(1),0);path(new TreeNode(1),1);path(new TreeNode(0,new TreeNode(0),new TreeNode(0)),0);
        path(new TreeNode(10,new TreeNode(5,new TreeNode(3,new TreeNode(3),new TreeNode(-2)),new TreeNode(2,null,new TreeNode(1))),new TreeNode(-3,null,new TreeNode(11))),8);
        for(int test=0;test<600;test++)path(tree(random.nextInt(101),20),random.nextInt(101)-50);
        path(chain(1000,0),0);path(chain(1000,1000000000),0);
        path(new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(294967296),null),null),null),null),0);
        TreeNode distinct=complete(1000);ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Integer> previous=new ArrayDeque<>();todo.add(distinct);previous.add(0);
        while(!todo.isEmpty()){TreeNode n=todo.remove();int pre=previous.remove(),now=n.val+1;n.val=now-pre;if(n.left!=null){todo.add(n.left);previous.add(now);}if(n.right!=null){todo.add(n.right);previous.add(now);}}
        Q437 retained=new Q437();require(retained.pathSum(distinct,0)==0,"437 positive-prefix tree");require(retained.map.size()==1001,"437 retained keys");checks[2]++;System.out.println("RETAINED_KEYS_437="+retained.map.size());
        Q437 reused=new Q437();int first=reused.pathSum(new TreeNode(1),1),second=reused.pathSum(new TreeNode(1),1);System.out.println("REUSE437_FIRST="+first);System.out.println("REUSE437_SECOND="+second);
        codec(null);codec(new TreeNode(-1000));codec(new TreeNode(1000));codec(new TreeNode(1,new TreeNode(2),null));codec(new TreeNode(1,null,new TreeNode(2)));
        for(int test=0;test<600;test++)codec(tree(random.nextInt(151),1000));
        TreeNode large=complete(10000);for(TreeNode n:nodes(large))n.val=random.nextInt(2001)-1000;codec(large);codec(chain(1000,-1000));codec(null);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
