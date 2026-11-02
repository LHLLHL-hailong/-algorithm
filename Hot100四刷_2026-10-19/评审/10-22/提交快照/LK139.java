/**
 * start: 8:12
 * end: 8:46
 * 问题答案：独立完成
 */
/**
 * start: 8:12
 */
class Solution {

    void print2(boolean[][]arr){

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }

         System.out.println("-----------------------------");

        

    }


    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[][]dp=new boolean[s.length()+1][wordDict.size()+1];

        // i-start+1=now.length()
        //start=i+1-now.length()

        dp[0][0]=true;

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=true;
        }

        int count=0;



        for(int i=1;i<=s.length();i++){

            for(int j=1;j<=wordDict.size();j++){

                String now=wordDict.get(j-1);
                if(i-now.length()>=0 &&  s.substring(i-now.length(),i).equals(now)){

                    dp[i][j]=(dp[i][j-1] || dp[i-now.length()][j]);
                }else{
                    dp[i][j]=dp[i][j-1];
                }

              // print2(dp);



            }
        }

        return dp[s.length()][wordDict.size()];

        
    }
}

/**
 * start:21:22
 * end: 21:31
 * 看完答案之后做出
 * 时间：O(mn)
 * 空间：O(mn)
 * m:s.length
 * n:maxLen
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;
        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            set.add(str);
            maxLen=Math.max(maxLen,str.length());
        }

        boolean[]dp=new boolean[s.length()+1];

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=maxLen && i-j>=0;j++){
                //start  i-1   i-1-stat+1=j
                //start=i-j   i
                if(set.contains(s.substring(i-j,i)) && dp[i-j] ){
                    dp[i]=true;
                }

            }
        }

        return dp[s.length()];
        
    }
}