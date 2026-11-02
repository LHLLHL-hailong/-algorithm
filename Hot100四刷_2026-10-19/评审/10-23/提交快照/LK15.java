/**
 * start:18:51
 * end: 19:01
 * 独立完成
 * 时间：O(n2)
 * 空间：O(1)
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list=new ArrayList<>();

        Arrays.sort(nums);

        for(int first=0;first<nums.length;first++){
            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int third=nums.length-1;
            int target=-nums[first];

            for(int second=first+1;second<nums.length;second++){
                if(second!=first+1 &&nums[second]==nums[second-1]){
                    continue;
                }

                while(nums[second]+nums[third]>target && third>second){
                    third--;
                }

                if(third<=second){
                    break;
                }
                if(nums[third]+nums[second]==target){
                    ArrayList<Integer>res=new ArrayList<>();

                    res.add(nums[first]);
                    res.add(nums[second]);
                    res.add(nums[third]);

                    list.add(res);
                }


            }
        }

        return list;

    }
}