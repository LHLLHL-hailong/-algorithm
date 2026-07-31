
class Solution {
    public int longestValidParentheses(String s) {

        char[]sc=s.toCharArray();

        int[]stack=new int[s.length()+1];
        int ptr=0;
        stack[ptr++]=-1;

        int maxLen=0;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                if(ptr>1){
                    maxLen=Math.max(maxLen,i-stack[ptr-2]);
                    ptr--;
                }else{
                    stack[ptr-1]=i;

                }
            }
        }

        return maxLen;

    }
}


class Solution {
    public int longestValidParentheses(String s) {

        char[]sc=s.toCharArray();
        int maxLen=0;

        int left=0;
        int len1=0;

        int right=0;
        int len2=0;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                left++;
                len1++;
            }else{
                left--;
                len1++;
                if(left==0){
                    maxLen=Math.max(maxLen,len1);

                }else if(left<0){
                    left=0;
                    len1=0;
                }

            }

            int j=sc.length-1-i;

            if(sc[j]==')'){
                right++;
                len2++;
            }else{
                right--;
                len2++;

                if(right==0){
                    maxLen=Math.max(maxLen,len2);
                }else if(right<0){
                    right=0;
                    len2=0;
                }
            }
        }

        return maxLen;
        
    }
}

/*
##review

两边遍历的方法记得还可以。但是用栈的方法稍微忘了一些
综合评价：栈方法:C+(还是需要再写一遍)
         两边遍历：B(需要再熟悉一下)
 */