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
    public ListNode reverseList(ListNode head) {
        
        if(head==null || head.next==null) return head;

        ListNode res=head;

        ListNode ptr=head.next;
        ListNode prePtr=head;

        prePtr.next=null;//开头的时候需要先辅助的断一下，不然会成环

        while(ptr!=null){
            ListNode tmp=ptr.next;

            ptr.next=prePtr;
            prePtr=ptr;
            ptr=tmp;
        }

        return prePtr;//返回的是翻转完成之后的链表的头节点
    }
}