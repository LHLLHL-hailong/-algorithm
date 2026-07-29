/**
 * 我记得方法是两边分别变一次，这个方法可能是更好的，而且实现也比较简单。这个最好记一下
 */

class Solution {
    public int longestValidParentheses(String s) {

        if(s.length()==0) return 0;//这一点需要注意，它有空测试，在这里边空测试会报错

        char[]c=s.toCharArray();

        int[]stack=new int[c.length+1];
        int ptr=0;

        stack[ptr++]=-1;

        int maxLen=0;

        for(int i=0;i<c.length;i++){

            if(c[i]=='('){

                stack[ptr++]=i;
            }else{
                ptr--;
                if(ptr==0){
                    stack[ptr++]=i;
                }else{
                    maxLen=Math.max(maxLen,i-stack[ptr-1]);
                }


            }

        }

        return maxLen;

    }
}

/**
 * 这个是两边遍历的初版
 */

class Solution {
    public int longestValidParentheses(String s) {

        int maxLen=0;

        int leftNum=0;

        char[] c=s.toCharArray();

        int len=0;

        for(int i=0;i<nums.length;i++){

            if(c[i]=='('){
                leftNum++;
                len++;
            }else{
                if(leftNum>=1){
                    leftNum--;
                    len++;
                }else{
                    leftNum=0;
                    maxLen=Math.max(maxLen,len);
                    len=0;
                }


            }
        }

        maxLen=Math.max(maxLen,len);
        len=0;

        int rightNum=0;

        for(int j=nums.length-1;j>=0;j--){

            if(c[i]==')'){
                rightNum++;
                len++;
            }else{
                if(rightNum>=1){
                    rightNum--;
                    len++;
                }else{
                    rightNum=0;
                    maxLen=Math.max(maxLen,len);
                    len=0;
                }


            }
        }

        maxLen=Math.max(maxLen,len);

        return maxLen;

 
 
    }
}

/**
 * 这个是两边遍历的改进版
 */
class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int leftNum = 0;
        char[] c = s.toCharArray();
        int len = 0;

        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                leftNum++;
                len++;
            } else {
                leftNum--;
                len++;
            }

            if (leftNum == 0)
                maxLen = Math.max(maxLen, len);
            else if (leftNum < 0) {
                leftNum = 0;
                len = 0;
            }

            // System.out.println(len);
        }

        len = 0;
        int rightNum = 0;

        for (int j = c.length - 1; j >= 0; j--) {
            if (c[j] == ')') {
                rightNum++;
                len++;
            } else {
                rightNum--;
                len++;
            }

            if (rightNum == 0)
                maxLen = Math.max(maxLen, len);
            else if (rightNum < 0) {
                rightNum = 0;
                len = 0;
            }
        }

        return maxLen;
    }
}
