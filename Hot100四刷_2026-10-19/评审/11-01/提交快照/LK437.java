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
 * start: 18:25
 * end: 18:33
 * 独立完成
 * 时间：O(n)
 * 空间：O(h)
 */
class Solution {

    HashMap<Long,Integer>map=new HashMap<>();

    int res=0;

    void dfs(TreeNode root,int targetSum,long now){

        if(root==null) return;
        long prefix=now+root.val;

        Integer count=map.get(prefix-targetSum);

        if(count!=null){
            res+=count;
        }

        map.put(prefix,map.getOrDefault(prefix,0)+1);

        dfs(root.left,targetSum,prefix);
        dfs(root.right,targetSum,prefix);

        map.put(prefix,map.getOrDefault(prefix,0)-1);


    }

    public int pathSum(TreeNode root, int targetSum) {

        map.put(0L,1);

        dfs(root,targetSum,0L);

        return res;
        
    }
}