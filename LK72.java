
/**
 * 状态压缩解法
 */
class Solution {
    public int minDistance(String word1, String word2) {

        int[]dp=new int[word2.length()+1];

        char[] c1=word1.toCharArray();
        char[] c2=word2.toCharArray();

        // for(int i=1;i<dp.length;i++){
        //     dp[i][0]=i;

        // }

        for(int j=1;j<dp.length;j++){
            dp[j]=j;
        }

        dp[0]=0;



        for(int i=1;i<=word1.length();i++){
            int cache=i-1;
            dp[0]=i;//状态压缩的时候，别忘了每次动态的更新没有被保留的边界，且这个边界是那种不会被动态规划中所更新，但是是被使用到的。而这种边界又需要动态的更新，因为数组压缩了
            for(int j=1;j<=word2.length();j++){
                int tmp=dp[j];

         if(c1[i-1]==c2[j-1]){
            dp[j]=cache;
        }else{
            dp[j]=Math.min(cache+1,Math.min(dp[j]+1,dp[j-1]+1));
        }

        //dp[i][j]=Math.min(dp[i][j],Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1)));
                cache=tmp;

        

            }
        }
         return dp[word2.length()];

    }
}


















/**
 * 正常的动态规划解法
 */

class Solution {
    public int minDistance(String word1, String word2) {

        int[][]dp=new int[word1.length()+1][word2.length()+1];

        char[] c1=word1.toCharArray();
        char[] c2=word2.toCharArray();

        for(int i=1;i<dp.length;i++){
            dp[i][0]=i;

        }

        for(int j=1;j<dp[0].length;j++){
            dp[0][j]=j;
        }

        dp[0][0]=0;



        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){

         if(c1[i-1]==c2[j-1]){
            dp[i][j]=dp[i-1][j-1];
        }else{
            dp[i][j]=Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
        }

        //dp[i][j]=Math.min(dp[i][j],Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1)));


        

            }
        }
         return dp[word1.length()][word2.length()];

    }
}