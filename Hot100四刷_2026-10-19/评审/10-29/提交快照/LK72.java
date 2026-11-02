/**
 * start:12:00
 * end: 12:07
 * 独立完成
 * 时间：O(mn)
 * 空间：O(mn)
 */
class Solution {
    public int minDistance(String word1, String word2) {

        char[]sc1=word1.toCharArray();

        char[]sc2=word2.toCharArray();

        int[][]dp=new int[sc1.length+1][sc2.length+1];


        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=j;

        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                if(sc1[i-1]==sc2[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+1,Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }

        return dp[sc1.length][sc2.length];
        
    }
}