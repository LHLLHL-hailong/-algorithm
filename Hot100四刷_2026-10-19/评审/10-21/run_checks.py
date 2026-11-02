from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-21'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [31, 33, 34, 48, 104]
hashes = {}
sources = {}
for qid in ids:
    source = SUBMISSION / f'LK{qid}.java'
    data = source.read_bytes()
    hashes[str(qid)] = hashlib.sha256(data).hexdigest()
    sources[qid] = data.decode('utf-8-sig')
    snapshot = SNAPSHOTS / source.name
    if not snapshot.exists(): snapshot.write_bytes(data)

def compile_java(path):
    return subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(path)],
                          capture_output=True, text=True, encoding='utf-8', errors='replace', timeout=60)

# Preserve the compile failure of the actual submitted source as evidence.
original34 = OUT / 'Q34Submitted.java'
original34.write_text(sources[34].replace('class Solution', 'class Q34Submitted', 1), encoding='utf-8')
compile34 = compile_java(original34)
normalization = '集合导入、独立类名与TreeNode测试定义；31/33/48/104方法体保持原样'
parts = ['import java.util.*;\nclass TreeNode { int val; TreeNode left,right; TreeNode(int v) { val=v; } }\n']
for qid in [31, 33, 48, 104]:
    code, count = re.subn(r'\bclass\s+Solution\b', f'class Q{qid}', sources[qid], count=1)
    assert count == 1
    parts.append(code)

code34 = sources[34]
patch34 = None
if compile34.returncode != 0:
    pattern = r'(int\s+r1\s*=\s*-1)([ \t]*)(?=\r?$)'
    code34, count = re.subn(pattern, r'\1;\2', code34, count=1, flags=re.MULTILINE)
    assert count == 1, 'Unexpected compiler failure; inspect rather than silently repair.'
    patch34 = '仅测试副本中给int r1=-1补一个分号；不计为原提交通过'
