
class Solution {

    /**
 * start: 9:35
 * end: 9:47
 * 独立完成
 * n: lists的长度 m:平均每个链条的长度
 * 时间：（log(n) * nm）
 * 空间： log(n)
 * 
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

ListNode merge(ListNode l1,ListNode l2){
    if(l1==null) return l2;
    if(l2==null) return l1;

    ListNode head=new ListNode(-1);

    ListNode ptr=head;

    while(l1!=null && l2!=null){
        if(l1.val<=l2.val){
            ptr.next=l1;
            l1=l1.next;
        }else{
            ptr.next=l2;
            l2=l2.next;
        }

        ptr=ptr.next;

    }

    if(l1!=null) ptr.next=l1;
    if(l2!=null) ptr.next=l2;

    return head.next;


}


ListNode build(ListNode[] lists,int l,int r){

    if(l==r){
        return lists[l];
    }else if(l>r){
        return null;
    }else if(l+1==r){
        return merge(lists[l],lists[r]);
    }else{

        int mid=(l+r)/2;

        return merge(build(lists,l,mid),build(lists,mid+1,r));
    }
}

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length==1){
            return lists[0];
        }
        
        return build(lists,0,lists.length-1);
    }
}