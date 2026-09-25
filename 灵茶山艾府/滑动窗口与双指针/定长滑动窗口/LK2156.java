class Solution {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {

       // char demo='a';

        //System.out.println((char)((int)demo-1));

       // System.out.println('e'-'`');

        long[]ps=new long[k+1];
        ps[0]=1;
        ps[1]=power;

        for(int i=2;i<=k;i++){
            ps[i]=(ps[i-1]*power)%modulo;
        }

        long res=0L;
        //String str="";
        for(int i=s.length()-k;i<s.length();i++){
            res=(res+(s.charAt(i)-'`')*ps[k+i-s.length()])%modulo;
            if(res<0){
                res=(res+modulo)%modulo;
            }
           // System.out.println("--->"+(s.charAt(i)-'`')*ps[k+i-s.length()]);
        }

        //k+s.length()-k-s.length()

      //  System.out.println(res);


        int start=-1;

        if(res==hashValue){
            //str=s.substring(s.length()-k,s.length());
            start=s.length()-k;
            
        }

        for(int i=s.length()-k-1;i>=0;i--){
            res=(res*power+(s.charAt(i)-'`')-(s.charAt(i+k)-'`')*ps[k])%modulo;
            if(res<0){
                res=(res+modulo)%modulo;
            }
            if(res==hashValue){
               // str=s.substring(i,i+k);
               start=i;
               // System.out.println("in");
            }
           // System.out.println(res);
        }

        return s.substring(start,start+k);

        
    }
}
/*
     取模后的值还能参与加、减、乘，不能直接参与整数除法。
     因为被取模丢掉的是 m 的倍数；乘完仍是 m 的倍数，除完则未必。这是本题能滚动更新的依据。
     窗口长度未知时，看看能否通过枚举一个取值范围很小的量，把长度确定下来。

 */