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

class Solution {

    int maxLen=Integer.MIN_VALUE/2;//这里忘了，因为结果可能是只有一个基点是负数，所以说这个初始化必须是初始一个最小值，最小值应该是负数

    int dfs(TreeNode root){

        if(root==null) return 0;

        int l=dfs(root.left);
        int r=dfs(root.right);

        maxLen=Math.max(maxLen,l+r+root.val);

        return Math.max(0,Math.max(l,r)+root.val);

    }


    public int maxPathSum(TreeNode root) {
        dfs(root);

        return maxLen;
    
    }
}

/**
##review
忘了初始，化成负数
综合评价C+
 */