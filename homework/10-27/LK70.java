/**
 * start:18:44
 * end: 18:47
 * 独立完成
 * 时间:O(n)
 * 空间:O(n)
 */
class Solution {
    public int climbStairs(int n) {

        if(n<=2) return n;

        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;

        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }

        return dp[n];
        
    }
}