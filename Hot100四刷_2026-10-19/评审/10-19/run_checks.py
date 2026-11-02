from pathlib import Path
import hashlib
import json
import re
import subprocess

OUT = Path(__file__).resolve().parent
ROOT = OUT.parents[2]
SUBMISSION = ROOT / 'homework' / '10-19'
BUILD = OUT / 'build'
BUILD.mkdir(exist_ok=True)
ids = [1, 283, 3, 438, 76]
hashes = {}
parts = ['import java.util.*;\n']
for qid in ids:
    source = SUBMISSION / f'LK{qid}.java'
    hashes[str(qid)] = hashlib.sha256(source.read_bytes()).hexdigest()
    code = source.read_text(encoding='utf-8-sig')
    code, count = re.subn(r'\bclass\s+Solution\b', f'class Q{qid}', code, count=1)
    assert count == 1
    parts.append(code)

parts.append(r'''
public class ReviewChecks {
    static Random random = new Random(1019);
    static int[] checks = new int[5];
    static Q1 q1 = new Q1(); static Q283 q283 = new Q283();
    static Q3 q3 = new Q3(); static Q438 q438 = new Q438(); static Q76 q76 = new Q76();
    static void require(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }
    static String word(int length, String alphabet) {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<length; i++) s.append(alphabet.charAt(random.nextInt(alphabet.length())));
        return s.toString();
    }
    static void sum(int[] a, int target) {
        int[] result = q1.twoSum(a.clone(), target);
        require(result.length == 2 && result[0] >= 0 && result[1] >= 0 &&
            result[0] < a.length && result[1] < a.length && result[0] != result[1] &&
            a[result[0]] + a[result[1]] == target, "1: " + Arrays.toString(a));
        checks[0]++;
    }
    static void zeros(int[] a) {
        int[] expected = new int[a.length]; int index = 0;
        for (int value : a) if (value != 0) expected[index++] = value;
        int[] actual = a.clone(); q283.moveZeroes(actual);
        require(Arrays.equals(actual, expected), "283: " + Arrays.toString(a));
        checks[1]++;
    }
    static void distinct(String s) {
        int expected = 0;
        for (int start=0; start<s.length(); start++) {
            Set<Character> seen = new HashSet<>();
            for (int end=start; end<s.length() && seen.add(s.charAt(end)); end++)
                expected = Math.max(expected, end-start+1);
        }
        require(q3.lengthOfLongestSubstring(s) == expected, "3: " + s);
        checks[2]++;
    }
    static void anagrams(String s, String p) {
        List<Integer> expected = new ArrayList<>();
        char[] sorted = p.toCharArray(); Arrays.sort(sorted);
        for (int i=0; i+p.length()<=s.length(); i++) {
            char[] candidate = s.substring(i, i+p.length()).toCharArray();
            Arrays.sort(candidate);
            if (Arrays.equals(sorted, candidate)) expected.add(i);
        }
        List<Integer> actual = q438.findAnagrams(s,p);
        require(expected.equals(actual), "438: s=" + s + ",p=" + p + ",got=" + actual);
        checks[3]++;
    }
    static boolean covers(int[] window, int[] target) {
        for (int i=0; i<128; i++) if (window[i]<target[i]) return false;
        return true;
    }
    static void minWindow(String s, String t) {
        int[] target = new int[128];
        for (char c : t.toCharArray()) target[c]++;
        String expected = ""; int best = Integer.MAX_VALUE;
        for (int start=0; start<s.length(); start++) {
            int[] window = new int[128];
            for (int end=start; end<s.length(); end++) {
                window[s.charAt(end)]++;
                if (covers(window,target)) {
                    if (end-start+1<best) { best=end-start+1; expected=s.substring(start,end+1); }
                    break;
                }
            }
        }
        String actual = q76.minWindow(s,t);
        int[] actualCounts = new int[128];
        for (char c : actual.toCharArray()) actualCounts[c]++;
        require(actual.length()==expected.length() &&
            (best==Integer.MAX_VALUE ? actual.isEmpty() : s.contains(actual) && covers(actualCounts,target)),
            "76: s=" + s + ",t=" + t + ",got=" + actual + ",reference=" + expected);
        checks[4]++;
    }
    public static void main(String[] args) {
        sum(new int[]{3,3},6); sum(new int[]{3,2,4},6);
        sum(new int[]{-1_000_000_000,1,1_000_000_000},0);
        for(int[] a : new int[][]{{0},{1},{0,0,0},{1,0,2,0,3},{0,1,0,3,12}}) zeros(a);
        for(String s : new String[]{"", "a", "aaaa", "abba", "abcabcbb", " ", "aA1!a"}) distinct(s);
        for(String[] pair : new String[][]{{"cbaebabacd","abc"},{"abab","ab"},{"a","aa"},{"aaa","aa"},{"aaab","ab"},{"a","a"},{"baa","aa"}})
            anagrams(pair[0],pair[1]);
        for(String[] pair : new String[][]{{"ADOBECODEBANC","ABC"},{"a","aa"},{"a","a"},{"AAA","AA"},{"aaab","aab"},{"aA","A"},{"A","a"},{"ABBAAC","AAC"},{"abc","Z"}})
            minWindow(pair[0],pair[1]);
        for(int i=0; i<2000; i++) {
            int[] a = new int[2+random.nextInt(25)];
            for(int j=0; j<a.length; j++) a[j]=random.nextInt(41)-20;
            int first=random.nextInt(a.length), second=(first+1+random.nextInt(a.length-1))%a.length;
            sum(a,a[first]+a[second]); zeros(a);
            distinct(word(random.nextInt(24),"abAB !01"));
            anagrams(word(random.nextInt(24),"abc"),word(1+random.nextInt(8),"abc"));
            minWindow(word(random.nextInt(24),"abAB"),word(1+random.nextInt(8),"abAB"));
        }
        StringBuilder large = new StringBuilder();
        for(int i=0; i<100_000; i++) large.append('a');
        require(q3.lengthOfLongestSubstring(large.toString())==1,"3 large"); checks[2]++;
        require(q438.findAnagrams(large.toString(),"aa").size()==99_999,"438 large"); checks[3]++;
        require(q76.minWindow(large.toString(),"aa").equals("aa"),"76 large"); checks[4]++;
        System.out.printf("{\"1\":%d,\"283\":%d,\"3\":%d,\"438\":%d,\"76\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4]);
    }
}
''')
test_source = OUT / 'ReviewChecks.java'
test_source.write_text('\n'.join(parts), encoding='utf-8')
compiled = subprocess.run(['javac', '-encoding', 'UTF-8', '-d', str(BUILD), str(test_source)],
                          capture_output=True, text=True, timeout=60)
assert compiled.returncode == 0, compiled.stderr
executed = subprocess.run(['java', '-cp', str(BUILD), 'ReviewChecks'],
                          capture_output=True, text=True, timeout=60)
assert executed.returncode == 0, executed.stderr
counts = json.loads(executed.stdout.strip())
for qid in ids:
    assert hashlib.sha256((SUBMISSION / f'LK{qid}.java').read_bytes()).hexdigest() == hashes[str(qid)]
result = {'date':'2026-10-19', 'status':'passed', 'test_cases_by_question': counts,
          'total_test_cases':sum(counts.values()), 'seed':1019,
          'method':'实际Java实现：边界样例、2000组/题随机对拍、3/438/76的10万字符用例',
          'normalization':'仅在新评审目录中添加java.util导入、重命名Solution类，保持方法体不变',
          'scope':'非空p/t；438小写英文字母；76大小写英文字母；本地测试不等同力扣全量AC',
          'submission_sha256':hashes, 'submission_files_unchanged':True}
(OUT / '测试结果.json').write_text(json.dumps(result, ensure_ascii=False, indent=2)+'\n', encoding='utf-8')
print(json.dumps(result, ensure_ascii=False))
