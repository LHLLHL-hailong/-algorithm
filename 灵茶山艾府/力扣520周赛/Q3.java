class Solution {

    
    public long maxValue(int[] nums) {


        long count=0L;

        long sum=0L;

        for(int i=0;i<nums.length;i++){
            if(++count %2==0L){
                nums[i]=-nums[i];
                
            }

            sum+=nums[i];
        }

        long res=0;

        long prefix=0;


        count=-1;

        long []maxPrefix=new long[]{Long.MIN_VALUE,0L};

        for(int i=0;i<nums.length;i++){

            prefix+=nums[i];

            if(++count%2==0 ){


                if(maxPrefix[0]!=Long.MIN_VALUE){
                    res=Math.min(res,prefix-maxPrefix[0]);
                }

                maxPrefix[0]=Math.max(maxPrefix[0],prefix);
                
                
            }

            if(count%2==1){
                res=Math.min(res,prefix-maxPrefix[1]);
                 maxPrefix[1]=Math.max(maxPrefix[1],prefix);
                
            }

        }


        return sum+2*(-res);

        
    }
}

/*
使用前缀和来计算区间最大值

 */