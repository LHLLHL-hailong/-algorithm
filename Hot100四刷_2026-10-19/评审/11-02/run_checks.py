from pathlib import Path
from datetime import datetime, timezone, timedelta
import hashlib
import json
import re
import subprocess
import sys

sys.stdout.reconfigure(encoding='utf-8')
OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '11-02'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
SNAPSHOTS = OUT / '提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
ids = [312, 4, 437, 297]
paths = {i: SUBMISSION / ('312.java' if i == 312 else f'LK{i}.java') for i in ids}
hashes = {}
parts = ['import java.util.*;\n', 'class TreeNode {int val; TreeNode left,right; TreeNode(int v){val=v;} TreeNode(int v,TreeNode l,TreeNode r){val=v;left=l;right=r;}}\n']
for i,path in paths.items():
    data = path.read_bytes()
    hashes[str(i)] = hashlib.sha256(data).hexdigest()
    target = SNAPSHOTS / path.name
    assert not target.exists(), 'Do not overwrite existing Java snapshot'
    target.write_bytes(data)
    name = 'Codec' if i == 297 else 'Solution'
    source,count = re.subn(r'\b(?:public\s+)?class\s+'+name+r'\b',f'class Q{i}',data.decode('utf-8-sig'),count=1)
    assert count == 1
    parts.append(source)
