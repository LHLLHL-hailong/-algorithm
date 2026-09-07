class Trie {

    Node root;

    class Node{

        int pass=0;
        int end=0;
        Node[]path=new Node[26];
    }

    public Trie() {

        root=new Node();
        
    }
    
    public void insert(String word) {
        char[]wc=word.toCharArray();

        Node now=root;

        for(int i=0;i<wc.length;i++){
            now.pass++;
            int idx=wc[i]-'a';
            if(now.path[idx]==null){
                now.path[idx]=new Node();
            }
            now=now.path[idx];

        }
        
        now.end++; 
    }
    
    public boolean search(String word) {

        Node now=root;
        char[]wc=word.toCharArray();

        for(int i=0;i<wc.length;i++){
            now=now.path[wc[i]-'a'];
            if(now==null){
                return false;
            }

        }

        return now.end>0;
        
    }
    
    public boolean startsWith(String prefix) {
        Node now=root;
        char[]wc=prefix.toCharArray();

        for(int i=0;i<wc.length;i++){
            now=now.path[wc[i]-'a'];
            if(now==null){
                return false;
            }

        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

/*
这个前缀树的题目中并没有删除逻辑，判断有没有前缀（startsWith）看看这个树的结构是否存在就可以了

 */