/**
 * 没什么难度
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

int count=0;

void midOrder(TreeNode root){

    if(root.right!=null){
        midOrder(root.right);
    }

    root.val+=count;
    count=root.val;


    if(root.left!=null){
        midOrder(root.left);

    }



}

class Solution {
    public TreeNode convertBST(TreeNode root) {
        if(root==null) return root;
        midOrder(root);
        return root;
        
    }
}