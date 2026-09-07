class Solution {
    public int mincostTickets(int[] days, int[] costs) {


        int[]dp=new int[366];

       // HashSet<Integer>set=new HashSet<>();
       boolean[] set=new boolean[366];//boolean[]替代HashSet

       

        for(int item:days){
            set[item]=true;
        }

        for(int i=1;i<=365;i++){
                if(!set[i]){
                    dp[i]=dp[i-1];
                }else{
                    dp[i]=Math.min(dp[i-1]+costs[0],Math.min(dp[Math.max(0,i-7)]+costs[1],dp[Math.max(0,i-30)]+costs[2]));
        }

        }

        return dp[365];


    }
}

// class Solution {
//     public int mincostTickets(int[] days, int[] costs) {


//         int[]dp=new int[days.length+1];


//         for(int i=1;i<=days.length;i++){
//         int pre7=i;
//         int pre30=i;
//         for(int j=i-1;j>=1;j--){
//             if(days[j-1]>=days[i-1]-6){
//                 pre7=j;
//                 pre30=j;
//             }else if(days[j-1]>=days[i-1]-29){
//                 pre30=j;
//             }else{
//                 break;
//             }
//         }
//         dp[i]=Math.min(dp[i-1]+costs[0],Math.min(dp[Math.max(0,pre7-1)]+costs[1],dp[Math.max(0,pre30-1)]+costs[2]));
//         // System.out.println(pre7);
//         // System.out.println(pre30);
//         // System.out.println(Arrays.toString(dp));


//         }

//         return dp[days.length];



//     }
// }

// class Solution {
//     public int mincostTickets(int[] days, int[] costs) {


//         int[]dp=new int[days.length+1];

//         Deque<Integer>pre7stack=new ArrayDeque<>();
//         Deque<Integer>pre30stack=new ArrayDeque<>();


//         for(int i=1;i<=days.length;i++){
//         int pre7=i;
//         int pre30=i;

//         while(!pre7stack.isEmpty() && days[pre7stack.getFirst()-1]<days[i-1]-6){
//             pre7stack.removeFirst();
//         }

//         while(!pre30stack.isEmpty() &&days[ pre30stack.getFirst()-1]<days[i-1]-29){
//             pre30stack.removeFirst();
//         }

//         if(!pre7stack.isEmpty()){
//             pre7=pre7stack.getFirst();
//         }

//         if(!pre30stack.isEmpty()){

//             pre30=pre30stack.getFirst();

//         }

//         dp[i]=Math.min(dp[i-1]+costs[0],Math.min(dp[Math.max(0,pre7-1)]+costs[1],dp[Math.max(0,pre30-1)]+costs[2]));
//         pre7stack.addLast(i);
//         pre30stack.addLast(i);

//        // System.out.println(Arrays.toString(dp));


//         }

//         return dp[days.length];



//     }
// }



class Solution {
    public int mincostTickets(int[] days, int[] costs) {


        int[]dp=new int[days.length+1];

        int pre7=1;//对于一个随着遍历会单向的往一个方向移动的特殊值可以用指针来解决
        int pre30=1;//对于一个随着遍历会单向的往一个方向移动的特殊值可以用指针来解决



        for(int i=1;i<=days.length;i++){


        while(days[pre7-1]<days[i-1]-6){
            pre7++;
        }

        while(days[pre30-1]<days[i-1]-29){
            pre30++;
        }


        dp[i]=Math.min(dp[i-1]+costs[0],Math.min(dp[pre7-1]+costs[1],dp[pre30-1]+costs[2]));


       // System.out.println(Arrays.toString(dp));


        }

        return dp[days.length];



    }
}