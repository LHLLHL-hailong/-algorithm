/**
 * start: 15:31
 * end: 15:54
 * 独立完成
 * 时间：O(log(n+m))
 * 空间：O(1)
 */
class Solution {

    int getNum(int[]nums1,int[]nums2,int target){

        int ptr0=0;
        int ptr1=0;

        while(true){

            if(ptr0>=nums1.length){
                return nums2[ptr1+target-1];
            }

            if(ptr1>=nums2.length){
                return nums1[ptr0+target-1];
            }

            if(target==1){
                return Math.min(nums1[ptr0],nums2[ptr1]);
            }

            int piviot1=Math.min(nums1.length-1,ptr0+target/2-1);
            int piviot2=Math.min(nums2.length-1,ptr1+target/2-1);

            if(nums1[piviot1]<=nums2[piviot2]){
                target-=(piviot1-ptr0+1);
                ptr0=piviot1+1;

            }else{
                target-=(piviot2-ptr1+1);
                ptr1=piviot2+1;

            }
        }
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;

        if(len%2==0){

            return (getNum(nums1,nums2,len/2)+getNum(nums1,nums2,len/2+1))*1.0/2;
        }else{

            return getNum(nums1,nums2,len/2+1)*1.0;
        }
        
    }
}