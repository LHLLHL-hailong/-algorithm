/**
 * 和接雨水这道题很像
 */
class Solution {


    public int largestRectangleArea(int[] heights) {

        int[] stack=new int[heights.length+3];
        int ptr=0;


        int[]copy=new int[heights.length+2];

        int max=0;

        for(int i=0;i<heights.length;i++){
            copy[i+1]=heights[i];
        }
        stack[ptr++]=0;

        for(int i=1;i<copy.length;i++){

             while(copy[i]<copy[stack[ptr-1]]){
                int with=i-stack[ptr-2]-1;
                max=Math.max(max,with*copy[stack[ptr-1]]);
                
                //System.out.println("l  "+stack[ptr-2]+"    " + "r   "+i);
                ptr--;

                
                
            }
            if(copy[i]>copy[stack[ptr-1]]){
                stack[ptr++]=i;

            }else if(copy[i]==copy[stack[ptr-1]]){
                stack[ptr-1]=i;
            }
            

        }

        return max;


    }
}