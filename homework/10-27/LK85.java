/**
 * start:18:16
 * end:18:26
 * 独立完成
 * 时间:O(nm)
 * 空间:O(m)
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {

        int[]heights=new int[matrix[0].length+2];

        int[]stack=new int[heights.length];
        int ptr=0;

        int res=0;

        for(int i=0;i<matrix.length;i++){

            for(int j=1;j<=heights.length-2;j++){

                if(matrix[i][j-1]=='1'){
                    heights[j]+=1;
                }else{
                    heights[j]=0;
                }
            }
            ptr=0;

            for(int j=0;j<heights.length;j++){
                while(ptr!=0 && heights[j]<heights[stack[ptr-1]]){

                    int with=j-stack[ptr-2]-1;
                    int hei=heights[stack[ptr-1]];

                    res=Math.max(res,with*hei);

                    ptr--;

                }
                stack[ptr++]=j;
            }

        }

        return res;
        
    }
}