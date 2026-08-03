/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {


    void dfs(TreeNode root ,StringBuilder sb){

        if(root==null){
            sb.append("null,");
            return ;
        }
        sb.append(root.val);
        sb.append(",");
        dfs(root.left,sb);
        dfs(root.right,sb);

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();
        dfs(root,sb);
        sb.setLength(sb.length()-1);

        return sb.toString();
        
    }



    TreeNode build(String[]split,int[]startIdx){

        if(split[startIdx[0]].equals("null")){
            startIdx[0]++;
            return null;
        }

        TreeNode now=new TreeNode(Integer.parseInt(split[startIdx[0]]));
        startIdx[0]++;

        now.left=build(split,startIdx);
        now.right=build(split,startIdx);

        return now;

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        String []split=data.split(",");

        TreeNode now=build(split,new int[]{0});


        return now;

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));


/*
 ##review
综合评价： B-
(需要注意的是，这里边有个限制，就是不能使用成员变量,否则会报错)
 */