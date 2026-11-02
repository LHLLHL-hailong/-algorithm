from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-29'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [312, 62, 64, 72, 96, 32]
paths = {i: SUBMISSION / ('312.java' if i == 312 else f'review/LK{i}.java' if i == 32 else f'LK{i}.java') for i in ids}
hashes = {}
parts = ['import java.util.*;\nimport java.math.BigInteger;\n']
for i, path in paths.items():
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.relative_to(SUBMISSION)
    target.parent.mkdir(parents=True, exist_ok=True)
    if target.exists():
        assert target.read_bytes() == data, 'Submission changed after snapshot'
    else:
        target.write_bytes(data)
    source, count = re.subn(r'\b(?:public\s+)?class\s+Solution\b', f'class Q{i}', data.decode('utf-8-sig'), count=1)
    assert count == 1
    parts.append(source)

parts.append(r'''
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
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-29', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util/java.math导入、调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['total_cases'] = sum(counts)
else:
    result['passed'] = False
for i, path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
