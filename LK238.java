/**
 * 基本上没有难度
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {

        int[] res=new int[nums.length];

        res[nums.length-1]=nums[nums.length-1];

        for(int i=nums.length-2;i>=0;i--){
            res[i]=res[i+1]*nums[i];
        }

        int cnt=1;

        for(int i=0;i<nums.length-1;i++){
            res[i]=cnt*res[i+1];
            cnt*=nums[i];
        }

        res[nums.length-1]=cnt;

        return res;

    }
}