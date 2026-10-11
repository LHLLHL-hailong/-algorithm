class Solution {

    int minCount(int[][]costs,int k){//大集合可以从n多个小集合状态转移计算出来的适合用状压dp

        // System.out.println("in");

        int maxValue=1<<k;

        int[]dp=new int[maxValue];//状压dp
        for(int i=1;i<dp.length;i++){
            dp[i]=Integer.MAX_VALUE/2;
        }
        dp[0]=0;

        

        for(int i=0;i<maxValue-1;i++){//状压dp,dp数组里面的是集合而不是位置，递增刚好能满足状态转移的依赖条件（因为大集合需要的小集合一定数值上更小（虽然数值上更小的集合不一定都需要））

            
            for(int j=0;j<k;j++){
                if((i&(1<<j))!=0){
                    continue;
                }
                int add=0;

                for(int c=0;c<k;c++){
                    if((i&(1<<c))!=0){

                        add+=costs[c][j];
                        
                    }
                }

                dp[i|(1<<j)]=Math.min(dp[i|(1<<j)],dp[i]+add);//向外扩散向的转移转移，而不是向内收缩向的状态转移

                // if((i|(1<<j))==maxValue-1){
                //     System.out.println("in");
                // }

                
            }

            
        }

        

        return dp[maxValue-1];

    
    }


    int[] getArr(ArrayList<int[]>list,int totalLen){

        if(list.size()==1) return list.get(0);

        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b)->{
            return a[0]-b[0];
        });

        int[]ptrs=new int[list.size()];

        int[]res=new int[totalLen];
        int ptr=0;

        for(int i=0;i<list.size();i++){
            if(ptrs[i]<list.get(i).length){
                queue.add(new int[]{list.get(i)[ptrs[i]],i});
                ptrs[i]++;
            }
            
        }

        while(!queue.isEmpty()){

            int[]now=queue.remove();

            res[ptr++]=now[0];

            if(ptrs[now[1]]<list.get(now[1]).length){

                queue.add(new int[]{list.get(now[1])[ptrs[now[1]]],now[1]});

                ptrs[now[1]]++;
            }
            
        }

        return res;
            
    }


    int[] getMinNum(int []arr1,int []arr2){

        int sum=arr1.length*arr2.length;

        int num1=0;// arr1 arr2

        int ptr=0;

        for(int i=0;i<arr1.length;i++){

            while(ptr!=arr2.length && arr2[ptr]<arr1[i]){
                ptr++;
            }

            num1+=ptr;
            
        }

        return new int[]{num1,sum-num1};
        
    }


    class Node{

        Node[]next=new Node['j'-'a'+1];

        int val=-1;

        int pass=0;
        int end=0;
        
    }

    class Tree{
        Node root=new Node();

        void insert(String now,int weight){
            char[]sc=now.toCharArray();

            Node cur=root;

            for(int i=0;i<sc.length;i++){
                cur.pass++;
                if(cur.next[sc[i]-'a']==null){
                    cur.next[sc[i]-'a']=new Node();
                }
                cur=cur.next[sc[i]-'a'];
            }
            cur.end++;
            cur.val=weight;

            
        }
    }
    
    int total=0;


    int[] dfs(Node root){

        if(root==null) return null;

        if(root.pass<=0){
            if(root.val==-1){
                return null;
            }else{
                return new int[]{root.val};
            }
        }

        int wei=root.val;

        ArrayList<int[]>list=new ArrayList<>();
        int lenTotal=0;

        for(int i=0;i<='j'-'a';i++){
            if(root.next[i]!=null ){
                int[]cc=dfs(root.next[i]);

                if(cc!=null){
                    for(int cnt:cc){
                        if(cnt<wei){
                            total++;
                        }
                    
                    }
                    lenTotal+=cc.length;
                    list.add(cc);
                }

            }
        }

        int[][]costs=new int[list.size()][list.size()];

        for(int i=0;i<list.size();i++){
            for(int j=i+1;j<list.size();j++){
                int[]tmp=getMinNum(list.get(i),list.get(j));
                costs[i][j]=tmp[0];
                costs[j][i]=tmp[1];
            }
        }

        total+=minCount(costs,list.size());

        if(wei!=-1){
            list.add(new int[]{wei});
            lenTotal++;
        }

        int[] res=getArr(list,lenTotal);

        return res;


        
    }
    
    public int minInversions(String[] words, int[] target) {

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<target.length;i++){
            map.put(target[i],i);
        }

        Tree tree=new Tree();

        for(int i=0;i<words.length;i++){

            tree.insert(words[i],map.get(i));
            
        }

        dfs(tree.root);

        return total;
        
        
    }
}