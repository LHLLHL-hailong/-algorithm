/**
 * 这个题单独在于动态规划的设置和
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        int maxLen=0;

        HashSet<String>set=new HashSet<>();

        for(String now:wordDict){
            maxLen=Math.max(maxLen,now.length());
            set.add(now);
        }

        //dp[i]  0----i-1 是不是匹配

        

        boolean []dp=new boolean[s.length()+1];
// i)  start-i==len   start=len+i    len<=maxLen len<=i

        dp[0]=true;


        for(int i=1;i<=s.length();i++){
                    //i-1-len 
                 for(int len=1;len<=i && len<=maxLen;len++){
                if( dp[i-len] && set.contains(s.substring(i-len,i))){
                    dp[i]=true;
                    }
                 }

        }


        return dp[s.length()];



        
        
    }
}