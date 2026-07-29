
/**
 * 这道题基本上没有难度
 */
class Solution {
    public int maxSubArray(int[] nums) {

        int base=-1000000;
        int maxV=-1000000;//初始化最小值不能是0，因为有负数

        for(int now:nums){
            base=Math.max(base+now,now);
            maxV=Math.max(maxV,base);//需要注意的是，这次每次都要记录更新最大值
        }

        return maxV;

    }
}
