/**
 * 这种做法才是真正的百分百
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



    int  dfs(TreeNode root){

        if(root==null) return 0;


       int leftV= dfs(root.left);
       int rightV= dfs(root.right);

       return Math.max(leftV,rightV)+1;

    }


    public int maxDepth(TreeNode root) {

        return dfs(root);


        
    }
}



/**
 * 这样写的解法并非百分百
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

    int level=0;


    void dfs(TreeNode root,int level0){

        level=Math.max(level,level0);


        if(root.left!=null)  dfs(root.left,level0+1);
        if(root.right!=null) dfs(root.right,level0+1);

    }


    public int maxDepth(TreeNode root) {

        if(root ==null) return level;

        dfs(root,1);

        return level;
        
    }
}