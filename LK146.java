/**
 * 这道题具体细节忘了，需要重点复习
 */
class LRUCache {


    int size=0;
    int capacity=0;

    HashMap<Integer,Node>map=new HashMap<>();

    Node head=null;
    Node tail=null;


    void remove(Node now){

        now.pre.next=now.next;
        now.next.pre=now.pre;

    }

    Node removeLast(){

        Node now=tail.pre;
        remove(now);
        return now;
    }

    void ToFirst(Node now){

        now.next=head.next;
        now.next.pre=now;
        head.next=now;
        now.pre=head;


    }


    class Node{

        int key;
        int value;
        Node pre;
        Node next;

        Node(int key,int value){
            this.key=key;
            this.value=value;
        }


    }

    public LRUCache(int capacity) {

        this.capacity=capacity;

        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.pre=head;

        size=0;

    }
    
    public int get(int key) {

        if(map.containsKey(key)){

            Node now=map.get(key);

            remove(now);

            ToFirst(now);

            return now.value;


        }else{
            return -1;
        }

        

    }
    
    public void put(int key, int value) {

        if(map.containsKey(key)){

            Node now=map.get(key);
            now.value=value;
            remove(now);
            ToFirst(now);
        }else{
            size++;
            Node now=new Node(key,value);
            map.put(key,now);
            ToFirst(now);

            if(size>capacity){
                Node last=removeLast();
                map.remove(last.key);

                size--;
            }


        }

    }
}