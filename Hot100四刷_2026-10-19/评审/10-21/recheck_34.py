from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT=Path(__file__).resolve().parent
ROOT=OUT.parents[2]
old=json.loads((OUT/'测试结果.json').read_text(encoding='utf-8'))
source=ROOT/'homework'/'10-21'/'LK34.java'
data=source.read_bytes()
code=data.decode('utf-8-sig')
assert re.search(r'int\s+r1\s*=\s*-1\s*;',code), 'Submitted semicolon is still missing.'
snapshot=OUT/'补正提交快照'
snapshot.mkdir(exist_ok=True)
(snapshot/'LK34.java').write_bytes(data)
build=OUT/'recheck_build'
build.mkdir(exist_ok=True)
java=code.replace('class Solution','class Q34Rechecked',1)+r'''
public class Review34Recheck {
    static int checks=0;
    static Q34Rechecked solution=new Q34Rechecked();
    static void check(int[] a,int target) {
        int first=-1,last=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { if(first==-1)first=i; last=i; }
        int[] actual=solution.searchRange(a.clone(),target);
        if(!java.util.Arrays.equals(actual,new int[]{first,last}))
            throw new AssertionError(java.util.Arrays.toString(a)+" target="+target);
        checks++;
    }
    public static void main(String[] args) {
        for(int[] a:new int[][]{{},{1},{2,2,2},{1,2,2,2,3},{-1000000000,1000000000}})
            for(int target=-1;target<=3;target++)check(a,target);
        java.util.Random random=new java.util.Random(1021);
        for(int test=0;test<1000;test++) {
            int[] a=new int[random.nextInt(50)];
            for(int i=0;i<a.length;i++)a[i]=random.nextInt(11)-5;
            java.util.Arrays.sort(a);check(a,random.nextInt(15)-7);
        }
        int[] a=new int[100000]; java.util.Arrays.fill(a,5);check(a,5);check(a,6);
        System.out.println(checks);
    }
}
'''
path=OUT/'Review34Recheck.java'
path.write_text(java,encoding='utf-8')
compiled=subprocess.run(['javac','-encoding','UTF-8','-d',str(build),str(path)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert compiled.returncode==0,compiled.stderr
executed=subprocess.run(['java','-cp',str(build),'Review34Recheck'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
assert executed.returncode==0,executed.stderr
assert hashlib.sha256(source.read_bytes()).hexdigest()==hashlib.sha256(data).hexdigest()
changes={}
for qid in [31,33,48,104]:
    p=ROOT/'homework'/'10-21'/f'LK{qid}.java'
    original=(OUT/'提交快照'/p.name).read_text(encoding='utf-8-sig')
    current=p.read_text(encoding='utf-8-sig')
    strip=lambda s: re.sub(r'//[^\n]*|/\*[\s\S]*?\*/','',s)
    assert strip(original)==strip(current),f'{qid} method body changed; run fresh checks.'
    changes[str(qid)]='unchanged' if original==current else 'comment_or_whitespace_only'
result={'date':'2026-10-21','question':34,'status':'passed','cases':int(executed.stdout.strip()),
        'source':'homework/10-21/LK34.java','original_submission_compiles':True,
        'normalization':'仅测试定义中重命名Solution，未补分号或更改用户方法体',
        'submitted_sha256':hashlib.sha256(data).hexdigest(),
        'old_submitted_sha256':old['submission_sha256']['34'],
        'other_method_bodies_unchanged':True,'other_file_changes':changes,
        'source_files_unchanged_by_assistant':True}
(OUT/'34补正复检.json').write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps({'status':'passed','question':34,'cases':result['cases'],'other_file_changes':changes}))
