/**
 * start: 22:08
 * end 22: 15
 * 独立完成
 * 时间：O(n3)
 * 空间：O(n2)
 */
class Solution {

    HashMap<Integer,Integer>map=new HashMap<>();


    int dfs(int from,int end){
        if(from>end) return 1;
        if(from==end) return 1;

        if(map.containsKey(end-from)){
            return map.get(end-from);
        }

        int res=0;



        for(int k=from;k<=end;k++){
           int lnum= dfs(from,k-1);
           int rnum= dfs(k+1,end);

           res+=(rnum*lnum);
        }

        map.put(end-from,res);

        return res;
    }



    public int numTrees(int n) {

        return dfs(1,n);
        
    }
}
