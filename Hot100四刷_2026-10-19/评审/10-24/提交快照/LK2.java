/**
 * start: 9:16
 * end: 9:25
 * 独立完成
 * 时间: O(max(m,n))
 * 空间: O(1)
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1=new ListNode(-1);
        head1.next=l1;
        ListNode head2=new ListNode(-1);
        head2.next=l2;
        
        ListNode ptr1=head1;
        ListNode ptr2=head2;

        int cnt=0;

        while(ptr1.next!=null && ptr2.next!=null){
            cnt+=ptr1.next.val+ptr2.next.val;
            ptr1.next.val=cnt%10;
            cnt/=10;
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }

        if(ptr1.next==null){
            ptr1.next=ptr2.next;
        }

        while(ptr1.next!=null && cnt!=0){
            cnt+=ptr1.next.val;
            ptr1.next.val=cnt%10;
            cnt=cnt/10;
            ptr1=ptr1.next;
        }

        while(cnt!=0){
            ListNode tmp=new ListNode(cnt%10);
            cnt/=10;
            ptr1.next=tmp;
            ptr1=ptr1.next;
        }


        return head1.next;
    }
}