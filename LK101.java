/**
 * 又是一道基本上没有难度的题
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

    boolean compare(TreeNode first,TreeNode second){

        if(first==null && second==null ) return true;

        if(first==null || second==null ) return false;

        if(first.val !=second.val) return false;

        return compare(first.left,second.right) && compare(first.right,second.left);

        
    }

    public boolean isSymmetric(TreeNode root) {

        if(root==null) return true;

        return compare(root.left, root.right);
        
    }
}