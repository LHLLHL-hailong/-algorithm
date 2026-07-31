class Solution {
    public boolean isMatch(String s, String p) {

        char[]sc=s.toCharArray();
        char[]pc=p.toCharArray();

        boolean[][]dp=new boolean[s.length()+1][p.length()+1];
        //dp[i][j]<-->sc[i-1]==pc[j-1] 以及之前

        dp[0][0]=true;

        for(int j=2;j<=p.length();j=j+2){
            if(pc[j-1]=='*'){
                dp[0][j]=true;
            }else{
                break;
            }
        }



        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){

                
        if(pc[j-1]=='*'){

            if(sc[i-1]==pc[j-2] ||  pc[j-2]=='.'){
               dp[i][j]=dp[i][j-2] || dp[i-1][j];
            }else{
                dp[i][j]=dp[i][j-2];//单独设置出口
            }
        }else{
            if(pc[j-1]=='.' || sc[i-1]==pc[j-1]){
                dp[i][j]=dp[i-1][j-1];
            }
        }
            }
        }

        return dp[s.length()][p.length()];




        
    }
}

/*

##know:
1. 在DP中进行递归，也可以设置条件，不过要单独设置出口
2. 向后状态转移的影响，一律可以改为上前，因为如果考虑向后的影响，后边向前也可以做到。而且这样也可以保证状态转移能进行下去。
*/ 

/*

##review 

1. 首先，这道题的整体的解题思路记得不熟
2. 其次，在dp的递归步骤过程中，忘记了能不能递归和递归条件
3. 综合评价：C-(需要再次复习,重写)

*/


  class Solution {

      public static void print(boolean[] arr) {
          System.out.println(java.util.Arrays.toString(arr));
      }

      public boolean isMatch(String s, String p) {

          char[] sc = s.toCharArray();
          char[] pc = p.toCharArray();

          boolean[] dp = new boolean[p.length() + 1];

          dp[0] = true;

          for (int j = 2; j <= p.length(); j = j + 2) {
              if (pc[j - 1] == '*') {
                  dp[j] = true;
              } else {
                  break;
              }
          }

          for (int i = 1; i <= s.length(); i++) {
              boolean pre = dp[0];
              dp[0] = false;
              for (int j = 1; j <= p.length(); j++) {
                  boolean tmp = dp[j];
                  if (pc[j - 1] == '*') {
                      if (sc[i - 1] == pc[j - 2] || pc[j - 2] == '.') {
                          dp[j] = dp[j - 2] || dp[j];
                      } else {
                          dp[j] = dp[j - 2]; // 单独设置出口
                      }
                  } else {
                      if (pc[j - 1] == '.' || sc[i - 1] == pc[j - 1]) {
                          dp[j] = pre;
                      } else {
                          dp[j] = false;//因为之前默认初始化都是false，所以我本质上来说，之前这里是一个省略的步骤，而这此时我进行了状态压缩，省略的步骤必须重新展开，否则它会延续上一次的状态
                      }
                  }
                  pre = tmp;
              }
          }

          return dp[p.length()];
      }
  }

/*
##konw
状态压缩时省略的步骤需要展开
 */