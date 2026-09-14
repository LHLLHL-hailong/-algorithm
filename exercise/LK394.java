class Solution {



    StringBuilder repeat( StringBuilder res,int count){

        StringBuilder ans=new StringBuilder();

        for(int i=0;i<count;i++){
            ans.append(res);
        }

        return ans;

    }



    StringBuilder decode(HashMap<Integer,Integer>map,int from,int to,char[]sc){
        
        StringBuilder sb=new StringBuilder();

        for(int i=from;i<=to;i++){

            if( sc[i]>='0' && sc[i]<='9' ){

                StringBuilder numStr=new StringBuilder();

                while( sc[i]>='0' && sc[i]<='9' ){

                    numStr.append(String.valueOf(sc[i]));
                    i++;
                }
                //i--->[
                int ll=i+1;

                i=map.get(i);
                int rr=i-1;
                i++;

                sb.append(repeat(decode(map,ll,rr,sc),Integer.parseInt(numStr)));


            }else{
                sb.append(String.valueOf(sc[i]));
            }

        }

        return sb;


    }


    public String decodeString(String s) {

        HashMap<Integer,Integer>map=new HashMap<>();

        char[]sc=s.toCharArray();

        int[]stack=new int[sc.length];
        int ptr=0;

        
        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                map.put(stack[ptr-1],i);
                ptr--;
            }
        }

        return decode(map,0,sc.length-1,sc).toString();
        
    }
}