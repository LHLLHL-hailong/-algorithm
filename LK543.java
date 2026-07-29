/**
 * 很简单了
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

    int max=0;


    int dfs(TreeNode root){

        if(root==null){
            return 0;
        }

        int leftNum=dfs(root.left);
        int rightNum=dfs(root.right);

        max=Math.max(max,leftNum+rightNum);

        return Math.max(leftNum,rightNum)+1;

    }



    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);

        return max;


        
    }
}