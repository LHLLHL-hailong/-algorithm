/**
 * 基本没有难度
 */

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


    void dfs( TreeNode root){
    TreeNode tmp=root.left;
    root.left=root.right;
    root.right=tmp;

    if(root.left!=null){
        dfs(root.left);
    }

    if(root.right!=null){
        dfs(root.right);
    }


}

    public TreeNode invertTree(TreeNode root) {

        if(root==null){
            return root;
        }
        dfs(root);

        return root;

    }
}