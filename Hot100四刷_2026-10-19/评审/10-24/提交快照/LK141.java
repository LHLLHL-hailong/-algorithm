/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/**
 * start: 10:17
 * end: 10:22
 * 独立完成
 * 时间 O(n)
 * 空间 O(1)
 */
public class Solution {
    public boolean hasCycle(ListNode head) {

        if(head==null || head.next==null) return false;
        if(head.next==head) return true;

        ListNode slow=head;
        ListNode quick=head.next;

        while(quick.next!=null && quick.next.next!=null ){
            quick=quick.next.next;
            slow=slow.next;

            if(quick==slow){

                return true;

            }
        }

        return false;
        
    }
}