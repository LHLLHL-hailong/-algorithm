/**
 * 这道题需要注意的一点就是下面那个
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {



        Arrays.sort(people,(a,b)->{

            if(b[0]!=a[0]){
                return b[0]-a[0];
            }else{
                return a[1]-b[1];
            }


        });

        List<int[]>list=new ArrayList<>();//注意，必须这样进行，否则很慢，不要自己进行挪移

        for(int[]now:people){
            list.add(now[1],now);

        }

        return list.toArray(new int[people.length][people[0].length]); 


    }

}

/**
 * 效率很低
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }else{
                return b[1]-a[1];
            }
        });

        int [][]res=new int[people.length][];//必须这么定义后面判断null才可能从成立

        for(int i=0;i<people.length;i++){
            int nums=people[i][1]+1;
            for(int j=0;j<res.length;j++){
               // System.out.println(res[j]);
                if(res[j]==null){//  j 已经填好了j个空位置 
                    nums--;       // total=people.length
                                    //  need : people[i][1] 我 people.length-j-people[i][1]-1
                    if(nums==0){
                        
                        res[j]=people[i];
                        break;

                    }

                }

            }
        }
        return res;

    }
}
/*
 * 之后一步反转后效率依旧很低
 */
// class Solution {
//     public int[][] reconstructQueue(int[][] people) {

//         Arrays.sort(people,(a,b)->{
//             if(a[0]!=b[0]){
//                 return a[0]-b[0];
//             }else{
//                 return b[1]-a[1];
//             }
//         });

//         int [][]res=new int[people.length][];//必须这么定义后面判断null才可能从成立

//         for(int i=0;i<people.length;i++){
//             int nums=people.length-i-people[i][1];
//             for(int j=res.length-1;j>=0;j--){
//                // System.out.println(res[j]);
//                 if(res[j]==null){//  i 已经填好了i个空位置 
//                     nums--;       // total=people.length
//                                     //  need : people[i][1] 我 people.length-i-people[i][1]-1
//                     if(nums==0){
                        
//                         res[j]=people[i];
//                         break;

//                     }

//                 }

//             }
//         }
//         return res;

//     }
// }