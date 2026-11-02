
/**
 * start 22:25
 * end:22:28
 * 独立完成
 * 时间：O（n2）
 * 空间O(1)
 */
class Solution {


    void swap(int[][] matrix , int fx,int fy ,int tx,int ty){

        int tmp=matrix[fx][fy];
        matrix[fx][fy]=matrix[tx][ty];
        matrix[tx][ty]=tmp;

    }


    void fix1(int[][] matrix){
        for(int i=0;i<matrix.length/2;i++){
            for(int j=0;j<matrix[i].length;j++){
                swap(matrix,i,j,matrix.length-1-i,j);
            }
            
        }

    }


    void fix2(int[][] matrix){


        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<=i;j++){
                swap(matrix,i,j,j,i);
            }
        }


    
    }
    
    
    public void rotate(int[][] matrix) {

        fix1(matrix);
        fix2(matrix);

    }


}
