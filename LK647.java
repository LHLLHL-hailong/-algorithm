/**
 * 正规的马拉车算法，需要好好背诵
 */
class Solution {

     public int countSubstrings(String s) {
        char[]c=s.toCharArray();
        char[] cc=new char[2*c.length+3];

        cc[0]='&';
        cc[1]='#';
        int ptr=2;

        for(int i=0;i<c.length;i++){

            cc[ptr++]=c[i];
            cc[ptr++]='#';
        }

        cc[ptr++]='$';

        int []f=new int[cc.length];
        
        int iMax=0;
        int rMax=0;

        int ans=0;

        for(int i=1;i<cc.length-1;i++){

            f[i]= i<rMax?Math.min(rMax-i+1,f[2*iMax-i]) :1;

         //   System.out.println(i-f[i]+1);

            while( cc[i+f[i]] == cc[i-f[i]]){
                f[i]++;
               // System.out.println("in");
            }

            if(i+f[i]-1>rMax){
                rMax=i+f[i]-1;
                iMax=i;
            }

            ans+=f[i]/2;

        }

        return ans;

    }
}


/**
 * 中心拓展法（重点应该是马拉车，还得现学）
 */
// class Solution {
//     public int countSubstrings(String s) {
        
//     }
// }
class Solution {

    int getNum(char[]c ,int left,int right){

        int count=0;

        // System.out.println("left  "+left+"   right" + right);

        while(left>=0 && right<c.length && c[left]==c[right]){
            count++;
            left--;
            right++;

        }

       // System.out.println("  count "+count);

        return count;

    }



    public int countSubstrings(String s) {

        char[] sc=s.toCharArray();

        int res=0;

        res+=getNum(sc,0,0);

        for(int i=1;i<sc.length;i++){
            res+=getNum(sc,i,i);
            res+=getNum(sc,i-1,i);//这里和上面的left和right的对应关系，不要弄错了
        }
        
        return res;
    }
}