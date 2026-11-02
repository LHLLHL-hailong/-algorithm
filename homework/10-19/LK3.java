/**
 * 独立完成（效率低下）
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {

        char[]sc=s.toCharArray();

        HashSet<Character>set=new HashSet<>();

        int len=0;

        int l=-1;

        int MaxLen=0;

        for(int i=0;i<sc.length;i++){
            while(set.contains(sc[i])){
                l++;
                set.remove(sc[l]);
                len--;

            }
            len++;
            set.add(sc[i]);

            MaxLen=Math.max(MaxLen,len);
        }

        return MaxLen;
        
    }
}