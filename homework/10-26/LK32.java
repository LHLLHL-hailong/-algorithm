/**
 * start: 10:12
 * end: 10:17
 * 时间： O(n)
 * 空间：O(n)
 */
class Solution {
    public int longestValidParentheses(String s) {

        if(s.length()<=1) return 0;

        char[]sc=s.toCharArray();

        int[]stack=new int[s.length()+1];//tip:多加1
        int ptr=0;

        int maxLen=0;

        stack[ptr++]=-1;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                if(ptr==1){
                    stack[ptr-1]=i;
                }else{
                    maxLen=Math.max(maxLen,i-stack[ptr-2]);
                    ptr--;
                }
            }
        }

        return maxLen;
        
    }
}