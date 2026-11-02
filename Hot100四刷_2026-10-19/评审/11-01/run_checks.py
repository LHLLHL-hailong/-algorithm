from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '11-01'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [236, 437, 538, 617, 297]
paths = {i: SUBMISSION / f'LK{i}.java' for i in ids}
hashes = {}
parts = ['import java.util.*;\n', 'class TreeNode { int val; TreeNode left,right; TreeNode(){} TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;} }\n']
for i, path in paths.items():
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.name
    assert not target.exists(), 'Do not overwrite existing Java snapshot'
    target.write_bytes(data)
    class_name = 'Codec' if i == 297 else 'Solution'
    source, count = re.subn(r'\b(?:public\s+)?class\s+' + class_name + r'\b', f'class Q{i}', data.decode('utf-8-sig'), count=1)
    assert count == 1
    parts.append(source)

parts.append(r'''
public class ReviewChecks {
    static Random random=new Random(1101); static int[] checks=new int[5];
    static Q297 serializer=new Q297(), deserializer=new Q297();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> out=new ArrayList<>(); if(root==null)return out;
        ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);
        Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"cycle/shared child in tree");out.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return out;
    }
    static String shape(TreeNode root){
        StringBuilder out=new StringBuilder();List<TreeNode> queue=new ArrayList<>();queue.add(root);
        for(int i=0;i<queue.size();i++){TreeNode n=queue.get(i);if(n==null){out.append("#, ");continue;}out.append(n.val).append(',');queue.add(n.left);queue.add(n.right);}return out.toString();
    }
    static TreeNode randomTree(int count,int bound){
        if(count==0)return null;TreeNode root=new TreeNode(random.nextInt(2*bound+1)-bound);
        List<TreeNode> open=new ArrayList<>();open.add(root);
        for(int i=1;i<count;i++){
            int pos=random.nextInt(open.size());TreeNode parent=open.get(pos),child=new TreeNode(random.nextInt(2*bound+1)-bound);
            if(parent.left==null&&parent.right==null){if(random.nextBoolean())parent.left=child;else parent.right=child;}else if(parent.left==null)parent.left=child;else parent.right=child;
            if(parent.left!=null&&parent.right!=null)open.remove(pos);open.add(child);
        }return root;
    }
    static TreeNode complete(int count){
        if(count==0)return null;TreeNode[] all=new TreeNode[count];for(int i=0;i<count;i++)all[i]=new TreeNode(i);
        for(int i=0;i<count;i++){if(2*i+1<count)all[i].left=all[2*i+1];if(2*i+2<count)all[i].right=all[2*i+2];}return all[0];
    }
    static TreeNode chain(int count,int value){TreeNode root=null;for(int i=0;i<count;i++)root=new TreeNode(value,root,null);return root;}
    static void lca(TreeNode root,int repeats){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,TreeNode> parents=new IdentityHashMap<>();parents.put(root,null);
        int unique=0;for(TreeNode n:all){n.val=unique++-50000;if(n.left!=null)parents.put(n.left,n);if(n.right!=null)parents.put(n.right,n);}
        String before=shape(root);Q236 solution=new Q236();
        for(int i=0;i<repeats;i++){
            TreeNode p=all.get(random.nextInt(all.size())),q=all.get(random.nextInt(all.size()));if(p==q){i--;continue;}
            Set<TreeNode> ancestors=Collections.newSetFromMap(new IdentityHashMap<>());for(TreeNode n=p;n!=null;n=parents.get(n))ancestors.add(n);
            TreeNode expected=q;while(!ancestors.contains(expected))expected=parents.get(expected);
            require(solution.lowestCommonAncestor(root,p,q)==expected,"236 LCA identity");checks[0]++;
        }require(before.equals(shape(root)),"236 changed tree");
    }
    static int pathOracle(TreeNode root,int target){
        int result=0;for(TreeNode start:nodes(root)){
            ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Long> sums=new ArrayDeque<>();todo.push(start);sums.push((long)start.val);
            while(!todo.isEmpty()){TreeNode n=todo.pop();long sum=sums.pop();if(sum==target)result++;if(n.left!=null){todo.push(n.left);sums.push(sum+n.left.val);}if(n.right!=null){todo.push(n.right);sums.push(sum+n.right.val);}}
        }return result;
    }
    static void path(TreeNode root,int target){
        String before=shape(root);require(new Q437().pathSum(root,target)==pathOracle(root,target),"437 target="+target);require(before.equals(shape(root)),"437 changed tree");checks[1]++;
    }
    static void greater(TreeNode root){
        List<TreeNode> all=nodes(root);IdentityHashMap<TreeNode,Integer> expected=new IdentityHashMap<>();IdentityHashMap<TreeNode,TreeNode[]> links=new IdentityHashMap<>();
        for(TreeNode n:all){int sum=0;for(TreeNode other:all)if(other.val>=n.val)sum+=other.val;expected.put(n,sum);links.put(n,new TreeNode[]{n.left,n.right});}
        require(new Q538().convertBST(root)==root,"538 root identity");
        for(TreeNode n:all){require(n.val==expected.get(n),"538 greater sum");require(n.left==links.get(n)[0]&&n.right==links.get(n)[1],"538 changed links");}checks[2]++;
    }
    static TreeNode bst(int[] values,int from,int to){if(from>=to)return null;int mid=(from+to)/2;return new TreeNode(values[mid],bst(values,from,mid),bst(values,mid+1,to));}
    static List<TreeNode> inorder(TreeNode root){
        List<TreeNode> out=new ArrayList<>();ArrayDeque<TreeNode> stack=new ArrayDeque<>();TreeNode current=root;
        while(current!=null||!stack.isEmpty()){while(current!=null){stack.push(current);current=current.left;}current=stack.pop();out.add(current);current=current.right;}return out;
    }
    static void makeBst(TreeNode root){
        int value=-500;for(TreeNode n:inorder(root)){value+=1+random.nextInt(10);n.val=value;}
    }
    static TreeNode mergeOracle(TreeNode a,TreeNode b){
        if(a==null&&b==null)return null;
        return new TreeNode((a==null?0:a.val)+(b==null?0:b.val),mergeOracle(a==null?null:a.left,b==null?null:b.left),mergeOracle(a==null?null:a.right,b==null?null:b.right));
    }
    static void merge(TreeNode a,TreeNode b){
        String expected=shape(mergeOracle(a,b)),beforeB=shape(b);TreeNode actual=new Q617().mergeTrees(a,b);
        require(shape(actual).equals(expected),"617 full tree");require(actual==(a==null?b:a),"617 root reuse");require(shape(b).equals(beforeB),"617 changed second tree");nodes(actual);checks[3]++;
    }
    static void codec(TreeNode root){
        String expected=shape(root),encoded=serializer.serialize(root);
        require(expected.equals(shape(root)),"297 changed input");
        TreeNode decoded=deserializer.deserialize(encoded);require(expected.equals(shape(decoded)),"297 cross-codec full tree");nodes(decoded);
        TreeNode same=serializer.deserialize(encoded);require(expected.equals(shape(same)),"297 same-codec round trip");
        require(serializer.serialize(decoded).equals(encoded),"297 stable encoding");checks[4]++;
    }
    public static void main(String[] args){
        lca(new TreeNode(1,new TreeNode(2),null),2);
        for(int test=0;test<500;test++)lca(randomTree(2+random.nextInt(99),100),12);
        lca(complete(100000),8);lca(chain(1000,1),8);
        path(null,0);path(new TreeNode(1),1);path(new TreeNode(1),0);
        path(new TreeNode(0,new TreeNode(0),new TreeNode(0)),0);
        path(new TreeNode(10,new TreeNode(5,new TreeNode(3,new TreeNode(3),new TreeNode(-2)),new TreeNode(2,null,new TreeNode(1))),new TreeNode(-3,null,new TreeNode(11))),8);
        for(int test=0;test<1200;test++)path(randomTree(random.nextInt(101),20),random.nextInt(101)-50);
        TreeNode zeros=chain(1000,0);path(zeros,0);path(chain(1000,1000000000),0);
        path(new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(294967296),null),null),null),null),0);
        TreeNode uniquePrefix=complete(1000);ArrayDeque<TreeNode> work=new ArrayDeque<>();ArrayDeque<Integer> prefix=new ArrayDeque<>();work.add(uniquePrefix);prefix.add(0);
        while(!work.isEmpty()){TreeNode n=work.remove();int previous=prefix.remove(),now=n.val+1;n.val=now-previous;if(n.left!=null){work.add(n.left);prefix.add(now);}if(n.right!=null){work.add(n.right);prefix.add(now);}}
        Q437 observed=new Q437();require(observed.pathSum(uniquePrefix,0)==0,"437 positive paths");checks[1]++;
        System.out.println("RETAINED_KEYS_437="+observed.map.size());
        require(observed.map.size()==1001,"437 retained zero-key observation");
        Q437 reused437=new Q437();int first437=reused437.pathSum(new TreeNode(1),1),second437=reused437.pathSum(new TreeNode(1),1);
        System.out.println("REUSE437_FIRST="+first437);System.out.println("REUSE437_SECOND="+second437);
        greater(null);greater(new TreeNode(-10000));greater(new TreeNode(10000));
        for(int test=0;test<700;test++){TreeNode root=randomTree(random.nextInt(101),1);makeBst(root);greater(root);}
        int[] values=new int[10000];for(int i=0;i<values.length;i++)values[i]=i;TreeNode large=bst(values,0,values.length);List<TreeNode> all=inorder(large);new Q538().convertBST(large);
        int suffix=0;for(int i=9999;i>=0;i--){suffix+=i;require(all.get(i).val==suffix,"538 10000-node suffix oracle");}checks[2]++;
        TreeNode skew=chain(1000,0);makeBst(skew);greater(skew);
        Q538 reused538=new Q538();int first538=reused538.convertBST(new TreeNode(1)).val,second538=reused538.convertBST(new TreeNode(1)).val;
        System.out.println("REUSE538_FIRST="+first538);System.out.println("REUSE538_SECOND="+second538);
        merge(null,null);merge(new TreeNode(1),null);merge(null,new TreeNode(-1));
        for(int test=0;test<1000;test++)merge(randomTree(random.nextInt(101),10000),randomTree(random.nextInt(101),10000));
        merge(complete(2000),complete(2000));merge(chain(1000,10000),chain(1000,-10000));
        codec(null);codec(new TreeNode(-1000));codec(new TreeNode(1000));codec(new TreeNode(1,new TreeNode(2),null));codec(new TreeNode(1,null,new TreeNode(2)));
        for(int test=0;test<1000;test++)codec(randomTree(random.nextInt(151),1000));
        TreeNode largeCodec=complete(10000);for(TreeNode n:nodes(largeCodec))n.val=random.nextInt(2001)-1000;codec(largeCodec);codec(chain(1000,-1000));codec(null);
        System.out.println("CHECKS="+Arrays.toString(checks));
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts), encoding='utf-8')
compiled = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(java)], capture_output=True, text=True, encoding='utf-8', timeout=40)
result = {'date': '2026-11-01', 'recorded_at': datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'), 'submission_hashes': hashes, 'compile_exit_code': compiled.returncode, 'compile_output': compiled.stdout+compiled.stderr, 'leetcode_ac_verified': False, 'source_methods_unchanged': True, 'adjustments': '只添加java.util导入/TreeNode辅助类，将Solution或Codec类名改为各题唯一名称，原方法体不变。'}
if compiled.returncode == 0:
    execution = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'], capture_output=True, text=True, encoding='utf-8', timeout=45)
    result.update({'execution_exit_code': execution.returncode, 'execution_output': execution.stdout+execution.stderr, 'passed': execution.returncode == 0, 'primary_invocation_scope': '236可复用；437/538主测每例新Solution，字段复用观察另记；297使用同一对Codec反复/交叉调用，覆盖接口正常使用方式。'})
    match = re.search(r'CHECKS=\[(.*?)\]', execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str, ids), counts))
        result['total_cases'] = sum(counts)
    if result['passed']:
        result['same_instance_reuse_observations'] = {str(i): {'calls': '[1]后再次[1]', 'second_expected': 1, 'second_actual': int(re.search(r'REUSE'+str(i)+r'_SECOND=(-?\d+)', execution.stdout).group(1))} for i in [437,538]}
        result['space_observation_437'] = {'balanced_tree_nodes': 1000, 'tree_height': 10, 'retained_map_keys': int(re.search(r'RETAINED_KEYS_437=(\d+)', execution.stdout).group(1)), 'reason': '回溯减至0后没有删除key，总空间最坏O(n)，不只是O(h)。'}
else:
    result['passed'] = False
for i,path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest() == hashes[str(i)], 'Original changed during checks'
result['original_hashes_unchanged'] = True
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps(result,ensure_ascii=False))
