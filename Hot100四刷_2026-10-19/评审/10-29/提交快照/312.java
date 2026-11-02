/**
 * start: 21:14
 * end: 21:23
 * 独立完成
 * 时间O(n2)
 * 空间O(n3)
 */
class Solution {
    public int maxCoins(int[] nums) {

        int []newNums=new int[nums.length+2];

        for(int i=0;i<nums.length;i++){
            newNums[i+1]=nums[i];
        }

        newNums[0]=1;
        newNums[newNums.length-1]=1;



        int [][]dp=new int[newNums.length][newNums.length];

        for(int i=dp.length-1;i>=0;i--){
            for(int j=i+2;j<dp[i].length;j++){
                for(int k=i+1;k<j;k++){
                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+newNums[i]*newNums[j]*newNums[k]);
                }

            }
        }

        return dp[0][newNums.length-1];


        
    }
}