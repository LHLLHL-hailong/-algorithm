/**
 * 这道题代码很简单，但是思路很难想出来
 */
class Solution {



    public boolean searchMatrix(int[][] matrix, int target) {

        int x=0;
        int y=matrix[0].length-1;

        while(x<matrix.length && y>=0){
            if(matrix[x][y]==target){
                return true;
            }else if(target  >matrix[x][y]){//注意谁是target谁是matrix[x][y] 别弄乱了
                x++;
            }else{
                y--;
                
            }

        }
        return false;

        
        
    }
}