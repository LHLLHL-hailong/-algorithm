class Solution {

    public void moveZeroes(int[] nums) {

        int ptr=0;

        for(int i=0;i<nums.length;i++){
            nums[ptr]=nums[i];
            if(nums[ptr]!=0){
                ptr++;
            }
        }

        for(int j=ptr;j<nums.length;j++){
            nums[j]=0;
        }
        
    }
}