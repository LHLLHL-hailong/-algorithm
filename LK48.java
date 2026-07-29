/**
 * 注意这个题先上下，再对角线,因为上下翻转的对应对角线翻转的公式好求，而左右翻转的对应的对角线翻转的公式很难求出来
 */

class Solution {


    void swap(int[][] matrix , int fx,int fy ,int tx,int ty){

        int tmp=matrix[fx][fy];
        matrix[fx][fy]=matrix[tx][ty];
        matrix[tx][ty]=tmp;

    }

    //上下翻转
    void fix1(int[][] matrix){
        for(int i=0;i<matrix.length/2;i++){//注意，这里边不能带等号，虽然它是向下取整，但是这里边length是从1开始算的
            for(int j=0;j<matrix[i].length;j++){
                swap(matrix,i,j,matrix.length-1-i,j);
            }
        }

    }


//对角线翻转
    void fix2(int[][] matrix){


        for(int i=0;i<matrix.length;i++){

            for(int j=0;j<=i;j++){
                swap(matrix,i,j,j,i);
            }
        }

      //  for(int i=0;i<=matrix.length/2;i++){
       //     for(int j=0;j<=matrix[i].length/2;j++){
                
       //     }
       // }

    
    }
    
    
    public void rotate(int[][] matrix) {

        fix1(matrix);
        fix2(matrix);

    }


}
