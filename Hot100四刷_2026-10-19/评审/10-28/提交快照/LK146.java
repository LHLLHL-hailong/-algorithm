
/**
 * start: 18:04;
 * end: 18:19
 * 独立完成
 * 时间: O(1)
 * 空间: O(1)
 */
class LRUCache {

    int capacity=-1;
    int size=0;
    Node head;
    Node tail;

    HashMap<Integer,Node>map=new HashMap<>();

    class Node{
        int key=0;
        int value=0;

        Node pre;
        Node next;

        Node(int key ,int value){

            this.key=key;
            this.value=value;
        }


    }

    void remove(Node now){
        now.next.pre=now.pre;
        now.pre.next=now.next;
    }
    

    Node RemoveTail(){
        Node target=tail.pre;

        remove(target);

        return target;
    }

    void addFirst(Node now){
        now.next=head.next;
        head.next.pre=now;
        head.next=now;
        now.pre=head;
    }

    

    public LRUCache(int capacity) {

        this.capacity=capacity;
        this.size=0;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);

        head.next=tail;
        tail.next=head;
        
    }
    
    public int get(int key) {

        Node target=map.get(key);
        if(target==null) return -1;

        remove(target);
        addFirst(target);

        return target.value;
        
    }
    
    public void put(int key, int value) {

        Node target=map.get(key);
        if(target!=null){

            target.value=value;
            remove(target);
            addFirst(target);

        }else{
            target=new Node(key,value);
            addFirst(target);
            map.put(key,target);
            if(++size>capacity){

                Node now= RemoveTail();
                map.remove(now.key);
                size--;
            }

        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */