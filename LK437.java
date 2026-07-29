/**
 * 这个要好好看看，很难，且基本上忘没了
 */
class Solution {


    int dfs(TreeNode root, HashMap<Long,Integer>map,int targetSum,long preV){

        preV+=root.val;

        int rst=map.getOrDefault(preV-targetSum,0);

        map.put( preV,map.getOrDefault(preV,0)+1);

        int l= root.left==null?0:dfs(root.left,map,targetSum,preV);
        int r= root.right==null?0:dfs(root.right,map,targetSum,preV);

        map.put(preV,map.get(preV)-1);


        return rst+l+r;


    }

     public int pathSum(TreeNode root, int targetSum) {

        if(root==null){
            return 0;

        }

        HashMap<Long,Integer>map=new HashMap<>();
        map.put(0L,1);//注意这个

        return  dfs(root,map,targetSum,0L);

     }
    
}


/**
 * 这个方法很慢，别用
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


    void addV(ArrayList<Long>arr,long val,int targetSum){

        if(val==targetSum){
            total++;

        }
        arr.add(val);
    }


    int total=0;

   ArrayList<Long> dfs(TreeNode root,int targetSum){

    if(root==null){
        return new ArrayList<>();
    }

    ArrayList<Long>l=dfs(root.left,targetSum);
    ArrayList<Long>r=dfs(root.right,targetSum);

    ArrayList now=new ArrayList<>();

    addV(now,root.val,targetSum);

    for(long val:l){
        addV(now,val+root.val,targetSum);
    }

    for(long val:r){
        addV(now,val+root.val,targetSum);

    }

    return now;

   }


    public int pathSum(TreeNode root, int targetSum) {

        dfs(root,targetSum);

        return total;

        
    }
}


// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */
// class Solution {

//    ArrayList<Integer> dfs(TreeNode root){

//     if()


//     }


//     public int pathSum(TreeNode root, int targetSum) {

        
//     }
// }