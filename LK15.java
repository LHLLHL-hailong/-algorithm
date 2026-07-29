/**
 * 这道题也是基本忘了
 */


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();


        for(int first=0; first<nums.length;first++){

            if(nums[first]>0){

                break;//剪枝
            }

            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int target=-nums[first];

            int third=nums.length-1;

            for(int second=first+1;second<nums.length;second++){

                if(second!=first+1 && nums[second]==nums[second-1]){
                    continue;
                }

                

                while(third>second && nums[second]+nums[third]>target){
                    third--;
                }

                if(third<=second){
                    break;
                }

                if(nums[second]+nums[third]==target){
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