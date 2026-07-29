/**
 * 记得状态压缩
 */

class Solution {
    public int maxProduct(int[] nums) {

        // int[]dpmax=new int[nums.length];
        // int[]dpmin=new int[nums.length];

       int  dpmax=nums[0];
       int  dpmin=nums[0];

        int maxV=dpmax;

        for(int i=1;i<nums.length;i++){
        int pre=dpmax;
        dpmax=Math.max(dpmax*nums[i],Math.max(dpmin*nums[i],nums[i]));
        dpmin=Math.min(dpmin*nums[i],Math.min(pre*nums[i],nums[i]));

        maxV=Math.max(maxV,dpmax);

        }

        return  maxV;                                // Math.max(dpmax[nums.length-1],dpmin[nums.length-1]);

    }
}
