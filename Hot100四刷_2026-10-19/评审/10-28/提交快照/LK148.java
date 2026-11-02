/**
 * start: 18:25
 * end: 18:38
 * 独立完成
 * 时间：O(nlogn)
 * 空间: O(1)
 */
/**3,4,6
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
        ListNode merge(ListNode l1,ListNode l2){
            if(l1==null) return l2;
            if(l2==null) return l1;

            ListNode head=new ListNode(-1);

            ListNode ptr=head;

            while(l1!=null && l2!=null){
                if(l1.val<=l2.val){

                    ptr.next=l1;
                    l1=l1.next;

                }else{
                    ptr.next=l2;
                    l2=l2.next;

                }

                ptr=ptr.next;
            }

            ptr.next=l1==null?l2:l1;

            return head.next;
        }

        ListNode build(ListNode now){
            if(now==null || now.next==null) return now;

            ListNode quick=now;
            ListNode slow=now;

            while(quick.next!=null && quick.next.next!=null){
                    quick=quick.next.next;
                    slow=slow.next;
            }

            ListNode h2=slow.next;
            slow.next=null;

            return merge(build(now),build(h2));
        }


    public ListNode sortList(ListNode head) {

        return build(head);


        
    }
}