/** 
 * 这道题难度一般
*/
class Solution {
    public int search(int[] nums, int target) {
        int kvalue=nums[0];

        int l=0;
        int r=nums.length-1;

        while(l<=r){
            int mid=(l+r)/2;

            if(target>=kvalue && nums[mid]<kvalue ){
                r=mid-1;
            }else if(target < kvalue && nums[mid]>=kvalue ){
                l=mid+1;

            }else if(target >nums[mid]){
                l=mid+1;
            }else if(target < nums[ mid]){
                r=mid-1;
            }else{
                return mid;
            }

        } 

        return -1;       
    }
}