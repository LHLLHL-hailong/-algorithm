/**
 * 难度一般，就是下面这个步骤容易落
 */
class Solution {
    public void moveZeroes(int[] nums) {

        int j=0;

        for(int i=0;i<nums.length;i++){
            nums[j]=nums[i];
            if(nums[j]!=0){
                j++;
            }

        }

        while(j<nums.length){// 千万别忘了这步
            nums[j]=0;
            j++;
        }

        
    }
}