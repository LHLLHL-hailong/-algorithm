/**
 * 最小栈-->找第K大的书，但是我需要最小栈，因为每一次超过数量的时候是从头开始揪的，那么每一次都揪小的，剩下的就是大的
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer>queue=new PriorityQueue<>((a,b)->a-b);

        for(int now:nums){
            queue.add(now);
            if(queue.size()>k){
                queue.remove();
            }
        }

        return queue.remove();

        
    }
    
}


/**
 * 快速排序的解法
 */
class Solution {


    void swap(int[] nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;

    }


     int partition(int[] nums, int left, int right) {//返回的是数值的下标，这个下标是正确排序后的下标
        int point=nums[(left+right)/2];

        swap(nums,right,(left+right)/2 );

        int ptrl=left;

        int count=0;

        for(int i=left;i<right;i++){
            if(nums[i]<point){
                swap(nums,i,ptrl);
                ptrl++;
            }else if(nums[i]==point){
                count++;
                if(count%2==0){   // 这个优化还是很重要的，就是相等的时候，让他们的左右均分,防止由于大量的相等元素而降级到O(n^2)
                swap(nums,i,ptrl);
                ptrl++;
                }

            }

        }

        swap(nums,ptrl,right);

        return ptrl;
        
     }



    public int findKthLargest(int[] nums, int k) {

        int kk=nums.length-k;//由转化为第几大的数变成转化为排序后下标为几的数

        int left=0;
        int right=nums.length-1;

        while(left<=right){ //标准的二分查找

            int cnt=partition(nums,left,right);
            if(cnt==kk){
                return nums[cnt];
            }else if(cnt > kk){
                right=cnt-1;
            }else{
                left=cnt+1;
            }
        }

        return -1;

    }
}