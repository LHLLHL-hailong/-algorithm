class Solution {
    public int minFlips(String s) {

        char[] sc=s.toCharArray();

        int count0=0;
        char cnt0='0';
        int count1=0;
        char cnt1='1';
        char tmp='?';


        for(int i=0;i<sc.length;i++){
            if(sc[i]!=cnt0){
                count0++;
            }

            if(sc[i]!=cnt1){
                count1++;
            }

            tmp=cnt0;
            cnt0=cnt1;
            cnt1=tmp;
        }

        int res=Math.min(count0,count1);

        // System.out.println(count0);
        // System.out.println(count1);

        if(sc.length%2==0){

            return res;
            
        }

        for(int i=1;i<s.length();i++){

            int pre0=count0;
            int pre1=count1;

                if(sc[i-1]=='0'){
                    count0=pre1-1;
                    count1=pre0+1;
                }else{
                    count0=pre1+1;
                    count1=pre0-1;
                }

            
             res=Math.min(res,Math.min(count0,count1));

        }

        return res;
 
    }
}

/*
相邻状态高度相似时，用增量更新维护答案
复用原有的状态，并不一定一定要从小的数据量大到的数据量的状态的转移，
同等数据量的情况下，或许我们可以通过增量更新的方式更改小部分，来实现从一种状态转移到另一种状态（注意，这里说的不是dp）
也就是类似于定长窗口
 */