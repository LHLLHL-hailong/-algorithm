/**
 * start: 19:11
 * end: 19:17
 * 独立完成
 * 时间： O(log(n))
 * 空间： O(1)
 */
class Solution {
    public int search(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;
        int k=nums[0];

        while(left<=right){
            int mid=(left+right)/2;

            if(target>=k && nums[mid]<k){
                right=mid-1;
            }else if(target<k && nums[mid]>=k){
                left=mid+1;
            }else if(target>nums[mid]){
                left=mid+1;
            }else if(target<nums[mid]){
                right=mid-1;
            }else{
                return mid;
            }
        }

        return -1;
        
    }
}