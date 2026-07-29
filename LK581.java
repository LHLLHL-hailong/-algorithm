/**
 * 重点记忆这个方法（这个思路很难想）
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int minValue=Integer.MAX_VALUE;
        int maxValue=Integer.MIN_VALUE;

        int left=-1;
        int right=-1;

        for(int i=0;i<nums.length;i++){

            if(nums[i]<maxValue){
                right=i;
            }else{
                maxValue=nums[i];
            }

            int j=nums.length-i-1;

            if( nums[j]>minValue){
                left=j;
            }else{
                minValue=nums[j];
            }

        }

        if(right==-1){
            
            return 0;
        }else{
            return right-left+1;
        }

    }
}


class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int l=0;
        int r=nums.length-1;

        for(int i=1;i<nums.length;i++){

            if(nums[i]>=nums[l]){
                if(i==l+1){
                    l++;
                }
            }else{
                while(l!=-1 && nums[i] < nums[l]){
                    l--;
                }
            }

            int j=nums.length-1-i;

            if(nums[j]<=nums[r]){
                if(r==j+1){
                    r--;
                }

            }else{

                while(r!=nums.length && nums[r]<nums[j]){
                    r++;
                }

            }

        }

        return r-l;

    }
}



/**
 * 简单写法
 */
// class Solution {
//     public int findUnsortedSubarray(int[] nums) {
        
//     }
// }

class Solution {
    public int findUnsortedSubarray(int[] nums) {

        if(nums.length==1) return 0;

        int l=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>=nums[l]){
                l++;    
            }else{
                break;
            }
        }

        if(l==nums.length-1) return 0;

        int now=l+1;

        for(int i=now;i<nums.length;i++){
            while(l!=-1 && nums[l]>nums[i]){
                l--;
            }

        }

        int r=nums.length-1;

        for(int i =nums.length-2;i>=0;i--){
            if(nums[i]<=nums[r]){
                r--;
            }else{
                break;
            }

        }

        now=r-1;

        for(int i=now;i>=0;i--){
            while(r!=nums.length && nums[r]<nums[i]){
                r++;
            }

        }

        return r-l-1;
        
        
    }
}