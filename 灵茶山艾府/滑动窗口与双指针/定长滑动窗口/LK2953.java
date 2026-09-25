// class Solution {


//     int getNums(String s,int l,int r,int count1,int k){



//         int res=0;
//                         {

//                     for(int i=1;i<=count1;i++){
//                         if(i*k<=r-l+1){
//                             int win=i*k;
//                             int[]count=new int[26];
//                             int more=0;
//                             int less=0;

//                             for(int j=l;j<l+win;j++){

//                                 int nowC=++count[s.charAt(j)-'a'];

//                                 if(nowC==1 && k!=1){
//                                     less++;
//                                 }

//                                 if(nowC==k && k!=1){
//                                     less--;
//                                 }

//                                 if(nowC==k+1){
//                                     more++;
//                                 }

//                             }

//                             if(less==0 && more==0){
//                                 res++;
//                             }

//                             for(int j=l+win;j<=r;j++){

//                                 int nowC=++count[s.charAt(j)-'a'];

//                                 if(nowC==1 && k!=1){
//                                     less++;
//                                 }

//                                 if(nowC==k && k!=1){
//                                     less--;
//                                 }

//                                 if(nowC==k+1){
//                                     more++;
//                                 }

//                                 nowC=--count[s.charAt(j-win)-'a'];

//                                 if(nowC==k){
//                                     more--;
//                                 }

//                                 if(nowC==0 && k!=1){
//                                     less--;
//                                 }

//                                 if(nowC==k-1 && k!=1){
//                                     less++;
//                                 }

//                            if(less==0 && more==0){
//                                 res++;
//                             } 
//                             }
//                         }
//                     }
//                 }




//         return res;

//     }

//     public int countCompleteSubstrings(String word, int k) {
        
//         boolean[]isVisited=new boolean[26];

//         isVisited[word.charAt(0)-'a']=true;
//         int count=1;
//         int l=0;
//         int r=-1;

//         int res=0;

//         for(int i=1;i<word.length();i++){

//             if(Math.abs( word.charAt(i)-word.charAt(i-1))>2){
//                 r=i-1;

//                 res+=getNums(word,l,r,count,k);

//                 l=i;

//                 count=0;
//                 isVisited=new boolean[26];

//             }

//             if(!isVisited[word.charAt(i)-'a']){
//                 isVisited[word.charAt(i)-'a']=true;
//                 count++;
//             }

//         }

//         res+=getNums(word,l,word.length()-1,count,k);


//         return res;
//     }
// }


class Solution {


    int getNums(String s,int l,int r,int count1,int k){



        int res=0;
                        {

                    for(int i=1;i<=count1;i++){
                        if(i*k<=r-l+1){
                            int win=i*k;
                            int[]count=new int[26];

                            int valid=0;

                            for(int j=l;j<l+win;j++){

                                int nowC=++count[s.charAt(j)-'a'];

                                if(nowC==k) valid++;
                                if(nowC==k+1) valid--;

                            }

                            if(valid==i){
                                res++;
                            }

                            for(int j=l+win;j<=r;j++){

                                int nowC=++count[s.charAt(j)-'a'];
                                if(nowC==k) valid++;
                                if(nowC==k+1) valid--;
 

                                nowC=--count[s.charAt(j-win)-'a'];
                                if(nowC==k) valid++;
                                if(nowC==k-1)valid--;

                           if(valid==i){
                                res++;
                            } 
                            }
                        }
                    }
                }




        return res;

    }

    public int countCompleteSubstrings(String word, int k) {
        
        boolean[]isVisited=new boolean[26];

        isVisited[word.charAt(0)-'a']=true;
        int count=1;
        int l=0;
        int r=-1;

        int res=0;

        for(int i=1;i<word.length();i++){

            if(Math.abs( word.charAt(i)-word.charAt(i-1))>2){
                r=i-1;

                res+=getNums(word,l,r,count,k);

                l=i;

                count=0;
                isVisited=new boolean[26];

            }

            if(!isVisited[word.charAt(i)-'a']){
                isVisited[word.charAt(i)-'a']=true;
                count++;
            }

        }

        res+=getNums(word,l,word.length()-1,count,k);


        return res;
    }
}

/*
1.  当你维护了多个判定条件时，检查其中一些条件，能否由“固定总量 + 已满足的条件”直接推出
2.  遇到多个合法性条件，先找有没有某个局部违规点，能直接成为所有答案的分界线。


 */