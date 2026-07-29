/**
 * 逻辑需要背，还是很难的，之后千万别忘记了double
 */
class Solution {

    void init(double[]weight,int[] nums){
        for(int i=0;i<nums.length;i++){
            nums[i]=i;
            weight[i]=1;
            
        }
    }

    int find(double[]weight,int[] nums,int i){
        if(nums[i]==i){
            return i;
        }else{
            int  fa=find(weight,nums,nums[i]);
            weight[i]*=weight[nums[i]];
            nums[i]=fa;
            return fa;

        }

    }
    //i/j
   // nums[i]=nums[parentI]*weight[i]
   // nums[j]=nums[patrntJ]*weight[j]
   //nums[i]=nums[j]*wei
   //nums[parentI]*weight[i]=nums[patrntJ]*weight[j]*wei
   //nums[parentI]=(nums[patrntJ]*weight[j]*wei)/weight[i]
   //pi/pj
   //pi---->pj
    void merge(double[]weight,int[]nums,int i,int j,double wei){

        int parentI=find(weight,nums, i);
        int parentJ=find(weight,nums,j);

        nums[parentI]=parentJ;
        weight[parentI]=(weight[j]*wei)/weight[i];



    }

    // a/b=val1    nums[a]=b wei[a]=val1
    // b/c=val2    nums[b]=c wei[b]=val2
    // a/c=val1 * val2      wei[a]=val1*val2


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int[]nums=new int[equations.size()*2];
        double[]weight=new double[equations.size()*2];
        init(weight,nums);
        HashMap<String,Integer>map=new HashMap<>();
        int cnt=0;
        for(int i=0;i<values.length;i++){
            double wei=values[i];
            List<String>strs=equations.get(i);
            String str1=strs.get(0);
            String str2=strs.get(1);
            if(!map.containsKey(str1)){
                map.put(str1,cnt++);
            }
            if(!map.containsKey(str2)){
                map.put(str2,cnt++);
            }
            int c1=map.get(str1);
            int c2=map.get(str2);
            merge(weight,nums,c1,c2,wei);

        }

        double[] res=new double[queries.size()];
        for(int i=0;i<res.length;i++){
            List<String>tmp=queries.get(i);
            if(!map.containsKey(tmp.get(0)) || !map.containsKey(tmp.get(1))){
                res[i]=-1.0;
                continue;
            }
            int i1=map.get(tmp.get(0));
            int j1=map.get(tmp.get(1));

            int parentI=find(weight,nums,i1);
            int parentJ=find(weight,nums,j1);
            if(parentI!=parentJ){
                res[i]=-1.0;
                continue;
            }
            //nums[i]=parent*weight[i];
            //nums[j]=parent*weight[j];
            //nums[i]=?*nums[j]
            //weight[i]/weight[j]
            res[i]=weight[i1]/weight[j1];

        }

        return res;

    }
}