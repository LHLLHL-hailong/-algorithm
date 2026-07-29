/**
 * 这道题遗忘得很厉害，而如果说不记得，这种写法可能会写得比较复杂，甚至可能会写成屎山代码
 */
class Solution {
    public String minWindow(String s, String t) {

        char[] sc=s.toCharArray();
        char[] tc=t.toCharArray();

        HashMap<Character,Integer>map=new HashMap<>();

        HashMap<Character,Integer>target=new HashMap<>();

        int tragetLen=0;

        for(char c:tc){

            if(target.get(c)==null){
                tragetLen++;
                target.put(c,1);
                map.put(c,0);

            }else{
                target.put(c,target.get(c)+1);

            }
        }

        int l=0;
        int r=-1;

        int nowV=0;

        int minLen=Integer.MAX_VALUE/2;
        int[] res=new int[2];

        while(r<sc.length-1){
            r++;
            if(map.containsKey(sc[r])){
                int count=map.get(sc[r]);
                if(count+1==target.get(sc[r])){
                    nowV++;
                }
                map.put(sc[r],count+1);
            }

                 if(nowV>=tragetLen && r-l+1<minLen){
                    minLen=r-l+1;
                    res=new int[]{l,r};
                }

            while(l<=r && nowV>=tragetLen ){

                if(map.containsKey(sc[l])){
                    int count =map.get(sc[l]);
                    if(count==target.get(sc[l])){
                        nowV--;
                    }
                    map.put(sc[l],count-1);
                    l++;
                }

                if(nowV>=tragetLen && r-l+1<minLen){
                    minLen=r-l+1;
                    res=new int[]{l,r};
                }
                
            }
        }

        return sc.substring(res[0],res[1]+1);

    }
}



// class Solution {
//     public String minWindow(String s, String t) {

//         HashMap<Character,Integer>map=new HashMap<>();

//         char[] sc=s.toCharArray();
//         char[] tc=t.toCharArray();

//         int minLen=0;
//         int start=0;

//         for(char cc:tc){
//             Integer now=map.get(cc);
//             map.put(cc,now==null?1:now+1);
//         }

//         int now=-1;

//         HashSet<Character>set=new HashSet<>(map.keySet());

//         while(now+1<s.length()){
//             now++;
//             Integer count=map.get(sc[now]);
//             if(count!=null){
//                 if(count==1){
//                     map.remove(sc[now]);
//                     if(map.isEmpty()){
//                         break;
//                     }
//                 }else{
//                     map.put(sc[now],count-1);
//                 }
//             }
//         }

//         if(!map.isEmpty()){
//             return "";
//         }

//         int r=now;//r在窗口里面
//         int l=0;//l也在窗口里面

//         minLen=r-l+1;
//         start=l;
//         Character target=null;

//         while(true){
//             while(target==null && l<=r){
//                 l++;
//                 if(set.contains(sc[l-1])){
//                     target=sc[l-1];
//                     break;
//                 }else{
//                     if(r-l+1<minLen){
//                         minLen=r-l+1;
//                         start=l;
//                     }
//                 }

//             }

//             r++;
//             if(r>=s.length()) break;
//             if(target==sc[r]){
//                 target=null;
//             }


//         }

        

//         return s.substring(start,start+minLen);

//     }
// }