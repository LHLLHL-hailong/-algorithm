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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode>queue=new LinkedList<>();

        List<List<Integer>> res=new ArrayList<>();

        if(root==null) return res;

        queue.add(root);

        while(!queue.isEmpty()){
            int count=queue.size();

            ArrayList<Integer>list=new ArrayList<>();

            while(--count>=0){

                TreeNode now=queue.remove();

                list.add(now.val);

                if(now.left!=null) queue.add(now.left);
                if(now.right!=null) queue.add(now.right);

            }

            res.add(list);


        }

        return res;

        
        
    }
}