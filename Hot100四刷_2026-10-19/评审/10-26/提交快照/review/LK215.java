
/**
 * start: 15:50
 * end: 16:07
 * 独立完成
 * 时间:O(log(n))
 * 空间:O(1)
 */
class Solution {


    void swap(int[] nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int search(int[]nums,int left,int right){
        
      //  System.out.println(left);
      //  System.out.println(right);

        int piviot=left+new Random().nextInt(right-left+1);

        swap(nums,piviot,right);

        int lptr=left;
        int count=0;

        for(int i=left;i<right;i++){
            if(nums[i]<nums[right]){
                swap(nums,i,lptr);
                lptr++;

            }else if(nums[i]==nums[right]){

                if(count++%2==0){
                    swap(nums,i,lptr);
                    lptr++;
                }

            }
        }

        swap(nums,lptr,right);

        return lptr;

    }


    public int findKthLargest(int[] nums, int k) {

        int kk=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=search(nums,left,right);

            if(kk<mid){
                right=mid-1;
            }else if(kk>mid){
                left=mid+1;
            }else{
                return nums[mid];
            }
        }

        return -1;
        
    }
}