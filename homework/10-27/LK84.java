/**
 * start: 18:08
 * end: 18:15
 * 独立完成
 * 时间:O(n)
 * 空间:O(n)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {

        int[]hei=new int[heights.length+2];

        for(int i=0;i<heights.length;i++){
            hei[i+1]=heights[i];
        }

        int res=0;

        int[]stack=new int[hei.length+1];
        int ptr=0;

        for(int i=0;i<hei.length;i++){
            while(ptr!=0 && hei[i]<hei[stack[ptr-1]]){

                int with=i-stack[ptr-2]-1;

                int heig=hei[stack[ptr-1]];

                res=Math.max(res,heig*with);

                ptr--;

            }

            stack[ptr++]=i;
        }

        return res;
        
    }
}