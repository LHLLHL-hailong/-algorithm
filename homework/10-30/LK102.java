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
 * start: 19:14
 * end: 19:20
 * 独立完成
 * 时间:O(n)
 * 空间:O(max(m1+m2))//相邻两成节点数量最多的
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        LinkedList<TreeNode>queue=new LinkedList<>();

        List<List<Integer>>list=new ArrayList<>();

        if(root==null) return list;

        queue.add(root);

        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer>res=new ArrayList<>();

            for(int i=0;i<size;i++){
                TreeNode now=queue.removeFirst();

                res.add(now.val);

                if(now.left!=null) queue.addLast(now.left);
                if(now.right!=null) queue.addLast(now.right);
            }

            list.add(res);

            
        }

        return list;

        
        
    }
}