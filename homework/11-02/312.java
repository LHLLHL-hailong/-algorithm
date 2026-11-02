/**
 * start: 15:23
 * end: 15:31
 * 独立完成
 * 时间: O(n3)
 * 空间: O(n2)
 */
class Solution {
    public int maxCoins(int[] nums) {

        int[]numss=new int[nums.length+2];
        for(int i=0;i<nums.length;i++){
            numss[i+1]=nums[i];
        }
        numss[0]=1;
        numss[numss.length-1]=1;

        int[][]dp=new int[numss.length][numss.length];

        //dp[i][j]=dp[i][k]+dp[k][j]+numss[i]*numss[k]*numss[j];

        for(int i=numss.length-1;i>=0;i--){
            for(int j=i+2;j<=numss.length-1;j++){
                for(int k=i+1;k<=j-1;k++){

                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+numss[i]*numss[k]*numss[j]);

                }
            }
        }        

        return dp[0][numss.length-1];
    }
}