class Solution {

    public int maximalSquare(char[][] matrix) {

        int[][]dp=new int[matrix.length][matrix[0].length];

        int maxLen=0;

        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0]=='1'){
                dp[i][0]=1;
                maxLen=1;

            }
        }

        for(int i=0;i<matrix[0].length;i++){
            if(matrix[0][i]=='1'){
                dp[0][i]=1;
                maxLen=1;

            }
        }

        

        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){

        if(matrix[i][j]=='1'){
            int len=Math.min(dp[i-1][j],dp[i][j-1]);
            if(matrix[i-len][j-len]=='1'){
                dp[i][j]=len+1;  // 这种状态转移虽然正确，但是不太标准
            }else{
                dp[i][j]=len;
            }

            maxLen=Math.max(maxLen,dp[i][j]);
        }

            }
        }

        return maxLen*maxLen;


    }
}


class Solution {

    public int maximalSquare(char[][] matrix) {


        int [][]dp=new int [matrix.length][matrix[0].length];
        int maxSize=0;

        for(int i=0;i<matrix.length;i++){

            dp[i][0]=matrix[i][0]-'0';
            maxSize=Math.max(maxSize, dp[i][0]);

        }

        for(int j=0;j<matrix[0].length;j++){
            dp[0][j]=matrix[0][j]-'0';
            maxSize=Math.max(maxSize,dp[0][j]);
        }


        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;// 这个状态转移更标准一些
                    maxSize=Math.max(dp[i][j],maxSize);
                }else{
                    dp[i][j]=0;
                }
            }
        }


        return maxSize*maxSize;
      
        
    }
}