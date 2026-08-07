/**
 * 注意:这道题只是给出了一道新的算法,并非是对原有算法的复习
 */
class Solution {

    int extend(char[] tar,int left,int right){

        while(left>=0 && right<tar.length && tar[left]==tar[right]){

            left--;
            right++;

        }

        return (right-left-2)/2;

    }

    public String longestPalindrome(String s) {

        if(s.length()==0) return "";

        char[]sc=s.toCharArray();

        char[]tar=new char[sc.length*2+1];
        int[] len= new int[tar.length]; 
        int ptr=0;

        tar[ptr++]='#';

        for(int i=0;i<sc.length;i++){

            tar[ptr++]=sc[i];
            tar[ptr++]='#';

        }


        int ini=-1; int right=-1;

        int start=0; int end=-1;

        for(int i=0;i<tar.length;i++){

            int cur=0;

            if(i<right){

                int ini_len=Math.min(len[2*ini-i],right-i) ;

                cur=extend(tar,i-ini_len,i+ini_len);

            }else{
                cur=extend(tar,i,i);
            }

            len[i]=cur;


            if(i+cur>right){
                right=i+cur;
                ini=i;
            }

            if(2*cur+1 > end-start+1){
                end=i+cur;
                start=i-cur;

            }

        }

        StringBuilder sb=new StringBuilder();

        for(int i=start;i<=end;i++){

            if(tar[i]!='#'){
                sb.append(tar[i]);
            }


        }

        return sb.toString();

        
    }
}

/*
##review

综合评价:C-(想要真正能写出来，还是需要复习的)

 */