/**
 * 这道题还是很简单的
 */

class Solution {


    ArrayList<List<Integer>>res=new ArrayList<>();

    void dfs( ArrayList<Integer> list,int [] candidates,int target,int start,int total){

        if(total==target){
            res.add(new ArrayList<>(list));
            return ;
        }

        if(total > target){
            return ;
        }

        for(int i=start;i<candidates.length;i++){
            list.add(candidates[i]);
            dfs(list,candidates,target,i,total+candidates[i]);
            list.remove(list.size()-1);

        }

    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        dfs(new ArrayList<Integer>(),candidates,target,0,0);

        return res;

    }
}