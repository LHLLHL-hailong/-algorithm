
/**
 * 就是代码有点复杂了，思路还可以，比较简单
 */
// class Solution {
//     public String decodeString(String s) {
        
//     }
// }
class Solution {

    StringBuilder repeat(StringBuilder target,int count){

        StringBuilder res=new StringBuilder();

        for(int i=0;i<count;i++){
            res.append(target);

        }
        return res;

    }


    StringBuilder decode(char[]c,int from ,int end,HashMap<Integer,Integer>map){

            int start=from;
            
            StringBuilder sb=new StringBuilder();

            while(start<=end){
                if(c[start]>='0' && c[start]<='9'){
                    StringBuilder nums=new StringBuilder();
                    nums.append(c[start]);
                    start++;

                    while(c[start]>='0' && c[start]<='9'){
                        nums.append(c[start]);
                         start++;
                    }

                    int value=Integer.parseInt(nums.toString());

                    int tfrom=start;
                    int tend=map.get(tfrom);
                    start=tend+1;
                    sb.append(repeat(decode(c,tfrom,tend,map),value));

                }else if(c[start]!='[' && c[start]!=']'){
                    sb.append(c[start]);
                    start++;
                }else{
                    start++;
                }

            }

            return sb;


    }



    public String decodeString(String s) {

        char[]sc=s.toCharArray();

        HashMap<Integer,Integer>map=new HashMap<>();

        int[]stack=new int[s.length()];
        int ptr=0;

        for(int i=0;i<s.length();i++){
            if(sc[i]=='['){
                stack[ptr++]=i;
            }else if(sc[i]==']'){
                map.put(stack[ptr-1],i);
                ptr--;
            }

        }

        return decode(sc,0,sc.length-1,map).toString();
        
    }
}