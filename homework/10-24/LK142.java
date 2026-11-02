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
 * start: 10:31
 * end: 10:38
 * 独立完成
 * 时间O（n）
 * 空间O(1)
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        
        if(head==null || head.next==null) return null ;
        if(head.next==head) return head;

        ListNode slow=head;
        ListNode quick=head;

        while(quick.next!=null && quick.next.next!=null ){
            quick=quick.next.next;
            slow=slow.next;

            if(quick==slow){

                ListNode tmp=head;

                while(tmp!=slow){

                    //System.out.println(tmp.val);
                    System.out.println(slow.val);
                    

                    tmp=tmp.next;
                    slow=slow.next;



                }

                return slow;

            }
        }

        return null;

    }
}

/**
 * tip: 必须让slow 和quick走的步数完全是2倍关系（此时尤其需要注意初始化），否则会死归
 */