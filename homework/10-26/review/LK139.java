/**
 * start: 15:30
 * end: 15:46
 * 独立完成
 * n:s.length()
 * m:maxLen
 * num: wordDicts.length
 * 时间: O(mn)
 * 空间：O(n+m*num)
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;

        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            //wordDict.add(str);
            maxLen=Math.max(maxLen,str.length());
            set.add(str);
        }

        boolean []dp=new boolean[s.length()+1];
        

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int len=1;len<=maxLen;len++){

                // i-1 i-1-len-1  

                if(i-len>=0 && set.contains(s.substring(i-len,i))){//

                    dp[i]=dp[i]|| dp[i-len];

                }



            }
        }


        

        return dp[s.length()];
        
    }
}