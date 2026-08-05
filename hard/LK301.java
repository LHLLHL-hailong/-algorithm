class Solution {


    boolean iaValid(char[] now){

        int leftCount=0;
        for(char c:now){
            if(c=='('){
                leftCount++;
            }else if(c==')'){
                if(leftCount>0){
                    leftCount--;
                }else{
                    return false;
                }
            }
        }

        return leftCount==0;
    }

    String build(char[]sc){
        StringBuilder sb=new StringBuilder();
        for(char now :sc){
            if(now!='#'){
                sb.append(now);
            }
        }
        return sb.toString();
    }



        void dfs(char[]sc,int leftCount,int rightCount,ArrayList<String> res,int startIdx){

            if(leftCount==0 && rightCount==0 ){

                if(iaValid(sc)){
                    res.add(build(sc));
                }
                return ;
            }

            //sc.length-i >=leftCount+rightCount
            //i<=sc.length-leftCount-rightCount
            for(int i=startIdx;i<=sc.length-leftCount-rightCount; i++){
                if(i!=startIdx && sc[i]==sc[i-1]){
                    continue;
                }

                if(sc[i]=='(' && leftCount>0){
                    sc[i]='#';
                    dfs(sc,leftCount-1,rightCount,res,i+1);
                    sc[i]='(';
                }

                if(sc[i]==')' && rightCount>0){
                    sc[i]='#';
                    dfs(sc,leftCount,rightCount-1,res,i+1);
                    sc[i]=')';
                }

            }
        }



    public List<String> removeInvalidParentheses(String s) {

        int leftCount=0;
        int rightCount=0;

        char[]sc=s.toCharArray();

        for(int i=0;i<sc.length;i++){

            if(sc[i]=='('){
                leftCount++;
            }else if(sc[i]==')'){
                if(leftCount>0){
                    leftCount--;
                }else{
                    rightCount++;
                }
            }
        }

        ArrayList<String>res=new ArrayList<>();

        dfs(sc,leftCount,rightCount,res,0);

        return res;
    }
}



class Solution {


    boolean isValid(char[] now){

        int leftCount=0;
        for(char c:now){
            if(c=='('){
                leftCount++;
            }else if(c==')'){
                if(leftCount>0){
                    leftCount--;
                }else{
                    return false;
                }
            }
        }

        return leftCount==0;
    }


    public List<String> removeInvalidParentheses(String s) {

        HashSet<String>set=new HashSet<>();
        HashSet<String>next=new HashSet<>();
        set.add(s);

        while(true){
            for(String res:set){
                if(isValid(res.toCharArray())){next.add(res);}
            }
                if(!next.isEmpty()){
                    return new ArrayList(next);
                }

                for(String res:set){

                    for(int i=0;i<res.length();i++){
                         if (res.charAt(i) == '(' || res.charAt(i) == ')'){//注意，要加上这个条件，不然会慢很多
                            next.add(res.substring(0,i)+res.substring(i+1,res.length()));

                         }
                        
                    }

                }

                HashSet<String> tmp=next;
                next=set;
                set=tmp;

                next.clear();

        }

    }
}


/*

1. 因为这道题的答案就是指数级别的，所以不要尝试去找常数级别的贪心优化,就是DFS或者是BFS
2. 这道题的DFS远比BFS快

综合评价:C-(第一印象一直想着贪心，关于深搜的想法是一点都没有，还需要再写)

 */


