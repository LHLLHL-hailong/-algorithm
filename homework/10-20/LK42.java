
/**
 * strat: 18:41
 * end: 18:52
 * 独立完成
 * 时间： O(n)
 * 空间： O(n)
 */
class Solution {
    public int trap(int[] height) {

        int[]stack=new int[height.length];
        int ptr=0;

        int res=0;

        for(int i=0;i<height.length;i++){

            while(ptr!=0 && height[i]>height[stack[ptr-1]]){
                if(ptr>=2){
                    int hei=Math.min(height[stack[ptr-2]],height[i]);
                    int bottle=height[stack[ptr-1]];

                    res+=((i-stack[ptr-2]-1)*(hei-bottle));

                   // System.out.println(res);


                }

                ptr--;
            }

            stack[ptr++]=i;//中途还加成了height[i]!
        }

        return res;
        
    }
}