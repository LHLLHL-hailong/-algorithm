
/**
 * start: 10:32
 * end: 11:04
 * 独立完成
 * 时间空间无法计算复杂度
 */
class Solution {


    StringBuilder repeat(int count,StringBuilder base){

       // System.out.println(count);
        //System.out.println(base.toString());

        StringBuilder now=new StringBuilder();

        for(int i=0;i<count;i++){

            now.append(base);

        }

        return now;
    }

    StringBuilder build(char[] expression,int from,int to,HashMap<Integer,Integer>map){

        StringBuilder now=new StringBuilder();

        if(from>to) return now;

        if(from==to){
            now.append(String.valueOf(expression[from]));

            return now;
        }

        //if(expression[from]>='0' && expression[from]<= )

        for(int i=from;i<=to;){
            if(expression[i]>='a' && expression[i]<='z'){
                now.append(String.valueOf(expression[i]));
                i++;
            }else{
                StringBuilder num=new StringBuilder();

                while(expression[i]!='['){
                    num.append(String.valueOf(expression[i]));
                    i++;
                }

                int count=Integer.parseInt(num.toString());
                int right=map.get(i);
                
                StringBuilder next=build(expression,i+1,right-1,map);

                now.append(repeat(count,next));

            
                i=right+1;

            }
        }

        return now;

    }



    public String decodeString(String s) {

        HashMap<Integer,Integer>map=new HashMap<>();

        char[]sc=s.toCharArray();

        int[]stack=new int[s.length()];
        int ptr=0;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='['){
                stack[ptr++]=i;
            }else if(sc[i]==']'){
                map.put(stack[ptr-1],i);
                ptr--;
            }
        }

        return build(sc,0,sc.length-1,map).toString();


        
    }
}