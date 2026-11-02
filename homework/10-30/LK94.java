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
 * start: 18:48
 * end: 18:51
 * 独立完成
 * 时间：O(n)
 * 空间：O(log(n))
 */
class Solution {

    void dfs(TreeNode root){
        if(root==null) return ;
        dfs(root.left);

        res.add(root.val);

        dfs(root.right);
    }

    List<Integer>res=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {

        dfs(root);

        return res;


        
    }
}