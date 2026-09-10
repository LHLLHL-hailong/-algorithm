class Solution {

            int extend(char[]cc,int point,int len){

                len++;
                while(point+len<cc.length && point-len>=0 && cc[point+len]==cc[point-len]){

                    len++;
                }
                 return len-1;
                
            }

            int getCount(char[]cc,int point,int len){

                //a # a # a---> len/2+1 //拓展后的半径不会出现！
                //a # a #-----> len/2+1 <--> (len+1)/2
                //# a # a #---> len/2  <--> (len+1)/2
                //# a # a-->len/2+1// 拓展后的半径不会出现！

                // if(cc[point]=='#' && cc[point+len]=='#'){
                //     return len/2;
                // }else{
                //     return len/2+1;
                // }

                return (len+1)/2;
                
            }

            public int countSubstrings(String s) {
                char[]sc=s.toCharArray();

                //  # B # a # B #
                //   # a # a #

                int[]len=new int[sc.length*2+1];
                char[] cc=new char[sc.length*2+1];
                int ptr=0;
                cc[ptr++]='#';
                for(int i=0;i<sc.length;i++){
                    cc[ptr++]=sc[i];
                    cc[ptr++]='#';
                }

                int piviot=-1;
                int right=-1;

                int res=0;

                for(int i=0;i<cc.length;i++){

                    int len1=0;

                    if(i<right){
                        len1=Math.min(right-i,len[2*piviot-i]);
                    }

                    len1=extend(cc,i,len1);

                    len[i]=len1;

                    if(i+len1>right){
                        piviot=i;
                        right=i+len1;
                    }

                    res+=getCount(cc,i,len1);

                }

                return res;
            
            }
}