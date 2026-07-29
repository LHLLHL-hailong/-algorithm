/**
 * 这道题整体代码不难，但是需要注意的点很多
 */
//  */
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */
class Solution {

    int maxV=-100000;//1. 首先，这个节点的值是有负数的，所以说这个最大值的初始值需要有一个负的额度

    int dfs(TreeNode root){

        if(root==null) return 0;

        int leftValue=dfs(root.left);

        int rightValue=dfs(root.right);

        maxV=Math.max(maxV,leftValue+rightValue+root.val);

        int res=Math.max(leftValue,rightValue)+root.val;
        return res>0?res:0 ;// 因为这个节点是可能为负的，那我这个递归的逻辑就是说我返回它的最大值的那一个边，如果说这一整条边是负数，那我宁可就不选，就宁可返回为0。通过这个逻辑也可以使得上述逻辑变得简单，也就是说，我更新整体，那么我本身节点是一定要带的，然后左右带不带呢？因为我要这个逻辑，让它读为若为负数时，自动和为0，所以左右带上肯定是比不带上是相对，我们最差是也是相等，有可能是更好。所以这个逻辑就变得简化了


    }


    public int maxPathSum(TreeNode root) {

        if(root==null) return 0;

        dfs(root);

        return maxV;
        
    }
}