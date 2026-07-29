/**
 * 这道题别看代码简单，但是其实这个状态转移特别难想，如果不背下来，基本上考上就废
 */
class Solution {


    public int maxCoins(int[] nums) {



        int[] copy=new int[nums.length+2];

        copy[0]=1;

        for(int i=0;i<nums.length;i++){
            copy[i+1]=nums[i];
        }

        copy[copy.length-1]=1;

        int [][]dp=new int[copy.length][copy.length];



        for(int i=dp.length-1; i>=0;i--){
            for(int j=i+2;j<dp[i].length;j++){
                for(int k=i+1;k<j;k++){//(i,j) 中最后一个删除的位置是k，遍历k得到dp[i][j] 在(i,j)中的最值
                    dp[i][j]=Math.max(dp[i][k]+dp[k][j]+copy[i]*copy[k]*copy[j],dp[i][j]);
                }
            }

        }

      //  print2D(dp);

        return dp[0][copy.length-1];

    }
}
