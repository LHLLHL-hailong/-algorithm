class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0,1);

        int prefix=0;

        int count=0;

        for(int i=0;i<nums.length;i++){

            prefix+=nums[i];

            count+=map.getOrDefault(prefix-k,0);

            map.put(prefix,map.getOrDefault(prefix,0)+1);


        }

        return count;

    }
}

/*
##review

就是想不起来使用这种方法，总是想动态规划(动态规划很难，还会超内存)

综合评价:C-

 */