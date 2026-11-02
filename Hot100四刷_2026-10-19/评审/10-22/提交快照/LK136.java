/**
 * start: 21:15
 * end: 21:16
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {
    public int singleNumber(int[] nums) {

        int base=0;

        for(int now:nums){
            base^=now;
        }

        return base;
        
    }
}