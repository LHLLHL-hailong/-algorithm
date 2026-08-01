class Solution {
    public int maximalRectangle(char[][] matrix) {

        int [][]up=new int[matrix.length][matrix[0].length];

        for(int j=0;j<matrix[0].length;j++){
            up[0][j]=matrix[0][j]-'0';

        }

        for(int i=1;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){

                if(matrix[i][j]-'0'!=0){
                    up[i][j]=1+up[i-1][j];//致命问题二: 必须连续，必须连续，必须连续!
                }
               
            }
        }

        int[]stack=new int[up[0].length+2];
        int ptr=0;
        int sum=0;

        for(int k=0;k<matrix.length;k++){
            int[]heights=up[k];
            int[]copy=new int[heights.length+2];
            for(int j=0;j<heights.length;j++){
                copy[j+1]=heights[j];
            }
            heights=copy;//致命问题一,之前忘使用新的了
            ptr=0;
            stack[ptr++]=0;

            for(int i=1;i<heights.length;i++){

                if(ptr>0 && heights[i]==heights[stack[ptr-1]]){
                    stack[ptr-1]=i;
                    continue;
                }

                while(ptr>0 && heights[i]<heights[stack[ptr-1]]){

                    int width=i-stack[ptr-2]-1;
                    int hh=heights[stack[ptr-1]];
                    sum=Math.max(sum,width*hh);
                    ptr--;

                }

                stack[ptr++]=i;

            }

        }

        return sum;
    }
}
/*

##review
致命问题二: 必须连续，必须连续，必须连续!

综合评价：
小错误太多: C+(还是再重写一遍吧)




 */