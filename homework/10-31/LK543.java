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
class Solution {

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