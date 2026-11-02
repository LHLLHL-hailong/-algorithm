/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * start: 18:45
 * end: 18:58
 * 独立完成
 * 时间：O(n)
 * 空间：O(h+n)
 */
public class Codec {

    void dfs(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("null,");

            return ;
        }else{
            sb.append(root.val+",");
        }

        dfs(root.left,sb);

        dfs(root.right,sb);

        
    }
    

    

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();

        dfs(root,sb);

        return sb.toString();

        //StringBuilder sb=dfs(root);
        
    }

    TreeNode build(String[]strs,int[]ptr){

        if(strs[ptr[0]].equals("null")){
            ptr[0]++;
            return null;
        }else{
            TreeNode root=new TreeNode(Integer.parseInt(strs[ptr[0]]));
            ptr[0]++;

            root.left=build(strs,ptr);
            root.right=build(strs,ptr);

            return root;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[]strs=data.split(",");

        return build(strs,new int[]{0});

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));