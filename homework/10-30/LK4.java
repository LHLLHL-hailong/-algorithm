/**
 * start: 19:39
 * end: 19:57
 * 忘了，看完答案后回忆出来
 * 时间：O(log(n+m))
 * 空间：O(1)
 */
class Solution {


    int dfs(int[]nums1,int []nums2,int k){

        int ptr1=0;
        int ptr2=0;

        while(true){

             if(ptr1>=nums1.length){//tip：必须先于k==1的判断条件
                return nums2[ptr2+k-1];
            }
            if(ptr2>=nums2.length){
                return nums1[ptr1+k-1];
            }

            if(k==1){
                return Math.min(nums1[ptr1],nums2[ptr2]);
            }
            


            int piviot1=Math.min(ptr1+k/2-1,nums1.length-1);
            int piviot2=Math.min(ptr2+k/2-1,nums2.length-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                k-=(piviot1-ptr1+1);
                ptr1=piviot1+1;
            }else{
                k-=(piviot2-ptr2+1);
                ptr2=piviot2+1;
            }

        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;


        if(len%2==0){

            return (dfs(nums1,nums2,len/2+1)+dfs(nums1,nums2,len/2))*1.0/2;

        }else{
            

            return dfs(nums1,nums2,len/2+1);

        }



        
    }
}