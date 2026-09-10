// class Solution {
//     public int findTargetSumWays(int[] nums, int target) {

//         int sum=0;

//         for(int now:nums){
//             sum+=now;
//         }
//         if((sum+target)%2!=0){
//             return 0;
//         }

//         int total=(sum+target)/2;

//         if(total<0){
//             return 0;
//         }

//         int[]dp=new int[total+1];

//         dp[0]=1;

//         for(int i=0;i<nums.length;i++){

//             for(int j=total;j>=0;j--){

//                 if(j>=nums[i])
//                 dp[j]+=dp[j-nums[i]];

//             }

//         }

//         return dp[total];
//     }
// }

class Solution {

    
    void print(int[]arr){
        System.out.println("--------------------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        
        System.out.println("--------------------------");
    }


    public int findTargetSumWays(int[] nums, int target) {



        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        //也就是涉及到加减数字的时候要加减分离

  
        
        int result=(sum+target)/2;//应该在除以2的时候就想到，这和除法是向下取整的，是有可能与实际产生误差 的，这里的误差就是无法搓出来(毕竟一堆整数怎么凑出来小数呢)
          if((sum+target)%2==1) return 0;


//还有一种凑不出来的情况就是result算完是负数的情况，这种情况下会new int[]时候执行出错的
         if(result<0) return 0;

       
       

        int []dp=new int[result+1];//定义dp数组的时候一定要根据题义
        dp[0]=1;
        print(dp);
        for(int i=0;i<nums.length;i++){
            for(int j=result;j>=0;j--){
                if(j>=nums[i]){
                   dp[j]=dp[j]+dp[j-nums[i]];
                }
            }
            print(dp);
        }

        return dp[result];


        // return count;

    }
}