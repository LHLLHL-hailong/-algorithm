/**
 * 依旧没什么难度
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

        TreeNode pre=null;
        


        void dfs(TreeNode root){

            if(root==null) return ;

            TreeNode left=root.left;

            TreeNode right=root.right;

            if(pre==null){
                pre=root;
                pre.left=null;
                pre.right=null;
            }else{
                root.left=null;
                root.right=null;
                pre.right=root;
                pre=pre.right;
            }

            dfs(left);
            dfs(right);

        }

        public void flatten(TreeNode root) {

            dfs(root);

        }
}