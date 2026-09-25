class Solution {
    public int longestOnes(int[] nums, int k) {

        int res=0;
        int l=-1;
        for(int r=0;r<nums.length;r++){
            if(nums[r]==0){
                while(k<=0 && l<r-1){
                    l++;
                    if(nums[l]==0){
                        k++;
                    }
                }

                if(k>0){
                    k--;
                }else{
                    l=r;
                }
            }

            res=Math.max(res,r-l);
            
        }

        return res;
        
    }
}