
/**
 * 摩尔投票算法
 */
class Solution {
    public int majorityElement(int[] nums) {

        int now=nums[0];
        int cnt=1;

        for(int i=1;i<nums.length;i++){
            if(nums[i]==now){
                cnt++;
            }else{
                if(--cnt <=0){
                    now=nums[i];
                    cnt++;
                }
            }
        }

        return now;



    }
}