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
 * start: 20:48
 * end: 21:10
 * 独立完成
 * 时间O(nlog(n))
 * 空间：log(n) 
 * 
 */
class Solution {

    ListNode merge(ListNode l1,ListNode l2){



        ListNode head=new ListNode();
        ListNode ptr=head;

        ListNode ptr1=l1;
        ListNode ptr2=l2;

        while(ptr1!=null && ptr2!=null){

           // System.out.println("in");

            if(ptr1.val<=ptr2.val){
                ptr.next=ptr1;
                ptr=ptr.next;
                ptr1=ptr1.next;
            }else{
                ptr.next=ptr2;
                ptr=ptr.next;
                ptr2=ptr2.next;

            }
        }

        ptr.next= ptr1 !=null?ptr1:ptr2;

        return head.next;

        
    }

    ListNode build(ListNode list){



        if(list==null) return null;
        if(list.next==null) return list;

        ListNode quick=list;
        ListNode slow=list;

        while(quick.next!=null && quick.next.next!=null){
            quick=quick.next.next;
            slow=slow.next;
        }
        
        ListNode ptr2=slow.next;
        slow.next=null;

        return merge(build(list),build(ptr2));
    }


    public ListNode sortList(ListNode head) {

        return build(head);
        
    }
}