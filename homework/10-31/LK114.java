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
 * start: 20:30
 * end: 20:33
 * 独立完成
 * 时间：O(n)
 * 空间: O(h)
 */
class Solution {

    TreeNode pre=null;


    void dfs(TreeNode root){
        if(root==null) return;

        TreeNode r=root.right;
        TreeNode l=root.left;

        root.left=null;
        root.right=null;

        if(pre==null){
            pre=root;
        }else{
            pre.right=root;
            pre=root;
        }

        dfs(l);
        dfs(r);

        
    }


    public void flatten(TreeNode root) {

        dfs(root);
        
    }
}