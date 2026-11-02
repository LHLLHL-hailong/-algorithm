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
/**
 * start: 21:18
 * end:21:22
 * 独立完成
 * 时间：O(n)
 * 空间: O(1)
 */
class Solution {
    public ListNode reverseList(ListNode head) {

        if(head==null || head.next==null) return head;

        ListNode pre=head;
        ListNode now=head.next;

        head.next=null;//tip:重点

        while(now!=null){
            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        return pre;
        
    }
}