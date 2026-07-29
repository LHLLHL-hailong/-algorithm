public class Solution {


    public ListNode detectCycle(ListNode head) {

        ListNode fast=head;
        ListNode slow=head;

        while(fast!=null && fast.next!=null){

            fast=fast.next.next;
            slow=slow.next;

            if(slow==fast){

                ListNode start=head;

                while(start!=slow){
                    start=start.next;
                    slow=slow.next;
                }
                return start;

            }

        }
        return null;

    }
}


