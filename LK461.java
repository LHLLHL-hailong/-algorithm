// class Solution {
//     public int hammingDistance(int x, int y) {

//         int base=0;

//         while(x!=0 || y!=0){
//             base+=(x&1)^(y&1);
//             x>>=1;
//             y>>=1;
//     }

//     return base;
//     }
// }
/**
 * 算法很巧妙，还是值得好好记一下的
 */
  class Solution {
      public int hammingDistance(int x, int y) {
        int now=x^y;
        int count=0;
        while(now!=0){
            now&=(now-1);
            count++;
        }

        return count;

      }
    }
    /**
     * 这个是我原本想到的解法，虽然在力扣上面是没有区别的，但是明显比上述的效率更低
     */
  class Solution {
      public int hammingDistance(int x, int y) {

        int now=x^y;
        int count=0;

        while(now!=0){
            count+=now&1;
            now>>=1;

        }

        return count;

      }
   }