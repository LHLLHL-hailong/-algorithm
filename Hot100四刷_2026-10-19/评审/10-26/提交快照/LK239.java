/**
 * start:10:19
 * end: 10:30
 * 时间：O(n)
 * 空间：O(n)
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {



        Deque<Integer>stack=new ArrayDeque<>();

        int[]res=new int[nums.length-k+1];

        for(int i=0;i<k;i++){

            while(stack.size()>0 && nums[i]>=nums[stack.getLast()]){
                stack.removeLast();
            }

            stack.addLast(i);


        }

        res[0]=nums[stack.getFirst()];

        for(int i=k;i<nums.length;i++){

            while(stack.size()>0 && nums[i]>=nums[stack.getLast()]){
                stack.removeLast();
            }

            stack.addLast(i);

            if(stack.getFirst()==i-k){

                stack.removeFirst();

            }

            res[i-k+1]=nums[stack.getFirst()];
        }

        return res;
        
    }
}