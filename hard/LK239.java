class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer>queue=new ArrayDeque<>();//是单调队列，不是单调栈，所以不要自己实现，要用集合框架

        int[]res=new int[nums.length-k+1];
        int ptr=0;


        for(int i=0;i<nums.length;i++){//注意要从0开始遍历，不要先加入0到队列中，否则对于K=1会出bug

            while(!queue.isEmpty() && nums[queue.getLast()]<= nums[i]){
                queue.removeLast();

            }

            queue.addLast(i);
            
            if(i>=k-1){
                res[ptr++]=nums[queue.getFirst()];

                if(queue.getFirst()==i-k+1){
                    queue.removeFirst();
                }
            }


        }

        return res;
        
    }
}


/*
##review

1. 是单调队列，不是单调栈，所以不要自己实现，要用集合框架（因ArrayDeque(这个名字也忘了)）
2. 注意要从0开始遍历，不要先加入0到队列中，否则对于K=1会出bug
综合评价: C+(需要再次复习)

 */