/**
 * start: 19:02
 * end: 19:04
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {
    public boolean canJump(int[] nums) {


        int max=0;

        for(int i=0;i<=max && i<nums.length ;i++ ){

            max=Math.max(max,i+nums[i]);

            if(i==nums.length-1){
                return true;
            }
        }

        return false;

        
        
    }
}