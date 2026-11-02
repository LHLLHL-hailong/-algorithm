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
 * start:21:22
 * end:21:35
 * 独立完成
 * 时间：O(n)
 * 空间：O(1)
 */
class Solution {
    public boolean isPalindrome(ListNode head) {

        if(head==null || head.next==null) return true;

        ListNode quick=head;
        ListNode slow=head;

        while(quick.next!=null && quick.next.next!=null){
            quick=quick.next.next;
            slow=slow.next;
        }

        ListNode pre=slow;
        ListNode now=slow.next;

        slow.next=null;//tip

        while(now!=null){
            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        ListNode ptr1=head;
        ListNode ptr2=pre;

        boolean res=true;

        while(ptr1!=null && ptr2!=null){
           // System.out.println("in");
            if(ptr1.val!=ptr2.val){
                res=false;
                break;
            }

            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }

        now=pre.next;
        pre.next=null;//tip

        while(now!=null){


            ListNode tmp=now.next;
            now.next=pre;
            pre=now;
            now=tmp;
        }

        return res;
        
    }
}