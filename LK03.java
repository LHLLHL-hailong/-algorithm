




class Solution {


    
void printOne(int [] arr){


    for(int num:arr){
        System.out.print(num+" ");
    }

    System.out.println();
}

    public int lengthOfLongestSubstring(String s) {

        char [] arr=s.toCharArray();

        HashSet<Character> set=new HashSet<>();


       

        int l=-1;

        int len=0;
        int maxLen=0;



        for(int r=0;r<s.length();){


            if(!set.contains( arr[r])){

                //printOne(isVisited);

                len++;
               
                set.add(arr[r]);


                 r++;
            }else{

                while(set.contains( arr[r])){

                    maxLen=Math.max(len,maxLen);

                  //  printOne(isVisited);

                    l++;
                    set.remove(arr[l]); 
                    len--;
                }

                


            }

        }

        maxLen=Math.max(len,maxLen);

        return maxLen;

        

        
    }
}