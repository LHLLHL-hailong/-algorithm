/**
 * 题本身很简单，但需要稍微注意一些细节才能达到百分百
 */
class Solution {
    public int maxProfit(int[] prices) {

        int minValue=prices[0];

        int maxV=0;

        for(int i=1;i<prices.length;i++){

            // maxV=Math.max(maxV,prices[i]-minValue);
            // minValue=Math.min(minValue,prices[i]);

            if(prices[i]<minValue){
                minValue=prices[i];

            }else if(prices[i]-minValue>maxV){
                maxV=prices[i]-minValue;
            }

        }   

        return maxV;
        
    }
}