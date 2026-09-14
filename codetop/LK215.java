class Solution {


    void swap(int[]nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }


    public int findKthLargest(int[] nums, int k) {

        int tarIdx=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){

            int piviot=(left+right)/2;

            swap(nums,piviot,right);

            int ptr=left;

            int count=0;

            for(int i=left;i<right;i++){

                if(nums[i]<nums[right]){
                    swap(nums,i,ptr);
                    ptr++;
                }else if(nums[i]==nums[right]){

                    if(++count%2==0){
                    swap(nums,i,ptr);
                    ptr++;              
                    }
                    
                }

            }

            swap(nums,ptr,right);

            if(ptr==tarIdx){
                return nums[ptr];
            }else if(ptr<tarIdx){
                left=ptr+1;

            }else{
                right=ptr-1;
            }


        }

        return -1;
    }
}