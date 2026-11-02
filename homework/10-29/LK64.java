/**
 * start: 21:33
 * end:21:45
 * 独立完成
 * 时间：O(mn)
 * 空间：O(mn)
 */
class Solution {


    public int minPathSum(int[][] grid) {

        int[][]dp=new int[grid.length+1][grid[0].length+1];

        for(int i=0;i<dp.length;i++){
            dp[i][0]=Integer.MAX_VALUE/2;
        }

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=Integer.MAX_VALUE/2;
        }

        dp[1][0]=0;

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i-1][j-1];
            }
        }



        return dp[grid.length][grid[0].length];


        
    }
}