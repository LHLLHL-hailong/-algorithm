class Solution {

      public static void printArray(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i]);
          if (i != arr.length - 1) System.out.print(" ");
      }
      System.out.println();
  }

    public int lengthOfLIS(int[] nums) {

        int[]d=new int[nums.length];

        d[0]=nums[0];
        int len=0;

        for(int i=1;i<nums.length;i++){

            if(nums[i]>d[len]){
                d[++len]=nums[i];
            }else{
                int l=0;
                int r=len;
                int pos=0;

                while(l<=r){
                    int mid=(l+r)/2;
                    if(nums[i]>d[mid]){
                        l=mid+1;
                         pos=mid+1;
                        
                    }else{

                        r=mid-1;
                    }
                }
                d[pos]=nums[i];//注意这个更新逻辑，是在nums[i]大的时候更新 mid+1 赋值，然后等号算到小于的那侧
            }

        }

        //printArray(d);

        return len+1;//注意：如果len是从0开始的这里需要+1
        
        
    }
}


class Solution {

      public static void printArray(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i]);
          if (i != arr.length - 1) System.out.print(" ");
      }
      System.out.println();
  }

    public int lengthOfLIS(int[] nums) {

        int[]d=new int[nums.length];

        d[0]=nums[0];
        int len=0;

        for(int i=1;i<nums.length;i++){

            if(nums[i]>d[len]){
                d[++len]=nums[i];
                System.out.println(nums[i]+"填入了"+len);
            }else{
                int l=0;
                int r=len;
                int pos=0;

                while(l<=r){
                    int mid=(l+r)/2;
                    if(nums[i]>d[mid]){
                        l=mid+1;
                    }else{
                        pos=mid;//如果我们小于的时候进行赋值，等号也要算在小于的那侧，如果我们发现相等了，大不了重复不赋值，如果我们算在大于那一侧，但是在小于的时候赋值，那等值的时候，就会被算作nums[i] 大于mid，就会出现错误
                        r=mid-1;
                    }
                }
                d[pos]=nums[i];
                System.out.println(nums[i]+"填入了"+pos);
            }

        }

        //printArray(d);

        return len+1;
    }
}

/*
##review
1. 注意len的起始位置，是从零开始还是从一开始
2. 要将等号算在小于的那侧，而不要算在的大于那侧,因为无论在哪个位置更新pos,等值算在小于的那侧，我大不了相等的时候重复赋值，这个是没有问题的。但是如果我们算在大于点那侧，我会把它默认当做nums[i]>mid,那么就彻底错了
3. 换一种理解角度也可以是等号位置应该被覆盖掉，而不应该更新下一个，因为答案要求是严格递增

综合评价:c-（基本上忘没了，下次还需要重写）


 */

class Solution {

    public static void printArray(int[] arr) {
      System.out.println(Arrays.toString(arr));
  }

    public int lengthOfLIS(int[] nums) {


        int []dp=new int[nums.length];

        for(int i=0;i<nums.length;i++){
            dp[i]=1;//注意，这里边要全赋值为一，初始值就是1，因为正常来说，你要进行状态转移的话，他如果没有一个能转移的，那默认长度应该是1，因为以这是以nums[i]为结尾的,也就是可以将状态转移的默认值现在初始化的时候设置出来

        }

        int maxLen=1;

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);

                }

            }
            maxLen=Math.max(maxLen,dp[i]);
        }

        //printArray(dp);

        return maxLen;



    }
}
/*
##review
此方法：

综合评价:B-

 */