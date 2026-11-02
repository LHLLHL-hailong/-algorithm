from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-27'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [84, 85, 56, 75, 70]
hashes = {}
parts = ['import java.util.*;\nimport java.math.BigInteger;\n']
for i in ids:
    path = SUBMISSION / f'LK{i}.java'
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.name
    if target.exists():
        assert target.read_bytes() == data, 'Submission changed after snapshot'
    else:
        target.write_bytes(data)
    source, count = re.subn(r'\b(?:public\s+)?class\s+Solution\b', f'class Q{i}', data.decode('utf-8-sig'), count=1)
    assert count == 1
    parts.append(source)

parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1027);
    static int[] checks = new int[5];
    static Q84 q84=new Q84();static Q85 q85=new Q85();static Q56 q56=new Q56();static Q75 q75=new Q75();static Q70 q70=new Q70();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static int histogramOracle(int[] a){
        int best=0;for(int left=0;left<a.length;left++){
            int minimum=Integer.MAX_VALUE;for(int right=left;right<a.length;right++){
                minimum=Math.min(minimum,a[right]);best=Math.max(best,minimum*(right-left+1));
            }
        }return best;
    }
    static void histogram(int[] a){histogram(a,histogramOracle(a));}
    static void histogram(int[] a,int expected){
        int[] input=a.clone();require(q84.largestRectangleArea(input)==expected,"84 "+Arrays.toString(a));
        require(Arrays.equals(input,a),"84 input changed");checks[0]++;
    }
    static int matrixOracle(char[][] a){
        int best=0;
        for(int top=0;top<a.length;top++){
            boolean[] allOne=new boolean[a[0].length];Arrays.fill(allOne,true);
            for(int bottom=top;bottom<a.length;bottom++){
                int run=0;for(int col=0;col<a[0].length;col++){
                    allOne[col]&=a[bottom][col]=='1';run=allOne[col]?run+1:0;best=Math.max(best,run*(bottom-top+1));
                }
            }
        }return best;
    }
    static void matrix(char[][] a){
        char[][] input=new char[a.length][];for(int i=0;i<a.length;i++)input[i]=a[i].clone();
        require(q85.maximalRectangle(input)==matrixOracle(a),"85 "+Arrays.deepToString(a));
        require(Arrays.deepEquals(input,a),"85 input changed");checks[1]++;
    }
    static int root(int[] p,int x){while(p[x]!=x)x=p[x];return x;}
    static int[][] mergeOracle(int[][] a){
        int[] parent=new int[a.length];for(int i=0;i<a.length;i++)parent[i]=i;
        for(int i=0;i<a.length;i++)for(int j=0;j<i;j++)if(Math.max(a[i][0],a[j][0])<=Math.min(a[i][1],a[j][1])){
            int x=root(parent,i),y=root(parent,j);parent[x]=y;
        }
        Map<Integer,int[]> groups=new HashMap<>();
        for(int i=0;i<a.length;i++){
            int x=root(parent,i);int[] g=groups.get(x);
            if(g==null)groups.put(x,a[i].clone());else{g[0]=Math.min(g[0],a[i][0]);g[1]=Math.max(g[1],a[i][1]);}
        }
        int[][] output=groups.values().toArray(new int[0][]);Arrays.sort(output,Comparator.comparingInt(x->x[0]));return output;
    }
    static void merge(int[][] a){merge(a,mergeOracle(a));}
    static void merge(int[][] a,int[][] expected){
        int[][] input=new int[a.length][];for(int i=0;i<a.length;i++)input[i]=a[i].clone();
        int[][] actual=q56.merge(input);Arrays.sort(actual,Comparator.comparingInt(x->x[0]));
        require(Arrays.deepEquals(actual,expected),"56 "+Arrays.deepToString(a));checks[2]++;
    }
    static void colors(int[] a){
        int[] expected=a.clone();Arrays.sort(expected);int[] input=a.clone();q75.sortColors(input);
        require(Arrays.equals(input,expected),"75 "+Arrays.toString(a));checks[3]++;
    }
    static BigInteger choose(int n,int k){
        BigInteger r=BigInteger.ONE;for(int i=1;i<=k;i++)r=r.multiply(BigInteger.valueOf(n-k+i)).divide(BigInteger.valueOf(i));return r;
    }
    static void stairs(int n){
        BigInteger ways=BigInteger.ZERO;for(int two=0;two<=n/2;two++)ways=ways.add(choose(n-two,two));
        require(q70.climbStairs(n)==ways.intValueExact(),"70 n="+n);checks[4]++;
    }
    public static void main(String[] args){
        for(int n=1;n<=7;n++){int count=1<<(2*n);for(int bits=0;bits<count;bits++){
            int v=bits;int[] a=new int[n];for(int i=0;i<n;i++){a[i]=v&3;v>>=2;}histogram(a);
        }}
        for(int[] a:new int[][]{{2,1,5,6,2,3},{2,2},{0},{10000},{0,2,0},{2,2,1,2,2}})histogram(a);
        for(int rows=1;rows<=3;rows++)for(int cols=1;cols<=4;cols++)for(int bits=0;bits<(1<<(rows*cols));bits++){
            char[][] a=new char[rows][cols];for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)a[i][j]=((bits>>(i*cols+j))&1)==0?'0':'1';matrix(a);
        }
        matrix(new char[][]{"10100".toCharArray(),"10111".toCharArray(),"11111".toCharArray(),"10010".toCharArray()});
        for(int[][] a:new int[][][]{{{1,3}},{{1,3},{2,6},{8,10},{15,18}},{{1,4},{4,5}},{{1,10},{2,3},{4,5}},{{0,0},{0,0}},{{3,4},{1,2}},{{5,5},{1,5},{5,8}}})merge(a);
        for(int n=1;n<=10;n++){int count=(int)Math.pow(3,n);for(int bits=0;bits<count;bits++){
            int v=bits;int[] a=new int[n];for(int i=0;i<n;i++){a[i]=v%3;v/=3;}colors(a);
        }}
        for(int n=1;n<=45;n++)stairs(n);
        for(int test=0;test<1000;test++){
            int[] h=new int[1+random.nextInt(100)];for(int i=0;i<h.length;i++)h[i]=random.nextInt(10001);histogram(h);
            int rows=1+random.nextInt(12),cols=1+random.nextInt(12);char[][] a=new char[rows][cols];
            for(int i=0;i<rows;i++)for(int j=0;j<cols;j++)a[i][j]=random.nextBoolean()?'0':'1';matrix(a);
            int[][] intervals=new int[1+random.nextInt(25)][2];
            for(int i=0;i<intervals.length;i++){int x=random.nextInt(10001),y=random.nextInt(10001);intervals[i][0]=Math.min(x,y);intervals[i][1]=Math.max(x,y);}merge(intervals);
            int[] c=new int[1+random.nextInt(300)];for(int i=0;i<c.length;i++)c[i]=random.nextInt(3);colors(c);
        }
        int[] huge=new int[100000];Arrays.fill(huge,10000);histogram(huge,1000000000);
        for(int i=0;i<huge.length;i++)huge[i]=i/10;
        int best=0;for(int i=0;i<huge.length;i++)best=Math.max(best,huge[i]*(huge.length-i));histogram(huge,best);
        for(int i=0;i<huge.length;i++)huge[i]=9999-i/10;
        best=0;for(int i=0;i<huge.length;i++)best=Math.max(best,huge[i]*(i+1));histogram(huge,best);
        char[][] big=new char[200][200];for(char[] row:big)Arrays.fill(row,'1');matrix(big);
        for(char[] row:big)Arrays.fill(row,'0');matrix(big);
        for(int i=0;i<200;i++)for(int j=0;j<200;j++)big[i][j]=((i+j)&1)==0?'0':'1';matrix(big);
        for(int i=0;i<200;i++)for(int j=0;j<200;j++)big[i][j]=(i>=50&&i<150&&j>=40&&j<160)?'1':'0';matrix(big);
        int[][] allPoints=new int[10000][2],pointsExpected=new int[10000][2];
        for(int i=0;i<10000;i++){allPoints[i]=new int[]{9999-i,9999-i};pointsExpected[i]=new int[]{i,i};}merge(allPoints,pointsExpected);
        int[][] overlapping=new int[10000][2];for(int i=0;i<10000;i++)overlapping[i]=new int[]{i,Math.min(10000,i+2)};merge(overlapping,new int[][]{{0,10000}});
        for(int v=0;v<=2;v++){int[] equal=new int[300];Arrays.fill(equal,v);colors(equal);}
        int[] descending=new int[300];for(int i=0;i<300;i++)descending[i]=2-i/100;colors(descending);
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-27', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util/java.math导入、调整Solution类名与public声明；原方法体不变。'}
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
for i in ids:
    assert hashlib.sha256((SUBMISSION / f'LK{i}.java').read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
