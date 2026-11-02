
/**
 * start: 18:49
 * end: 19:12
 * 独立完成
 * 时间：O(nm)
 * 空间：O(m)
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {

        int[]hei=new int[matrix[0].length+2];

        int []stack=new int[hei.length+1];
        int ptr=0;

        int res=0;

        for(int i=0;i<matrix.length;i++){

            ptr=0;

            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]=='1'){
                   // System.out.println("in");
                    hei[j+1]++;
                }else{
                    hei[j+1]=0;
                }
            }

           // System.out.println(Arrays.toString(hei));

            for(int j=0;j<hei.length;j++){

                

                while(ptr!=0 && hei[j]<hei[stack[ptr-1]]){
                    int with=j-stack[ptr-2]-1;
                    int heigt=hei[stack[ptr-1]];

                    //System.out.println("in");

                    res=Math.max(res,with*heigt);

                    ptr--;

                }

                stack[ptr++]=j;
            }
        }

        return res;
        
    }
}