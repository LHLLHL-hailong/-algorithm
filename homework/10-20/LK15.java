/**
 * 独立完成
 * sartt: 18:27
 * end: 18:38
 * 时间：O(n2)
 * 空间：O(1)
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>>list=new ArrayList<>();


        for(int first=0;first<nums.length;first++){

            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int target=-nums[first];

            int third=nums.length-1;

            for(int second=first+1;second<nums.length;second++){

                if(second!=first+1 && nums[second]==nums[second-1]){
                    continue;
                }

                while(nums[second]+nums[third]>target && third>second){
                    third--;
                }

                if(third<=second){
                    break;
                }

                if(nums[second]+nums[third]==target){
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