class Solution {
    public int maxSubArray(int[] nums) {

        int maxValue=nums[0];
        int now=nums[0];

        for(int i=1;i<nums.length;i++){
            now=Math.max(now+nums[i],nums[i]);
            maxValue=Math.max(maxValue,now);
        }

        return maxValue;


    }
}