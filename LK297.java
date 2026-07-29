/**
 * 一道稍微简单一些的hard题，但是也需要仔细看
 */
    //   public class TreeNode {
    //       int val;
    //       TreeNode left;
    //       TreeNode right;
    //       TreeNode(int x) { val = x; }
    //   }

public class Codec {

    String PreOrder(TreeNode root,StringBuilder sb){

        if(root==null){
            sb.append("null");
            sb.append(",");
            return ;
        }
        sb.append(root.val);
        sb.append(",");
        PreOrder(root.left);
        PreOrder(root.right);

    }

     String serialize(TreeNode root) {

        StringBuilder sb=new StringBuilder();

        PreOrder(root,sb);

        return sb.toString();

    }

    int cnt=0;//难点

    TreeNode build(String[] nums){

        if(cnt>=nums.length){
            return null;
        }
        if(nums[cnt].equals("null")){
            cnt++;
            return ;
        }
        TreeNode now=new TreeNode(cnt);
        cnt++;
        now.left==build(nums);
        now.right=build(nums);

        return now;
    }


     TreeNode deserialize(String data) {

        String[] nums=data.split(",");
        return build(nums);'



     }
}