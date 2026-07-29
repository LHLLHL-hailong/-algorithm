    class Solution {
        public int[][] merge(int[][] intervals) {


            if(intervals.length<=1) return intervals;

            Arrays.sort(intervals,(a,b)->a[0]-b[0]);

            int lastEnd=intervals[0][1];
            int start=intervals[0][0];

            ArrayList<int[]>list=new ArrayList<>();


            for(int i=1;i<intervals.length;i++){
                int [] mid=intervals[i];

                if(mid[0] <= lastEnd){
                    lastEnd=Math.max(lastEnd,mid[1]);

                }else{

                    list.add(new int[]{start,lastEnd});
                    start=mid[0];
                    lastEnd=mid[1];

                }

            }

             list.add(new int[]{start,lastEnd});

            // int[][]res=new int[list.size()][2];
            // int ptr=0;
            // for(int[] cnt:list){
            //     res[ptr++]=cnt;
            // }
            return list.toArray(new int[list.size()][]); // 这个方式更快


        }
    }
