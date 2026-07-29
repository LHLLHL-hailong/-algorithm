/**
 * 很简单
 */

class Solution {

    List<List<Integer>>res=new ArrayList<>();

    void dfs(ArrayList<Integer>list,int[] isVisited ,int[] nums){

        if(list.size()==nums.length){
            res.add(new ArrayList<>(list));
            return ;
        }

        if(list.size()>nums.length){
            return ;
        }

        for(int i=0;i<nums.length;i++){
            if(isVisited[i]==0){
                isVisited[i]=1;
                list.add(nums[i]);

                dfs(list,isVisited,nums);


                list.remove(list.size()-1);
                isVisited[i]=0;
            }
        }

    }

    public List<List<Integer>> permute(int[] nums) {

        dfs(new ArrayList<Integer>(),new int[nums.length],nums);

        return res;



    }

}



