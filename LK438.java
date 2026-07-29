
/**
 * 这个需要好好记一下
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] sc=s.toCharArray();
        char[] pc=p.toCharArray();
        int [] count=new int[26];
        int diff=0;
        ArrayList<Integer>list=new ArrayList<>();

        int win=p.length();


        if(win>s.length()){
            return new ArrayList<>();
        }

        for(int i=0;i<win;i++){
            count[sc[i]-'a']++;
            count[pc[i]-'a']--;
        }

        for(int i=0;i<26;i++){
            if(count[i]!=0){
                diff++;
            }

        }

        if(diff==0){
            list.add(0);
        }

        for(int i=1;i+win-1<s.length();i++){//因为这个窗口的大小是固定的
            int left=sc[i-1]-'a';
            count[left]--;
            if(count[left]==-1){
                diff++;
            }else if(count[left]==0){
                diff--;
            }

            int right=sc[i+win-1]-'a';
            count[right]++;
            if(count[right]==0){
                diff--;

            }else if(count[right]==1){
                diff++;
            }

            if(diff==0){
                list.add(i);
            }

        }

        return list;

    }

    
}




/**
 * 很麻烦
 */
class Solution {


    void reset(HashSet<Character>set,HashMap<Character,Integer>map){
        for(Character c:set){
            map.put(c,0);
        }

    }



    public List<Integer> findAnagrams(String s, String p) {

        char[] pc=p.toCharArray();
        char[] sc=s.toCharArray();

        Map<Character,Integer>map=new HashMap<>();
        Map<Character,Integer>nowMap=new HashMap<>();
        for(char c:pc){
            map.put( c,map.getOrDefault(c,0)+1);
            nowMap.put(c,0);
        }

        Set<Character> set=map.keySet();

        int l=0;
        int get=0;//到达
        int more=0;//过了
        ArrayList<Integer>total=new ArrayList<>();

        for(int r=0;r<sc.length;r++){
            if(set.contains(sc[r])){
                int num=nowMap.get(sc[r]); 
                nowMap.put(sc[r],num+1);  

                if(num+1==map.get(sc[r])){
                    get++;
                }else if(num==map.get(sc[r])){
                    more++;
                }

                while(get==set.size()){

                    if(more==0){
                        total.add(l);
                    }
                int num=nowMap.get(sc[l]); 
                nowMap.put(sc[l],num-1);

                if(num==map.get(sc[l])){
                    get--;
                }else if(num-1==map.get(sc[l])){
                    more--;
                    
                }
                l++;


                }



            }else{
                l=r+1;
                reset(set,nowMap);
                get=0;
                more=0;
            }
            
        }
        return total;

        
    }
}