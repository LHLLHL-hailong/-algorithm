/**
 * 这道题还是比较简单的，就是一个明显的动态规划
 */

/**
 * 这道题状态压缩可以快一点
 */
class Solution {
    public int minPathSum(int[][] grid) {

        int[]dp=new int[grid[0].length];

        dp[0]=grid[0][0];

        // for(int i=1;i<grid.length;i++){
        //     dp[i][0]=grid[i][0]+dp[i-1][0];

        // }

        for(int j=1;j<grid[0].length;j++){
            dp[j]=dp[j-1]+grid[0][j];

        }


        for(int i=1;i<grid.length;i++){

            dp[0]+=grid[i][0];
            for(int j=1;j<grid[0].length;j++){

                dp[j]=Math.min(dp[j],dp[j-1])+grid[i][j];


            }
        }

        return dp[grid[0].length-1];

        
        
    }
}

/**
 * 经典解法
 */
class Solution {
    public int minPathSum(int[][] grid) {

        int[][]dp=new int[grid.length][grid[0].length];

        dp[0][0]=grid[0][0];

        for(int i=1;i<grid.length;i++){
            dp[i][0]=grid[i][0]+dp[i-1][0];

        }

        for(int j=1;j<grid[0].length;j++){
            dp[0][j]=dp[0][j-1]+grid[0][j];

        }


        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){

                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];


            }
        }

        return dp[grid.length-1][grid[0].length-1];

        
        
    }
}