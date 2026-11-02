import java.util.*;

/**
 * start: 10:03
 * end: 10:10
 * 独立完成
 * 时间：O(n)
 * 空间: O(n)
 * 
 */
class Q20 {
    public boolean isValid(String s) {


        char[] sc=s.toCharArray();
        int[]stack=new int[sc.length];
        int ptr=0;//tip:需要用栈而不能计数，因为括号需要用正确的序号关闭

        for(int i=0;i<sc.length;i++){
            switch(sc[i]){
                case '(': stack[ptr++]=1; break;
                case '[': stack[ptr++]=2; break;
                case '{': stack[ptr++]=3; break;
                case ')': if(ptr==0||stack[ptr-1]!=1) return false;ptr--; break;
                case ']': if(ptr==0||stack[ptr-1]!=2) return false;ptr--; break;
                case '}': if(ptr==0||stack[ptr-1]!=3) return false;ptr--; break;
            }
        }

        return ptr==0;
        
    }
}
/**
 * start: 10:12
 * end: 10:17
 * 时间： O(n)
 * 空间：O(n)
 */
class Q32 {
    public int longestValidParentheses(String s) {

        if(s.length()<=1) return 0;

        char[]sc=s.toCharArray();

        int[]stack=new int[s.length()+1];//tip:多加1
        int ptr=0;

        int maxLen=0;

        stack[ptr++]=-1;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                if(ptr==1){
                    stack[ptr-1]=i;
                }else{
                    maxLen=Math.max(maxLen,i-stack[ptr-2]);
                    ptr--;
                }
            }
        }

        return maxLen;
        
    }
}
/**
 * start:10:19
 * end: 10:30
 * 时间：O(n)
 * 空间：O(n)
 */
class Q239 {
    public int[] maxSlidingWindow(int[] nums, int k) {



        Deque<Integer>stack=new ArrayDeque<>();

        int[]res=new int[nums.length-k+1];

        for(int i=0;i<k;i++){

            while(stack.size()>0 && nums[i]>=nums[stack.getLast()]){
                stack.removeLast();
            }

            stack.addLast(i);


        }

        res[0]=nums[stack.getFirst()];

        for(int i=k;i<nums.length;i++){

            while(stack.size()>0 && nums[i]>=nums[stack.getLast()]){
                stack.removeLast();
            }

            stack.addLast(i);

            if(stack.getFirst()==i-k){

                stack.removeFirst();

            }

            res[i-k+1]=nums[stack.getFirst()];
        }

        return res;
        
    }
}

/**
 * start: 10:32
 * end: 11:04
 * 独立完成
 * 时间空间无法计算复杂度
 */
class Q394 {


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
/**
 * start: 11:06
 * end:11:10
 * 独立完成  
 * 时间：O(n)
 * 空间： O(n)  
 */
class Q739 {
    public int[] dailyTemperatures(int[] temperatures) {

        int[]stack=new int[temperatures.length];
        int ptr=0;
        int[]res=new int[temperatures.length];

        for(int i=0;i<temperatures.length;i++){
            while(ptr!=0 && temperatures[i]>temperatures[stack[ptr-1]]){

                res[stack[ptr-1]]=i-stack[ptr-1];

                ptr--;
            }

            stack[ptr++]=i;
        }

        return res;
        
    }
}  
/**
 * start: 15:30
 * end: 15:46
 * 独立完成
 * n:s.length()
 * m:maxLen
 * num: wordDicts.length
 * 时间: O(mn)
 * 空间：O(n+m*num)
 */
class Q139 {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;

        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            //wordDict.add(str);
            maxLen=Math.max(maxLen,str.length());
            set.add(str);
        }

        boolean []dp=new boolean[s.length()+1];
        

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int len=1;len<=maxLen;len++){

                // i-1 i-1-len-1  

                if(i-len>=0 && set.contains(s.substring(i-len,i))){//

                    dp[i]=dp[i]|| dp[i-len];

                }



            }
        }


        

        return dp[s.length()];
        
    }
}

