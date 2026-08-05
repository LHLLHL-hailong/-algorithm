class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int minV=Integer.MAX_VALUE/2;
        int maxV=Integer.MIN_VALUE/2;

        int l=-1;
        int r=-1;


        for(int i=0;i<nums.length;i++){

            if(nums[i]<maxV){
                r=i;
            }else{
                maxV=nums[i];
            }

            int j=nums.length-1-i;

            if(nums[j]>minV){
                l=j;
            }else{
                minV=nums[j];
            }

        }

        if(l==-1 || r==-1){//注意这个边界情况,如果一直没更新，说明它都符合规则，只是需要单独的考虑
            return 0;
        }else{
            return r-l+1;
        }

    }
}






class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int l=-1;
        int r=nums.length;

        for(int i=0;i<nums.length;i++){

            if(l==-1 || nums[i]>=nums[l]){
                if(l+1==i){
                    l++;
                }
            }else{
                while(l!=-1 &&nums[i]<nums[l]){
                    l--;
                }
                
            }


            int j=nums.length-1-i;
            if(r==nums.length || nums[j]<=nums[r]){

                if(j+1==r){
                    r--;
                }
            }else{

                while(r!=nums.length && nums[j]>nums[r]){
                    r++;
                }
                    

            }

        }

        return r-l-1>=0?r-l-1:0;
        
    }
}

/*
##review

综合评价
1. 第一个方法：C+(还需要重新复习)
2. 第二个方法: B+
 */