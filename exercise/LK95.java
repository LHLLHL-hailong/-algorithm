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
     List<TreeNode> dfs(int from ,int to){

    List<TreeNode>list=new ArrayList<>();
    if(from>to){
        list.add(null);//为空的时候显示的像集合中加入null值，这样for的时候为空也会遍历一遍元素值为null的，在一些情况下很好用
    }
    if(from==to){
        TreeNode node=new TreeNode(from);
        list.add(node);
        return list;
    }
    for(int i=from;i<=to;i++){
      //  TreeNode root=new TreeNode(i);
        List<TreeNode>left=dfs(from,i-1);
        List<TreeNode>right=dfs(i+1,to);

            for(TreeNode l:left){//mark
                for(TreeNode r:right){//mark
                    TreeNode root=new TreeNode(i);
                    root.left=l;
                    root.right=r;
                    list.add(root);                    
                }
            }
        }

    return list;
 }

    public List<TreeNode> generateTrees(int n) {

        return dfs(1,n);
        
    }
}