class Solution {


    public String longestPalindrome(String s) {


        if(s.length()==0)return "";

        char [] cc=s.toCharArray();

        int maxLen=0;
        int ll=0;
        int rr=0;

        for(int i=0;i<cc.length;i++){

           int[] item= getMax(cc,i,i);
            int len=item[1]-item[0]+1;

            if(len>maxLen){
                maxLen=len;
                ll=item[0];
                rr=item[1];
            }


        }

        for(int i=0;i+1<cc.length;i++){

            int[] item= getMax(cc,i,i+1);
            int len=item[1]-item[0]+1;

            if(len>maxLen){
                maxLen=len;
                ll=item[0];
                rr=item[1];
            }


            

        }

        System.out.println(rr-ll+1);

        return s.substring(ll,rr+1);



    }


    int[] getMax(char[] cc,int l,int r){

        if(cc[l]!=cc[r]) return new int[]{l,l};

        while(l-1>=0 && r+1<cc.length && cc[l-1]==cc[r+1]){

            l--;
            r++;
        }
        return new int[]{l,r};
        


    }
    
    
}




class Solution {


    public String longestPalindrome(String s) {

        char[] cc=s.toCharArray();

        if(cc.length==0) return "";


        int maxLen=1;
        int l=0;
        int r=0;

        boolean[][]dp=new boolean[cc.length][cc.length];


        for(int i=0;i<cc.length;i++) dp[i][i]=true;

        for(int i=0;i+1<cc.length;i++) {

            
            if(cc[i]==cc[i+1]){

                dp[i][i+1]=true;
                maxLen=2;
                l=i;
                r=i+1;


            }
           

        }




        for(int i=cc.length-2;i>=0;i--){
            for(int j=i+2;j<cc.length;j++){

                dp[i][j]=(dp[i+1][j-1] && c[i]==c[j]);

                if(dp[i][j] && j-i+1 >maxLen){
                    maxLen=j-i+1;
                    l=i;
                    r=j;
                }


            }
        }


        return s.substring(l,r+1);

        





    }
    
    
    
}