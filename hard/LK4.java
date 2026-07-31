class Solution {

    int getK(int[]nums1,int[]nums2,int k){

        int idx1=0;
        int idx2=0;

        while(true){

            if(idx1==nums1.length){
                return nums2[idx2+k-1];
            }

            if(idx2==nums2.length){
                return nums1[idx1+k-1];
            }

            if(k==1){
                return Math.min(nums1[idx1],nums2[idx2]);
            }

            int pivit1=Math.min(idx1+k/2-1,nums1.length-1);
            int pivit2=Math.min(idx2+k/2-1,nums2.length-1);

            if(nums1[pivit1]<nums2[pivit2]){
                k=k-(pivit1-idx1+1);
                idx1=pivit1+1;//注意:此处写反了
                
            }else{
                k=k-(pivit2-idx2+1);
                idx2=pivit2+1;//注意:此处写反了
                
            }

        }

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+nums2.length;

        if(len%2==1){
            return getK(nums1,nums2,len/2+1);
        }else{
            int n1=getK(nums1,nums2,len/2);
            int n2=getK(nums1,nums2,len/2+1);

            return (n1+n2)*1.0/2;

        }
        
    }
}

/*
##review
 1. 首先，这道题我在第三刷的时候有两处写反了
 2. 那三个边界条件基本上是背诵出来的，之后很可能会忘了
 3. 综合评分：B- (需要进行下次复习)


 */