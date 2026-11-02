import java.util.*;

/**
 * start: 8:12
 * end: 8:46
 * 问题答案：独立完成
 */
/**
 * start: 8:12
 */
class Q139V1 {

    void print2(boolean[][]arr){

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }

         System.out.println("-----------------------------");

        

    }


    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[][]dp=new boolean[s.length()+1][wordDict.size()+1];

        // i-start+1=now.length()
        //start=i+1-now.length()

        dp[0][0]=true;

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=true;
        }

        int count=0;



        for(int i=1;i<=s.length();i++){

            for(int j=1;j<=wordDict.size();j++){

                String now=wordDict.get(j-1);
                if(i-now.length()>=0 &&  s.substring(i-now.length(),i).equals(now)){

                    dp[i][j]=(dp[i][j-1] || dp[i-now.length()][j]);
                }else{
                    dp[i][j]=dp[i][j-1];
                }

              // print2(dp);



            }
        }

        return dp[s.length()][wordDict.size()];

        
    }
}

/**
 * start:21:22
 * end: 21:31
 * 看完答案之后做出
 * 时间：O(mn)
 * 空间：O(mn)
 * m:s.length
 * n:maxLen
 */
class Q139V2 {
    public boolean wordBreak(String s, List<String> wordDict) {

        int maxLen=0;
        HashSet<String>set=new HashSet<>();

        for(String str:wordDict){
            set.add(str);
            maxLen=Math.max(maxLen,str.length());
        }

        boolean[]dp=new boolean[s.length()+1];

        dp[0]=true;

        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=maxLen && i-j>=0;j++){
                //start  i-1   i-1-stat+1=j
                //start=i-j   i
                if(set.contains(s.substring(i-j,i)) && dp[i-j] ){
                    dp[i]=true;
                }

            }
        }

        return dp[s.length()];
        
    }
}
/**
 * start:20:49
 * end: 21:01
 * 独立完成
 * 时间O(1)
 * 空间O(n)
 */
class MinStack {

    long minValue=0L;
    long[]stack=new long[30005];//必须都是long
    int ptr=0;

    public MinStack() {


        ptr=0;
        
    }
    
    public void push(int value) {

        if(ptr<=0){
            stack[ptr++]=0L;
            minValue=value;
        }else{
            stack[ptr++]=(0L+value-minValue);
            if(stack[ptr-1]<0L){
                minValue=value;
            }
        }

    }
    
    public void pop() {

        if(stack[ptr-1]>=0L){
            ptr--;
        }else{
            //minValue-target=stack[ptr-1]
            //target=minValue-stack[ptr-1]

            minValue=minValue-stack[ptr-1];
            ptr--;
        }
        
    }
    
    public int top() {

        if(stack[ptr-1]>=0L){
            return (int)(minValue+stack[ptr-1]);
        }else{
            return (int)minValue;
        }

       // return stack[ptr-1];
        
    }
    
