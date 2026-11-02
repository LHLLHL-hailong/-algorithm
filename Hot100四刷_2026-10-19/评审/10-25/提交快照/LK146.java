/**
 * start:20:30 
 * end: 20:46
 * 独立完成
 * 时间: O(1)
 * 空间O(Math.min(插入的数据，capacity))
 */
class LRUCache {

    class Node{
        int value;
        int key;
        Node pre;
        Node next;

        Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    Node head;
    Node tail;
    int capacity=-1;
    int size=0;
    HashMap<Integer,Node>map;


    Node removeTail(){

        Node target=tail.pre;

        target.next.pre=target.pre;
        target.pre.next=target.next;


        return target;

    }


    Node removeByKey(Integer key){

        Node target=map.get(key);

        return removeNode(target);


    }

        Node removeNode(Node target){

        target.next.pre=target.pre;
        target.pre.next=target.next;


        return target;
    }

    void addFirst(Node target){


        target.next=head.next;
        head.next.pre=target;
        head.next=target;
        target.pre=head;
    }

    public LRUCache(int capacity) {

        this.capacity=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);


        head.next=tail;
        tail.pre=head;

        map=new HashMap<>();
        
    }
    
    public int get(int key) {

        if(map.containsKey(key)){

            Node target=removeByKey(key);

            addFirst(target);

            return target.value;

            
        }else{

            return -1;
        }
        
    }
    
    public void put(int key, int value) {

        Node target=map.get(key);

        if(target==null){

            target=new Node(key,value);

            map.put(key,target);
            addFirst(target);

            if(++size>capacity){
                Node now=removeTail();
                map.remove(now.key);
                size--;
            }


        }else{
            target.value=value;
            removeNode(target);

            addFirst(target);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */