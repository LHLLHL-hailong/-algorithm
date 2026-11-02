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
 * start: 18:59
 * end: 19:02
 * 独立完成
 * 时间： O(n)
 * 空间：O(log(n))
 */
class Solution {

    boolean dfs(TreeNode root1,TreeNode root2){

        if(root1==null && root2==null){
            return true;
        }  

        if(root1==null || root2==null){
            return false;
        }

        return root1.val==root2.val && dfs(root1.left,root2.right) && dfs(root1.right,root2.left);

    }

    public boolean isSymmetric(TreeNode root) {

        return dfs(root.left,root.right);
        
    }
}