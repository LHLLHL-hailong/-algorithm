/**
 * 基本上没有难度
 */
class Solution {

    TreeNode merge(TreeNode root1,TreeNode root2){
        if(root1==null) return root2;
        if(root2==null) return root1;

        root1.val+=root2.val;
        root1.left=merge(root1.left,root2.left);
        root1.right=merge(root1.right,root2.right);

        return root1;

    }


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return  merge(root1,root2);//注意要return这个，不要只return root1

       // return root1;

    }
}