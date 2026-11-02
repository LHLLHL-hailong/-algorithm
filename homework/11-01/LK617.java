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
 * start: 18:39
 * end: 18:45
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Solution {


    void merge(TreeNode root1,TreeNode root2){

        root1.val+=root2.val;

        if(root2.left!=null){
            if(root1.left==null){
                root1.left=root2.left;
            }else{
                merge(root1.left,root2.left);
            }
        }

        if(root2.right!=null){
            if(root1.right==null){
                root1.right=root2.right;
            }else{
                merge(root1.right,root2.right);
            }
        }
    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if(root1==null) return root2;
        if(root2==null) return root1;

        merge(root1,root2);

        return root1;


        
    }
}