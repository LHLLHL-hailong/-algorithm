class Solution {


    // String getStr(char[]sc,int from,int end){

    //     StringBuilder sb=new StringBuilder();

    //     for(int i=from;i<=end;i++){
    //         sb.append(String.valueOf(sc[i]));
    //     }

    //     return sb.toString();
    // }



    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        char[]sc=s.toCharArray();
        HashMap<String,Integer>map=new HashMap<>();

        int winSize=minSize;

       // for(int winSize=minSize;winSize<=maxSize;winSize++){
            int[]count=new int[26];
            int diff=0;

            

            for(int i=0;i<winSize;i++){
                if(++count[sc[i]-'a']==1){
                    diff++;
                }
            }

            if(diff<=maxLetters){
               // String now=getStr(sc,0,winSize-1);

                String now=s.substring(0,winSize);
                map.put(now,map.getOrDefault(now,0)+1);
            }

            for(int i=winSize;i<sc.length;i++){
                if(++count[sc[i]-'a']==1){
                    diff++;
                }

                if(--count[sc[i-winSize]-'a']==0){
                    diff--;
                }

                if(diff<=maxLetters){
                    //String now=getStr(sc,i-winSize+1,i);
                    String now=s.substring(i-winSize+1,i+1);
                    map.put(now,map.getOrDefault(now,0)+1);         
                }

            }

        //}

        int res=0;

        for(String str:map.keySet()){

            res=Math.max(res,map.get(str));
        }

        return res;
        
    }
}

/*
1. charAt+substring 我比较容易犯的错误就是提前给它转换成char数组,然后需要子创的时候，又从char数中返回去构造
2. 这道题没必要设置多个窗口，因为相比大窗口，小窗口只会比大窗口得到的值大或者相等，而不会更小
 */