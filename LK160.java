/**
 * 虽然这只是一道简单题，但是这道题的思路必须记住,只有这么做，它的效率才能更高一些，不然效率很低
 */
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode ptr1=headA;
        ListNode ptr2=headB;

        int cnt=0;

        while(ptr1 != ptr2){

            if(ptr1.next!=null){
                ptr1=ptr1.next;
            }else{
                ptr1=headB;
                cnt++;
                if(cnt>=3){
                    return null;
                }
            }

            if(ptr2.next!=null){
                ptr2=ptr2.next;

            }else{
                ptr2=headA;
                cnt++;
                if(cnt>=3){
                    return null;
                }

            }

        }

        return ptr1;

    
    }
}





/**
 * 这个解的效率很慢，如果使用这个就完蛋了
 */
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

        HashSet<ListNode>set=new HashSet<>();

        while(headA!=null){
            set.add(headA);
            headA=headA.next;
        }

        while(headB!=null){
            if(set.contains(headB)){
                return headB;

            }
            headB=headB.next;
        }

        return null;
    }
}