/**
 * 这也是一道hard题，不过它的区别就是说这道题,和上面那个题类似，只需要一步转化就可以实现由若干个上道题来进行组成这道题的答案,而且注意，这道题不能使用动态规划，因为其无法进行正常的状态转移
 */

class Solution {




    public int maximalRectangle(char[][] matrix) {

        int[][]heights=new int[matrix.length][matrix[0].length];

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                        if(matrix[i][j]=='1')
                        heights[i][j]=(i==0?0:heights[i-1][j])+1;

            }
        }

        //printMatrix(heights);

        int max=0;
        for(int k=0;k<heights.length;k++){
            int[] hei=heights[k];

            int [] now=new int[hei.length+2];

            for(int i=0;i<hei.length;i++){
                now[i+1]=hei[i];
            }

            int[] stack=new int[now.length+2];
            int ptr=0;

            stack[ptr++]=0;
            for(int i=1;i<now.length;i++){
                while(now[i]<now[stack[ptr-1]]){
                    max=Math.max(max,now[stack[ptr-1]]*(i-stack[ptr-2]-1));

                    //System.out.println(k+"   "+max+"   l:"+(stack[ptr-2]-1)+"   r:"+(i-1)+"   hei:"+now[stack[ptr-1]]);
                    ptr--;

                }

                if(now[i]==now[stack[ptr-1]]){
                    stack[ptr-1]=i;

                }else if(now[i]>now[stack[ptr-1]]){
                    stack[ptr++]=i;
                    
                }
            }


        }

        return max;



    }
}

