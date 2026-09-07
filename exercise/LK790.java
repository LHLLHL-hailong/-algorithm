class Solution {
    public int numTilings(int n) {

        int base=1000000007;

        if(n<=1) return n;

        if(n==2) return 2;
        long[][]dp=new long[n][3];

        dp[0][2]=1;
        dp[1][0]=1;
        dp[1][1]=1;
        dp[1][2]=2;

        for(int i=2;i<n;i++){
            dp[i][0]=(dp[i-2][2]+dp[i-1][1])%base;
            dp[i][1]=(dp[i-2][2]+dp[i-1][0])%base;
            dp[i][2]=(dp[i-1][2]+dp[i-1][0]+dp[i-1][1]+dp[i-2][2])%base;//注意一旦涉及到结果取模，可能就警醒超过int范围，光取模不够，可能在运算的时候就溢出了
        }

        return (int)(dp[n-1][2]%base);


        
    }
}

//dp[i][0]: 上突
//dp[i][1]: 下突
//dp[i][2]: 平滑


