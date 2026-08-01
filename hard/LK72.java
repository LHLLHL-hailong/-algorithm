class Solution {

    public int minDistance(String word1, String word2) {

        int[][]dp=new int[word1.length()+1][word2.length()+1];

        char[] wc1=word1.toCharArray();
        char[] wc2=word2.toCharArray();

        for(int i=1;i<=word1.length();i++){
            dp[i][0]=i;
        }

        for(int j=1;j<=word2.length();j++){
            dp[0][j]=j;

        }


        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(wc1[i-1]==wc2[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                  //  System.out.println("in");
                    dp[i][j]=Math.min(dp[i][j-1]+1,Math.min(dp[i-1][j]+1,dp[i-1][j-1]+1));
                }

            }

        }

        return dp[word1.length()][word2.length()];
        
    }
}

class Solution {
    public int minDistance(String word1, String word2) {

        int[]dp=new int[word2.length()+1];

        char[] wc1=word1.toCharArray();
        char[] wc2=word2.toCharArray();


        for(int j=1;j<=word2.length();j++){
            dp[j]=j;

        }

        for(int i=1;i<=word1.length();i++){
            int pre=dp[0];
            dp[0]=i;
            for(int j=1;j<=word2.length();j++){
                int tmp=dp[j];
                if(wc1[i-1]==wc2[j-1]){
                    dp[j]=pre;
                }else{
                    dp[j]=Math.min(dp[j-1]+1,Math.min(dp[j]+1,pre+1));
                }
                pre=tmp;

            }

        }

        return dp[word2.length()];
        
    }
}

/*
##review

除了有一处误写了以外，没有其他的问题
综合评价 A-(不用参与下次复习)

 */