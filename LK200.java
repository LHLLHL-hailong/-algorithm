
/**
 * 有一个需要注意的点，其他还好
 */
class Solution {

        int[]dx={0,1,0,-1};
        int[]dy={1,0,-1,0};


        void dfs(char[][]grid,int x,int y){

            for(int i=0;i<=3;i++){
                int xx=x+dx[i];
                int yy=y+dy[i];
                if(xx>=0 && yy>=0 && xx<grid.length && yy<grid[0].length){
                    if(grid[xx][yy]=='1'){
                        grid[xx][yy]='0';//这里有一个尤其需要注意的点，这里是通过改原数组，而不是建立isVisited极大的优化
                        dfs(grid,xx,yy);
                    }
                }

            }


        }

    public int numIslands(char[][] grid) {


        boolean [][]isVisited=new boolean[grid.length][grid[0].length];

        int count=0;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){

                if(grid[i][j]=='1' ){

                    count++;

                    grid[i][j]='0';

                    dfs(grid,i,j);
                }

            }

        }

        return count;

    }
}

