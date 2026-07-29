
/**
 * 这道题的难点在于在动态规划的状态转移中，使用递归的思路来进行状态转移
 */

class Solution {






    public boolean isMatch(String s, String p) {

        char[] sc=s.toCharArray();


        char[] pc=p.toCharArray();


        boolean [][]dp=new boolean[s.length()+1][p.length()+1];


        dp[0][0]=true;

        for(int i=1;i<pc.length;i+=2){

            if(pc[i]=='*'){

                dp[0][i+1]=true;

            }else{
                break;
            }

        }

        for(int i=1;i<=s.length();i++){

            for(int j=1;j<=p.length();j++){

                        if(pc[j-1]=='.'){
            dp[i][j]=dp[i-1][j-1];
        }else if(pc[j-1]=='*'){


            if(sc[i-1]==pc[j-2] || pc[j-2]=='.'){

                dp[i][j]=dp[i][j-2] || dp[i-1][j];
               
            }else{

                 dp[i][j]=dp[i][j-2];
                

            }


            



        }else{
            if(sc[i-1]==pc[j-1]){
                dp[i][j]=dp[i-1][j-1];
            }else{
                dp[i][j]=false;
            }
        }




            }
        }

       // print2DBooleanArray(dp);





        return dp[s.length()][p.length()];


    }



}