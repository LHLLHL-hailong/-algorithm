
/**
 * 独立完成
 * 时间：O(n)
 * 空间：O(n)
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        ArrayList<Integer>res=new ArrayList<>();//这个不能省略（虽然一般测试样例不会这样做的）

        if(p.length()>s.length()){
            return res;
        }

        char[]pc=p.toCharArray();
        char[]sc=s.toCharArray();

        int[]count=new int[26];

        for(char now:pc){
            count[now-'a']++;
        }

        int[] count1=new int[26];

        for(int i=0;i<pc.length;i++){
            count1[sc[i]-'a']++;
        }

        int diff=0;

        for(int i=0;i<26;i++){
            if(count[i]!=count1[i]) diff++;
        }

        //ArrayList<Integer>res=new ArrayList<>();

        if(diff==0){
            res.add(0);
        }

        //s.length()-start=p.length()
        //s.length()-p.length()


        for(int i=1;i<=s.length()-p.length();i++){
            int idx1=sc[i-1]-'a';
            if((--count1[idx1]) == count[idx1]-1){
                diff++;
            }else if(count1[idx1]== count[idx1]){
                diff--;
            }

            // end-i+1=p.length()
            // end=p.length()-1+i

            int idx2=sc[p.length()-1+i]-'a';

            if((++count1[idx2])==count[idx2]+1){
                diff++;
            }else if((count1[idx2])==count[idx2]){
                diff--;
                
            }

            if(diff==0){
                res.add(i);
            }

            
        }

        return res;
    }
}