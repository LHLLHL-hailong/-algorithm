/**
 * start: 11:06
 * end: 11:14
 * 独立完成
 * 时间：log(n)
 * 空间 O(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(nums[mid]>=nums[0] && target<nums[0]){
                left=mid+1;
            }else if(nums[mid]<nums[0] && target>=nums[0]){
                right=mid-1;

            }else if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                return mid;
            }
        }

        return -1;
        
    }
}