parts.append(code34.replace('class Solution', 'class Q34LogicCopy', 1))
parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1021);
    static int[] checks = new int[5];
    static Q31 q31=new Q31(); static Q33 q33=new Q33(); static Q34LogicCopy q34=new Q34LogicCopy();
    static Q48 q48=new Q48(); static Q104 q104=new Q104();
    static void require(boolean value,String message) { if(!value) throw new AssertionError(message); }
    static int compare(int[] a,int[] b) {
        for(int i=0;i<a.length;i++) if(a[i]!=b[i]) return Integer.compare(a[i],b[i]); return 0;
    }
    static int[] bestPermutation;
    static void enumerate(int[] sorted,boolean[] used,int[] candidate,int depth,int[] original) {
        if(depth==sorted.length) {
            if(compare(candidate,original)>0 && (bestPermutation==null || compare(candidate,bestPermutation)<0))
                bestPermutation=candidate.clone();
            return;
        }
        for(int i=0;i<sorted.length;i++) {
            if(used[i] || (i>0 && sorted[i]==sorted[i-1] && !used[i-1])) continue;
            used[i]=true; candidate[depth]=sorted[i]; enumerate(sorted,used,candidate,depth+1,original); used[i]=false;
        }
    }
    static void permutation(int[] a) {
        int[] sorted=a.clone(); Arrays.sort(sorted); bestPermutation=null;
        enumerate(sorted,new boolean[a.length],new int[a.length],0,a);
        int[] expected=bestPermutation==null?sorted:bestPermutation;
        int[] actual=a.clone(); q31.nextPermutation(actual);
        require(Arrays.equals(actual,expected),"31: "+Arrays.toString(a)); checks[0]++;
    }
    static void search(int[] a,int target) {
        int expected=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { expected=i; break; }
        require(q33.search(a.clone(),target)==expected,"33: "+Arrays.toString(a)+" target="+target); checks[1]++;
    }
    static void range(int[] a,int target) {
        int lo=-1,hi=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { if(lo==-1)lo=i; hi=i; }
        require(Arrays.equals(q34.searchRange(a.clone(),target),new int[]{lo,hi}),"34-copy: "+Arrays.toString(a)); checks[2]++;
    }
    static void rotate(int[][] a) {
        int n=a.length; int[][] expected=new int[n][n]; int[][] actual=new int[n][];
        for(int i=0;i<n;i++) { actual[i]=a[i].clone(); for(int j=0;j<n;j++) expected[j][n-1-i]=a[i][j]; }
        q48.rotate(actual); require(Arrays.deepEquals(expected,actual),"48 size="+n); checks[3]++;
    }
    static void depth(TreeNode root) {
        int expected=0; ArrayDeque<TreeNode> queue=new ArrayDeque<>();
        if(root!=null) queue.add(root);
        while(!queue.isEmpty()) {
            int width=queue.size(); expected++;
            for(int i=0;i<width;i++) { TreeNode node=queue.remove(); if(node.left!=null)queue.add(node.left); if(node.right!=null)queue.add(node.right); }
        }
        require(q104.maxDepth(root)==expected,"104 expected="+expected); checks[4]++;
    }
    static TreeNode tree(int budget) {
        if(budget==0 || random.nextInt(5)==0) return null;
        TreeNode node=new TreeNode(random.nextInt()); int left=random.nextInt(budget);
        node.left=tree(left); node.right=tree(budget-1-left); return node;
    }
    public static void main(String[] args) {
        for(int[] a:new int[][]{{1},{1,2},{2,1},{1,3,2,2},{3,2,1},{1,1,5},{2,2,0,4,3,1},{0,0,0}}) permutation(a);
        for(int[] a:new int[][]{{1},{4,5,6,7,0,1,2},{1,3},{3,1},{0,1,2,3,4},{2,3,4,0,1}})
            for(int target=-1;target<=8;target++) search(a,target);
        for(int[] a:new int[][]{{},{1},{2,2,2},{1,2,2,2,3},{-1000000000,1000000000}})
            for(int target=-1;target<=3;target++) range(a,target);
        rotate(new int[][]{{1}}); rotate(new int[][]{{1,2},{3,4}}); rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        depth(null); depth(new TreeNode(1));
        TreeNode chain=new TreeNode(0),tail=chain;
        for(int i=0;i<999;i++) { tail.left=new TreeNode(i); tail=tail.left; } depth(chain);
        for(int i=0;i<1000;i++) {
            int[] p=new int[1+random.nextInt(7)]; for(int j=0;j<p.length;j++)p[j]=random.nextInt(5); permutation(p);
            int n=1+random.nextInt(50),shift=random.nextInt(n); int[] a=new int[n];
            for(int j=0;j<n;j++)a[j]=((j+shift)%n)*2-50;
            search(a,-51+random.nextInt(103)); search(a,a[random.nextInt(n)]);
            int[] b=new int[random.nextInt(50)]; for(int j=0;j<b.length;j++)b[j]=random.nextInt(11)-5;
            Arrays.sort(b); range(b,random.nextInt(15)-7);
            int m=1+random.nextInt(12); int[][] matrix=new int[m][m];
            for(int row=0;row<m;row++)for(int col=0;col<m;col++)matrix[row][col]=random.nextInt(); rotate(matrix);
            depth(tree(random.nextInt(150)));
        }
        int[] decreasing=new int[100]; for(int i=0;i<100;i++)decreasing[i]=99-i;
        q31.nextPermutation(decreasing); for(int i=0;i<100;i++)require(decreasing[i]==i,"31 size100"); checks[0]++;
        int[] repeats=new int[100000]; Arrays.fill(repeats,5); range(repeats,5); range(repeats,6);
        int[][] matrix=new int[20][20]; rotate(matrix);
        System.out.printf("{\"31\":%d,\"33\":%d,\"34_logic_copy\":%d,\"48\":%d,\"104\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4]);
    }
}
''')
test_source=OUT/'ReviewChecks.java'
test_source.write_text('\n'.join(parts),encoding='utf-8')
compiled=compile_java(test_source)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(BUILD),'ReviewChecks'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
counts=json.loads(executed.stdout.strip())
unchanged=all(hashlib.sha256((SUBMISSION/f'LK{qid}.java').read_bytes()).hexdigest()==hashes[str(qid)] for qid in ids)
assert unchanged
result={'date':'2026-10-21','status':'original_34_compile_failed_others_passed' if compile34.returncode else 'passed',
    'original_submission_passed_ids':[31,33,48,104]+([] if compile34.returncode else [34]),
    'original_34_compile':{'status':'failed' if compile34.returncode else 'passed','exit_code':compile34.returncode,
                           'diagnostics':compile34.stderr.strip(),'logic_copy_patch':patch34},
    'test_cases_by_question':counts,'total_cases_including_logic_copy':sum(counts.values()),
    'method':'实际Java：31穷举多重集合排列参考；33/34线性参考；48坐标参考；104层序遍历参考；固定种子随机和边界',
    'seed':1021,'normalization':normalization,'submission_sha256':hashes,'submission_files_unchanged':unchanged,
    'scope':'34副本补分号后的逻辑通过不能算作原件编译通过；本地测试不等于力扣全量AC'}
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':result['status'],'original_passed_ids':result['original_submission_passed_ids'],
                  'counts':counts,'34_compile_exit_code':compile34.returncode,'source_unchanged':unchanged}))
