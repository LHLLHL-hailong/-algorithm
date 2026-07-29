
/**
 * 虽然最后也是勉强写出来的，但是这个像第一次做一样，遗忘了很多
 */
class Solution {


        void swap(int [] nums,int f,int t){

        int tmp=nums[f];
        nums[f]=nums[t];
        nums[t]=tmp;

    }

    void reverse(int []nums ,int from ,int to){

        if(from >=to) return ;

        for(int i=from;i<=((to+from)/2);i++){
            swap(nums,i,to-i+from);

        }

    }


    public void nextPermutation(int[] nums) {

        int idx=-1;

        for(int i=nums.length-2;i>=0;i--){
            if(nums[i] <nums[i+1]){
                idx=i;
                break;

                
            }
        }

        if(idx==-1){
            reverse(nums,0,nums.length-1);
            return ;
        }

        for(int i=nums.length-1;i>=idx;i--){
            if(nums[i]>nums[idx]){

                swap(nums,i,idx);
                reverse(nums,idx+1,nums.length-1);
                return ;
                
            }

        }



    }

}












/**
 * 自己想到的单调栈写法思路和正常答案都是一样的，唯一不同就是多用了O(n)的空间
 */
class Solution {


    void swap(int [] nums,int f,int t){

        int tmp=nums[f];
        nums[f]=nums[t];
        nums[t]=tmp;

    }

    void reverse(int []nums ,int from ,int to){

        if(from >=to) return ;

        for(int i=from;i<=((to+from)/2);i++){
            swap(nums,i,to-i+from);

        }

    }

    public void nextPermutation(int[] nums) {

        int []stack=new int[nums.length];
        int ptr=0;

        stack[ptr++]=nums.length-1;

        for(int i=nums.length-2;i>=0;i--){

            if(nums[i]>=nums[stack[ptr-1]]){
                stack[ptr++]=i;
            }else{
                int pre=-1;
                while( ptr>0 && nums[i]<nums[stack[ptr-1]]){
                    // if( pre==-1|| nums[pre] != nums[stack[ptr-1]])
                    pre=stack[ptr-1];
                    ptr--;
                    
                }

                int tmp=nums[i];
                nums[i]=nums[pre];
                nums[pre]=tmp;

                reverse(nums,i+1,nums.length-1);
                return ;
            }

        }

        reverse(nums,0,nums.length-1);



        return ;
        
    }
}