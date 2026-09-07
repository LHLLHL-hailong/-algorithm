class Solution {


    ListNode merge(ListNode[]lists ,int left,int right){

        if(left>right) return null;
        if(left==right) return lists[left];

        int mid=(left+right)/2;

        return build(merge(lists,left,mid),merge(lists,mid+1,right));//注意这里，merge确保返回一个ListNode(可能为null) build负责进行排序

    }

    ListNode build(ListNode ptr1,ListNode ptr2){

        if(ptr1==null){
            return ptr2;
        }
        if(ptr2==null){
            return ptr1;
        }

        ListNode head=new ListNode(-1);
        ListNode ptr=head;

        while(ptr1!=null && ptr2!=null){

            if(ptr1.val<=ptr2.val){
                ptr.next=ptr1;
                ptr1=ptr1.next;

            }else{
                ptr.next=ptr2;
                ptr2=ptr2.next;
            }
                ptr=ptr.next;
        }

        ptr.next=(ptr1==null?ptr2:ptr1);

        return head.next;

    }



    public ListNode mergeKLists(ListNode[] lists) {

        return merge(lists,0,lists.length-1);

    }
}






/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode head=new ListNode(-1);
        ListNode ptr=head;

        PriorityQueue<ListNode>queue=new PriorityQueue<>((a,b)->a.val-b.val);

        for(ListNode now:lists){
            if(now!=null){
                queue.add(now);
            }
        }

        while(!queue.isEmpty()){
            ListNode now=queue.remove();
            
            ptr.next=now;
            ptr=ptr.next;
            if(now.next!=null){
                queue.add(now.next);
            }
        }

        return head.next;
        
    }
}

/*
优先队列的方法想起来了，但是归并的方法忘了
综合评价:C+ (需要重点复习归并的方法)

 */


/*

  - k = 链表个数
  - N = 所有链表的节点总数
  - 假设每个链表平均长度 ≈ N/k
  
================================================================================
                        时间复杂度 & 空间复杂度 详细推导
================================================================================

设:
  k = 链表个数
  N = 所有链表的节点总数
  单个链表平均长度 ≈ N/k

------------------------------------------------------------------------------
解法一：分治归并 (Divide & Conquer Merge)
------------------------------------------------------------------------------

■ 时间复杂度

归并树:
  - 每层递归将 k 个链表对半拆分，递归树高度 = log₂ k
  - 第 0 层（根）：合并两半，每半长度各约 N/2，build() 比较次数 ≈ N
  - 第 1 层：合并 2 组，每组两端各约 N/4，build() 比较次数 ≈ N/2 + N/2 = N
  - 第 2 层：合并 4 组，每组两端各约 N/8，build() 比较次数 ≈ N/4 × 2 = N
  - ...
  - 第 i 层：合并 2^i 组，总比较次数 ≈ N
  - 共 log₂ k 层

  总比较次数 = N × log₂ k  →  O(N log k)


■ 空间复杂度

  递归栈深度 = 归并树高度 = O(log k)

  build() 内部:
    - 仅使用常数个指针变量 (head, ptr, ptr1, ptr2)
    - 合并过程是"原地重连指针"，不创建新节点（哑节点 head 占用 O(1)）
    - → O(1)

  总计: O(log k)（递归栈）

  注意: 若计入结果链表本身占用 N 个节点，那是输入/输出固有的空间，
        不属于算法"额外"空间。


------------------------------------------------------------------------------
解法二：优先队列 / 最小堆 (Priority Queue / Min-Heap)
------------------------------------------------------------------------------

■ 时间复杂度

  分两步:

  [1] 初始化建堆
      遍历 k 个链表的头节点，每个插入堆 (add) 复杂度 = O(log k)
      → 总计 O(k log k)

  [2] 主循环
      总共 N 个节点，每个节点经历:
        出堆 (remove / poll): O(log k)     ← 从堆顶取走当前最小值
        入堆 (add / offer):   O(log k)     ← 将弹出节点的后继加入堆（尾节点无后继，少一次入堆）

      故每个节点 1~2 次堆操作，每次 O(log k)
      → 总计 ≈ 2N × O(log k) = O(N log k)

  综合:
    O(k log k) + O(N log k)
    当 N ≥ k 时（通常如此，除非大量空链表），主导项为 O(N log k)

  最终: O(N log k)

■ 空间复杂度

  PriorityQueue 内部是一个二叉堆数组，最多同时持有 k 个节点（每个链表在堆中最多 1 个节点）
  → O(k)

  对比解法一: O(k) > O(log k)，所以解法一空间更优（k 很大时明显）。


------------------------------------------------------------------------------
最终对比
------------------------------------------------------------------------------

| 维度       | 分治归并 (解法一)    | 优先队列 (解法二)    |
|-----------|---------------------|---------------------|
| 时间       | O(N log k)           | O(N log k)           |
| 空间(额外) | O(log k)（递归栈）    | O(k)（堆）            |
| 常数因子   | 小（纯指针）          | 大（每节点两次堆调整）  |

  k 较小时（如 k ≤ 100），两者实际差距不大
  k 极大时（如 k ≈ N，每个链表只有 1~2 个节点），解法一空间明显更优

================================================================================
*/