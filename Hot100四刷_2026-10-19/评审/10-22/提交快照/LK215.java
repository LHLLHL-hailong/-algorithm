/**
 * start: 21:04
 * end: 21:13
 * 独立完成
 * 时间: 最坏（N方） 最好：O(1) 
 * 空间：O(1)
 */
class Solution {

    void swap(int[]nums,int from ,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int search(int[]nums,int left,int right ){

        int piviot=nums[left];
        swap(nums,left,right);

        int ptr=left;

        int count=0;

        for(int i=left;i<right;i++){

            if(nums[i]<piviot){
                swap(nums,i,ptr);
                ptr++;

            }else if(nums[i]==piviot){

                if(++count%2==0){
                    swap(nums,i,ptr);
                    ptr++;
                }
                
            }

        }

        swap(nums,ptr,right);

        return ptr;

    }


    public int findKthLargest(int[] nums, int k) {

        int target=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int idx=search(nums,left,right);

            if(idx<target){
                left=idx+1;
            }else if(idx >target){
                right=idx-1;
            }else{
                return nums[idx];
            }
        }

        return -1;

        
        
    }
}