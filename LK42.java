/**
 * 这个更标准
 */

class Solution {
    public int trap(int[] height) {

        int []stack=new int[height.length+1];
        int ptr=0;

        stack[ptr++]=0;

        int sum=0;

        for(int i=1;i<height.length;i++){

            while(ptr>0 && height[stack[ptr-1]] < height[i]){

                int bottom =height[stack[ptr-1]];
                ptr--;
                if(ptr>0){
                    int left=stack[ptr-1];
                    int with=i-left-1;
                    int hei=Math.min(height[left],height[i])-bottom;

                    sum+=with*hei;`



                }


            }

            stack[ptr++]=i;



        }

        return sum;

    }
}




/**
 * 自己回忆出来的算法虽然也是正确的，但是很迂回
 */
class Solution {
    public int trap(int[] height) {

        int stack[]=new int[height.length+1];
        int ptr=0;

        stack[ptr++]=0;

        int sum=0;

        for(int i=1;i<height.length;i++){

            while(height[i]>height[stack[ptr-1]]){

                int preH=0;
                int lastIdx=stack[ptr-1];
                ptr--;
                int with=i-lastIdx;
                int height=Math.min(height[lastIdx],height[i])-preH;
                sum+=with*height;
                preH+=height;



            }

            stack[ptr++]=i;

        }

        return sum;



    }
}