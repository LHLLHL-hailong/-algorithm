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

    int count=0;

    void dfs(TreeNode root,long prefix,HashMap<Long,Integer>map,int target){

        if(root==null){
            return ;
        }

        prefix+=root.val;

      //  System.out.println(root.val+" 的值是"+prefix+"找  "+(target-prefix) +"存在"+map.getOrDefault(target-prefix,0)+"个");

        count+=map.getOrDefault(prefix-target,0);



        map.put(prefix,map.getOrDefault(prefix,0)+1);

        dfs(root.left,prefix,map,target);
        dfs(root.right,prefix,map,target);

        map.put(prefix,map.get(prefix)-1);
    }


    public int pathSum(TreeNode root, int targetSum) {

        HashMap<Long,Integer>map=new HashMap<>();

        map.put(0L,1);

        dfs(root,0L,map,targetSum);

        return count;


        
    }
}

/*
##review
1. 这个思路本身很难，虽然这次想起来了
2. 这个Long啊，又丢了
综合评价：C+(还需要重写)
 */