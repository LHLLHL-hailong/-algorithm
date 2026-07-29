/**
 * 这道题比较难，想的就是要破坏原来的链表，别看它只是一道简单题，但是它的写法很难像是这么的不优雅
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
    public boolean isPalindrome(ListNode head) {

        if(head.next==null) return true;

        ListNode quick=head;
        ListNode slow=head;

        while(quick.next!=null && quick.next.next!=null){
            quick=quick.next.next;
            slow=slow.next;
        }

        if(quick.next!=null){
            quick=quick.next;
        }

        ListNode pic=slow.next;

        ListNode pre=slow;

        while(pic!=null){
            ListNode tmp=pic.next;
            pic.next=pre;
            pre=pic;
            pic=tmp;
        }
        
        ListNode check=head;
        boolean isTrue=true;

        ListNode recover=quick;

        while(check!=slow.next){
            if(check.val!=quick.val){
                isTrue=false;
                break;
            }
            check=check.next;
            quick=quick.next;
        } 


        ListNode tmp=recover.next;
        recover.next=null;
        while(recover!=slow){
           
            ListNode cc=tmp.next;
            tmp.next=recover;
            recover=tmp;
            tmp=cc;

        }

        return isTrue;

        
    }
}




