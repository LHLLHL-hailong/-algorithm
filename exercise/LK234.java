class Solution {
        public boolean isPalindrome(ListNode head) {

                if(head==null || head.next==null){
                    return true;
                }

                if(head.next.next==null){
                    if(head.val==head.next.val){
                        return true;
                    }else{
                        return false;
                    }
                }

                ListNode quick=head;
                ListNode slow=head;

                while(quick.next!=null && quick.next.next!=null){
                    quick=quick.next.next;
                    slow=slow.next;
                }

            
                // head    slow    quick 

                ListNode ptr=slow.next;

                slow.next=null;

                ListNode pre=slow;

                while(ptr!=null){

                    ListNode tmp=ptr.next;

                    ptr.next=pre;
                    pre=ptr;
                    ptr=tmp;
                }

                ListNode ptr1=head;
                ListNode ptr2=pre;

                while(ptr2!=slow){

                    //System.out.println("in");
                    if(ptr1.val!=ptr2.val){

                ptr=pre.next;
                pre.next=null;
                while(ptr!=slow){
                    ListNode tmp=ptr.next;
                    ptr.next=pre;
                    pre=ptr;
                    ptr=tmp;
                }

                ptr.next=pre;

                        
                        return false;
                    }

                    ptr1=ptr1.next;
                    ptr2=ptr2.next;
                }

                ptr=pre.next;
                pre.next=null;
                while(ptr!=slow){
                    ListNode tmp=ptr.next;
                    ptr.next=pre;
                    pre=ptr;
                    ptr=tmp;
                }

                ptr.next=pre;

                return true;

        }
}
