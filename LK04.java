/**
 * 注意背诵这个思路
 */
class Solution {

    int findK(int[]nums1,int[]nums2,int k){

        int idx1=0;
        int idx2=0;

        while(true){

            //这三个判断条件，少一个都不行
            if(idx1==nums1.length){//保证每次选举都是有意义的,否则可能会本身已经没了结果又选举成最后一个值，导致一个错误
                return nums2[idx2+k-1];
            }

            if(idx2==nums2.length){//保证每次选举都是有意义的,否则可能会本身已经没了结果又选举成最后一个值，导致一个错误
                return nums1[idx1+k-1];
            }

            if(k==1){//如果K=1 k/2=0 idx+k/2-1 就会回退导致错误
                return Math.min(nums1[idx1],nums2[idx2]);
            }

            int piviot1=Math.min(nums1.length-1,idx1+k/2-1);//不要忘记这个防越界的检查
            int piviot2=Math.min(nums2.length-1,idx2+k/2-1);//不要忘记这个防越界的检查

            if(nums1[piviot1]<=nums2[piviot2]){
                k=k-(piviot1-idx1+1);
                idx1=piviot1+1;
                
            }else{
                k=k-(piviot2-idx2+1);
                idx2=piviot2+1;
                
            }

        }

    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;


        if(len%2==1){
            return findK(nums1,nums2,len/2+1);
        }else{
            int n1=findK(nums1,nums2,len/2);
            int n2=findK(nums1,nums2,len/2+1);
            return (n1+n2)*1.0/2;
        }


        
    }
}