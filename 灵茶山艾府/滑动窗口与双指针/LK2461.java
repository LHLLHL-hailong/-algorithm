class Solution {
    public long maximumSubarraySum(int[] nums, int k) {


        int maxV=0;
        for(int num:nums){
            maxV=Math.max(maxV,num);
        }

        int []count=new int[maxV+1];

        int diff=0;

        long res=0L;

        long base=0L;

        for(int i=0;i<k;i++){

            base+=nums[i];
            if(++count[nums[i]]==2){
                diff++;
            }

        }

        if(diff==0){
            res=base;
        }

        for(int i=k;i<nums.length;i++){
            base+=nums[i];
            base-=nums[i-k];

            if(++count[nums[i]]==2) diff++;
            if(--count[nums[i-k]]==1) diff--;

            if(diff==0) res=Math.max(res,base);

        }

        return res;
        
    }
}