    public int getMin() {

        return (int)minValue;
        
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


/**
 * start: 21:04
 * end: 21:13
 * 独立完成
 * 时间: 最坏（N方） 最好：O(1) 
 * 空间：O(1)
 */
class Q215 {

    void swap(int[]nums,int from ,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int search(int[]nums,int left,int right ){

        int piviot=nums[left];
        swap(nums,left,right);

        int ptr=left;

        int count=0;

        for(int i=left;i<right;i++){

            if(nums[i]<piviot){
                swap(nums,i,ptr);
                ptr++;

            }else if(nums[i]==piviot){

                if(++count%2==0){
                    swap(nums,i,ptr);
                    ptr++;
                }
                
            }

        }

        swap(nums,ptr,right);

        return ptr;

    }


    public int findKthLargest(int[] nums, int k) {

        int target=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int idx=search(nums,left,right);

            if(idx<target){
                left=idx+1;
            }else if(idx >target){
                right=idx-1;
            }else{
                return nums[idx];
            }
        }

        return -1;

        
        
    }
}
/**
 * start: 21:15
 * end: 21:16
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Q136 {
    public int singleNumber(int[] nums) {

        int base=0;

        for(int now:nums){
            base^=now;
        }

        return base;
        
    }
}
/**
 * start: 21:18
 * end: 21:21
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Q169 {
    public int majorityElement(int[] nums) {

        int now=nums[0];
        int flage=1;

        for(int i=1;i<nums.length;i++){
            if(nums[i]==now){
                flage++;
            }else{
                if(--flage<=0){
                    now=nums[i];
                    flage=1;
                }
            }
        }

        return now;


        
    }
}

public class ReviewChecks {
    static Random random=new Random(1022);
    static Q139V1 first139=new Q139V1(); static Q139V2 q139=new Q139V2();
    static Q215 q215=new Q215(); static Q136 q136=new Q136(); static Q169 q169=new Q169();
    static int[] checks=new int[5]; static boolean first139Counterexample;
    static void require(boolean value,String message) { if(!value)throw new AssertionError(message); }
    static String word(int len) { StringBuilder b=new StringBuilder(); for(int i=0;i<len;i++)b.append((char)('a'+random.nextInt(2))); return b.toString(); }
    static void words(String s,List<String> dictionary) {
        boolean[] seen=new boolean[s.length()+1]; ArrayDeque<Integer> queue=new ArrayDeque<>(); queue.add(0); seen[0]=true;
        while(!queue.isEmpty()) {
            int index=queue.remove();
            for(String w:dictionary) if(s.startsWith(w,index)) {
                int next=index+w.length(); if(!seen[next]) { seen[next]=true; queue.add(next); }
            }
        }
        require(q139.wordBreak(s,dictionary)==seen[s.length()],"139 V2: "+s+" "+dictionary); checks[0]++;
    }
    static void stackStep(MinStack stack,List<Integer> reference,boolean push,int value) {
        if(push) { stack.push(value); reference.add(value); }
        else { stack.pop(); reference.remove(reference.size()-1); }
        if(!reference.isEmpty()) {
            require(stack.top()==reference.get(reference.size()-1),"155 top");
            require(stack.getMin()==Collections.min(reference),"155 min");
        }
        checks[1]++;
    }
    static void kth(int[] a,int k) {
        int[] sorted=a.clone(); Arrays.sort(sorted);
        require(q215.findKthLargest(a.clone(),k)==sorted[a.length-k],"215: "+Arrays.toString(a)+" k="+k); checks[2]++;
    }
    static void single(int[] a,int expected) {
        require(q136.singleNumber(a.clone())==expected,"136: "+Arrays.toString(a)); checks[3]++;
    }
    static void majority(int[] a,int expected) {
        int count=0; for(int v:a)if(v==expected)count++;
        require(count>a.length/2,"invalid reference majority");
        require(q169.majorityElement(a.clone())==expected,"169: "+Arrays.toString(a)); checks[4]++;
    }
    static void shuffle(int[] a) { for(int i=a.length-1;i>0;i--) { int j=random.nextInt(i+1),v=a[i];a[i]=a[j];a[j]=v; } }
    public static void main(String[] args) {
        first139Counterexample=!first139.wordBreak("ba",List.of("a","b"));
        require(first139Counterexample,"139 V1 expected counterexample disappeared; inspect changed source");
        require(q139.wordBreak("ba",List.of("a","b")),"139 V2 counterexample");
        words("ba",List.of("a","b")); words("leetcode",List.of("leet","code"));
        words("applepenapple",List.of("apple","pen")); words("catsandog",List.of("cats","dog","sand","and","cat"));
        words("aaaaaaa",List.of("aaaa","aaa")); words("a",List.of("b"));
        MinStack extreme=new MinStack(); List<Integer> reference=new ArrayList<>();
        for(int v:new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,0,Integer.MAX_VALUE})stackStep(extreme,reference,true,v);
        while(!reference.isEmpty())stackStep(extreme,reference,false,0);
        stackStep(extreme,reference,true,Integer.MIN_VALUE); stackStep(extreme,reference,false,0);
        for(int trial=0;trial<100;trial++) {
            MinStack stack=new MinStack(); List<Integer> values=new ArrayList<>();
            for(int step=0;step<100;step++)stackStep(stack,values,values.isEmpty()||random.nextInt(3)!=0,random.nextInt());
        }
        for(int[] a:new int[][]{{1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{2,2,2,2},{-10000,10000,0}})
            for(int k=1;k<=a.length;k++)kth(a,k);
        single(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},Integer.MIN_VALUE);
        single(new int[]{0},0); single(new int[]{2,2,1},1);
        majority(new int[]{1},1); majority(new int[]{2,2,1,1,1,2,2},2); majority(new int[]{-1,2,-1,3,-1},-1);
        for(int test=0;test<2000;test++) {
            Set<String> dictionary=new HashSet<>(); for(int i=0;i<1+random.nextInt(6);i++)dictionary.add(word(1+random.nextInt(4)));
            words(word(1+random.nextInt(16)),new ArrayList<>(dictionary));
            int[] a=new int[1+random.nextInt(60)]; for(int i=0;i<a.length;i++)a[i]=random.nextInt(21)-10; kth(a,1+random.nextInt(a.length));
            int pairs=random.nextInt(20),only=123456789; int[] paired=new int[2*pairs+1]; Set<Integer> used=new HashSet<>();
            paired[0]=only;
            for(int i=0;i<pairs;i++) { int value; do {value=random.nextInt(1000)-500;}while(!used.add(value)); paired[2*i+1]=paired[2*i+2]=value; }
            shuffle(paired);single(paired,only);
            int n=1+random.nextInt(100),m=random.nextInt(5)-2; int[] voters=new int[n];
            int major=n/2+1+random.nextInt(n-n/2); Arrays.fill(voters,0,major,m);
            for(int i=major;i<n;i++){int other; do {other=random.nextInt(5)-2;}while(other==m);voters[i]=other;}
            shuffle(voters);majority(voters,m);
        }
        int[] identical=new int[100000];Arrays.fill(identical,7);kth(identical,50000);
        int[] increasing=new int[3000];for(int i=0;i<increasing.length;i++)increasing[i]=i;kth(increasing,1);
        words("a".repeat(299)+"b",List.of("a","aa","aaa","aaaa","aaaaa"));
        System.out.printf("{\"139_v2\":%d,\"155_operations\":%d,\"215\":%d,\"136\":%d,\"169\":%d,\"139_v1_counterexample_confirmed\":%s}%n",checks[0],checks[1],checks[2],checks[3],checks[4],first139Counterexample);
    }
}
