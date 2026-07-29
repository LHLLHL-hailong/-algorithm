/**
 * 难点在于复杂的推导和成功认识边界条件
 */
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


    TreeNode build(int[] preorder,int pl,int pr, int[] inorder,int il,int ir,HashMap<Integer,Integer>map){


        if(pl>pr){
            return null;
        }
        if(il>ir){
            return null;   // 记住这两个边界条件，有这两个就足够了，没有也不行
        }
        // if(pl==pr || il==ir){
        //     return new TreeNode(preorder[pl]);
        // }   
        int idx=map.get(preorder[pl]);



        // il----idx-1  idx+1-----ir
        //左          中     右
        // idx-1-il+1 
        //tar-(pl+1)+1=idx-il
        //tar=idx-il+pl
        // pl+1 idx-il+pl  idx-il+pl+1   pr

        return new TreeNode(preorder[pl],build(preorder,pl+1,idx-il+pl,inorder,il,idx-1,map),build(preorder,idx-il+pl+1,pr,inorder,idx+1,ir,map));

                


    }




    public TreeNode buildTree(int[] preorder, int[] inorder) {

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<inorder.length;i++){//注意的是这边构造哈希表
            map.put(inorder[i],i);
        }

        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);

    }
}