class LRUCache {


    class Node{

        Node pre;
        Node next;

        int val;
        int key;

        Node(int key,int val){
            this.key=key;
            this.val=val;
        }

    }

    int size=0;
    int capacity=0;

    Node head=new Node(-1,-1);
    Node tail=new Node(,-1-1);

    HashMap<Integer,Node>map=new HashMap<>();

    void remove(Node now){

        now.pre.next=now.next;
        now.next.pre=now.pre;

    }

    void moveToFirst(Node now){

        now.next=head.next;
        head.next.pre=now;
        head.next=now;
        now.pre=head;

    }

    Node removeLast(){
        Node tar=tail.pre;

        remove(tar);

        return tar;
    }



    public LRUCache(int capacity) {
        
        this.capacity=capacity;
        head.next=tail;
        tail.pre=head;
    }
    
    public int get(int key) {

        Node target=map.get(key);

        if(target==null){
            return -1
        }else{
            remove(target);
            moveToFirst(target);

            return target.val;
        }

    }
    
    public void put(int key, int value) {

        Node target =map.get(key);

        if(target==null){
            target=new Node(key,value);
            moveToFirst(target);
            map.put(key,target);
            
            if(++size>capacity){
                Node rev=removeLast();
                map.remove(res.key);
            }
        }else{
            target.val=value;
            remove(target);
            moveToFirst(target);
        }


        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */