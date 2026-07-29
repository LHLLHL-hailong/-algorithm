/**
 * 这道题也很简单
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

    int pre=0;
    boolean first=true;

    boolean dfs(TreeNode root){

        if(root.left!=null){
           if(!dfs(root.left)){
                return false;
           }
        }

        if(first){
            pre=root.val;
            first=false;
        }else{

            if(root.val<=pre){
                return false;
            }
            pre=root.val;
        }


        if(root.right!=null){
            if(!dfs(root.right)){
                return false;
            }
        }

        return true;

    }



    public boolean isValidBST(TreeNode root) {

        if(root==null) return  true;
        else return dfs(root);


        
    }
}