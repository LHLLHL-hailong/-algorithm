/**
 * 这道题就很简单了
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {

        int l=0;
        int r=nums.length-1;

        while(l<=r){

            int mid=(l+r)/2;

            if(nums[mid] > target){
                r=mid-1;
            }else if (nums[mid] < target){
                l=mid+1;
            }else{
                int l1=mid;
                int r1=mid;
                while(l1-1>=0 && nums[l1-1] == target){
                    l1--;
                }

                while(r1+1<nums.length && nums[r1+1] == target){
                    r1++;
                }
                return new int[]{l1,r1};

            }


        }

        return new int[]{-1,-1};
        
    }
}