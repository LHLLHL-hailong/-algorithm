/**
 * start: 21:18
 * end: 21:21
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {
    public int majorityElement(int[] nums) {

        int now=nums[0];
        int flage=1;

        for(int i=1;i<nums.length;i++){
            if(nums[i]==now){
                flage++;
            }else{
                if(--flage<=0){
                    now=nums[i];
                    flage=1;
                }
            }
        }

        return now;


        
    }
}