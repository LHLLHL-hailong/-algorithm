/**
 * 还是好好复习一下吧，我这个要是一开始忘了，现在都不一定能写出来
 */
class Solution {


//     public static void print2DArray(int[][] arr) {
//     for (int[] row : arr) {
//         for (int num : row) {
//             System.out.print(num + " ");
//         }
//         System.out.println();
//     }
// }


    public int maxProfit(int[] prices) {

        if(prices.length<=1) return 0;

        int[][]dp=new int[prices.length][2];

        //dp[i][0] 未持有股票
        //dp[i][1] 持有股票

        dp[0][0]=0;
        
        dp[0][1]=-prices[0];

        dp[1][0]=Math.max(0,dp[0][1]+prices[1]);
        dp[1][1]=Math.max(-prices[0],-prices[1]);

        for(int i=2;i<prices.length;i++){

            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
        }

        //print2DArray(dp);

        return dp[prices.length-1][0];

        
    }
}