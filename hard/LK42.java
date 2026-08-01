class Solution {
    public int trap(int[] height) {

        if(height.length==0) return 0;

        int []stack=new int[height.length];
        int ptr=0;

        stack[ptr++]=0;

        int res=0;

        for(int i=1;i<height.length;i++){

            while(ptr!=0 && height[i]> height[stack[ptr-1]]){

                    if(ptr-2>=0){

                        int hei= Math.min(height[i],height[stack[ptr-2]]);
                        int bottle=height[stack[ptr-1]];
                        int with=i-stack[ptr-2]-1;

                        res+=((hei-bottle)*with);
                    }

                    ptr--;
            }

            stack[ptr++]=i;

        }

        return res;
        
    }
}

/*

##review

记住了大体思路 
  - 宽度 → width
  - 高度 → height
  - 底部 → bottom
单词记一下，不然写写错了很尴尬

综合评价：B+

 */