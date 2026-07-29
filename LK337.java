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
 * 重点是这个树上dp（返回数组）
 */

class Solution {


 int[] dfs(TreeNode root){
    //int[] [0] 偷 [1] 不偷

    if(root==null){
        return new int[]{0,0};
    }

    int[]ll=dfs(root.left);
    int[]rr=dfs(root.right);

    int rob=root.val+ll[1]+rr[1];
    int noRob=Math.max(ll[0],ll[1])+Math.max(rr[0],rr[1]);
    return new int[]{rob,noRob};

 }




    public int rob(TreeNode root) {

        int[] res=dfs(root);

        return Math.max(res[0],res[1]);


        
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
 * 注意，这个暴力的算法不对，会超时
 */

class Solution {

    int getMax(TreeNode root , boolean isRobParent){

    if(root==null) return 0;

    if(isRobParent){
        return getMax(root.left,false)+getMax(root.right,false);

    }else{
        int RobV=root.val+getMax(root.left,true)+getMax(root.right,true);
        int NoRobV=getMax(root.left,false)+getMax(root.right,false);
        return Math.max(RobV,NoRobV);

    }

}
    public int rob(TreeNode root) {

        return getMax(root,false);
        
    }
}