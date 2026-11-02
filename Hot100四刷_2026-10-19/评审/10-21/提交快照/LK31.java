/**
 * start: 6:54
 * end: 7:08
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Solution {

    void swap(int[]nums ,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }


    // 0 1 2
    // 1
    void reverse(int []nums,int from ,int to){
        //0---3
        //1
        //01  23
        //0----4
        //0 1 2 3 4
        int mid=(from+to)/2;
        for(int i=from;i<=mid;i++){
           // System.out.println(Arrays.toString(nums));
            //System.out.println(i);
            
            swap(nums,i ,from+to-i);
        }

        //System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {

        if(nums.length<=1) return ;

        for(int i=nums.length-2;i>=0;i--){

            if(nums[i]<nums[i+1]){
                int j=i+1;
                while(j+1<nums.length && nums[j+1]>nums[i]){
                    j++;
                }
                swap(nums,i,j);

                reverse(nums,i+1,nums.length-1);

                return ;

            }


        }

        //System.out.println("in");

        reverse(nums,0,nums.length-1);

        return ;
        
    }
}