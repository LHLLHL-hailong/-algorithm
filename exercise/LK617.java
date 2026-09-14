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


    TreeNode merge( TreeNode root1,TreeNode root2 ){

        if(root1==null) return root2;

        if(root2==null) return root1;

        TreeNode l=merge(root1.left,root2.left);

        TreeNode r=merge(root1.right,root2.right);

        root1.val+=root2.val;

        root1.left=l;
        root1.right=r;

        return root1;

    }



    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return merge(root1,root2);
        
    }
}