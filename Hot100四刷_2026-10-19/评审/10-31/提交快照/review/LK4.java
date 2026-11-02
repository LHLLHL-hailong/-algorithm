/**
 * start: 20:50
 * end: 21:02
 * 独立完成
 * 时间：log(n+m)
 * 空间: O(1)
 */
class Solution {


    int dfs(int[]nums1,int[]nums2,int target){
        int ptr1=0;
        int ptr2=0;

        while(true){

            if(ptr1>=nums1.length){
                return nums2[ptr2+target-1];
            }

            if(ptr2>=nums2.length){
                return nums1[ptr1+target-1];
            }

            if(target==1){

                return Math.min(nums1[ptr1],nums2[ptr2]);

            }

            int piviot1=Math.min(ptr1+target/2-1,nums1.length-1);
            int piviot2=Math.min(ptr2+target/2-1,nums2.length-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                target-=(piviot1-ptr1+1);
                ptr1=piviot1+1;
            }else{
                target-=(piviot2-ptr2+1);
                ptr2=piviot2+1;
            }

        }
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;

        if(len%2==0){

            return (dfs(nums1,nums2,len/2)+dfs(nums1,nums2,len/2+1))*1.0/2;


        }else{
            return dfs(nums1,nums2,len/2+1)*1.0;//tip target是第几个数，不是下标是几
        }
        
    }
}