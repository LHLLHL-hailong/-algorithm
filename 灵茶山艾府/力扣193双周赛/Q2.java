class Solution {
    public int resilientSubarray(int[] nums, int k) {


        //a---> 1---k-1

        // a+nk 


        int[]count=new int[k];

        int res=0;


        int cnt=0;

        for(int i=0;i<nums.length;i++){
            if(i!=0 && nums[i-1]%k==nums[i]%k){
                cnt++;
                
            }else{
                cnt=1;
            }
            count[nums[i]%k]=Math.max(count[nums[i]%k],cnt);
        }

        // for(int now :nums){
        //     count[now%k]++;
        //     //res=Math.max(res,count[now%k]);
        // }

        for(int i=0;i<k;i++){

            for(int j=0;j<nums.length;j++){

                if((i*j)%k==0){
                    if(j+1<=count[i]){
                        res=Math.max(res,j+1);
                    }else{
                        break;
                    }
                }
            }
        }

        return res;
        
    }
}