/*
面试回答：LeetCode 301 删除无效括号 — DFS vs BFS 时间复杂度分析
=====================================================================

问题：给定只含括号的字符串，删最少字符，返回所有合法结果。
      DFS 回溯和 BFS 层序遍历是两种标准解法，分析时间复杂度，并解释 DFS 为什么更快。


一、变量定义

设字符串长度 N，最少需删 K 个字符。K 通过一遍扫描 O(N) 算出。


二、BFS 时间复杂度分析

BFS 的核心结构：逐层从原串删除括号。第 0 层是原串本身（删 0 个），第 1 层是所有删 1 个括号的串，
第 2 层是所有删 2 个的串，直到第 K 层首次出现合法串。

逐层分解：

  层数：共 K+1 层（第 0 层到第 K 层）。

  每层的候选数量：第 i 层有 C(N,i) 个候选串。因为是从 N 个括号中选 i 个位置删除。

  每个候选的处理代价：
    - 生成候选：从上一层串中删一个字符，调用 substring 两次再拼接。Java 中 substring 会分配新
      char 数组，开销 O(N)。
    - 校验合法性：调用 isValid 从头遍历计数左右括号，开销 O(N)。
    - 合计每个候选约 O(N)。

  每层总代价：C(N,i) × O(N)

  总代价 = Σ(i=0 to K) C(N,i) × O(N) = O(N × ΣC(N,i))

  最坏情况 K ≈ N/2 时，组合数求和 ΣC(N,i) 趋近 2^(N-1)。

  最终：T_BFS = O(N × 2^N)


三、DFS 时间复杂度分析

DFS 的核心结构：提前算出要删几个左括号几个右括号，然后在原串的 char 数组上回溯，到每个字符位置
决策"删还是不删"，删够 K 个且合法就收集。节点分两类：

  （1）内部节点（未删够 K 个）：
       数量：每个叶子对应一条从根出发、长约 N 的路径。所有路径上除去叶子，内部节点总数约为
             O(K × C(N,K))。
       单节点代价：看一眼当前字符，决定是否标记删除然后递归，O(1)。

  （2）叶子节点（恰好删够 K 个）：
       数量：在 N 个括号中选 K 个删除，共 C(N,K) 个叶子。
       单节点代价：遍历 char 数组拼结果串 O(N) + 校验 O(N)，合计 O(N)。

  总代价 = 内部节点开销 + 叶子节点开销
         = O(K × C(N,K)) × O(1) + C(N,K) × O(N)
         = O(K × C(N,K)) + O(N × C(N,K))

  由于 K ≤ N，叶子开销主导，简化得：T_DFS = O(N × C(N,K))

  最坏情况下同为 O(N × 2^N)。


四、渐进同量级下 DFS 为什么远比 BFS 快

两者最坏都是 O(N × 2^N)，但实际表现差距巨大。原因从三个维度分析：

  维度一：处理的节点总量不同。

    BFS 处理了第 0 层到第 K 层所有候选串，总计 Σ(i=0 to K) C(N,i) 个，其中只有第 K 层可能合法，
    前面所有中间层都是无效的。当 K 较大时，Σ(i=0 to K-1) C(N,i) 约等于 2^N，意味着绝大多数节点
    都是无效计算。

    DFS 只对 C(N,K) 个叶子做 O(N) 处理。其内部节点只做 O(1) 决策，不生成完整字符串、不做校验。
    换句话讲，同样一个"删了一半但还不合法"的中间状态，BFS 会为它生成一个完整的 String 并校验，
    DFS 只是递归栈上多了一层调用。

    对比：BFS 处理了组合数求和量级的节点，DFS 仅处理了单层组合数量级的节点。

  维度二：单个内部节点的操作代价不同。

    BFS 内部节点：四次 O(N) 操作（两次 substring、一次拼接、一次 isValid）。
    DFS 内部节点：一次 O(1) 操作（标记数组元素）。

    中间层的候选数以百万甚至千万计，乘以 O(N) 和 O(1) 的差距是致命的。

  维度三：空间开销不同。

    BFS：每层用 HashSet 存储所有候选 String，对象数量 = 该层候选数，GC 压力极大。
    DFS：一份 char 数组原地修改加回溯恢复，递归栈深度 O(N)。无额外对象分配。

五、一句话总结

"两个算法最坏都是 O(N × 2^N)，但 BFS 需要处理目标层之前所有中间层的候选，而中间层的总数是指数
级别的组合数求和，且每候选 O(N)；DFS 只在 C(N,K) 个叶子上做 O(N) 校验，内部节点 O(1)。同量级
下常数差距可达指数倍，DFS 显著优于 BFS。"

 */
