import java.util.*;
import java.math.BigInteger;

/**
 * start: 21:14
 * end: 21:23
 * 独立完成
 * 时间O(n2)
 * 空间O(n3)
 */
class Q312 {
    public int maxCoins(int[] nums) {

        int []newNums=new int[nums.length+2];

        for(int i=0;i<nums.length;i++){
            newNums[i+1]=nums[i];
        }

        newNums[0]=1;
        newNums[newNums.length-1]=1;



        int [][]dp=new int[newNums.length][newNums.length];

        for(int i=dp.length-1;i>=0;i--){
            for(int j=i+2;j<dp[i].length;j++){
                for(int k=i+1;k<j;k++){
                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+newNums[i]*newNums[j]*newNums[k]);
                }

            }
        }

        return dp[0][newNums.length-1];


        
    }
}
/**
 * start: 21:30
 * end: 21:33
 * 独立完成
 * 时间:O(mn)
 * 空间:O(mn)
 *  */
class Q62 {
    public int uniquePaths(int m, int n) {

        int[][]dp=new int[m][n];

        dp[0][0]=1;

        for(int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=1;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
        
    }
}
/**
 * start: 21:33
 * end:21:45
 * 独立完成
 * 时间：O(mn)
 * 空间：O(mn)
 */
class Q64 {


    public int minPathSum(int[][] grid) {

        int[][]dp=new int[grid.length+1][grid[0].length+1];

        for(int i=0;i<dp.length;i++){
            dp[i][0]=Integer.MAX_VALUE/2;
        }

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=Integer.MAX_VALUE/2;
        }

        dp[1][0]=0;

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i-1][j-1];
            }
        }



        return dp[grid.length][grid[0].length];


        
    }
}
/**
 * start:12:00
 * end: 12:07
 * 独立完成
 * 时间：O(mn)
 * 空间：O(mn)
 */
class Q72 {
    public int minDistance(String word1, String word2) {

        char[]sc1=word1.toCharArray();

        char[]sc2=word2.toCharArray();

        int[][]dp=new int[sc1.length+1][sc2.length+1];


        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }

        for(int j=0;j<dp[0].length;j++){
            dp[0][j]=j;

        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                if(sc1[i-1]==sc2[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+1,Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }

        return dp[sc1.length][sc2.length];
        
    }
}
/**
 * start: 22:08
 * end 22: 15
 * 独立完成
 * 时间：O(n3)
 * 空间：O(n2)
 */
class Q96 {

    HashMap<Integer,Integer>map=new HashMap<>();


    int dfs(int from,int end){
        if(from>end) return 1;
        if(from==end) return 1;

        if(map.containsKey(end-from)){
            return map.get(end-from);
        }

        int res=0;



        for(int k=from;k<=end;k++){
           int lnum= dfs(from,k-1);
           int rnum= dfs(k+1,end);

           res+=(rnum*lnum);
        }

        map.put(end-from,res);

        return res;
    }



