/**
 * 这个就是纯粹的背了
 */
class Solution {
    public int numSquares(int n) {

        if(isSquery(n)){
            return 1;
        }

        if(isFour(n)){
            return 4;
        }

        for(int i=1;i*i<n;i++){
            if(isSquery(n-i*i)){
                return 2;
            }

        }

        return 3;

    }

    boolean isSquery(int n){

        int cnt=(int)Math.sqrt(n);

        return cnt*cnt==n;
    }


    boolean isFour(int x){

        while(x%4==0){
            x/=4;
        }
        return  x%8==7;


    }
}

//@https://leetcode.cn/problems/perfect-squares/solutions/822940/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/?envType=problem-list-v2&envId=2cktkvj
/**
 * 这个解法很笨,很慢
 */
class Solution {
    public int numSquares(int n) {

        int maxNum=(int)Math.sqrt(n)  +1;  //fixme: 可能会偏小，需要考虑边界

        int[][]dp=new int[maxNum+1][n+1]; 


        //dp[i][0]=0
        //dp[0][j(j不等于0)]=MAX
        
        for(int j=1;j<=n;j++){
            dp[0][j]=Integer.MAX_VALUE/2;
        }


        for(int i=1;i<=maxNum;i++){
            for(int j=1;j<=n;j++){

                if(j<i*i){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.min(dp[i][j-i*i]+1,dp[i-1][j]);
                }
                
            }
        }

        return dp[maxNum][n];
        
    }
}