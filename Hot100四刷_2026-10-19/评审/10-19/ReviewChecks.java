import java.util.*;

/**
 * 独立完成
 * 时间复杂度 O(n)
 * 空间复杂度 O(n)
 */
class Q1 {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer>map=new HashMap<>();


        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }

        return new int[0];
        
    }
}
/**
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Q283 {
    public void moveZeroes(int[] nums) {

        int pre=0;

        for(int i=0;i<nums.length;i++){

            if(nums[i]!=0){
                nums[pre]=nums[i];
                pre++;
            }

        }

        for(int j=pre;j<nums.length;j++){
            nums[j]=0;
        }
        
    }
}
/**
 * 独立完成（效率低下）
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
class Q3 {
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

/**
 * 独立完成
 * 时间：O(n)
 * 空间：O(n)
 */
class Q438 {
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
/**
 * 独立做出
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */

class Q76 {
    public String minWindow(String s, String t) {

        int[] tCount=new int['z'-'A'+1];
        int[] sCount=new int['z'-'A'+1];
        char[] tc=t.toCharArray();
        char[] sc=s.toCharArray();
        int diff=0;

        //A---Z  a--z

        for(char now:tc ){
            tCount[now-'A']++;
        }

        for(int i=0;i<tCount.length;i++){
            if(tCount[i]!=0){
                diff++;
            }
        }

        int l=-1;

        int[]res=new int[]{-1,-1};
        boolean isGet=false;

        //l 没有
        //r 有

        for(int r=0;r<sc.length;r++){

            if(((++sCount[sc[r]-'A'])==tCount[sc[r]-'A'])){
                diff--;
            }

            while(diff<=0 && l<r){
                //System.out.println("l="+l);
                //System.out.println("r="+r);
                if(!isGet|| (r-l)<(res[1]-res[0])){

                   
                    res[0]=l;
                    res[1]=r;
                    isGet=true;
                }
                l++;
                if((--sCount[sc[l]-'A'])==tCount[sc[l]-'A']-1){
                    diff++;
                    
                }
            }
            

        }

        if(!isGet){
            return "";
        }else{

            StringBuilder sb=new StringBuilder();

            for(int j=res[0]+1;j<=res[1];j++){
                sb.append(String.valueOf(sc[j]));
            }



            return sb.toString();
        }



        
    }
}

public class ReviewChecks {
    static Random random = new Random(1019);
    static int[] checks = new int[5];
    static Q1 q1 = new Q1(); static Q283 q283 = new Q283();
    static Q3 q3 = new Q3(); static Q438 q438 = new Q438(); static Q76 q76 = new Q76();
    static void require(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }
    static String word(int length, String alphabet) {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<length; i++) s.append(alphabet.charAt(random.nextInt(alphabet.length())));
        return s.toString();
    }
    static void sum(int[] a, int target) {
        int[] result = q1.twoSum(a.clone(), target);
        require(result.length == 2 && result[0] >= 0 && result[1] >= 0 &&
            result[0] < a.length && result[1] < a.length && result[0] != result[1] &&
            a[result[0]] + a[result[1]] == target, "1: " + Arrays.toString(a));
        checks[0]++;
    }
    static void zeros(int[] a) {
        int[] expected = new int[a.length]; int index = 0;
        for (int value : a) if (value != 0) expected[index++] = value;
        int[] actual = a.clone(); q283.moveZeroes(actual);
        require(Arrays.equals(actual, expected), "283: " + Arrays.toString(a));
        checks[1]++;
    }
    static void distinct(String s) {
        int expected = 0;
        for (int start=0; start<s.length(); start++) {
            Set<Character> seen = new HashSet<>();
            for (int end=start; end<s.length() && seen.add(s.charAt(end)); end++)
                expected = Math.max(expected, end-start+1);
        }
        require(q3.lengthOfLongestSubstring(s) == expected, "3: " + s);
        checks[2]++;
    }
    static void anagrams(String s, String p) {
        List<Integer> expected = new ArrayList<>();
        char[] sorted = p.toCharArray(); Arrays.sort(sorted);
        for (int i=0; i+p.length()<=s.length(); i++) {
            char[] candidate = s.substring(i, i+p.length()).toCharArray();
            Arrays.sort(candidate);
            if (Arrays.equals(sorted, candidate)) expected.add(i);
        }
        List<Integer> actual = q438.findAnagrams(s,p);
        require(expected.equals(actual), "438: s=" + s + ",p=" + p + ",got=" + actual);
        checks[3]++;
    }
    static boolean covers(int[] window, int[] target) {
        for (int i=0; i<128; i++) if (window[i]<target[i]) return false;
        return true;
    }
    static void minWindow(String s, String t) {
        int[] target = new int[128];
        for (char c : t.toCharArray()) target[c]++;
        String expected = ""; int best = Integer.MAX_VALUE;
        for (int start=0; start<s.length(); start++) {
            int[] window = new int[128];
            for (int end=start; end<s.length(); end++) {
                window[s.charAt(end)]++;
                if (covers(window,target)) {
                    if (end-start+1<best) { best=end-start+1; expected=s.substring(start,end+1); }
                    break;
                }
            }
        }
        String actual = q76.minWindow(s,t);
        int[] actualCounts = new int[128];
        for (char c : actual.toCharArray()) actualCounts[c]++;
        require(actual.length()==expected.length() &&
            (best==Integer.MAX_VALUE ? actual.isEmpty() : s.contains(actual) && covers(actualCounts,target)),
            "76: s=" + s + ",t=" + t + ",got=" + actual + ",reference=" + expected);
        checks[4]++;
    }
    public static void main(String[] args) {
        sum(new int[]{3,3},6); sum(new int[]{3,2,4},6);
        sum(new int[]{-1_000_000_000,1,1_000_000_000},0);
        for(int[] a : new int[][]{{0},{1},{0,0,0},{1,0,2,0,3},{0,1,0,3,12}}) zeros(a);
        for(String s : new String[]{"", "a", "aaaa", "abba", "abcabcbb", " ", "aA1!a"}) distinct(s);
        for(String[] pair : new String[][]{{"cbaebabacd","abc"},{"abab","ab"},{"a","aa"},{"aaa","aa"},{"aaab","ab"},{"a","a"},{"baa","aa"}})
            anagrams(pair[0],pair[1]);
        for(String[] pair : new String[][]{{"ADOBECODEBANC","ABC"},{"a","aa"},{"a","a"},{"AAA","AA"},{"aaab","aab"},{"aA","A"},{"A","a"},{"ABBAAC","AAC"},{"abc","Z"}})
            minWindow(pair[0],pair[1]);
        for(int i=0; i<2000; i++) {
            int[] a = new int[2+random.nextInt(25)];
            for(int j=0; j<a.length; j++) a[j]=random.nextInt(41)-20;
            int first=random.nextInt(a.length), second=(first+1+random.nextInt(a.length-1))%a.length;
            sum(a,a[first]+a[second]); zeros(a);
            distinct(word(random.nextInt(24),"abAB !01"));
            anagrams(word(random.nextInt(24),"abc"),word(1+random.nextInt(8),"abc"));
            minWindow(word(random.nextInt(24),"abAB"),word(1+random.nextInt(8),"abAB"));
        }
        StringBuilder large = new StringBuilder();
        for(int i=0; i<100_000; i++) large.append('a');
        require(q3.lengthOfLongestSubstring(large.toString())==1,"3 large"); checks[2]++;
        require(q438.findAnagrams(large.toString(),"aa").size()==99_999,"438 large"); checks[3]++;
        require(q76.minWindow(large.toString(),"aa").equals("aa"),"76 large"); checks[4]++;
        System.out.printf("{\"1\":%d,\"283\":%d,\"3\":%d,\"438\":%d,\"76\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4]);
    }
}
