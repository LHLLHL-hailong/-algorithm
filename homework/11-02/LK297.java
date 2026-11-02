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
 * start: 16:06
 * end: 16:19
 * 独立完成
 * 时间：O(n)
 * 空间：O(n)
 */
public class Codec {


    void dfs(StringBuilder sb,TreeNode root){
        if(root==null){
            sb.append("null,");
            return;
        }else{
            sb.append(root.val);
            sb.append(",");

            dfs(sb,root.left);
            dfs(sb,root.right);
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();

        dfs(sb,root);

        //System.out.println(sb.toString());
        
        return sb.toString();
    }

    TreeNode build(String[]strs,int[]ptr){
        if(strs[ptr[0]].equals("null")){
            ptr[0]++;
            return null;
        }else{

            int value=Integer.parseInt(strs[ptr[0]]);
            ptr[0]++;
            TreeNode root=new TreeNode (value);

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