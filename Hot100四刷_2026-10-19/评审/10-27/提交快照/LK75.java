/**
 * start:18:35
 * end: 18:44
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Solution {

    void swap(int[]nums,int from,int to){

        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }


    public void sortColors(int[] nums) {

        //0 1 2

        int ptr0=-1;
        int ptr2=nums.length;

        for(int i=0;i<ptr2;i++){
            while(nums[i]!=1 && i!=ptr0 && i<ptr2){


                if(nums[i]==0){
                    swap(nums,i,++ptr0);

                }else if(nums[i]==2){
                    swap(nums,i,--ptr2);
                }



            }
        }
        
    }
}