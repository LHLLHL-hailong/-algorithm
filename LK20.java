
/**
 * 这个题还是比较简单的
 */

class Solution {
    public boolean isValid(String s) {


        char[] c=s.toCharArray();

        int[] stack=new int[c.length];
        int ptr=0;




        for(char cc:c){

            switch(cc){
                case '(' : stack[ptr++]=1;break;
                case '[':  stack[ptr++]=2;break;
                case '{': stack[ptr++]=3;break;
                case ')': if( ptr==0 || stack[ptr-1]!=1) return false; else ptr-- ; break;
                case ']' :if(ptr==0 || stack[ptr-1]!=2) return false; else ptr--;break;
                case '}': if(ptr==0 || stack[ptr-1]!=3) return false; else ptr --;break;
            
            }

        }

        return ptr==0;


    }
}