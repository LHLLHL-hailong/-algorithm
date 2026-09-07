class Solution {


    int extend(char[]tar,int l,int r){

        int res=0;
        l--;
        r++;
        while(l>=0 && r<tar.length && tar[l]==tar[r]){
            l--;
            r++;
            res++;
        }

        return res;
    }


    public String longestPalindrome(String s) {

        char[]sc=s.toCharArray();

        char[] tar=new char[sc.length*2+1];
        int ptr=0;
        tar[ptr++]='#';
        for(char cc:sc){
            tar[ptr++]=cc;
            tar[ptr++]='#';
        }

        int[]next=new int[tar.length];

        int middle=-1;

        int right=-1;

        int maxlen=0;
        int []limit=new int[2];

        for(int i=0;i<tar.length;i++){

            int leen=0;

            if(i<right){
                leen=Math.min(next[2*middle-i],right-i);
            }

            leen+=extend(tar,i-leen,i+leen);

            next[i]=leen;

            if(2*leen+1>maxlen){
                maxlen=2*leen+1;
                limit[0]=i-leen;
                limit[1]=i+leen;
            }

            if(i+leen>right){
                right=i+leen;
                middle=i;

            }
        }

        StringBuilder result=new StringBuilder();

        for(int j=limit[0];j<=limit[1];j++){

            if(tar[j]!='#'){
                result.append(String.valueOf(tar[j]));
            }
            
        }

        return result.toString();

        

    }
}


class Solution {
        public int longestPalindrome(String s) {

            char[]sc=s.toCharArray();

            boolean [][]dp=new boolean[s.length()][s.length()];

            int maxLen=0;

            for(int i=0;i<nums.length;i++){
                dp[i][i]=true;
                maxLen=Math.max(maxLen,1);
                if(i!=nums.length-1 && sc[i]==sc[i+1]){
                    dp[i][i+1]=true;
                    maxLen=Math.max(maxLen,2);
                    
                }
            }

            for(int i=nums.length;i>=0;i--){
                for(int j=i+2;j<=nums.length-1;j++){

                    dp[i][j]=(sc[i]==sc[j] && dp[i+1][j-1]);
                    if(dp[i][j]){
                        maxLen=Math.max(maxLen,j-i+1);
                    }

                }
            }

            return maxLen;


        }
    
}
