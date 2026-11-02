/**
 * start: 19:17
 * end:19:28
 * 独立完成
 * 时间：O(log(n))
 * 空间：O(1)
 * 
 */
/**
 * tip：又改限制了，这个只有左右边界用二分才能ac,找到一个后两边扫描不再被允许
 */
class Q34Submitted {
    public int[] searchRange(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;
        int l1=-1;
        int r1=-1

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{  
                right=mid-1;
                l1=mid;
                
            }

        }

        left=0;
        right=nums.length-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{
                left=mid+1;
                r1=mid;
                
            }

        }

        return new int[]{l1,r1};
        
    }
}