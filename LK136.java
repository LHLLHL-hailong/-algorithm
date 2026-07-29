/**
 * 送分题
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