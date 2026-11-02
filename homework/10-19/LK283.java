/**
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Solution {
    public void moveZeroes(int[] nums) {

        int pre=0;

        for(int i=0;i<nums.length;i++){

            if(nums[i]!=0){
                nums[pre]=nums[i];
                pre++;
            }

        }

        for(int j=pre;j<nums.length;j++){
            nums[j]=0;
        }
        
    }
}