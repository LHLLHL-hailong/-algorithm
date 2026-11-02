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
 * strat: 15:56
 * end: 16:06
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Solution {

    HashMap<Long,Integer>map=new HashMap<>();

    int res=0;

    void dfs(TreeNode root,long prefix,int target){
        if(root==null){
            return ;
        }
        prefix+=root.val;
        int count=map.getOrDefault(prefix-target,0);
        res+=count;

        map.put(prefix,map.getOrDefault(prefix,0)+1);
        dfs(root.left,prefix,target);
        dfs(root.right,prefix,target);

        map.put(prefix,map.getOrDefault(prefix,0)-1);


    }



    public int pathSum(TreeNode root, int targetSum) {

        map.put(0L,1);

        dfs(root,0L,targetSum);

        return res;

        
    }
}