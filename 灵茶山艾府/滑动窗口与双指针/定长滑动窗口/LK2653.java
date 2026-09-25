class Solution {

    int getCount(int[]count,int x){

        int cnt=0;

        for(int i=0;i<50;i++){
            if(count[i]>0) cnt+=count[i];
            if(cnt>=x) return i-50;
        }

        return 0;
    }



    public int[] getSubarrayBeauty(int[] nums, int k, int x) {

        int[]count=new int[101];
        int base=50;

        for(int i=0;i<k;i++){
            count[nums[i]+base]++;
        }

        int[]res=new int[nums.length-k+1];
        res[0]=getCount(count,x);

        for(int i=k;i<nums.length;i++){

            count[nums[i]+base]++;
            count[nums[i-k]+base]--;


            res[i-k+1]=getCount(count,x);
            
        }


        return res;
        


    }
}

/*
1. 值域计数,直接存储进入count[val+base] 适用于数字本身的范围非常小的场景
2. 用堆来维护第几小的数字前提是已经进入堆里面的元素不能被删除，否则之前被淘汰的数字就不能被淘汰了（也就是只能新增不能删除）
3. PriorityQueue<Integer> 中的 queue.remove(val) 按元素值删除，不是按下标删除（因为它没有按照下标删除的api）

 */