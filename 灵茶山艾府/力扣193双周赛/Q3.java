class Solution {
    public int resilientSubarray(int[] nums, int k) {

        for(int i=0;i<nums.length;i++){

            
            nums[i]=nums[i]%k;
        }

        int len=0;
        int total=0;


        int res=1;

        for(int i=0;i<nums.length;i++){
            if(i!=0 && nums[i]!=nums[i-1]){
                len=1;
                total=nums[i];
            }else{
                len++;
                total+=nums[i];
            }

            total%=k;
            if(total==0 && i+1<nums.length && nums[i+1]==nums[i]){

                res=Math.max(res,len+1);
                
            }
        }

        return res;


        
    }
}