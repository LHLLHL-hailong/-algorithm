class Solution {
    public int lengthOfLIS(int[] nums) {

        if(nums.length<=1) return nums.length;

        int[]dis=new int[nums.length];
        int ptr=0;

        dis[ptr++]=nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i]>dis[ptr-1]){
                dis[ptr++]=nums[i];
            }else{
                int l=0;
                int r=ptr-1;

                int cnt=-1;

                while(l<=r){
                    int mid=(l+r)/2;
                    if(nums[mid]>=nums[i]){
                        r=mid-1;
                        cnt=mid;
                    }else{
                        l=mid+1;
                    }


                }

                dis[cnt]=nums[i];
            }
        }

        return ptr;
    }
}
