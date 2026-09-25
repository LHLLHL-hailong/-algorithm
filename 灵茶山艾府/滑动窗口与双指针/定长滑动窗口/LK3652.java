class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {

        long sum=0L;

        for(int i=0;i<prices.length;i++){
            sum+=prices[i]*strategy[i];
        }

        long maxPlus=0L;

            int win=k;

            long nowWin=0L;
            long plusWin=0L;
            for(int i=0;i<win;i++){

                nowWin+=prices[i]*strategy[i];

                if(i>=win/2){
                    plusWin+=prices[i];
                }



            }

            maxPlus=Math.max(maxPlus,plusWin-nowWin);

            for(int i=win;i<strategy.length;i++){

                nowWin+=prices[i]*strategy[i];
                nowWin-=prices[i-win]*strategy[i-win];

                plusWin+=prices[i];
                plusWin-=prices[i-win/2];


                maxPlus=Math.max(maxPlus,plusWin-nowWin);


            }


        return sum+maxPlus;


        
    }
}