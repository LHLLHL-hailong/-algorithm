class Solution {


    void swap(int []nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int pareelen(int[]nums,int l,int r){

        int pivit=nums[l];
        swap(nums,l,r);

        int ll=l-1;//注意从l开始的
        int count=0;
        for(int i=l;i<r;i++){
            if(nums[i]<pivit){
                ll++;
                swap(nums,ll,i);
            }else if(nums[i]==pivit){
                if(++count %2==0){//这个是必须的，否则会超时
                 ll++;
                swap(nums,ll,i);
                }

            }
        }
        ll++;

        swap(nums,ll,r);

        return ll;
    }

    public int findKthLargest(int[] nums, int k) {

        int kk=nums.length-k;

       // System.out.println(kk);

        int left=0;
        int right=nums.length-1;

        while(left<=right){

            int mid=pareelen(nums,left,right);

           // System.out.println(nums[mid]);
           // System.out.println(Arrays.toString(nums));

            if(kk>mid){
               // kk=kk-(mid-left+1);//注意别改变kk的值，这个代表着的是下标，是不变的
                left=mid+1;
            }else if(kk<mid){
                right=mid-1;
            }else{
                return nums[mid];
            }


        }

        return -1;

    }

}