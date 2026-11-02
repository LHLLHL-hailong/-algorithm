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
class Solution {

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