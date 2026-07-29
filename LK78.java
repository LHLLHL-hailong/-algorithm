/**
 * 组合的思维写的题解
 */

class Solution {

    List<List<Integer>> res=new ArrayList<>();

    void dfs( int startIdx,int [] nums,ArrayList<Integer> list){

        if(startIdx>=nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }

        for(int i=startIdx;i<nums.length;i++){
            list.add(nums[i]);

            dfs(i+1,nums,list);

            list.remove(list.size()-1);
        }

        dfs(nums.length,nums,list);

    
    }

        public List<List<Integer>> subsets(int[] nums) {

            dfs(0,nums,new ArrayList<Integer>());
            return res;

        }
}







/**
 * 一种比较暴力的01解决办法
 */
class Solution {
    List<List<Integer>> res=new ArrayList<>();

    void dfs( int level,int [] nums,ArrayList<Integer> list){

        if(level==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }

        if(level>nums.length){
            return ;
        }

        list.add(nums[level]);

        dfs(level+1,nums,list);

        list.remove(list.size()-1);

        dfs(level+1,nums,list);

    }


        public List<List<Integer>> subsets(int[] nums) {
            dfs(0,nums,new ArrayList<>());
            return res;

        }

}