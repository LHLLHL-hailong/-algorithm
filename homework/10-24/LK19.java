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
 * start: 9:28
 * end: 9:32
 * 独立完成
 * 时间O(n)
 * 空间O(1)
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode ptr1=head;
        ListNode ptr2=head;

        for(int i=1;i<n;i++){
            if(ptr1==null) return head;
            ptr1=ptr1.next;
        }
        
        ListNode pre=null;

        while(ptr1.next!=null){
            ptr1=ptr1.next;
            pre=ptr2;
            ptr2=ptr2.next;
        }

        if(pre==null){
            return head.next;
        }else{
            pre.next=pre.next.next;
        }

        return head;
        
    }
}