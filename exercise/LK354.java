/**
 * 这个方法会超时
 */
// class Solution {
//     public int maxEnvelopes(int[][] envelopes) {

//         if(envelopes.length<0) return 0;


//         int[]dp=new int[envelopes.length];

//         Arrays.sort(envelopes,(a,b)->{
//             if(a[0]!=b[0]){
//                 return a[0]-b[0];
//             }else{
//                 return a[1]-b[1];
//             }
//         });

//         int maxLen=1;

//         for(int i=0;i<envelopes.length;i++){
//             dp[i]=1;
//         }

       

//         for(int i=1;i<envelopes.length;i++){
//         for(int j=0;j<i;j++){
//             if(envelopes[i][0]>envelopes[j][0] && envelopes[i][1]>envelopes[j][1]){
//                 dp[i]=Math.max(dp[i],dp[j]+1);
//             }

//             maxLen=Math.max(dp[i],maxLen);
//         }

//         }

//         return maxLen;

        
//     }
// }

class Solution {
    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes.length<0) return 0;


        int[]dp=new int[envelopes.length];

        Arrays.sort(envelopes,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }else{
                return b[1]-a[1];
            }
        });//通过这种排序,把二维的问题变成一维的连续最长子序列

        int[]len=new int[envelopes.length];
        int ptr=0;
        len[ptr++]=envelopes[0][1];

        for(int i=1;i<envelopes.length;i++){
            if(envelopes[i][1]>len[ptr-1]){
                len[ptr++]=envelopes[i][1];
            }else{
                int left=0;
                int right=ptr-1;
                int piviot=-1;
                while(left<=right){
                    int mid=(left+right)/2;

                    if(envelopes[i][1]>len[mid]){
                        left=mid+1;
                    }else{
                        right=mid-1;
                        piviot=mid;
                    }


                }

                len[piviot]=envelopes[i][1];
            }
        }

        return ptr;
        
    }
}