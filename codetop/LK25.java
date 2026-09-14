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
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head==null || head.next==null || k<=1){
            return head;
        }

        ListNode phead=new ListNode(-1);
        phead.next=head;

        ListNode preHead=phead;
        ListNode nextHead=phead;

        while(true){

        for(int i=0;i<k;i++){
            if(nextHead.next!=null){
                nextHead=nextHead.next;
            }else{
                return phead.next;
            }
        }
        nextHead=nextHead.next;

        ListNode nowHead=preHead.next;

        ListNode prePtr=nowHead;
        ListNode ptr=nowHead.next;
        nowHead.next=null;
        while(ptr!=nextHead && ptr!=null){
            ListNode tmp=ptr.next;
            ptr.next=prePtr;
            prePtr=ptr;
            ptr=tmp;
        }

        preHead.next=prePtr;
        nowHead.next=nextHead;

        preHead=nowHead;
        nextHead=nowHead;
        }
        
    }
}