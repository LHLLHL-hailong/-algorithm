class Solution {
    public String minWindow(String s, String t) {

        char[] sc=s.toCharArray();
        char[] tc=t.toCharArray();

        int[]tar=new int['z'-'A'+1];
        int[]now=new int['z'-'A'+1];
        int diff=0;

        for(char c:tc){
            tar[c-'A']++;
        }

        for(int i=0;i<tar.length;i++){
            if(now[i]!=tar[i]){
                diff++;
            }
        }

        int minLen=Integer.MAX_VALUE/2;
        int ll=0;
        int rr=0;

        if(diff==0) return "";

        int l=-1;

        for(int r=0;r<sc.length;r++){

            int idx=sc[r]-'A';
            now[idx]++;
            if(now[idx]==tar[idx]){
                diff--;
            }

            while(diff==0){
               // minLen=Math.min(minLen,r-l);
               if(r-l<minLen){
                minLen=r-l;
                ll=l+1;
                rr=r+1;
               }
                l++;
                int idx2=sc[l]-'A';
                now[idx2]--;

                if(now[idx2]+1==tar[idx2]){
                    diff++;
                }
         
            }
        }

        if(minLen==Integer.MAX_VALUE/2){
            return "";
        }else{
            return s.substring(ll,rr);
        }


    }
}

/*
#know
1. 'z'-'A'+1 因为ascii从小到大的顺序是 'A'->'Z' 'a'->'z' 所以这么做就可以替代传统的HashMap
2. 使用diff来加速

#review
1. 这个算法是临时想起的，比原先的算法效率更高的一种方法，而他的熟悉度不够高,下一次不一定能复现
2. 一开始我算错了Di更新的时间机,原先的语义是超过了也不行，而现在的语义是超过是可以的
综合评价: C-(必须进行重新复习)

 */