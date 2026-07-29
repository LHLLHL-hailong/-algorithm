
/**
 * 难度一般
 */
class Solution {
    public int[] countBits(int n) {
        int[]res=new int[n+1];

        for(int i=0;i<=n;i++){
            int now=i;
            int count=0;
            while(now!=0){
                count+=now&1;// 这样算而不是判断快点

                now>>=1;

            }
            res[i]=count;
        }

        return res;
    }
}

// class Solution {
//     public int[] countBits(int n) {
        
//     }
// }