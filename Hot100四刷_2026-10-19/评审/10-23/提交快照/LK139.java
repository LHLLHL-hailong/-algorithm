/**
 * start: 18:25
 * end : 18:29
 * 独立完成
 * 时间O(nm)
 * 空间O(n+k)
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;
        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            set.add(str);
            maxLen=Math.max(maxLen,str.length());
        }

        boolean []dp=new boolean[s.length()+1];

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int len=1;len<=maxLen&& len<=i;len++){
                // start 有
                // i 无
                //i-start=len
                //start=i-len
                //len<=i
                if(set.contains(s.substring(i-len,i))){
                    dp[i]=dp[i]|dp[i-len];
                }
            }

        }

        return dp[s.length()];
        
    }
}