/**
 * start: 15:50
 * end: 16:07
 * 独立完成
 * 时间:O(log(n))
 * 空间:O(1)
 */
class Q215 {


    void swap(int[] nums,int from,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int search(int[]nums,int left,int right){
        
      //  System.out.println(left);
      //  System.out.println(right);

        int piviot=left+new Random().nextInt(right-left+1);

        swap(nums,piviot,right);

        int lptr=left;
        int count=0;

        for(int i=left;i<right;i++){
            if(nums[i]<nums[right]){
                swap(nums,i,lptr);
                lptr++;

            }else if(nums[i]==nums[right]){

                if(count++%2==0){
                    swap(nums,i,lptr);
                    lptr++;
                }

            }
        }

        swap(nums,lptr,right);

        return lptr;

    }


    public int findKthLargest(int[] nums, int k) {

        int kk=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid=search(nums,left,right);

            if(kk<mid){
                right=mid-1;
            }else if(kk>mid){
                left=mid+1;
            }else{
                return nums[mid];
            }
        }

        return -1;
        
    }
}

public class ReviewChecks {
    static Random random = new Random(1026);
    static int[] checks = new int[7];
    static int partitionChecks;
    static Q20 q20=new Q20();static Q32 q32=new Q32();static Q239 q239=new Q239();
    static Q394 q394=new Q394();static Q739 q739=new Q739();static Q139 q139=new Q139();static Q215 q215=new Q215();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static void valid(String s){
        String reduced=s,old;
        do{old=reduced;reduced=reduced.replace("()","").replace("[]","").replace("{}","");}while(!old.equals(reduced));
        require(q20.isValid(s)==reduced.isEmpty(),"20 "+s);checks[0]++;
    }
    static int longestOracle(String s){
        int best=0;
        for(int i=0;i<s.length();i++){int balance=0;for(int j=i;j<s.length();j++){
            balance+=s.charAt(j)=='('?1:-1;if(balance<0)break;if(balance==0)best=Math.max(best,j-i+1);
        }}return best;
    }
    static void longest(String s){longest(s,longestOracle(s));}
    static void longest(String s,int expected){require(q32.longestValidParentheses(s)==expected,"32 "+s);checks[1]++;}
    static void window(int[] a,int k){
        int[] expected=new int[a.length-k+1];
        for(int i=0;i<expected.length;i++){int best=Integer.MIN_VALUE;for(int j=i;j<i+k;j++)best=Math.max(best,a[j]);expected[i]=best;}
        window(a,k,expected);
    }
    static void window(int[] a,int k,int[] expected){
        int[] input=a.clone();require(Arrays.equals(q239.maxSlidingWindow(input,k),expected),"239 k="+k+" n="+a.length);
        require(Arrays.equals(input,a),"239 input changed");checks[2]++;
    }
    static void largeWindow(int[] a,int k){
        int[] expected=new int[a.length-k+1];TreeMap<Integer,Integer> counts=new TreeMap<>();
        for(int i=0;i<a.length;i++){
            counts.merge(a[i],1,Integer::sum);
            if(i>=k){int v=a[i-k];counts.compute(v,(key,c)->c==1?null:c-1);}
            if(i>=k-1)expected[i-k+1]=counts.lastKey();
        }window(a,k,expected);
    }
    static void temperatures(int[] a){
        int[] expected=new int[a.length];
        for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++)if(a[j]>a[i]){expected[i]=j-i;break;}
        temperatures(a,expected);
    }
    static void temperatures(int[] a,int[] expected){
        int[] input=a.clone();require(Arrays.equals(q739.dailyTemperatures(input),expected),"739 n="+a.length);
        require(Arrays.equals(input,a),"739 input changed");checks[4]++;
    }
    record Encoding(String text,String plain){}
    static Encoding expression(int depth){
        StringBuilder text=new StringBuilder(),plain=new StringBuilder();
        int terms=1+random.nextInt(3);
        for(int i=0;i<terms;i++){
            if(depth>0&&random.nextBoolean()){
                Encoding child=expression(depth-1);int copies=1+random.nextInt(12);
                text.append(copies).append('[').append(child.text).append(']');plain.append(child.plain.repeat(copies));
            }else{
                int len=1+random.nextInt(3);for(int j=0;j<len;j++){char c=(char)('a'+random.nextInt(4));text.append(c);plain.append(c);}
            }
        }return new Encoding(text.toString(),plain.toString());
    }
    static void decode(String text,String expected){
        require(q394.decodeString(text).equals(expected),"394 "+text);checks[3]++;
    }
    static boolean wordOracle(String s,List<String> words){
        boolean[] seen=new boolean[s.length()+1];ArrayDeque<Integer> queue=new ArrayDeque<>();seen[0]=true;queue.add(0);
        while(!queue.isEmpty()){
            int start=queue.remove();if(start==s.length())return true;
            for(String word:words)if(s.startsWith(word,start)){
                int next=start+word.length();if(!seen[next]){seen[next]=true;queue.add(next);}
            }
        }return false;
    }
    static void wordBreak(String s,List<String> words){require(q139.wordBreak(s,words)==wordOracle(s,words),"139 "+s+" "+words);checks[5]++;}
    static void kth(int[] a,int k){
        int[] sorted=a.clone();Arrays.sort(sorted);int[] input=a.clone();
        require(q215.findKthLargest(input,k)==sorted[a.length-k],"215 k="+k+" n="+a.length);
        Arrays.sort(input);require(Arrays.equals(input,sorted),"215 multiset changed");checks[6]++;
    }
    static void partition(int[] a,int left,int right){
        int[] input=a.clone(),expected=a.clone();Arrays.sort(expected,left,right+1);
        int split=q215.search(input,left,right);require(split>=left&&split<=right,"215 split range");
        for(int i=left;i<split;i++)require(input[i]<=input[split],"215 left partition");
        for(int i=split+1;i<=right;i++)require(input[i]>=input[split],"215 right partition");
        Arrays.sort(input,left,right+1);require(Arrays.equals(input,expected),"215 interval multiset/outside changed");partitionChecks++;
    }
    static String letters(int n,int alphabet){StringBuilder b=new StringBuilder();for(int i=0;i<n;i++)b.append((char)('a'+random.nextInt(alphabet)));return b.toString();}
    public static void main(String[] args){
        char[] bracketChars={'(',')','[',']','{','}'};
        for(int n=1;n<=5;n++){int count=(int)Math.pow(6,n);for(int bits=0;bits<count;bits++){
            int v=bits;StringBuilder s=new StringBuilder();for(int i=0;i<n;i++){s.append(bracketChars[v%6]);v/=6;}valid(s.toString());
        }}
        valid("()[]{}");valid("([)]");valid("([])");valid("(]");valid("()".repeat(5000));valid("(".repeat(5000)+")".repeat(5000));
        for(int n=0;n<=14;n++)for(int bits=0;bits<(1<<n);bits++){
            StringBuilder s=new StringBuilder();for(int i=0;i<n;i++)s.append(((bits>>i)&1)==0?'(':')');longest(s.toString());
        }
        longest("(".repeat(30000),0);longest(")".repeat(30000),0);longest("()".repeat(15000),30000);
        longest("(".repeat(15000)+")".repeat(15000),30000);longest(")()())",4);
        for(int n=1;n<=7;n++){int count=(int)Math.pow(3,n);for(int bits=0;bits<count;bits++){
            int v=bits;int[] a=new int[n];for(int i=0;i<n;i++){a[i]=v%3-1;v/=3;}for(int k=1;k<=n;k++)window(a,k);
        }}
        window(new int[]{1,3,-1,-3,5,3,6,7},3);window(new int[]{1},1);window(new int[]{9,9,9},2);
        decode("a","a");decode("abc","abc");decode("3[a]2[bc]","aaabcbc");decode("3[a2[c]]","accaccacc");
        decode("2[abc]3[cd]ef","abcabccdcdcdef");decode("10[a]","a".repeat(10));decode("300[300[a]]","a".repeat(90000));
        decode("10[10[10[10[a]]]]","a".repeat(10000));decode("1[1[1[1[1[1[1[1[1[a]]]]]]]]]","a");
        int generated=0;while(generated<1000){Encoding e=expression(3);if(e.text.length()<=30&&e.plain.length()<=100000){decode(e.text,e.plain);generated++;}}
        for(int[] a:new int[][]{{30},{30,30},{100,100},{73,74,75,71,69,72,76,73},{100,90,80,30},{30,40,50,60}})temperatures(a);
        wordBreak("leetcode",List.of("leet","code"));wordBreak("applepenapple",List.of("apple","pen"));
        wordBreak("catsandog",List.of("cats","dog","sand","and","cat"));wordBreak("ba",List.of("a","b"));
        wordBreak("a",List.of("b"));wordBreak("aaaaaaa",List.of("aaaa","aaa"));
        List<String> aWords=new ArrayList<>();for(int i=1;i<=20;i++)aWords.add("a".repeat(i));
        wordBreak("a".repeat(300),aWords);wordBreak("a".repeat(299)+"b",aWords);
        for(int[] a:new int[][]{{1},{2,1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{0,0,0},{-10000,10000}})for(int k=1;k<=a.length;k++)kth(a,k);
        for(int test=0;test<1000;test++){
            int[] a=new int[1+random.nextInt(100)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(20001)-10000;window(a,1+random.nextInt(a.length));
            int[] ts=new int[1+random.nextInt(200)];for(int i=0;i<ts.length;i++)ts[i]=30+random.nextInt(71);temperatures(ts);
            StringBuilder s=new StringBuilder();for(int i=0,n=random.nextInt(100);i<n;i++)s.append(random.nextBoolean()?'(':')');longest(s.toString());
            String wordString=letters(1+random.nextInt(60),3);Set<String> dict=new HashSet<>();for(int i=0,n=1+random.nextInt(25);i<n;i++)dict.add(letters(1+random.nextInt(8),3));wordBreak(wordString,new ArrayList<>(dict));
            int[] kInput=new int[1+random.nextInt(200)];for(int i=0;i<kInput.length;i++)kInput[i]=random.nextInt(21)-10;
            kth(kInput,1+random.nextInt(kInput.length));int left=random.nextInt(kInput.length),right=left+random.nextInt(kInput.length-left);partition(kInput,left,right);
        }
        int[] large=new int[100000];for(int i=0;i<large.length;i++)large[i]=i/5-10000;
        for(int k:new int[]{1,50000,100000}){largeWindow(large,k);kth(large,k);}
        for(int i=0;i<large.length;i++)large[i]=9999-i/5;
        largeWindow(large,1000);for(int k:new int[]{1,50000,100000})kth(large,k);
        Arrays.fill(large,7);largeWindow(large,100000);for(int k:new int[]{1,50000,100000})kth(large,k);
        for(int i=0;i<large.length;i++)large[i]=random.nextInt(20001)-10000;largeWindow(large,1234);kth(large,54321);
        int[] hugeT=new int[100000],expectedT=new int[100000];Arrays.fill(hugeT,30);temperatures(hugeT,expectedT);
        for(int i=0;i<hugeT.length;i++)hugeT[i]=30+i%71;
        for(int i=0;i<hugeT.length;i++)expectedT[i]=(hugeT[i]<100&&i+1<hugeT.length)?1:0;temperatures(hugeT,expectedT);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("PARTITION_CHECKS="+partitionChecks);
    }
}
