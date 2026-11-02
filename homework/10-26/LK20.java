/**
 * start: 10:03
 * end: 10:10
 * 独立完成
 * 时间：O(n)
 * 空间: O(n)
 * 
 */
class Solution {
    public boolean isValid(String s) {


        char[] sc=s.toCharArray();
        int[]stack=new int[sc.length];
        int ptr=0;//tip:需要用栈而不能计数，因为括号需要用正确的序号关闭

        for(int i=0;i<sc.length;i++){
            switch(sc[i]){
                case '(': stack[ptr++]=1; break;
                case '[': stack[ptr++]=2; break;
                case '{': stack[ptr++]=3; break;
                case ')': if(ptr==0||stack[ptr-1]!=1) return false;ptr--; break;
                case ']': if(ptr==0||stack[ptr-1]!=2) return false;ptr--; break;
                case '}': if(ptr==0||stack[ptr-1]!=3) return false;ptr--; break;
            }
        }

        return ptr==0;
        
    }
}