class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        int len=words[0].length();

        

       // HashSet<String>set=new HashSet<>();

       HashMap<String,Integer>sMap=new HashMap<>();

        for(String str: words){
        //    set.add(str);
              sMap.put(str,sMap.getOrDefault(str,0)+1);
        }

        

        HashMap<String,Integer>cnt=new HashMap<>();

       // StringBuilder sb=new StringBuilder();

        List<Integer> list=new ArrayList<>();

        if(s.length() < words.length * len){
            return list;//减枝
        }

        String tmp="";

        for(int pre=0;pre<len;pre++){
            int l=pre;

            for(int r=pre+len;r<=s.length();r=r+len){
                String now=s.substring(r-len,r);
                if(!sMap.containsKey(now)){
                    l=r;
                    //sb.setLength(0);
                    cnt.clear();
                    continue;
                }
                if(r-l>words.length*len){
                     tmp=s.substring(l,l+len);
                    if(cnt.get(tmp)>1){
                        cnt.put(tmp,cnt.get(tmp)-1);

                    }else{
                        cnt.remove(tmp);
                    }
                    
                   // sb.delete(0,len);
                    l+=len;
                }

                while(cnt.containsKey(now) && cnt.get(now)>=sMap.get(now)){
                     tmp=s.substring(l,l+len);
                    if(cnt.get(tmp)>1){
                        cnt.put(tmp,cnt.get(tmp)-1);

                    }else{
                        cnt.remove(tmp);
                    }
                   // sb.delete(0,len);
                    l+=len;
                }

                cnt.put(now,cnt.getOrDefault(now,0)+1);
                //sb.append(now);

                if(r-l==words.length*len ){

                   // String res=sb.toString();

                    //Integer rank= map.get(res);

                    //if(rank==null || rank>l){
                    //    map.put(res,l);
                   // }

                   list.add(l);
                    
                }
            }

            cnt.clear();
           // sb.setLength(0);

        }

     


        return list;
    }
}

/*
1. 使用HashSet的时候要显示的注意这个数字是否存在重复的可能，只要题目没有显示的强调的，就是可以重复的
2. 滑动窗口，可以为了固定窗口大小而进行多个滑动窗口（多次可能是窗口的大小不同，也可能是窗口的初始偏移量不同）
3. 注意剪枝，可能知识一个普通的剪枝就能快很多


 */