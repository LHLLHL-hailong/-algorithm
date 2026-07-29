/**
 * 难度有限(但是双指针一起遍历算一次遍历，属实有点搞怪了)
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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode tmp=new ListNode();
        tmp.next=head;
        ListNode first=tmp;
        ListNode second=tmp;
        for(int i=0;i<n;i++){

            first=first.next;

        }


        while(first.next!=null){

            first=first.next;
            second=second.next;

        }

        second.next=second.next.next;

        return  tmp.next;





        

        // if(head==null) return null;


        // HashMap<Integer,ListNode>map=new HashMap<>();

        // int ptr=0;

        // while(head!=null){
        //     map.put(ptr++,head);
        //     head=head.next;
        // }

        // if(n>ptr){
        //     return map.get(0);
        // }

        // ptr-=n;

        // if(ptr==0){

        //     return map.get(1);
        // }else{
        //     ListNode node= map.get(ptr-1);
        //     node.next=node.next.next;

        //     return map.get(0);
        // }

    }
}