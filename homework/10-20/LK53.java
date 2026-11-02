/**
 * start: 18:55
 * end: 19:00
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {
    public int maxSubArray(int[] nums) {

        int base=nums[0];

        int res=nums[0];

        for(int i=1;i<nums.length;i++){

          //  res=Math.max(res,base);
            base=Math.max(base,0)+nums[i];
            res=Math.max(res,base);
        }

    //    res=Math.max(res,base);


        return res;


        
    }
}