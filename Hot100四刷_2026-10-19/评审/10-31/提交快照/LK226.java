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
class Solution {

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