/**
 * 就是一个简单的搜索，别想多了
 */
class Solution {

int []dx=new int[]{0,1,0,-1};
int []dy=new int[]{1,0,-1,0};


boolean dfs(char[][] board ,int x,int y,char[] word,int cnt,int[][] isVisited){

    if(cnt>=word.length-1){
        return true;
    }


    for(int i=0;i<=3;i++){

        int xx=x+dx[i];
        int yy=y+dy[i];

        if(xx>=0 && yy>=0 &&   xx<board.length && yy<board[0].length){
            if(isVisited[xx][yy]==0 && board[xx][yy]==word[cnt+1]){
                isVisited[xx][yy]=1;
               if( dfs(board,xx,yy,word,cnt+1,isVisited)){
                return true;
               }
                isVisited[xx][yy]=0;
            }

        }

       
    }

     return false;
 
}



    public boolean exist(char[][] board, String word) {


        int[][]isVisited=new int[board.length][board[0].length];

        char[]c=word.toCharArray();    


        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){

                if(board[0][0]==word[0]){

                isVisited[i][j]=1;
                if(dfs(board,i,j,c,0,isVisited)){
                    return true;
                }
                isVisited[i][j]=0;

                }



            }
        }

        return false;
        
    }
}