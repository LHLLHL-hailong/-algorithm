/**
 * 基本无难度
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

    List<Integer>list=new ArrayList<>();

    void dfs(TreeNode root){

        if(root.left!=null) dfs(root.left);
        list.add(root.val);
        if(root.right!=null) dfs(root.right);

    }

    public List<Integer> inorderTraversal(TreeNode root) {


        if(root==null) return list;
        dfs(root);
        return list;

    }
}