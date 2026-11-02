/**
 * 独立做出
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */

class Solution {
    public String minWindow(String s, String t) {

        int[] tCount=new int['z'-'A'+1];
        int[] sCount=new int['z'-'A'+1];
        char[] tc=t.toCharArray();
        char[] sc=s.toCharArray();
        int diff=0;

        //A---Z  a--z

        for(char now:tc ){
            tCount[now-'A']++;
        }

        for(int i=0;i<tCount.length;i++){
            if(tCount[i]!=0){
                diff++;
            }
        }

        int l=-1;

        int[]res=new int[]{-1,-1};
        boolean isGet=false;

        //l 没有
        //r 有

        for(int r=0;r<sc.length;r++){

            if(((++sCount[sc[r]-'A'])==tCount[sc[r]-'A'])){
                diff--;
            }

            while(diff<=0 && l<r){
                //System.out.println("l="+l);
                //System.out.println("r="+r);
                if(!isGet|| (r-l)<(res[1]-res[0])){

                   
                    res[0]=l;
                    res[1]=r;
                    isGet=true;
                }
                l++;
                if((--sCount[sc[l]-'A'])==tCount[sc[l]-'A']-1){
                    diff++;
                    
                }
            }
            

        }

        if(!isGet){
            return "";
        }else{

            StringBuilder sb=new StringBuilder();

            for(int j=res[0]+1;j<=res[1];j++){
                sb.append(String.valueOf(sc[j]));
            }



            return sb.toString();
        }



        
    }
}