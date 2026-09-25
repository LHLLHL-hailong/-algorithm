class Solution {
    public int distinctPoints(String s, int k) {

    

        int base=16;

        HashSet<Integer>set=new HashSet<>();

        int[]now=new int[]{0,0};

        // 0--->上下
        // 1--->左右

        // for(int i=0;i<k;i++){

        //     switch(s.charAt(i)){
        //         case 'L': now[1]--; break;
        //         case 'R': now[1]++; break;
        //         case 'U': now[0]++; break;
        //         case 'D': now[0]--; break;
        //     }
        // }

        set.add(now[0]<<base|now[1]&0x0000ffff);

        for(int i=k;i<s.length();i++){

            switch(s.charAt(i)){
                case 'L': now[1]--; break;
                case 'R': now[1]++; break;
                case 'U': now[0]++; break;
                case 'D': now[0]--; break;
            }

            switch(s.charAt(i-k)){
                case 'L': now[1]++; break;
                case 'R': now[1]--; break;
                case 'U': now[0]--; break;
                case 'D': now[0]++; break;
            }

             set.add(now[0]<<base|now[1]&0x0000ffff);
        }

        return set.size();
        
    }
}
/*

  1. 
  HashSet<Long> set = new HashSet<>();
  long key = ((long) x << 32) | (y & 0xffffffffL);
  set.add(key);

  用一个Long，来设计坐标(两个)的去重
  使用位移来进行加速（注意是 位移<< 不是 位移等于 <<==）
  一个数移到高32位，另一个是低32位，用或来进行连接
  低位要与上0xffffffffL，防止是负数时，高位都是1

  2. 
  如果计算的是不同情况，可能窗口内的初始状态都不用考虑，之后考虑相对位移就可以了

 */