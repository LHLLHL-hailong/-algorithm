
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


    void dfs(TreeNode root){

        if(root == null){
            return ;
        }

        TreeNode left=root.left;
        TreeNode right=root.right;

        root.left=null;
        root.right=null;
        head.right=root;
        head=head.right;

        dfs(left);
        dfs(right);


    }

    TreeNode head=new TreeNode(-1);


    public void flatten(TreeNode root) {

       // TreeNode ptr=head;

        dfs(root);

       // return ptr.right;

        
    }
}