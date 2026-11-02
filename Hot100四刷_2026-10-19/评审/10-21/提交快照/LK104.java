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
class Solution {

    int dfs(TreeNode root){
        if(root==null) return 0;
        return Math.max(dfs(root.left),dfs(root.right))+1;
    }

    public int maxDepth(TreeNode root) {

        return dfs(root);
        
    }
}