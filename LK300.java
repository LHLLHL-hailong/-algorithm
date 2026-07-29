/**
 * 这个需要好好背一下，很难理解，且已经有面试考过了
 */
class Solution {
    public int lengthOfLIS(int[] nums) {

        if(nums.length==0) return 0;

        int[]d=new int[nums.length+1];//d[i] 长度为I的序列最小递增末尾值是d[i]


        int len=1;

        d[len]=nums[0];

        for(int i=1;i<nums.length;i++){

            if(nums[i]>d[len]){
                d[++len]=nums[i];
            }else{

                int pos=0;

                int l=1;
                int r=len;

                while(l<=r){
                    int mid=(l+r)/2;

                    if(d[mid]>=nums[i]){
                        r=mid-1;

                    }else{
                        pos=mid;
                        l=mid+1;
                    }

                }

                d[pos+1]=nums[i];

            }
        }

        return len;

    }
}


/**
 * 常规的dp的解法，但是O(n^2) 的时间复杂度
 */
class Solution {
    public int lengthOfLIS(int[] nums) {

        if(nums.length<=1) return nums.length;


        int[]dp=new int[dp.length];

        for(int i=0;i<dp.length;i++){
            dp[i]=1;

        }

        int maxLen=1;



        for(int i=1;i<dp.length;i++){
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }

            }

            maxLen=Math.max(maxLen,dp[i]);

        }

        return maxLen;
        
    }
}