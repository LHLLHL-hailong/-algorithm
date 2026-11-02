/**
 * start: 18:38
 * end: 18:49
 * 独立完成
 * 时间: O(n)
 * 空间: O(n)
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[]res=new int[nums.length-k+1];

        Deque<Integer>queue=new ArrayDeque<>();

        for(int i=0;i<k;i++){
            while(!queue.isEmpty()&& nums[i]>=nums[queue.getLast()]){

                queue.removeLast();

            }
            queue.addLast(i);
        }

        res[0]=nums[queue.getFirst()];

        for(int i=k;i<nums.length;i++){
            while(!queue.isEmpty()&& nums[i]>=nums[queue.getLast()]){

                queue.removeLast();

            }

            queue.addLast(i);

            if(queue.getFirst()==i-k){
                queue.removeFirst();
            }

            res[i-k+1]=nums[queue.getFirst()];
            
        }

        return res;


        
    }
}