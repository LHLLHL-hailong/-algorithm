class Solution {


    void init(int[]nums,double[]weight){
        for(int i=0;i<nums.length;i++){
            nums[i]=i;
            weight[i]=1.0;
        }
    }

    //a/b=v1
    //b/c=v2
    //a/c=v1*v2

    int find(int[]nums,double weight[],int now){

        if(nums[now]==now){
            return now;
        }

        int fa=find(nums,weight,nums[now]);

        weight[now]*=weight[nums[now]];

        nums[now]=fa;

        return fa;

    }
    //   a/b=wei   a=wei*b
   //    a/fa=weight[a]  a=weight[a]*fa
   //    b/fb=weight[b]  b=weight[b]*fb
   //     weight[a]*fa=weight[b]*fb*wei
   //     fb/fa=weight[a]/(weight[b]*wei)

    void merge(int[]nums,double []weight,int a ,int b ,double wei){

        int fa=find(nums,weight,a);
        int fb=find(nums,weight,b);

        if(fa!=fb){

            nums[fb]=fa;
            weight[fb]=weight[a]/(weight[b]*wei);

        }
    }




    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String,Integer>map=new HashMap<>();
        Integer cnt=0;
        
        int len=equations.size()*2+queries.size()*2;//注意初始化长度为这个
        int[]nums=new int[len];
        double[]weight=new double[len];
        init(nums,weight);


        for(int i=0;i<values.length;i++){

            List<String>list=equations.get(i);
            Integer s1=map.get(list.get(0));
            if(s1==null){
                s1=cnt++;
                map.put(list.get(0),s1);
            }
            Integer s2=map.get(list.get(1));
            if(s2==null){
                s2=cnt++;
                map.put(list.get(1),s2);
            }
            merge(nums,weight,s1,s2,values[i]);
        }

        
        double []res=new double[queries.size()];
        int ptr=0;

        for(List<String> mid:queries){
            Integer f1=map.get(mid.get(0));
            Integer f2=map.get(mid.get(1));

            if(f1==null || f2==null){
                
                res[ptr++]=-1.0;
                continue;
            }
            
            int ff1=find(nums,weight,f1);
            int ff2=find(nums,weight,f2);

            if(ff1!=ff2){

              //  System.out.println("fa="+map.get(f1)+"   fb="+map.get(f2));
                
                res[ptr++]=-1.0;
                continue;   
            }

            // f1/ff1=weight[f1]
            // f2/ff1=weight[f2]
            // f1/f2=weight[f1]/weight[f2]
           // System.out.println("in");
            res[ptr++]=weight[f1]/weight[f2];
            
        }

        return res;

    }
}

/*

##review

思路也不是多难,但是中间过程太多了,算术也有些复杂,出一些莫名其妙的bug

综合评价:B-
 */