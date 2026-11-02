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
class Solution {

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