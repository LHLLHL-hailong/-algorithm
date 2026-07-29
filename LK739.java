/**
 * 简单的单调栈
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int[]stack=new int[temperatures.length];//递减/相等栈
        int ptr=0;
        int[]res=new int[temperatures.length];

        stack[ptr++]=0;

        for(int i=1;i<temperatures.length;i++){

            while(ptr!=0 && temperatures[i]>temperatures[stack[ptr-1]]){
                res[stack[ptr-1]]=i-stack[ptr-1];
                ptr--;
            }
            stack[ptr++]=i;

        }

        return res;


        
    }
}