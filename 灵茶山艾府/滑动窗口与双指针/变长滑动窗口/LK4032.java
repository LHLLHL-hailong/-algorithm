class Solution {


    public int longestSubarray(int[] nums, int k) {

        int max=0;

        for(int num:numms)max=Math.max(max,num);

        int limit=(int)Math.sqrt(max);

        boolean []is=new boolean[limit+1];

        is[0]=true;

        ArrayList<Integer>que=new ArrayList<>();

        for(int i=2;i<=limit;i++){

            if(is[i]) continue;
            que.add(i);

            for(int j=i*2;j<l=imit;j+=i){
                is[j]=true;
            }

        }


        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();

        for(int now:nums){
            if(!map.containsKey(now)){
                ArrayList<Integer>list=new ArrayList<>();
                
                for(int q:que){
                    if(){
                        
                    }
                    
                    if(now%q==0){
                        list.add(q);

                        do{
                            now/=q;
                        }while(now%q==0);
                    }
                }

                    
                    

                map.put(now,list);
            }
        }

        // for(int key:map.keySet()){
        //     System.out.println("-------------------");
        //     System.out.println(key);
        //     for(int val:map.get(key)){
        //         System.out.println(val);
        //     }
        // }
        
        int l=-1;
        int res=0;
        HashMap<Integer,Integer>sMap=new HashMap<>();

        for(int r=0;r<nums.length;r++){
            for(int son:map.get(nums[r])){
                sMap.put(son,sMap.getOrDefault(son,0)+1);
            }

            while(sMap.size()>k){
                l++;
            for(int son:map.get(nums[l])){
                int cnt=sMap.get(son);
                if(cnt>=2){
                    sMap.put(son,cnt-1);
                }else{
                    sMap.remove(son);
                }
            
            }
                
            }

            //System.out.println(l);
            //System.out.println(r);
            //System.out.println(sMap.size());

            res=Math.max(res,r-l);
        }


        return res;
        
    }
}

// class Solution {
//     public int longestSubarray(int[] nums, int k) {

//         HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();

//         for(int now:nums){
//             if(!map.containsKey(now)){
//                 ArrayList<>list=new ArrayList<>();
//                 for(int i=2;i<=Math.sqrt(now);i++){
//                     if(i*(now/i)==now){
//                         list.add(i);
//                         list.add(now/i);
//                     }
//                 }
//             }
//         }
        
//         int l=-1;
//         int res=0;
//         HashMap<Integer,Integer>sMap=new HashMap<>();

//         for(int r=0;r<nums.length;r++){
//             for(int son:map.get(nums[r])){
//                 sMap.put(son,sMap.getOrDefault(son)+1);
//             }

//             while(sMap.size()>k){
//                 l++;
//             for(int son:map.get(nums[l])){
//                 int cnt=sMap.get(son);
//                 if(cnt>=2){
//                     sMap.put(son,sMap.cnt-1);
//                 }else{
//                     sMap.remove(son);
//                 }
            
//             }
                
//             }

//             res=Math.max(res,l-r);
//         }


//         return res;
        
//     }
// }


、
 import java.util.*;

  class Solution {
      public int longestSubarray(int[] nums, int k) {
          int max = 0;
          for (int num : nums) max = Math.max(max, num);

          // 只需要筛到 sqrt(max)
          int limit = (int) Math.sqrt(max);
          boolean[] composite = new boolean[limit + 1];
          List<Integer> primes = new ArrayList<>();

          for (int i = 2; i <= limit; i++) {
              if (composite[i]) continue;
              primes.add(i);
              if ((long) i * i <= limit) {
                  for (int j = i * i; j <= limit; j += i) {
                      composite[j] = true;
                  }
              }
          }

          Map<Integer, List<Integer>> factorCache = new HashMap<>();
          Map<Integer, Integer> counts = new HashMap<>();
          int left = 0, answer = 0;

          for (int right = 0; right < nums.length; right++) {
              for (int p : factors(nums[right], primes, factorCache)) {
                  counts.put(p, counts.getOrDefault(p, 0) + 1);
              }

              while (counts.size() > k) {
                  for (int p : factors(nums[left], primes, factorCache)) {
                      int count = counts.get(p);
                      if (count == 1) counts.remove(p);
                      else counts.put(p, count - 1);
                  }
                  left++;
              }

              answer = Math.max(answer, right - left + 1);
          }
          return answer;
      }

      private List<Integer> factors(
              int num,
              List<Integer> primes,
              Map<Integer, List<Integer>> cache) {
          if (cache.containsKey(num)) return cache.get(num);

          int remaining = num;
          List<Integer> result = new ArrayList<>();

          for (int p : primes) {
              if ((long) p * p > remaining) break;
              if (remaining % p == 0) {
                  result.add(p);
                  do {
                      remaining /= p;
                  } while (remaining % p == 0);
              }
          }
          if (remaining > 1) result.add(remaining);

          cache.put(num, result);
          return result;
      }
  }