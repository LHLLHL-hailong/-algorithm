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
 * start: 18:52
 * end: 18:58
 * 独立完成
 * 时间: O(n)
 * 空间：O(log(n))
 */



class Solution {
int pre=0;
boolean isFirst=true;

    boolean dfs(TreeNode root){

        if(root==null) return true;

        if(!dfs(root.left)) return false;

        if(isFirst){

            isFirst=false;
        }else{
            if(pre>=root.val){
                return false;
            }

        }
        pre=root.val;

        if(!dfs(root.right)) return false;

        return true;

    }


    


    public boolean isValidBST(TreeNode root) {


        return dfs(root);
        
    }
}