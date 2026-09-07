class Solution {
    public int coinChange(int[] coins, int amount) {

        int[][]dp=new int[coins.length+1][amount+1];

        for(int i=0;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=Integer.MAX_VALUE/2;
            }
        }


        for(int i=1;i<=coins.length;i++){
            for(int j=1;j<=amount;j++){
                if(j>=coins[i-1]){
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
                
            }

        }
        if(dp[coins.length][amount]==Integer.MAX_VALUE/2){
            return -1;
        }else{
            return dp[coins.length][amount];
        }
    }
}