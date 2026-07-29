/**
 * 这个思路需要仔细地记一下
 */

/**
 * 转化一步，用动态规划的公式才是这道题的正解
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        //a+b=sum
        //a-b=target
        //2a=sum+target

        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }

        int tar=target+sum;
        if(tar%2==1){
         
            return 0;
        }

         if(tar<0){
            return 0;
        }

        tar=tar/2;



        int[]dp=new int[tar+1];

        dp[0]=1;
        for(int i=1;i<=nums.length;i++){
            for(int j=tar;j>=nums[i-1];j-- ){
               dp[j]=dp[j]+dp[j-nums[i-1]];
            }
        }
        return dp[tar];

    }
}
/**
 * 效率很低
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        HashMap<Integer,Integer>map=new HashMap<>();

        if(nums[0]!=-nums[0]){
        map.put(nums[0],1);
        map.put(-nums[0],1);
        }else{
            map.put(nums[0],2);
        }

       
        for(int i=1;i<nums.length;i++){
            HashMap<Integer,Integer> tmp=new HashMap<>();
            for(int key:map.keySet()){
                int count=map.get(key);
                tmp.put(key+nums[i],tmp.getOrDefault(key+nums[i],0)+count);
                tmp.put(key-nums[i],tmp.getOrDefault(key-nums[i],0)+count);

            }
            map=tmp;

        }

        Integer res=map.get(target);

        return res==null?0:res;

    }
}