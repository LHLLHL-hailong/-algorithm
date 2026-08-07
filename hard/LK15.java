class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);


        ArrayList<List<Integer>>res=new ArrayList<>();

        for(int first=0;first<nums.length;first++){

            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            if(nums[first]>0){
                break;
            }
            int third=nums.length-1;

            for(int second=first+1;second<third;second++){
                if(second!=first+1 && nums[second]==nums[second-1]){
                    continue;
                }
                while(third>second && nums[second]+nums[third]>-nums[first]){//third>second注意别反了
                    third--;
                }
                if(third<=second) break;//别反了
                

                if(nums[second]+nums[third]==-nums[first]){

                    ArrayList<Integer>list=new ArrayList<>();

                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    
                    res.add(list);
                }

            }
 
        }

        return res;
        
    }
}

/*
##review 基本上忘没了

综合评价：C-
 */