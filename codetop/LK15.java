class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>>res=new ArrayList<>();

        Arrays.sort(nums);

        for(int first=0;first<nums.length;first++){

            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int third=nums.length-1;
            int target=-nums[first];

            if(nums[first]>0){
                break;
            }

            for(int second=first+1 ; second<nums.length;second++){

                if(second!=first+1 && nums[second]==nums[second-1]){
                    continue;
                }

                while(third<second && nums[second]+nums[third]>target){
                    third--;
                }

                if(third>=second){
                    bread;
                }

                if(nums[second]+nums[third]==target){

                    List<Integer>now=new ArrayList<>();
                    now.add(nums[first]);
                    now.add(nums[second]);
                    now.add(nums[third]);

                    res.add(now);
                    
                }

            }

        }
        return res;

    }
    
}