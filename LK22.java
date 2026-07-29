/**
 * 这个还是比较简单的
 */

class Solution {


    List<String>str=new ArrayList<>();

    //right<=left <=n
    void dfs( StringBuilder sb,int n,int left,int right ){

        if(left==right && left==n){
            str.add(sb.toString());
            return ;
        }

        // if(left>n || right >n){
        //     return ;
        // }

        int len=sb.length();

        if(left<n){
            sb.append("(");
            dfs(sb,n,left+1,right);
            sb.setLength(len);
        }

        if(right<left){

            sb.append(")");
            dfs(sb,n,left,right+1);
            sb.setLength(len);
        }

    } 



    public List<String> generateParenthesis(int n) {


        dfs(new StringBuilder(),n,0,0);

        return str;
        
    }
}