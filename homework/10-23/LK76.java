/**
 * start: 18:33
 * end: 18:47
 * 独立完成
 * 时间： O(m+n)
 * 空间： O(1) ('z'-'A'+1 大小固定，其他的都是单独的变量)
 */
class Solution {
    public String minWindow(String s, String t) {

        int[]count1=new int['z'-'A'+1];
        int[]count=new int['z'-'A'+1];

        for(int i=0;i<t.length();i++){
            count1[t.charAt(i)-'A']++;
        }

        int diff=0;

        int minWindows=Integer.MAX_VALUE/2;
        int[]res=new int[]{-1,-1};

        for(int i=0;i<'z'-'A'+1;i++){
            if(count1[i]>count[i]) diff++;
        }

        if(diff<=0) return "";

        int l=-1;

        for(int r=0;r<s.length();r++){
            ++count[s.charAt(r)-'A'];

            if(count[s.charAt(r)-'A']==count1[s.charAt(r)-'A']){
                diff--;
            }

            while(diff<=0){

                if(r-l<minWindows){

                    res[0]=l;
                    res[1]=r;

                    minWindows=r-l;
                }

              //  minWindows=Math.min(minWindows,r-l);

                l++;
                --count[s.charAt(l)-'A'];

                if(count[s.charAt(l)-'A']==count1[s.charAt(l)-'A']-1){
                    diff++;
                }
            
            }
        }

        if(minWindows==Integer.MAX_VALUE/2){
            return "";
        }else{

            return s.substring(res[0]+1,res[1]+1);

        }

        
    }
}