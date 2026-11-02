from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT=Path(__file__).resolve().parent
ROOT=OUT.parents[2]
path=ROOT/'homework'/'10-22'/'LK215.java'
data=path.read_bytes()
source,count=re.subn(r'\bclass\s+Solution\b','class Q215Corrected',data.decode('utf-8-sig'),count=1)
assert count==1
SNAPSHOTS=OUT/'补正提交快照'
SNAPSHOTS.mkdir(exist_ok=True)
(SNAPSHOTS/'LK215.java').write_bytes(data)
BUILD=OUT/'recheck_215_build'
BUILD.mkdir(exist_ok=True)
java='import java.util.*;\n'+source+r'''
public class Review215Recheck {
    static Q215Corrected solution=new Q215Corrected();
    static int checks=0;
    static void check(int[] a,int k){
        int[] reference=a.clone();Arrays.sort(reference);
        int actual=solution.findKthLargest(a.clone(),k);
        if(actual!=reference[a.length-k])throw new AssertionError("215: "+Arrays.toString(a)+" k="+k+" got="+actual);
        checks++;
    }
    public static void main(String[] args){
        for(int[] a:new int[][]{{1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{2,2,2,2},{-10000,10000,0}})
            for(int k=1;k<=a.length;k++)check(a,k);
        Random inputRandom=new Random(1023);
        for(int test=0;test<2000;test++){
            int[] a=new int[1+inputRandom.nextInt(100)];
            for(int i=0;i<a.length;i++)a[i]=inputRandom.nextInt(41)-20;
            check(a,1+inputRandom.nextInt(a.length));
        }
        int[] equal=new int[100000];Arrays.fill(equal,7);check(equal,1);check(equal,50000);check(equal,100000);
        int[] ascending=new int[100000];for(int i=0;i<ascending.length;i++)ascending[i]=i/5-10000;
        for(int run=0;run<3;run++){check(ascending,1);check(ascending,50000);check(ascending,100000);}
        int[] descending=ascending.clone();
        for(int i=0;i<descending.length/2;i++){int v=descending[i];descending[i]=descending[descending.length-1-i];descending[descending.length-1-i]=v;}
        check(descending,1);check(descending,50000);check(descending,100000);
        System.out.println(checks);
    }
}
'''
java_path=OUT/'Review215Recheck.java'
java_path.write_text(java,encoding='utf-8')
compiled=subprocess.run(['javac','-encoding','UTF-8','-d',str(BUILD),str(java_path)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(BUILD),'Review215Recheck'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
assert hashlib.sha256(path.read_bytes()).hexdigest()==hashlib.sha256(data).hexdigest()
result={'date':'2026-10-23','question':215,'status':'passed','cases':int(executed.stdout.strip()),
        'source':'homework/10-22/LK215.java','source_sha256':hashlib.sha256(data).hexdigest(),
        'normalization':'只在测试副本补java.util导入、重命名Solution；方法体保持原样',
        'large_cases':'10万元素升序（范围-10000..9999）、降序、全相等；k为1、中位、n，多次随机路径',
        'performance_assessment':'当前区间均匀随机枢轴，保留等值均分；期望O(n)，最好Theta(n)，最坏Theta(n^2)。大样例通过不构成所有随机路径的最坏时间保证。',
        'remaining_comment_correction':'原注释最好O(1)尚未改为O(n)，已反馈，不阻止代码验收',
        'input_seed':1023,'algorithm_random_seed':'使用用户new Random()，未注入固定算法种子',
        'source_unchanged_by_assistant':True,'scope':'本地检查，不声称访问确认力扣全量AC'}
(OUT/'215补正复检.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':'passed','cases':result['cases'],'large_cases':'100000 sorted, reverse, equal','source_unchanged':True}))
