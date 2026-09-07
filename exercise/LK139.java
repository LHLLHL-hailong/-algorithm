class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        boolean []dp=new boolean[s.length()+1];

        HashSet<String>set=new HashSet<>();

        for(String res:wordDict){
            set.add(res);
        }

        dp[0]=true;
        
        for(int i=1;i<=s.length();i++){
            for(int j=i;j>=1;j--){
                if(set.contains(s.substring(j-1,i)) && dp[j-1]){
                    dp[i]=true;
                    break;
                }

            }

            //System.out.println(Arrays.toString(dp));
        }

        return dp[s.length()];
    }
}

/*

1. 忘记了初始化
 */