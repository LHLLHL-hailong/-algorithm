class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int maxV=0;

        for(int i=0;i<k;i++){
            maxV+=cardPoints[i];
        }

        int base=maxV;

        for(int i=0;i<k;i++){

            base+=cardPoints[cardPoints.length-1-i];
            base-=cardPoints[k-1-i];

            maxV=Math.max(maxV,base);

       }

       return maxV;


        
    }
}