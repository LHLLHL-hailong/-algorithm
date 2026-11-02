/**
 * start: 19:32
 * end: 19:39
 * 独立完成
 * 时间：O(mn)
 * 空间：O(1)
 */
class Solution {

    void swap(int[][]matrix,int x1,int y1,int x2,int y2){
        int tmp=matrix[x1][y1];
        matrix[x1][y1]=matrix[x2][y2];
        matrix[x2][y2]=tmp;
    }

    void reverse(int[][]matrix){

        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix[0].length;j++){
                swap(matrix,i,j,j,i);

            }
        }

    }

    void reverse2(int[][]matrix){

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length/2;j++){
                swap(matrix,i,j,i,matrix[i].length-1-j);

            }
        }
    }


    public void rotate(int[][] matrix) {

        reverse(matrix);
        reverse2(matrix);
        
    }
}