/**
 * 很难。基本上纯粹的背
 */
class Solution {

    public int findDuplicate(int[] nums) {
        int slow=0;
        int quick=0;

        do{
            slow=nums[slow];
            quick=nums[nums[quick]];
        }while(slow!=quick);

        int cnt=0;

        while(cnt!=slow){
            cnt=nums[cnt];
            slow=nums[slow];
        }

        return slow;

    }
}