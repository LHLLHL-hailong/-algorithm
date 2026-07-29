/**
 * 状态压缩版本
 */
class Solution {

    public int coinChange(int[] coins, int amount) {





        int[]dp=new int[amount+1];

        // for(int i=0;i<dp.length;i++){
        //     for(int j=1;j<dp[i].length;j++){
        //         dp[i][j]=Integer.MAX_VALUE/2;

        //     }
        // }

       // dp[0][0]=0;

       for(int j=1;j<=amount;j++){
        dp[j]=Integer.MAX_VALUE/2;

       }



        for(int i=1;i<=coins.length;i++){
            for(int j=coins[i-1];j<=amount;j++){//这里可以从这个进行初始，便利的原因是不满足这个的，不必要更新，维持原状就好

                    
                    dp[j]=Math.min(dp[j],dp[j-coins[i-1]]+1);
                
            }
        }

      //  print2DArray(dp);

        return dp[amount]==Integer.MAX_VALUE/2?-1:dp[amount];


        
    }
}

/**
 * 正常解法
 */
class Solution {

    public int coinChange(int[] coins, int amount) {





        int[][]dp=new int[coins.length+1][amount+1];

        // for(int i=0;i<dp.length;i++){
        //     for(int j=1;j<dp[i].length;j++){
        //         dp[i][j]=Integer.MAX_VALUE/2;

        //     }
        // }

       // dp[0][0]=0;

       for(int j=1;j<=amount;j++){
        dp[0][j]=Integer.MAX_VALUE/2;

       }



        for(int i=1;i<=coins.length;i++){
            for(int j=1;j<=amount;j++){//二维的这个必须从一开始的原因是，哪怕不满足那个条件（j>=coins[i-1]），它也可以进行更新（ dp[i][j]=dp[i-1][j]）

                if(j>=coins[i-1]){
                    
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

      //  print2DArray(dp);

        return dp[coins.length][amount]==Integer.MAX_VALUE/2?-1:dp[coins.length][amount];


        
    }
}

// class Solution {
//     public int coinChange(int[] coins, int amount) {
        
//     }
// }