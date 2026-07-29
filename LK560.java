/**
 * 这道题的思想特别难,千万不要忘了前缀和这种方法，否则基本上做不出来
 */
class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer,Integer>map=new HashMap<>();

        map.put(0,1);

        int pre=0;

        int count=0;

        for(int i=0;i<nums.length;i++){

            pre+=nums[i];

            count+=map.getOrDefault(pre-k,0);
            map.put(pre,map.getOrDefault(pre,0)+1);

        }

        return count;

    }
}


/**
 * 严重注意，这道题不能够使用动态规划，否则会超出内存
 */
// \// class Solution {
// //     public int subarraySum(int[] nums, int k) {
        
// //     }
// // }
// class Solution {

//       public static void print(int[][] arr) {
//       for (int[] row : arr) {
//           System.out.println(Arrays.toString(row));
//       }
//   }

//     public int subarraySum(int[] nums, int k) {

//         int sum1=0;
//         int sum2=0;
        
//         for(int i=0;i<nums.length;i++){
//             if(nums[i]>=0){
//                 sum1+=nums[i];
//             }else{
//                 sum2+=nums[i];
//             }

//         }

//         int base=-sum2;

//         if(k>sum1 || k<sum2){
//             return 0;
//         }

//         int[][]dp=new int[nums.length+1][sum1+base+1];


//         //dp[i][j+base]  以i为结尾  总和是j的存在多少个

//         //i=0 && nn

//         //

//         //dp[0][0+base]=1;

//         for(int i=1;i<=nums.length;i++){
//             dp[i][nums[i-1]+base]=1;

//         }

//         for(int i=1;i<=nums.length;i++){
//             for(int j=Math.max(nums[i-1]-base,-base) ;j<=Math.min(sum1,sum1+nums[i-1]) ;j++){
//                 dp[i][j+base]+=dp[i-1][j+base-nums[i-1]];
//             }
//         }

//         //i+base<=base+sum1+nums[i-1]

//         int count=0;

//         for(int i=0;i<=nums.length;i++){

//             count+=dp[i][k+base];

//         }

//       //  print(dp);

//         return count;
        

//     }
// }