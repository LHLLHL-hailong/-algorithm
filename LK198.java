/**
 * 就是一个简单的动态规划
 * 
 */

/**
 * 状态压缩版本
 */
class Solution {
    public int rob(int[] nums) {

        if(nums.length<=0){
            return 0;
        }else if(nums.length==1){
            return nums[0];
        }

        int[] dp=new int[3];

        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);


        for(int i=2;i<nums.length;i++){

            dp[i%3]=Math.max(dp[(i-1)%3],dp[(i-2)%3]+nums[i]);

        }

        return dp[(nums.length-1)%3];

        
        
    }
}
/**
 * 正常版本
 */
class Solution {
    public int rob(int[] nums) {

        if(nums.length<=0){
            return 0;
        }else if(nums.length==1){
            return nums[0];
        }

        int[] dp=new int[nums.length];

        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);


        for(int i=2;i<nums.length;i++){

            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);

        }

        return dp[nums.length-1];

        
        
    }
}