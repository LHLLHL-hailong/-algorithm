from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-30'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [94, 98, 101, 102, 4]
hashes = {}
parts = ['import java.util.*;\n', 'class TreeNode { int val; TreeNode left,right; TreeNode(){} TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;} }\n']
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
    static Random random=new Random(1030);
    static int[] checks=new int[5];static int rankChecks;
    static Q4 q4=new Q4();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> all=new ArrayList<>();if(root==null)return all;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"cycle/alias in tree");all.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return all;
    }
    static List<Integer> inorderOracle(TreeNode root){
        List<Integer> values=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){
            while(current!=null){stack.push(current);current=current.left;}
            current=stack.pop();values.add(current.val);current=current.right;
        }return values;
    }
    record Bound(TreeNode node,long low,long high){}
    static boolean bstOracle(TreeNode root){
        ArrayDeque<Bound> stack=new ArrayDeque<>();if(root!=null)stack.push(new Bound(root,Long.MIN_VALUE,Long.MAX_VALUE));
        while(!stack.isEmpty()){
            Bound b=stack.pop();TreeNode n=b.node;if(n.val<=b.low||n.val>=b.high)return false;
            if(n.left!=null)stack.push(new Bound(n.left,b.low,n.val));if(n.right!=null)stack.push(new Bound(n.right,n.val,b.high));
        }return true;
    }
    static String structure(TreeNode root,boolean mirror){
        StringBuilder b=new StringBuilder();LinkedList<TreeNode> stack=new LinkedList<>();stack.push(root);
        while(!stack.isEmpty()){
            TreeNode n=stack.pop();if(n==null){b.append("#,");continue;}b.append(n.val).append(',');
            if(mirror){stack.push(n.left);stack.push(n.right);}else{stack.push(n.right);stack.push(n.left);}
        }return b.toString();
    }
    record Depth(TreeNode node,int level){}
    static List<List<Integer>> levelsOracle(TreeNode root){
        List<List<Integer>> levels=new ArrayList<>();ArrayDeque<Depth> stack=new ArrayDeque<>();if(root!=null)stack.push(new Depth(root,0));
        while(!stack.isEmpty()){
            Depth d=stack.pop();if(d.level==levels.size())levels.add(new ArrayList<>());levels.get(d.level).add(d.node.val);
            if(d.node.right!=null)stack.push(new Depth(d.node.right,d.level+1));if(d.node.left!=null)stack.push(new Depth(d.node.left,d.level+1));
        }return levels;
    }
    static void tree(TreeNode root){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,TreeNode[]> before=new IdentityHashMap<>();
        boolean smallValues=true,mediumValues=true;
        for(TreeNode n:all){before.put(n,new TreeNode[]{n.left,n.right});smallValues&=n.val>=-100&&n.val<=100;mediumValues&=n.val>=-1000&&n.val<=1000;}
        // Fresh Solution per primary case, matching the ordinary problem runner.
        if(all.size()<=100&&smallValues){require(new Q94().inorderTraversal(root).equals(inorderOracle(root)),"94 inorder");checks[0]++;}
        if(!all.isEmpty()&&all.size()<=10000){require(new Q98().isValidBST(root)==bstOracle(root),"98 ancestor/strict bound");checks[1]++;}
        if(!all.isEmpty()&&all.size()<=1000&&smallValues){
            boolean expected=structure(root.left,false).equals(structure(root.right,true));require(new Q101().isSymmetric(root)==expected,"101 mirrored structure");checks[2]++;
        }
        if(all.size()<=2000&&mediumValues){require(new Q102().levelOrder(root).equals(levelsOracle(root)),"102 DFS grouped levels");checks[3]++;}
        for(var e:before.entrySet())require(e.getKey().left==e.getValue()[0]&&e.getKey().right==e.getValue()[1],"tree input changed");
    }
    static TreeNode randomTree(int size){
        if(size==0)return null;TreeNode root=new TreeNode(random.nextInt(11)-5);List<TreeNode> available=new ArrayList<>();available.add(root);
        for(int i=1;i<size;i++){
            int pick=random.nextInt(available.size());TreeNode parent=available.get(pick),child=new TreeNode(random.nextInt(11)-5);
            if(parent.left==null&&parent.right==null){if(random.nextBoolean())parent.left=child;else parent.right=child;}
            else if(parent.left==null)parent.left=child;else parent.right=child;
            if(parent.left!=null&&parent.right!=null)available.remove(pick);available.add(child);
        }return root;
    }
    static TreeNode mirrored(TreeNode root){if(root==null)return null;return new TreeNode(root.val,mirrored(root.right),mirrored(root.left));}
    static TreeNode balanced(int from,int to){if(from>to)return null;int mid=(from+to)>>>1;return new TreeNode(mid,balanced(from,mid-1),balanced(mid+1,to));}
    static int[] combined(int[] a,int[] b){int[] all=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,all,a.length,b.length);Arrays.sort(all);return all;}
    static void median(int[] a,int[] b){
        int[] sorted=combined(a,b);require(sorted.length>0,"invalid empty pair");int n=sorted.length;
        double expected=n%2==0?((long)sorted[n/2-1]+sorted[n/2])/2.0:sorted[n/2];
        int[] x=a.clone(),y=b.clone();require(Double.compare(q4.findMedianSortedArrays(x,y),expected)==0,"4 "+Arrays.toString(a)+" "+Arrays.toString(b));
        require(Arrays.equals(a,x)&&Arrays.equals(b,y),"4 input changed");checks[4]++;
    }
    static void rank(int[] a,int[] b,int k){int[] sorted=combined(a,b);require(q4.dfs(a.clone(),b.clone(),k)==sorted[k-1],"4 rank k="+k);rankChecks++;}
    static void sortedArrays(List<int[]> result,int[] prefix,int position,int minimum){
        if(position==prefix.length){result.add(prefix.clone());return;}
        for(int v=minimum;v<=2;v++){prefix[position]=v;sortedArrays(result,prefix,position+1,v);}
    }
    public static void main(String[] args){
        tree(null);tree(new TreeNode(0));tree(new TreeNode(-1));
        tree(new TreeNode(2,new TreeNode(1),new TreeNode(3)));tree(new TreeNode(1,new TreeNode(1),null));
        tree(new TreeNode(5,new TreeNode(1),new TreeNode(7,new TreeNode(4),new TreeNode(8))));
        tree(new TreeNode(0,new TreeNode(Integer.MIN_VALUE),new TreeNode(Integer.MAX_VALUE)));
        tree(new TreeNode(Integer.MIN_VALUE));tree(new TreeNode(Integer.MAX_VALUE));
        tree(new TreeNode(1,new TreeNode(2,new TreeNode(3),null),new TreeNode(2,new TreeNode(3),null)));
        tree(new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(2,new TreeNode(4),new TreeNode(3))));
        for(int test=0;test<1000;test++)tree(randomTree(random.nextInt(101)));
        for(int test=0;test<300;test++){
            TreeNode left=randomTree(random.nextInt(500));TreeNode root=new TreeNode(0,left,mirrored(left));tree(root);
            if(root.right!=null){root.right.val++;tree(root);}
        }
        TreeNode rightChain=null,leftChain=null;
        for(int i=99;i>=0;i--){rightChain=new TreeNode(i,null,rightChain);leftChain=new TreeNode(99-i,leftChain,null);}tree(rightChain);tree(leftChain);
        TreeNode deep=null;for(int i=1999;i>=0;i--)deep=new TreeNode(i,null,deep);tree(deep);
        TreeNode longLeft=null;for(int i=0;i<499;i++)longLeft=new TreeNode(i%3,longLeft,null);tree(new TreeNode(0,longLeft,mirrored(longLeft)));
        tree(balanced(0,1999));tree(balanced(0,9999));
        Q94 repeat94=new Q94();List<Integer> first94=new ArrayList<>(repeat94.inorderTraversal(new TreeNode(1)));List<Integer> second94=new ArrayList<>(repeat94.inorderTraversal(new TreeNode(2)));
        Q98 repeat98=new Q98();boolean first98=repeat98.isValidBST(new TreeNode(1)),second98=repeat98.isValidBST(new TreeNode(1));
        System.out.println("REUSE94_FIRST="+first94);System.out.println("REUSE94_SECOND="+second94);System.out.println("REUSE98_FIRST="+first98);System.out.println("REUSE98_SECOND="+second98);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=5;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        median(new int[]{1,3},new int[]{2});median(new int[]{1,2},new int[]{3,4});median(new int[]{},new int[]{1});
        median(new int[]{-1000000},new int[]{1000000});median(new int[]{1000000},new int[]{1000000});
        for(int test=0;test<1000;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];
            for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;
            Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        int[] longA=new int[1000],longB=new int[1000];for(int i=0;i<1000;i++){longA[i]=i*2;longB[i]=i*2+1;}
        median(longA,longB);median(new int[]{},longB);median(longA,new int[]{});median(new int[]{-1000000},longB);median(longA,new int[]{1000000});
        for(int k=1;k<=2000;k++)rank(longA,longB,k);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-30', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util导入、TreeNode辅助类、调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0, 'primary_tree_invocation_scope': '每个主测试用例新建Solution，约束内按题分别筛选；复用实例行为另行记录，不混作主测试失败'})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['total_cases'] = sum(counts)
    rank_match = re.search(r'RANK_CHECKS=(\d+)', execution.stdout)
    if rank_match:
        result['additional_rank_checks_4'] = int(rank_match.group(1))
    result['same_instance_reuse_observations'] = {'94': {'calls': ['[1]', '[2]'], 'first': '[1]', 'second_expected': '[2]', 'second_actual': re.search(r'REUSE94_SECOND=(.*)', execution.stdout).group(1)}, '98': {'calls': ['[1]', '[1]'], 'first': True, 'second_expected': True, 'second_actual': re.search(r'REUSE98_SECOND=(true|false)', execution.stdout).group(1) == 'true'}}
else:
    result['passed'] = False
for i in ids:
    assert hashlib.sha256((SUBMISSION / f'LK{i}.java').read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
