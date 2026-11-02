/**
 * start: 11:06
 * end:11:10
 * 独立完成  
 * 时间：O(n)
 * 空间： O(n)  
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int[]stack=new int[temperatures.length];
        int ptr=0;
        int[]res=new int[temperatures.length];

        for(int i=0;i<temperatures.length;i++){
            while(ptr!=0 && temperatures[i]>temperatures[stack[ptr-1]]){

                res[stack[ptr-1]]=i-stack[ptr-1];

                ptr--;
            }

            stack[ptr++]=i;
        }

        return res;
        
    }
}  