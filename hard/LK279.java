class Solution {


    boolean isFirst(int n){
        int cnt=(int)Math.sqrt(n);

        return cnt*cnt==n;
    }


    boolean isFour(int n){

        while(n%4==0){
            n=n/4;
        }
        return n%8==7;

    }


    public int numSquares(int n) {

        if(isFirst(n)) return 1;

        if(isFour(n)) return 4;

        for(int i=1;i<n;i++){
            if(isFirst(n-i*i)){
                return 2;
            }

        }

        return 3;
        
    }
}

/*
单纯的背诵，且我忘记了不少

 */