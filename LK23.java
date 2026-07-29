


/**
 * 这个算法是最快的
 */
class Solution {




    public ListNode mergeKLists(ListNode[] lists) {

        return build(lists,0,lists.length-1);



    }


    ListNode build(ListNode[] lists ,int l,int r){

        if(l==r) return lists[l];
        if(l>r) return null;
        int mid =(l+r)/2;
        return merge(build(lists,l,mid),build(lists,mid+1,r));



    }


    ListNode merge(ListNode ptra , ListNode ptrb){

        if(ptra==null || ptrb ==null){
            return ptra==null?ptrb:ptra;
        }

        ListNode ptr=new ListNode();
        ListNode head=ptr;

        while(ptra!=null && ptrb!=null){
            if(ptra.val<ptrb.val){
                ptr.next=ptra;
                ptr=ptr.next;
                ptra=ptra.next;
            }else{
                ptr.next=ptrb;
                ptr=ptr.next;
                ptrb=ptrb.next;
            }
        }

        ptr.next= ptra==null?ptrb:ptra;

        return head.next;

    }
}
















/**
 * 1. 别忘了用优先队列来加速找最值的过程
 * 2. 改变从数组中拿出的引用的指向并不能改变原指针在输入中的位置，必须再次放回或覆盖
 * 3. 优先队列add的时候不能为空，remove的时候不能为空，但是poll的时候可以为空，空表示队列为空
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




    public ListNode mergeKLists(ListNode[] lists) {

        ListNode ptr=new ListNode();

        ListNode head=ptr;

        PriorityQueue<ListNode>queue=new PriorityQueue<>((a,b)->{

            int va= a==null?Integer.MAX_VALUE/2:a.val;
            int vb= b==null?Integer.MAX_VALUE/2:b.val;

            return va-vb;
            
        });

        for(ListNode now: lists){
            if(now!=null)
            queue.add(now);
        }

        while(true){
            ListNode tmp=queue.poll();
            if(tmp==null) break;
            ptr.next=tmp;
            ptr=ptr.next;
            tmp=tmp.next;
            if(tmp!=null)
            queue.add(tmp);
        }

        return head.next;

        
    }
}























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




    public ListNode mergeKLists(ListNode[] lists) {

        ListNode ptr=new ListNode();

        ListNode head=ptr;

        while(true){

        int minValue=Integer.MAX_VALUE/2;
        int idx=-1;


        for(int i=0;i<lists.length;i++){
            ListNode now=lists[i];

           if(now!=null){

                if(now.val<minValue){
                    minValue=now.val;
                    idx=i;
                }
            }


        }
 
          //  ListNode now=getMax(lists);
            if(idx==-1) break;
            ListNode tmp=lists[idx];
            ptr.next=tmp;
            ptr=ptr.next;
            tmp=tmp.next;
            lists[idx]=tmp;
            
        }

        return head.next;


        
    }
}