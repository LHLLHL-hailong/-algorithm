import java.util.*;

/**
 * start: 18:25
 * end : 18:29
 * 独立完成
 * 时间O(nm)
 * 空间O(n+k)
 */
class Q139 {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;
        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            set.add(str);
            maxLen=Math.max(maxLen,str.length());
        }

        boolean []dp=new boolean[s.length()+1];

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int len=1;len<=maxLen&& len<=i;len++){
                // start 有
                // i 无
                //i-start=len
                //start=i-len
                //len<=i
                if(set.contains(s.substring(i-len,i))){
                    dp[i]=dp[i]|dp[i-len];
                }
            }

        }

        return dp[s.length()];
        
    }
}
/**
 * start: 18:33
 * end: 18:47
 * 独立完成
 * 时间： O(m+n)
 * 空间： O(1) ('z'-'A'+1 大小固定，其他的都是单独的变量)
 */
class Q76 {
    public String minWindow(String s, String t) {

        int[]count1=new int['z'-'A'+1];
        int[]count=new int['z'-'A'+1];

        for(int i=0;i<t.length();i++){
            count1[t.charAt(i)-'A']++;
        }

        int diff=0;

        int minWindows=Integer.MAX_VALUE/2;
        int[]res=new int[]{-1,-1};

        for(int i=0;i<'z'-'A'+1;i++){
            if(count1[i]>count[i]) diff++;
        }

        if(diff<=0) return "";

        int l=-1;

        for(int r=0;r<s.length();r++){
            ++count[s.charAt(r)-'A'];

            if(count[s.charAt(r)-'A']==count1[s.charAt(r)-'A']){
                diff--;
            }

            while(diff<=0){

                if(r-l<minWindows){

                    res[0]=l;
                    res[1]=r;

                    minWindows=r-l;
                }

              //  minWindows=Math.min(minWindows,r-l);

                l++;
                --count[s.charAt(l)-'A'];

                if(count[s.charAt(l)-'A']==count1[s.charAt(l)-'A']-1){
                    diff++;
                }
            
            }
        }

        if(minWindows==Integer.MAX_VALUE/2){
            return "";
        }else{

            return s.substring(res[0]+1,res[1]+1);

        }

        
    }
}
/**
 * start:18:51
 * end: 19:01
 * 独立完成
 * 时间：O(n2)
 * 空间：O(1)
 */
class Q15 {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list=new ArrayList<>();

        Arrays.sort(nums);

        for(int first=0;first<nums.length;first++){
            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int third=nums.length-1;
            int target=-nums[first];

            for(int second=first+1;second<nums.length;second++){
                if(second!=first+1 &&nums[second]==nums[second-1]){
                    continue;
                }

                while(nums[second]+nums[third]>target && third>second){
                    third--;
                }

                if(third<=second){
                    break;
                }
                if(nums[third]+nums[second]==target){
                    ArrayList<Integer>res=new ArrayList<>();

                    res.add(nums[first]);
                    res.add(nums[second]);
                    res.add(nums[third]);

                    list.add(res);
                }


            }
        }

        return list;

    }
}

public class ReviewChecks {
    static Random random=new Random(1023);
    static Q139 q139=new Q139(); static Q76 q76=new Q76(); static Q15 q15=new Q15();
    static int[] checks=new int[3];
    static void require(boolean value,String message){if(!value)throw new AssertionError(message);}
    static String word(int n,String alphabet){StringBuilder b=new StringBuilder();for(int i=0;i<n;i++)b.append(alphabet.charAt(random.nextInt(alphabet.length())));return b.toString();}
    static void words(String s,List<String> dictionary){
        boolean[] seen=new boolean[s.length()+1];ArrayDeque<Integer> queue=new ArrayDeque<>();queue.add(0);seen[0]=true;
        while(!queue.isEmpty()){
            int index=queue.remove();
            for(String w:dictionary)if(s.startsWith(w,index)){
                int next=index+w.length();if(!seen[next]){seen[next]=true;queue.add(next);}
            }
        }
        require(q139.wordBreak(s,dictionary)==seen[s.length()],"139 "+s+" "+dictionary);checks[0]++;
    }
    static boolean covers(int[] a,int[] b){for(int i=0;i<128;i++)if(a[i]<b[i])return false;return true;}
    static void window(String s,String t){
        int[] target=new int[128];for(char c:t.toCharArray())target[c]++;
        int best=Integer.MAX_VALUE;
        for(int start=0;start<s.length();start++){
            int[] counts=new int[128];
            for(int end=start;end<s.length();end++){
                counts[s.charAt(end)]++;
                if(covers(counts,target)){best=Math.min(best,end-start+1);break;}
            }
        }
        String actual=q76.minWindow(s,t);int[] counts=new int[128];for(char c:actual.toCharArray())counts[c]++;
        require(best==Integer.MAX_VALUE?actual.isEmpty():actual.length()==best&&s.contains(actual)&&covers(counts,target),"76 "+s+" "+t+" got="+actual);checks[1]++;
    }
    static String tuple(int a,int b,int c){int[] values={a,b,c};Arrays.sort(values);return Arrays.toString(values);}
    static void sum(int[] a){
        Set<String> expected=new HashSet<>();
        for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++)for(int k=j+1;k<a.length;k++)if(a[i]+a[j]+a[k]==0)expected.add(tuple(a[i],a[j],a[k]));
        List<List<Integer>> result=q15.threeSum(a.clone());Set<String> actual=new HashSet<>();
        for(List<Integer> item:result){require(item.size()==3,"15 tuple length");actual.add(tuple(item.get(0),item.get(1),item.get(2)));}
        require(actual.equals(expected)&&actual.size()==result.size(),"15 "+Arrays.toString(a));checks[2]++;
    }
    public static void main(String[] args){
        words("ba",List.of("a","b"));words("ba",List.of("b","a"));words("leetcode",List.of("leet","code"));
        words("applepenapple",List.of("apple","pen"));words("catsandog",List.of("cats","dog","sand","and","cat"));words("aaaaaaa",List.of("aaaa","aaa"));
        for(String[] pair:new String[][]{{"ADOBECODEBANC","ABC"},{"a","aa"},{"a","a"},{"AAA","AA"},{"aaab","aab"},{"aA","A"},{"A","a"},{"ABBAAC","AAC"},{"abc","Z"}})window(pair[0],pair[1]);
        for(int[] a:new int[][]{{-1,0,1,2,-1,-4},{0,0,0,0},{-2,0,0,2,2},{1,2,3},{-4,-2,-2,0,2,2,4},{-100000,0,100000}})sum(a);
        for(int test=0;test<2000;test++){
            Set<String> dictionary=new HashSet<>();int size=1+random.nextInt(6);
            for(int i=0;i<size;i++)dictionary.add(word(1+random.nextInt(4),"ab"));
            words(word(1+random.nextInt(16),"ab"),new ArrayList<>(dictionary));
            window(word(random.nextInt(24),"abAB"),word(1+random.nextInt(8),"abAB"));
            int[] a=new int[3+random.nextInt(14)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(31)-15;sum(a);
        }
        words("a".repeat(299)+"b",List.of("a","aa","aaa","aaaa","aaaaa"));
        require(q76.minWindow("a".repeat(100000),"aa").equals("aa"),"76 large");checks[1]++;
        require(q15.threeSum(new int[3000]).equals(List.of(List.of(0,0,0))),"15 large repeats");checks[2]++;
        System.out.printf("{\"139\":%d,\"76\":%d,\"15\":%d}%n",checks[0],checks[1],checks[2]);
    }
}
