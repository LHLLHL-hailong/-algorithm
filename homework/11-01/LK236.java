/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * start: 18:20
 * end: 18:23
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Solution {


    TreeNode dfs(TreeNode root,TreeNode p,TreeNode q){

        if(root==null|| root==p || root==q){
            return root;
        }

        TreeNode l=dfs(root.left,p,q);
        TreeNode r=dfs(root.right,p,q);

        if(l!=null && r!=null){
            return root;
        }

        if(l!=null){
            return l;
        }else{
            return r;
        }


    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return dfs(root,p,q);
        
    }
}