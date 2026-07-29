/**
 * 难度很低。但是要记住这个零也就是空的情况下，也算一种，而不能算零种，因为这个涉及到后边的状态转移
 */
class Solution {
    public int numTrees(int n) {
        

        //dp[i]-->  我有i个节点，那么有多少种二叉搜索树

        // j 0----i-1
        //   i-1   0

        if(n<=1) return  1;

        int[]dp=new int[n+1];

        dp[0]=1;
        dp[1]=1;


        for(int i=2;i<=n;i++){
            for(int j=0;j<=i-1;j++){
            dp[i]+=dp[j]*dp[i-1-j];
           }
        }

        //System.out.println(dp[2]);

        return dp[n];


    }
}