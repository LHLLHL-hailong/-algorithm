from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-31'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [105, 114, 124, 226, 543, 4]
paths = {i: SUBMISSION / (f'review/LK{i}.java' if i == 4 else f'LK{i}.java') for i in ids}
hashes = {}
parts = ['import java.util.*;\n', 'class TreeNode { int val; TreeNode left,right; TreeNode(){} TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;} }\n']
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
    static Random random=new Random(1031);static int[] checks=new int[6];static int rankChecks;
    static Q4 q4=new Q4();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> result=new ArrayList<>();if(root==null)return result;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"tree cycle/alias");result.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return result;
    }
    static TreeNode copy(TreeNode root){if(root==null)return null;return new TreeNode(root.val,copy(root.left),copy(root.right));}
    static String shape(TreeNode root){
        StringBuilder b=new StringBuilder();LinkedList<TreeNode> stack=new LinkedList<>();stack.push(root);
        while(!stack.isEmpty()){TreeNode n=stack.pop();if(n==null){b.append("#,");continue;}b.append(n.val).append(',');stack.push(n.right);stack.push(n.left);}return b.toString();
    }
    static int[] inorder(TreeNode root){
        List<Integer> values=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){while(current!=null){stack.push(current);current=current.left;}current=stack.pop();values.add(current.val);current=current.right;}
        return values.stream().mapToInt(x->x).toArray();
    }
    static void construct(TreeNode expected){
        int[] pre=nodes(expected).stream().mapToInt(n->n.val).toArray(),in=inorder(expected);int[] preCopy=pre.clone(),inCopy=in.clone();
        TreeNode actual=new Q105().buildTree(preCopy,inCopy);
        require(shape(actual).equals(shape(expected)),"105 full shape/values");require(Arrays.equals(pre,preCopy)&&Arrays.equals(in,inCopy),"105 arrays changed");nodes(actual);checks[0]++;
    }
    static void flatten(TreeNode original){
        TreeNode root=copy(original);List<TreeNode> expected=nodes(root);new Q114().flatten(root);TreeNode current=root;
        for(TreeNode node:expected){require(current==node&&current.left==null,"114 original identity/preorder/left");current=current.right;}
        require(current==null,"114 tail/cycle");checks[1]++;
    }
    static void invert(TreeNode original){
        TreeNode root=copy(original);IdentityHashMap<TreeNode,TreeNode[]> links=new IdentityHashMap<>();for(TreeNode n:nodes(root))links.put(n,new TreeNode[]{n.left,n.right});
        require(new Q226().invertTree(root)==root,"226 root identity");
        for(var e:links.entrySet())require(e.getKey().left==e.getValue()[1]&&e.getKey().right==e.getValue()[0],"226 swapped edges");nodes(root);checks[3]++;
    }
    static List<List<Integer>> graph(List<TreeNode> all){
        IdentityHashMap<TreeNode,Integer> index=new IdentityHashMap<>();for(int i=0;i<all.size();i++)index.put(all.get(i),i);
        List<List<Integer>> edges=new ArrayList<>();for(int i=0;i<all.size();i++)edges.add(new ArrayList<>());
        for(int i=0;i<all.size();i++)for(TreeNode child:new TreeNode[]{all.get(i).left,all.get(i).right})if(child!=null){int j=index.get(child);edges.get(i).add(j);edges.get(j).add(i);}return edges;
    }
    static int maxPathOracle(TreeNode root){
        List<TreeNode> all=nodes(root);List<List<Integer>> edges=graph(all);int best=Integer.MIN_VALUE;
        for(int from=0;from<all.size();from++){
            ArrayDeque<int[]> stack=new ArrayDeque<>();stack.push(new int[]{from,-1,all.get(from).val});
            while(!stack.isEmpty()){int[] s=stack.pop();best=Math.max(best,s[2]);for(int next:edges.get(s[0]))if(next!=s[1])stack.push(new int[]{next,s[0],s[2]+all.get(next).val});}
        }return best;
    }
    static int[] farthest(List<List<Integer>> edges,int from){
        int[] dist=new int[edges.size()];Arrays.fill(dist,-1);dist[from]=0;ArrayDeque<Integer> queue=new ArrayDeque<>();queue.add(from);int last=from;
        while(!queue.isEmpty()){last=queue.remove();for(int next:edges.get(last))if(dist[next]<0){dist[next]=dist[last]+1;queue.add(next);}}return new int[]{last,dist[last]};
    }
    static int diameterOracle(TreeNode root){List<List<Integer>> edges=graph(nodes(root));return farthest(edges,farthest(edges,0)[0])[1];}
    static void paths(TreeNode root){paths(root,maxPathOracle(root));}
    static void paths(TreeNode root,int expected){String before=shape(root);require(new Q124().maxPathSum(root)==expected,"124 max node path");require(shape(root).equals(before),"124 changed tree");checks[2]++;}
    static void diameter(TreeNode root){String before=shape(root);require(new Q543().diameterOfBinaryTree(root)==diameterOracle(root),"543 diameter edges");require(shape(root).equals(before),"543 changed tree");checks[4]++;}
    static TreeNode randomTree(int n){
        if(n==0)return null;TreeNode root=new TreeNode(0);List<TreeNode> available=new ArrayList<>();available.add(root);
        for(int i=1;i<n;i++){
            int pick=random.nextInt(available.size());TreeNode p=available.get(pick),c=new TreeNode(i);
            if(p.left==null&&p.right==null){if(random.nextBoolean())p.left=c;else p.right=c;}else if(p.left==null)p.left=c;else p.right=c;
            if(p.left!=null&&p.right!=null)available.remove(pick);available.add(c);
        }return root;
    }
    static TreeNode complete(int n,int value){
        if(n==0)return null;TreeNode[] nodes=new TreeNode[n];for(int i=0;i<n;i++)nodes[i]=new TreeNode(value);
        for(int i=0;i<n;i++){if(2*i+1<n)nodes[i].left=nodes[2*i+1];if(2*i+2<n)nodes[i].right=nodes[2*i+2];}return nodes[0];
    }
    static void sortedArrays(List<int[]> out,int[] a,int pos,int minimum){if(pos==a.length){out.add(a.clone());return;}for(int v=minimum;v<=2;v++){a[pos]=v;sortedArrays(out,a,pos+1,v);}}
    static int[] combined(int[] a,int[] b){int[] all=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,all,a.length,b.length);Arrays.sort(all);return all;}
    static void median(int[] a,int[] b){
        int[] sorted=combined(a,b);int n=sorted.length;double expected=n%2==0?((long)sorted[n/2-1]+sorted[n/2])/2.0:sorted[n/2];
        int[] x=a.clone(),y=b.clone();require(Double.compare(q4.findMedianSortedArrays(x,y),expected)==0,"4 median");require(Arrays.equals(x,a)&&Arrays.equals(y,b),"4 arrays changed");checks[5]++;
    }
    static void rank(int[] a,int[] b,int k){require(q4.dfs(a.clone(),b.clone(),k)==combined(a,b)[k-1],"4 rank="+k);rankChecks++;}
    public static void main(String[] args){
        flatten(null);invert(null);
        for(int test=0;test<800;test++){
            TreeNode root=randomTree(1+random.nextInt(100));construct(root);flatten(root);invert(root);diameter(root);
            for(TreeNode node:nodes(root))node.val=random.nextInt(2001)-1000;paths(root);
        }
        for(TreeNode root:new TreeNode[]{new TreeNode(-3),new TreeNode(-10,new TreeNode(-20),new TreeNode(-5)),new TreeNode(-10,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7))),new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3))}){paths(root);diameter(root);flatten(root);invert(root);}
        TreeNode c3000=complete(3000,0);int index=-1500;for(TreeNode node:nodes(c3000))node.val=index++;construct(c3000);
        TreeNode skew=null;for(int i=999;i>=0;i--)skew=new TreeNode(i,null,skew);construct(skew);
        TreeNode c2000=complete(2000,3);flatten(c2000);
        TreeNode c10000=complete(10000,0);diameter(c10000);
        TreeNode positive=complete(30000,1000);paths(positive,(diameterOracle(positive)+1)*1000);
        for(TreeNode node:nodes(positive))node.val=-1000;paths(positive,-1000);
        TreeNode path=null;for(int i=0;i<1000;i++)path=new TreeNode(1,path,null);paths(path,1000);flatten(path);diameter(path);
        Q114 reused114=new Q114();TreeNode first=new TreeNode(1),second=new TreeNode(2);reused114.flatten(first);reused114.flatten(second);
        Q124 reused124=new Q124();int first124=reused124.maxPathSum(new TreeNode(5)),second124=reused124.maxPathSum(new TreeNode(-1));
        Q543 reused543=new Q543();int first543=reused543.diameterOfBinaryTree(new TreeNode(1,new TreeNode(2),new TreeNode(3))),second543=reused543.diameterOfBinaryTree(new TreeNode(0));
        System.out.println("REUSE114_CROSS_LINK="+(first.right==second));System.out.println("REUSE124_FIRST="+first124);System.out.println("REUSE124_SECOND="+second124);System.out.println("REUSE543_FIRST="+first543);System.out.println("REUSE543_SECOND="+second543);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=5;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        median(new int[]{1,3},new int[]{2});median(new int[]{1,2},new int[]{3,4});median(new int[]{},new int[]{1});median(new int[]{-1000000},new int[]{1000000});
        for(int test=0;test<1000;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];
            for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;
            Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        int[] a=new int[1000],b=new int[1000];for(int i=0;i<1000;i++){a[i]=i*2;b[i]=i*2+1;}median(a,b);median(new int[]{},b);median(a,new int[]{});for(int k=1;k<=2000;k++)rank(a,b,k);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compile_result = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-10-31', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compile_result.returncode, 'compile_output': compile_result.stdout + compile_result.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '仅添加java.util导入、TreeNode辅助类、调整Solution类名与public声明；原方法体不变。'}
if compile_result.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout + execution.stderr, 'passed': execution.returncode == 0, 'primary_tree_invocation_scope': '主测试每例新Solution；有字段的同实例复用边界另记'})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['total_cases'] = sum(counts)
    rank_match = re.search(r'RANK_CHECKS=(\d+)', execution.stdout)
    if rank_match:
        result['additional_rank_checks_4'] = int(rank_match.group(1))
    result['same_instance_reuse_observations'] = {'114': {'first_root': '[1]', 'second_root': '[2]', 'cross_tree_link': 'REUSE114_CROSS_LINK=true' in execution.stdout}, '124': {'calls': ['[5]', '[-1]'], 'second_expected': -1, 'second_actual': int(re.search(r'REUSE124_SECOND=(-?\d+)', execution.stdout).group(1))}, '543': {'calls': ['[1,2,3]', '[0]'], 'second_expected': 0, 'second_actual': int(re.search(r'REUSE543_SECOND=(-?\d+)', execution.stdout).group(1))}}
else:
    result['passed'] = False
for i, path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest() == hashes[str(i)], 'Original file changed during check'
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
