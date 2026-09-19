class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {


      // Deque<Integer>queue=new ArrayDeque<>(k+1);

        int start=0;

        k++;
        
        int count=k;

        int base=0;

        int res=0;

        for(int i=0;i<startTime.length;i++){

   
            int now=startTime[i]-start;

            //   startTime[i-k]-(i-k-1>=0?endTime[i-k-1]:0)

            if(--count>=0){
                base+=now;
            }else{
                base+=now;
                base-=(startTime[i-k]-(i-k-1>=0?endTime[i-k-1]:0));
            }

          //  if(count<=0){
                res=Math.max(res,base);
           // }

         //  System.out.println("now="+now);
          // System.out.println("base="+base);

            start=endTime[i];


        }

 
        int now=eventTime-start;
            if(--count>=0){
                base+=now;
            }else{
                base+=now;
                base-=(startTime[startTime.length-k]-(startTime.length-k-1>=0?endTime[startTime.length-k-1]:0));
            }

          //  if(count<=0){
                res=Math.max(res,base);
           // }

       //    System.out.println("now="+now);
        //   System.out.println("base="+base);

    return res;

    }
}

/*
1.把执行操作转换成为执行了操作之后的结果
2.需要历史的数据，不代表需要额外的存储空间（有时候可以快速的重新计算）
 */