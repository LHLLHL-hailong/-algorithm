class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {

        int tCount=0;
        int fCount=0;

        int res=0;

        int l=-1;

        for(int r=0;r<answerKey.length();r++){
            if(answerKey.charAt(r)=='T'){
                tCount++;
            }else{
                fCount++;
            }

            while(!(Math.min(tCount,fCount)<=k)){
                l++;
                if(answerKey.charAt(l)=='T'){
                    tCount--;
                }else{
                    fCount--;

                }
            }

            res=Math.max(res,r-l);
        }

        return res;
        
    }
}

// class Solution {
//     public int maxConsecutiveAnswers(String answerKey, int k) {

//         int kk=k;

//         int res=0;
//         int l=-1;
//         int l2=-1;
//         for(int r=0;r<answerKey.length;r++){
//             if(answerKey.charAt(r)=='T'){
//                 while(k<=0 && l<r-1){
//                     l++;
//                     if(answerKey.charAt(l)=='T'){
//                         k++;
//                     }
//                 }

//                 if(k>0){
//                     k--;
//                 }else{
//                     l=r;
//                 }
//             }

//             res=Math.max(res,r-l);

//             if(answerKey.charAt(r)=='F'){
//                 while(kk<=0 && l1<r-1){
//                     l1++;
//                     if(answerKey.charAt(l1)=='F'){
//                         kk++;
//                     }
//                 }

//                 if(kk>0){
//                     kk--;
//                 }else{
//                     l1=r;
//                 }
//             }

//             res=Math.max(res,r-l1);

//         }

//         return res;

        
//     }
// }