/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode ptrA=headA;
        ListNode ptrB=headB;

        int cntA=0;
        int cntB=0;

        if(headA==null || headB==null){
            return null;
        }

        while(true){

            if(headA==headB){
                return headA;
            }
            if(headA.next!=null){
                headA=headA.next;
            }else{
                if(++cntA==1){
                    headA=ptrB;
                }else{
                    return null;
                }
            }

            if(headB.next!=null){
                headB=headB.next;
            }else{
                if(++cntB==1){
                    headB=ptrA;
                }else{
                    return null;
                }
            }
        }
        
    }
}