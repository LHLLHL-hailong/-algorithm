/**
 * strat: 10:56
 * end: 11:05
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {


    void swap(int[]nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    void reverse(int[]nums,int l,int r){

        for(int i=l;i<=(l+r)/2;i++){
            swap(nums,i,r+l-i);
        }
    }


    public void nextPermutation(int[] nums) {

        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                for(int j=nums.length-1;j>i;j--){
                    if(nums[j]>nums[i]){

                        swap(nums,i,j);
                        //i之后需要反转
                        reverse(nums,i+1,nums.length-1);
                        return ;
                    }
                }
            }
        }

        reverse(nums,0,nums.length-1);
        

        
    }
}