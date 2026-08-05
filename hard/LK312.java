class Solution {

      public static void print2D(int[][] arr) {
      for (int[] row : arr) {
          System.out.println(Arrays.toString(row));
      }
  }


    public int maxCoins(int[] nums) {

        int[]copy=new int[nums.length+2];

        copy[0]=1;

        for(int i=0;i<nums.length;i++){
            copy[i+1]=nums[i];
        }
        copy[copy.length-1]=1;

        int[][]dp=new int[copy.length][copy.length];

        for(int i=copy.length-1;i>=0;i--){
            for(int j=i+1;j<copy.length;j++){//是存在顺序性的，别忘了j要从i+1开始
                for(int k=i+1;k<j;k++){ 
                   dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+copy[i]*copy[j]*copy[k]);
                }

            }

        }

      //  print2D(dp);

        return dp[0][copy.length-1];//从0开始

    }
}

/*

##review
虽然最终是写对了，但是写的过程中差点记错了他的状态转移方法
综合评价:C+(还是需要再复习一些)
 
 */