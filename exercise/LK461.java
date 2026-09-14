class Solution {
    public int hammingDistance(int x, int y) {

        int count=0;

        int tar=x^y;

        while(tar!=0){
            count+=tar&1;
            tar>>=1;
        }

        return count;
        
    }
}