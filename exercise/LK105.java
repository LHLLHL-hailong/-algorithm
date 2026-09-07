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

    
    TreeNode build(int[]preorder,int pleft,int pright,int[] inorder,int ileft,int iright,HashMap<Integer,Integer>map){

        if(pleft>pright || ileft>iright){
            return null;
        }

        TreeNode root =new TreeNode(preorder[pleft]);
        if(pleft==pright){
            return root;
        }

        int idx=map.get(preorder[pleft]);
        //len=idx-1-ileft+1=idx-ileft
        //x-pleft=idx-ileft
        //x=idx-ileft+pleft
        root.left=build(preorder,pleft+1,idx-ileft+pleft,inorder,ileft,idx-1,map);
        root.right=build(preorder,idx-ileft+pleft+1,pright,inorder,idx+1,iright,map);

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