


/**
 * 这个是标准的解法，需要重点背诵
 */
// class Solution {
//     public List<String> removeInvalidParentheses(String s) {
        
//     }
// }

class Solution {


    boolean isValid(char[] tar){

        int leftCount=0;

        for(char now :tar){
            if(now=='('){
                leftCount++;
            }else if(now==')'){
                leftCount--;
                if(leftCount<0){
                    return false;
                }
            }
            
        }

        return leftCount==0;

    }

    String build(char[] tar){

        //System.out.println(new String(tar));


        StringBuilder sb=new StringBuilder();

        for(char c:tar){
            if(c!='#'){
                sb.append(String.valueOf(c));
            }

        }

        return sb.toString();

    }







    void dfs( ArrayList<String>res,char[]target,int startIdx,int leftCount,int rightCount){

        if(leftCount==0 && rightCount==0){
            if(isValid(target))
            res.add(build(target));
            return ;
        }

        
                //can: target.length-i
                //need: leftCount+rightCount
                //target.length-i>=leftCount+rightCount
                //i<=target.length-leftCount-rightCount
        for(int i=startIdx;i<=target.length-leftCount-rightCount;i++){

            if(i!=startIdx && target[i]==target[i-1]){
                continue;
            }
            if(target[i]=='(' && leftCount>0){
                    target[i]='#';
                    dfs(res,target,i+1,leftCount-1,rightCount);
                    target[i]='(';

            }

            if(target[i]==')' && rightCount>0){
                    target[i]='#';
                    dfs(res,target,i+1,leftCount,rightCount-1);
                    target[i]=')';
                

            }
            
        }
        
    }

    public List<String> removeInvalidParentheses(String s) {

        char[]sc=s.toCharArray();

        int leftCount=0;
        int rightCount=0;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                leftCount++;
            }else if(sc[i]==')') {

                if(leftCount>0){
                    leftCount--;
                }else{
                    rightCount++;
                }
            }

        }

        ArrayList<String>res=new ArrayList<>();

        dfs(res,sc,0,leftCount,rightCount);

        return res;



        
    }
}

/**
 * 这个方法也比较新颖，但是效率并不高，实现的也不是十分优雅
 */
class Solution {
        boolean isValid(char[] tar){

        int leftCount=0;

        for(char now :tar){
            if(now=='('){
                leftCount++;
            }else if(now==')'){
                leftCount--;
                if(leftCount<0){
                    return false;
                }
            }
            
        }

        return leftCount==0;

    }


        public List<String> removeInvalidParentheses(String s) {

            HashSet<String>set=new HashSet<>();

            HashSet<String>set1=new HashSet<>();

            set.add(s);



            while(true){
                for(String sss:set){
                    if(isValid(sss.toCharArray())){
                        set1.add(sss);

                    }

                }

                if(!set1.isEmpty()){
                    return new ArrayList<String>(set1);
                }

                for(String ss:set){

                    
                    //[0,i) [i+1,ss.length())//fixme:最后一个索引可能会越界，api兼容未知
                    for(int i=0;i<ss.length();i++){
                        if(ss.charAt(i)=='(' ||ss.charAt(i)==')'  )
                        set1.add(ss.substring(0,i)+ss.substring(i+1,ss.length()));
                    }

                }
                HashSet<String>tmp=set;
                set=set1;
                set1=tmp;

                set1.clear();
            }
        }
}
