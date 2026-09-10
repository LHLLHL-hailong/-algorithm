// class Solution {
//     public boolean canPartition(int[] nums) {
//  //让容量和价值数组是一个，这样找价值最大就是找容量最大，而容量最大就是预设的背包容量（就看能不能达到了）
//             int sum=0;
//             for(int i=0;i<nums.length;i++){
//                 sum+=nums[i];
//             }
//             if(sum%2!=0)return false;

//             int result = sum / 2;
            
//             int[]dp=new int[result+1];
//             for (int i = 0; i < nums.length; i++) {
//                 for (int j = dp.length-1; j >=0; j--) {
//                   if(j-nums[i]>=0)  dp[j]=Math.max(dp[j],dp[j-nums[i]]+nums[i]);
//                 }
//             }
            
//             return dp[dp.length-1]==result;
//     }
// }

class Solution {
    public boolean canPartition(int[] nums) {

        int sum=0;

        for(int item:nums){
            sum+=item;
        }

        int target=sum/2;

        if(target*2!=sum){
            return false;
        }

        boolean[]dp=new boolean[target+1];

        dp[0]=true;

        for(int i=0;i<nums.length;i++){
            for(int j=target;j>=nums[i];j--){


                    dp[j]=dp[j]|| dp[j-nums[i]];

                
            }

        }

        return dp[target];


    }
}