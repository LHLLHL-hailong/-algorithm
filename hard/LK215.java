class Solution {
            void swap(int[]nums,int from ,int to){//输入的必须是下标
            int tmp=nums[from];
            nums[from]=nums[to];
            nums[to]=tmp;
        }


//+ 注意函数里面要按照left-right别还是从0开始就闹麻了
        int parellen(int[]nums,int left,int right){

             if(left==right) return left;

            int piviot=nums[left];//+

            swap(nums,left,right);

            int lptr=left-1;//+

            int count=0;

            for(int i=left;i<right;i++){//右侧right处不能存在等号（否则可能移走piviot，导致错误）//+
                if(nums[i]<piviot){
                    swap(nums,lptr+1,i);
                    lptr++;
                }else if(nums[i]==piviot){
                    if(++count%2==0){//优化时间的 （这个题不优化会超时）
                    swap(nums,lptr+1,i);
                    lptr++;              
                    }

                }

            }
            lptr++;
            swap(nums,lptr,right);

            return lptr;
        }
    public int findKthLargest(int[] nums, int k) {

        int kk=nums.length-k;//转换

        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int cnt=parellen(nums,left,right);

            if(cnt==kk){
                return nums[cnt];
            }else if(cnt<kk){
                left=cnt+1;
            }else{
                right=cnt-1;

            }

        }

        return -1;
        
    }
}