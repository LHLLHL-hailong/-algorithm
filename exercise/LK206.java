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

        if(head==null || head.next==null){
            return head;
        }

        ListNode nowPtr=head.next;
        ListNode prePtr=head;

        head.next=null;

        while(nowPtr!=null){
            ListNode tmp=nowPtr.next;

            nowPtr.next=prePtr;
            prePtr=nowPtr;
            nowPtr=tmp;
        }

        return prePtr;
        
    }
}