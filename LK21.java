/**
 * 这个题也是基本上没有难度了
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ptr=new ListNode();

        ListNode head=ptr;

        while(list1!=null && list2!=null){

            if(list1.val<list2.val){
                ptr.next=list1;
                ptr=ptr.next;
                list1=list1.next;
            }else{
                ptr.next=list2;
                ptr=ptr.next;
                list2=list2.next;
                
            }

        }

        ptr.next=(list1==null?list2:list1);

        return head.next;


    }
}