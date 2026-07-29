
/**
 * 这道题的重点就是要注意它执行的时序问题
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1==null) return l2;
        if(l2==null) return l1;

        int cnt=0;

        ListNode ptr=new ListNode();
        ListNode head=ptr;
        ptr.next=l1;



        while(l1!=null && l2!=null){

            int val=(l1.val+l2.val+cnt)%10;
            cnt=(l1.val+l2.val+cnt)/10;
            ptr.next.val=val;

            l1=l1.next;
            l2=l2.next;
            ptr=ptr.next;

        }

        ListNode tmp=l1==null?l2:l1;

        ptr.next=tmp;       

        while(cnt!=0 && ptr.next!=null){


            int mid=ptr.next.val;

            
            ptr.next.val=(mid+cnt)%10;
            cnt=(mid+cnt)/10;
            ptr=ptr.next;
        }

        while(cnt!=0){

            ptr.next=new ListNode(cnt%10);
            cnt=cnt/10;
            ptr=ptr.next;
        }

        return  head.next;



    }
}

























// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */
// class Solution {
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {



//         if(l1==null) return l2;

//         if(l2==null) return l1;


//         ListNode head=new ListNode(-1);
//         ListNode result=head;

//         int now=0;
//         int post=0;


//     //    while(l1 !=null || l2 != null){

//     //         int value= (l1==null?0:l1.val)+ (l2==null?0:l2.val)+post;

//     //         now=value%10;

//     //         post=value/10;

//     //         head.next=new ListNode(now);
//     //         head=head.next;

//     //        if(l1!=null) l1=l1.next;
//     //        if(l2!=null) l2=l2.next;


//     //     }




        

//         while(l1 !=null && l2 != null){//还是分开求吧，合上反而慢了

//             int value=l1.val+l2.val+post;

//             now=value%10;

//             post=value/10;

//             head.next=new ListNode(now);
//             head=head.next;

//             l1=l1.next;
//             l2=l2.next;


//         }

//         l1=l1==null?l2:l1;


//         while(l1!=null){

//          int value=l1.val+post;

//             now=value%10;

//             post=value/10;

//             head.next=new ListNode(now);
//             head=head.next;

//             l1=l1.next;
            
//         }

//         if(post!=0){
        
//             head.next=new ListNode(post);
//         }



//         return result.next;





// }


// }