/**
 * 正确的做法不是每次去从set里面去删，而是每次只从序列的起点开始进行遍历，这样即使不删，依旧是O(n)
 */
class Solution {

    public int longestConsecutive(int[] nums) {

        HashSet<Integer>set=new HashSet<>();

        for(int now:nums){
            set.add(now);
        }

        int maxLen=0;

        for(int now:set){

            if(!set.contains(now-1)){

                int l=now+1;
                int nowLen=1;

                while(set.contains(l)){
                    l++;
                    nowLen++
                }
                maxLen=Math.max(maxLen,nowLen);


            }

        }

        return maxLen;


    }
}


/**
 * 经典错误算法，会超时
 */
class Solution {

    public int longestConsecutive(int[] nums) {

        HashSet<Integer>set=new HashSet<>();

        for(int now:nums){
            set.add(now);
        }

        int maxLen=0;

        while(!set.isEmpty()){

        int now=0;

        for(int c:set){
            now=c;
            break;
        }
        set.remove(now);
        int l=now-1;
        int r=now+1;
        while(set.contains(l)){
            set.remove(l);
            l--;
        }

        while(set.contains(r)){
            set.remove(r);
            r++;
        }

        maxLen=Math.max(maxLen,r-l-1);

        }

        return maxLen;

        
    }
}