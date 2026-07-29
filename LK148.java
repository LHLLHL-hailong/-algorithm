/**
 * 这道题主要的问题就是可能会不收敛
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

   // int count=0;


    ListNode merge(ListNode first,ListNode second){

       

        ListNode head=new ListNode();
        ListNode ptr=head;

        while(first!=null && second!=null){
            if(first.val<second.val){
                ptr.next=first;
                ptr=ptr.next;
                first=first.next;
            }else{
                ptr.next=second;
                ptr=ptr.next;
                second=second.next;
            }
        }

        ptr.next= (first==null?second:first);

        return head.next;

    }


    ListNode builde(ListNode root){ // 需要显式的考虑，0 1 2等原子情况下能不能收敛
         //System.out.println(count++);
        if(root==null) return null;

        if(root.next==null){
            return root;

        }

        ListNode fast=root;
        ListNode slow=root;
     //   while(fast.next!=null){
        //     fast=fast.next;
        //     if(fast.next!=null){
        //         fast=fast.next;
        //     }else{
        //         break;
        //     }
        //     slow=slow.next;
        // }

        while (fast.next != null && fast.next.next != null) { // 这个才是快慢指针的正确写法
            slow = slow.next;
            fast = fast.next.next;
         }
        ListNode cnt=slow.next;
        slow.next=null;
        

        return merge(builde(root),builde(cnt));



    }



    public ListNode sortList(ListNode head) {

        return builde(head);


        
    }
}