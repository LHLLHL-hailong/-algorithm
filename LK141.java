/**
 * 这道题虽然简单，却是快慢指针的基础
 */
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
public class Solution {
    public boolean hasCycle(ListNode head) {

        if(head==null){
            return false;
        }

        ListNode quick=head;
        ListNode slow=head;

        while(quick.next!=null){

            quick=quick.next;
            if(quick.next!=null){
                quick=quick.next;
            }else{
                return false;
            }
            slow=slow.next;

            if(slow==quick){
                return true;
            }


        }

        return false;
        
    }
}
/**
 * 这么写更规范
 */

public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode fast=head;
        ListNode slow=head;

        if(fast==null || fast.next==null){
            return false;
        }

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;

            if(fast==slow){
                return true;
            }

        }

        return false;


        
    }
}

