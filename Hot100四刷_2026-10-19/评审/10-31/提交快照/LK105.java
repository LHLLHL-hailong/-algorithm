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
 * start: 20:15
 * end: 20:30
 * 独立完成
 * 时间：O(n)
 * 空间：O(n+h)
 */
class Solution {


    TreeNode build(int []preorder,int from1,int to1,int[]inorder,int from2,int to2,HashMap<Integer,Integer>map){


        if(from1>to1|| from2>to2) return null;

        TreeNode root=new TreeNode(preorder[from1]);

        int idx=map.get(preorder[from1]);

        //target-from1=idx-1-from2+1
        //target=idx-from2+from1

        //前序遍历
        //from1+1 idx-from2+from1
        //idx-from2+from1+1  to1

        //中序遍历
        //from2   idx-1
        //idx+1   to2

        root.left=build(preorder,from1+1,idx-from2+from1,inorder,from2, idx-1,map);
        root.right=build(preorder,idx-from2+from1+1,to1,inorder,idx+1,to2,map);

        return root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }

        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);


        
    }
}