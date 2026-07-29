/**
 * 基本上没有难度
 */
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

        ListNode ptr=new ListNode();
        ListNode now=ptr;

        while(head!=null){
            ListNode tmp=head.next;
            head.next=ptr.next;
            ptr.next=head;
            head=tmp;
        }

        return now.next;
        
    }
}