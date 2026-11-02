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
/**
 * start: 21:10
 * end:21:18
 * 独立完成
 * 时间：O(n+m)
 * 空间：O(1)
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA==null || headB==null) return null;

        ListNode ptr1=headA;
        int countA=0;

        ListNode ptr2=headB;
        int countB=0;

        while(true){

            
            if(ptr1==ptr2){//tip:要在开头判断而不是结尾
                return ptr1;
            }

            if(ptr1.next!=null){
                ptr1=ptr1.next;
            }else{
                if(++countA>=2){

                    return null;

                }else{
                    ptr1=headB;
                }
                
            }

            if(ptr2.next!=null){
                ptr2=ptr2.next;
            }else{
                if(++countB>=2){

                    return null;
                }else{
                    ptr2=headA;
                }
            }

        }
        
    }
}