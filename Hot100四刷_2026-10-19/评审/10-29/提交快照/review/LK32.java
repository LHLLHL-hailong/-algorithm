/**
 * start: 22:17
 * end: 22:20
 * 独立完成
 * 时间O(n)
 * 空间O(n)
 */
class Solution {
    public int longestValidParentheses(String s) {

        char[]sc=s.toCharArray();

        int res=0;

        int[]stack=new int[sc.length+1];
        int ptr=0;
        stack[ptr++]=-1;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                if(ptr==1){
                    stack[ptr-1]=i;
                }else{

                    res=Math.max(res,i-stack[ptr-2]);
                    ptr--;
                    
                }
            }
        }

        return res;
        
    }
}