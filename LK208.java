/**
 * 需要注意的就是，这个pass是没有必要的
 */
  class Trie {
    class Node{
        // int pass=0;
        int end=0;
        Node[]path=new Node[26];

        // Node(int pass){
        //   this.pass=pass;
        // }

        Node(){}
    }

    Node root=null;

      public Trie() {
        root=new Node();

      }

      public void insert(String word) {
        char[] w=word.toCharArray();

        // root.pass++;

        Node now=root;

        for(char cc:w){
            if(now.path[cc-'a']==null){
                now.path[cc-'a']=new Node();
                now=now.path[cc-'a'];
            }else{
                now=now.path[cc-'a'];
               // now.pass++;
            }
        }

        now.end++;

      }

      public boolean search(String word) {

        char[] w=word.toCharArray();

        Node now=root;

        for(int i=0;i<w.length;i++){
            if(now.path[w[i]-'a']==null){
             //   System.out.println(i);
                return false;
            }
             now=now.path[w[i]-'a'];
        }

       // System.out.println("in");

        return now.end>0;

      }

      public boolean startsWith(String prefix) {

        char[] w=prefix.toCharArray();

        Node now=root;

        for(int i=0;i<w.length;i++){
            if(now.path[w[i]-'a']==null){
                return false;
            }
            now=now.path[w[i]-'a'];
        }

        return true;

      }
  }