class Solution {
    public boolean canPartition(int[] nums) {

        int target=0;

        for(int now:nums){
            target+=now;
        }

        if(target%2!=0){
            return false;
        }

        target%=2;

        int[][]dp=new int[nums.length+1][target+1];

        for(int i=0;i<=nums.length;i++){
            dp[i][0]=true;
        }


        for(int i=1;i<=nums.length;i++){
            for(int j=nums[i-1];j<=target;j++){
               dp[i][j]=(dp[i-1][j] || dp[i-1][j-nums[i-1]]);
            }
        }

        return dp[nums.length][target];


    }
}