parts.append(r'''
public class ReviewChecks {
    static Random random=new Random(1102); static int[] checks=new int[4]; static int rankChecks=0;
    static Q312 q312=new Q312();static Q4 q4=new Q4();static Q297 ser=new Q297(),deser=new Q297();
    static void require(boolean ok,String message){if(!ok)throw new AssertionError(message);}
    static int burstOracle(int[] a,int mask,int[] memo){
        if(mask==0)return 0;if(memo[mask]>=0)return memo[mask];int best=0;
        for(int i=0;i<a.length;i++)if((mask&(1<<i))!=0){
            int left=i-1,right=i+1;while(left>=0&&(mask&(1<<left))==0)left--;while(right<a.length&&(mask&(1<<right))==0)right++;
            int score=(left<0?1:a[left])*a[i]*(right==a.length?1:a[right]);best=Math.max(best,score+burstOracle(a,mask^(1<<i),memo));
        }return memo[mask]=best;
    }
    static void burst(int[] a){int[] memo=new int[1<<a.length];Arrays.fill(memo,-1);burst(a,burstOracle(a,(1<<a.length)-1,memo));}
    static void burst(int[] a,int expected){int[] input=a.clone();require(q312.maxCoins(input)==expected,"312 "+Arrays.toString(a));require(Arrays.equals(a,input),"312 input mutation");checks[0]++;}
    static int[] sorted(int[] a,int[] b){int[] out=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,out,a.length,b.length);Arrays.sort(out);return out;}
    static void median(int[] a,int[] b){
        int[] all=sorted(a,b),x=a.clone(),y=b.clone();int n=all.length;
        double expected=n%2==0?((long)all[n/2-1]+all[n/2])/2.0:all[n/2];require(q4.findMedianSortedArrays(x,y)==expected,"4 median");require(Arrays.equals(x,a)&&Arrays.equals(y,b),"4 mutation");checks[1]++;
    }
    static void rank(int[] a,int[] b,int k){require(q4.getNum(a,b,k)==sorted(a,b)[k-1],"4 rank k="+k);rankChecks++;}
    static void sortedArrays(List<int[]> out,int[] a,int pos,int minimum){if(pos==a.length){out.add(a.clone());return;}for(int v=minimum;v<=2;v++){a[pos]=v;sortedArrays(out,a,pos+1,v);}}
    static List<TreeNode> nodes(TreeNode root){
        List<TreeNode> out=new ArrayList<>();if(root==null)return out;ArrayDeque<TreeNode> todo=new ArrayDeque<>();todo.push(root);Set<TreeNode> seen=Collections.newSetFromMap(new IdentityHashMap<>());
        while(!todo.isEmpty()){TreeNode n=todo.pop();require(seen.add(n),"tree alias/cycle");out.add(n);if(n.right!=null)todo.push(n.right);if(n.left!=null)todo.push(n.left);}return out;
    }
    static String shape(TreeNode root){StringBuilder s=new StringBuilder();List<TreeNode> q=new ArrayList<>();q.add(root);for(int i=0;i<q.size();i++){TreeNode n=q.get(i);if(n==null){s.append("#, ");continue;}s.append(n.val).append(',');q.add(n.left);q.add(n.right);}return s.toString();}
    static TreeNode tree(int count,int bound){
        if(count==0)return null;TreeNode root=new TreeNode(random.nextInt(2*bound+1)-bound);List<TreeNode> open=new ArrayList<>();open.add(root);
        for(int i=1;i<count;i++){int pos=random.nextInt(open.size());TreeNode p=open.get(pos),n=new TreeNode(random.nextInt(2*bound+1)-bound);if(p.left==null&&p.right==null){if(random.nextBoolean())p.left=n;else p.right=n;}else if(p.left==null)p.left=n;else p.right=n;if(p.left!=null&&p.right!=null)open.remove(pos);open.add(n);}return root;
    }
    static TreeNode complete(int count){if(count==0)return null;TreeNode[] a=new TreeNode[count];for(int i=0;i<count;i++)a[i]=new TreeNode(i);for(int i=0;i<count;i++){if(2*i+1<count)a[i].left=a[2*i+1];if(2*i+2<count)a[i].right=a[2*i+2];}return a[0];}
    static TreeNode chain(int count,int value){TreeNode root=null;for(int i=0;i<count;i++)root=new TreeNode(value,root,null);return root;}
    static int pathOracle(TreeNode root,int target){
        int result=0;for(TreeNode start:nodes(root)){ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Long> sums=new ArrayDeque<>();todo.push(start);sums.push((long)start.val);while(!todo.isEmpty()){TreeNode n=todo.pop();long sum=sums.pop();if(sum==target)result++;if(n.left!=null){todo.push(n.left);sums.push(sum+n.left.val);}if(n.right!=null){todo.push(n.right);sums.push(sum+n.right.val);}}}return result;
    }
    static void path(TreeNode root,int target){String before=shape(root);require(new Q437().pathSum(root,target)==pathOracle(root,target),"437 target="+target);require(before.equals(shape(root)),"437 mutation");checks[2]++;}
    static void codec(TreeNode root){
        String before=shape(root),encoded=ser.serialize(root);TreeNode decoded=deser.deserialize(encoded);
        require(before.equals(shape(decoded)),"297 cross codec structure/values");require(before.equals(shape(ser.deserialize(encoded))),"297 self codec");require(before.equals(shape(root)),"297 source changed");require(ser.serialize(decoded).equals(encoded),"297 stable encoding");nodes(decoded);checks[3]++;
    }
    public static void main(String[] args){
        for(int n=1;n<=5;n++)for(int code=0;code<(1<<(2*n));code++){int[] a=new int[n];int value=code;for(int i=0;i<n;i++){a[i]=value&3;value>>=2;}burst(a);}
        burst(new int[]{3,1,5,8});burst(new int[]{1,5});burst(new int[]{100});burst(new int[]{0,2,0});
        for(int test=0;test<200;test++){int[] a=new int[1+random.nextInt(9)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(101);burst(a);}
        int[] full=new int[300];burst(full,0);Arrays.fill(full,1);burst(full,300);Arrays.fill(full,100);burst(full,298010100);
        List<int[]> arrays=new ArrayList<>();for(int n=0;n<=4;n++)sortedArrays(arrays,new int[n],0,-2);
        for(int[] a:arrays)for(int[] b:arrays)if(a.length+b.length>0)median(a,b);
        for(int test=0;test<600;test++){
            int m=random.nextInt(1001),n=random.nextInt(1001);if(m+n==0)n=1;int[] a=new int[m],b=new int[n];for(int i=0;i<m;i++)a[i]=random.nextInt(2000001)-1000000;for(int i=0;i<n;i++)b[i]=random.nextInt(2000001)-1000000;Arrays.sort(a);Arrays.sort(b);median(a,b);rank(a,b,1);rank(a,b,m+n);rank(a,b,(m+n+1)/2);
        }
        for(int test=0;test<100;test++){int[] a=new int[random.nextInt(31)],b=new int[random.nextInt(31)];for(int i=0;i<a.length;i++)a[i]=random.nextInt(5)-2;for(int i=0;i<b.length;i++)b[i]=random.nextInt(5)-2;Arrays.sort(a);Arrays.sort(b);if(a.length+b.length>0){median(a,b);for(int k=1;k<=a.length+b.length;k++)rank(a,b,k);}}
        int[] a=new int[1000],b=new int[1000];for(int i=0;i<1000;i++){a[i]=2*i;b[i]=2*i+1;}median(a,b);median(new int[]{},a);median(b,new int[]{});for(int k=1;k<=2000;k++)rank(a,b,k);median(new int[]{-1000000},new int[]{1000000});median(new int[]{1000000},new int[]{1000000});
        path(null,0);path(new TreeNode(1),0);path(new TreeNode(1),1);path(new TreeNode(0,new TreeNode(0),new TreeNode(0)),0);
        path(new TreeNode(10,new TreeNode(5,new TreeNode(3,new TreeNode(3),new TreeNode(-2)),new TreeNode(2,null,new TreeNode(1))),new TreeNode(-3,null,new TreeNode(11))),8);
        for(int test=0;test<600;test++)path(tree(random.nextInt(101),20),random.nextInt(101)-50);
        path(chain(1000,0),0);path(chain(1000,1000000000),0);
        path(new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(294967296),null),null),null),null),0);
        TreeNode distinct=complete(1000);ArrayDeque<TreeNode> todo=new ArrayDeque<>();ArrayDeque<Integer> previous=new ArrayDeque<>();todo.add(distinct);previous.add(0);
        while(!todo.isEmpty()){TreeNode n=todo.remove();int pre=previous.remove(),now=n.val+1;n.val=now-pre;if(n.left!=null){todo.add(n.left);previous.add(now);}if(n.right!=null){todo.add(n.right);previous.add(now);}}
        Q437 retained=new Q437();require(retained.pathSum(distinct,0)==0,"437 positive-prefix tree");require(retained.map.size()==1001,"437 retained keys");checks[2]++;System.out.println("RETAINED_KEYS_437="+retained.map.size());
        Q437 reused=new Q437();int first=reused.pathSum(new TreeNode(1),1),second=reused.pathSum(new TreeNode(1),1);System.out.println("REUSE437_FIRST="+first);System.out.println("REUSE437_SECOND="+second);
        codec(null);codec(new TreeNode(-1000));codec(new TreeNode(1000));codec(new TreeNode(1,new TreeNode(2),null));codec(new TreeNode(1,null,new TreeNode(2)));
        for(int test=0;test<600;test++)codec(tree(random.nextInt(151),1000));
        TreeNode large=complete(10000);for(TreeNode n:nodes(large))n.val=random.nextInt(2001)-1000;codec(large);codec(chain(1000,-1000));codec(null);
        System.out.println("CHECKS="+Arrays.toString(checks));System.out.println("RANK_CHECKS="+rankChecks);
    }
}
''')
java = OUT / 'ReviewChecks.java'
assert not java.exists(), 'Do not overwrite existing Java'
java.write_text('\n'.join(parts),encoding='utf-8')
compiled = subprocess.run(['javac','-encoding','UTF-8','-d',str(BUILD),str(java)],capture_output=True,text=True,encoding='utf-8',timeout=40)
result = {'date':'2026-11-02','recorded_at':datetime.now(timezone(timedelta(hours=8))).isoformat(timespec='seconds'),'submission_hashes':hashes,'compile_exit_code':compiled.returncode,'compile_output':compiled.stdout+compiled.stderr,'source_methods_unchanged':True,'leetcode_ac_verified':False,'adjustments':'仅补java.util/TreeNode，调整Solution或Codec类名与public声明，原方法体不变。'}
if compiled.returncode == 0:
    execution = subprocess.run(['java','-cp',str(BUILD),'ReviewChecks'],capture_output=True,text=True,encoding='utf-8',timeout=45)
    result.update({'execution_exit_code':execution.returncode,'execution_output':execution.stdout+execution.stderr,'passed':execution.returncode==0,'primary_invocation_scope':'312/4可复用；437主测每例新Solution、额外复用单列；297同一对Codec反复交叉/自调用。'})
    match = re.search(r'CHECKS=\[(.*?)\]',execution.stdout)
    if match:
        counts = [int(x.strip()) for x in match.group(1).split(',')]
        result['checks_by_question'] = dict(zip(map(str,ids),counts))
        result['total_cases'] = sum(counts)
        result['additional_rank_checks_4'] = int(re.search(r'RANK_CHECKS=(\d+)',execution.stdout).group(1))
    if result['passed']:
        result['same_instance_reuse_observations'] = {'437':{'calls':'[1],target1后再次[1],target1','second_expected':1,'second_actual':int(re.search(r'REUSE437_SECOND=(-?\d+)',execution.stdout).group(1))}}
        result['space_observation_437'] = {'balanced_tree_nodes':1000,'tree_height':10,'retained_map_keys':int(re.search(r'RETAINED_KEYS_437=(\d+)',execution.stdout).group(1)),'reason':'计数降为0的键仍保留，当前实际总辅助空间最坏O(n)。'}
else:
    result['passed'] = False
for i,path in paths.items():
    assert hashlib.sha256(path.read_bytes()).hexdigest()==hashes[str(i)],'Original changed during checks'
result['original_hashes_unchanged'] = True
(OUT/'测试结果.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps(result,ensure_ascii=False))
