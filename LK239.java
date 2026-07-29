/**
 * 这道题涉及到了单调队列，很难
 */
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer>queue=new ArrayDeque<>();
        queue.add(0);

        for(int i=1;i<k;i++){
            while(!queue.isEmpty() && nums[i]>=nums[queue.getLast()] ){
                queue.removeLast();
            }
            queue.addLast(i);
        }

        int[]res=new int[nums.length-k+1];
        res[0]=nums[queue.getFirst()];
        for(int i=k;i<nums.length;i++){
            //i-k+1
            while(!queue.isEmpty() && nums[i]>=nums[queue.getLast()] ){
                queue.removeLast();
            }
            queue.addLast(i);

            //i-k
            if(queue.getFirst()==i-k){
                queue.removeFirst();
            }
            
            res[i-k+1]=nums[queue.getFirst()];


        }

        return res;

    }
}