    public int numTrees(int n) {

        return dfs(1,n);
        
    }
}

/**
 * start: 22:17
 * end: 22:20
 * 独立完成
 * 时间O(n)
 * 空间O(n)
 */
class Q32 {
    public int longestValidParentheses(String s) {

        char[]sc=s.toCharArray();

        int res=0;

        int[]stack=new int[sc.length+1];
        int ptr=0;
        stack[ptr++]=-1;

        for(int i=0;i<sc.length;i++){
            if(sc[i]=='('){
                stack[ptr++]=i;
            }else{
                if(ptr==1){
                    stack[ptr-1]=i;
                }else{

                    res=Math.max(res,i-stack[ptr-2]);
                    ptr--;
                    
                }
            }
        }

        return res;
        
    }
}

public class ReviewChecks {
    static Random random=new Random(1029);
    static int[] checks=new int[6];
    static Q312 q312=new Q312();static Q62 q62=new Q62();static Q64 q64=new Q64();
    static Q72 q72=new Q72();static Q96 q96=new Q96();static Q32 q32=new Q32();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static int burstOracle(int[] a,int mask,int[] memo){
        if(mask==0)return 0;if(memo[mask]>=0)return memo[mask];int best=0;
        for(int i=0;i<a.length;i++)if((mask&(1<<i))!=0){
            int left=i-1,right=i+1;while(left>=0&&(mask&(1<<left))==0)left--;
            while(right<a.length&&(mask&(1<<right))==0)right++;
            int gain=(left<0?1:a[left])*a[i]*(right==a.length?1:a[right]);
            best=Math.max(best,gain+burstOracle(a,mask^(1<<i),memo));
        }return memo[mask]=best;
    }
    static void burst(int[] a){int[] memo=new int[1<<a.length];Arrays.fill(memo,-1);burst(a,burstOracle(a,(1<<a.length)-1,memo));}
    static void burst(int[] a,int expected){
        int[] input=a.clone();require(q312.maxCoins(input)==expected,"312 "+Arrays.toString(a));
        require(Arrays.equals(input,a),"312 input changed");checks[0]++;
    }
    static BigInteger choose(int n,int k){
        BigInteger r=BigInteger.ONE;for(int i=1;i<=k;i++)r=r.multiply(BigInteger.valueOf(n-k+i)).divide(BigInteger.valueOf(i));return r;
    }
    static void paths(int m,int n,BigInteger expected){require(q62.uniquePaths(m,n)==expected.intValueExact(),"62 m="+m+" n="+n);checks[1]++;}
    static int minPathOracle(int[][] a){
        int rows=a.length,cols=a[0].length;int[] dist=new int[rows*cols];Arrays.fill(dist,Integer.MAX_VALUE);dist[0]=a[0][0];
        PriorityQueue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt(x->x[0]));queue.add(new int[]{dist[0],0});
        while(!queue.isEmpty()){
            int[] state=queue.remove();int cost=state[0],index=state[1];if(cost!=dist[index])continue;
            if(index==rows*cols-1)return cost;int r=index/cols,c=index%cols;
            if(r+1<rows){int next=index+cols,value=cost+a[r+1][c];if(value<dist[next]){dist[next]=value;queue.add(new int[]{value,next});}}
            if(c+1<cols){int next=index+1,value=cost+a[r][c+1];if(value<dist[next]){dist[next]=value;queue.add(new int[]{value,next});}}
        }throw new AssertionError("64 oracle unreachable");
    }
    static void minPath(int[][] a){
        int[][] input=new int[a.length][];for(int i=0;i<a.length;i++)input[i]=a[i].clone();
        require(q64.minPathSum(input)==minPathOracle(a),"64 "+Arrays.deepToString(a));
        require(Arrays.deepEquals(input,a),"64 input changed");checks[2]++;
    }
    static void relax(int index,int cost,int[] dist,PriorityQueue<int[]> queue){
        if(cost<dist[index]){dist[index]=cost;queue.add(new int[]{cost,index});}
    }
    static int editOracle(String a,String b){
        int m=a.length(),n=b.length(),cols=n+1;int[] dist=new int[(m+1)*cols];Arrays.fill(dist,Integer.MAX_VALUE);dist[0]=0;
        PriorityQueue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt(x->x[0]));queue.add(new int[]{0,0});
        while(!queue.isEmpty()){
            int[] s=queue.remove();int cost=s[0],index=s[1];if(cost!=dist[index])continue;
            if(index==dist.length-1)return cost;int i=index/cols,j=index%cols;
            if(i<m)relax(index+cols,cost+1,dist,queue);
            if(j<n)relax(index+1,cost+1,dist,queue);
            if(i<m&&j<n)relax(index+cols+1,cost+(a.charAt(i)==b.charAt(j)?0:1),dist,queue);
        }throw new AssertionError("72 oracle unreachable");
    }
    static void edit(String a,String b){edit(a,b,editOracle(a,b));}
    static void edit(String a,String b,int expected){require(q72.minDistance(a,b)==expected,"72 "+a+" -> "+b);checks[3]++;}
    static void trees(int n){
        int expected=choose(2*n,n).divide(BigInteger.valueOf(n+1)).intValueExact();
        require(q96.numTrees(n)==expected,"96 n="+n);checks[4]++;
    }
    static int longestOracle(String s){
        int best=0;for(int i=0;i<s.length();i++){int balance=0;for(int j=i;j<s.length();j++){
            balance+=s.charAt(j)=='('?1:-1;if(balance<0)break;if(balance==0)best=Math.max(best,j-i+1);
        }}return best;
    }
    static void longest(String s){longest(s,longestOracle(s));}
    static void longest(String s,int expected){require(q32.longestValidParentheses(s)==expected,"32 "+s);checks[5]++;}
    static String text(int length){StringBuilder b=new StringBuilder();for(int i=0;i<length;i++)b.append((char)('a'+random.nextInt(3)));return b.toString();}
    public static void main(String[] args){
        for(int n=1;n<=6;n++){int count=1<<(2*n);for(int bits=0;bits<count;bits++){
            int[] a=new int[n];int v=bits;for(int i=0;i<n;i++){a[i]=v&3;v>>=2;}burst(a);
        }}
        burst(new int[]{3,1,5,8});burst(new int[]{1,5});burst(new int[]{0,2,0});burst(new int[]{100});
        for(int m=1;m<=100;m++)for(int n=1;n<=100;n++){
            BigInteger expected=choose(m+n-2,Math.min(m-1,n-1));if(expected.compareTo(BigInteger.valueOf(2000000000L))<=0)paths(m,n,expected);
        }
        for(int rows=1;rows<=3;rows++)for(int cols=1;cols<=3;cols++)for(int bits=0;bits<(1<<(rows*cols));bits++){
            int[][] a=new int[rows][cols];for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)a[i][j]=(bits>>(i*cols+j))&1;minPath(a);
        }
        List<String> words=new ArrayList<>();for(int n=0;n<=5;n++)for(int bits=0;bits<(1<<n);bits++){
            StringBuilder s=new StringBuilder();for(int i=0;i<n;i++)s.append(((bits>>i)&1)==0?'a':'b');words.add(s.toString());
        }
        for(String a:words)for(String b:words)edit(a,b);
        edit("horse","ros");edit("intention","execution");
        for(int n=1;n<=19;n++)trees(n);for(int n=19;n>=1;n--)trees(n);
        for(int n=0;n<=14;n++)for(int bits=0;bits<(1<<n);bits++){
            StringBuilder s=new StringBuilder();for(int i=0;i<n;i++)s.append(((bits>>i)&1)==0?'(':')');longest(s.toString());
        }
        for(int test=0;test<500;test++){
            int[] a=new int[1+random.nextInt(9)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(101);burst(a);
            int rows=1+random.nextInt(12),cols=1+random.nextInt(12);int[][] grid=new int[rows][cols];
            for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)grid[i][j]=random.nextInt(201);minPath(grid);
            edit(text(random.nextInt(31)),text(random.nextInt(31)));
            trees(1+random.nextInt(19));
            StringBuilder s=new StringBuilder();for(int i=0,n=random.nextInt(100);i<n;i++)s.append(random.nextBoolean()?'(':')');longest(s.toString());
        }
        int[] bigBalloons=new int[300];Arrays.fill(bigBalloons,1);burst(bigBalloons,300);
        Arrays.fill(bigBalloons,0);burst(bigBalloons,0);Arrays.fill(bigBalloons,100);burst(bigBalloons,298010100);
        int[][] bigGrid=new int[200][200];for(int[] row:bigGrid)Arrays.fill(row,200);minPath(bigGrid);
        for(int[] row:bigGrid)Arrays.fill(row,0);minPath(bigGrid);
        for(int i=0;i<200;i++)for(int j=0;j<200;j++)bigGrid[i][j]=random.nextInt(201);minPath(bigGrid);
        edit("a".repeat(500),"a".repeat(500),0);edit("a".repeat(500),"b".repeat(500),500);
        edit("","a".repeat(500),500);edit("a".repeat(500),"",500);edit("ab".repeat(250),"ba".repeat(250),2);
        longest("(".repeat(30000),0);longest(")".repeat(30000),0);longest("()".repeat(15000),30000);
        longest("(".repeat(15000)+")".repeat(15000),30000);longest(")()())",4);
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
