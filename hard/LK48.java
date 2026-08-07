class Solution {


    void swap(int[][] matrix , int fx,int fy ,int tx,int ty){

        int tmp=matrix[fx][fy];
        matrix[fx][fy]=matrix[tx][ty];
        matrix[tx][ty]=tmp;

    }

    //上下翻转
    void fix1(int[][] matrix){
        for(int i=0;i<matrix.length/2;i++){
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

    //左右翻转
    void fix3(int[][]matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length/2;j++){
                swap(matrix,i,j,i,matrix[0].length-1-j);
            }
        }




    }
    
    
    public void rotate(int[][] matrix) {

        //fix1(matrix);
        fix2(matrix);
        fix3(matrix);

    }


}

/*
1. 顺时针90=对角线翻转+左右翻转/(上下翻转+对角线翻转)
2. 进行折半变换时，半值不要带等号，因为它是从0开始的，如果半值内带了等号，会导致为偶数时中间被两次交换，导致没有交换
 */