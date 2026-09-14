class Solution {
    public int lengthOfLongestSubstring(String s) {

        char[]sc=s.toCharArray();

        int[]count=new int[1024];

        int maxLen=0;

        int diff=0;

        int len=0;

        int l=-1;

        for(int r=0;r<sc.length;r++){

            count[(int)sc[r]]++;
            len++;

            if(count[(int)sc[r]]==2){
                diff++;
            }

            while(diff>0){
                l++;
                len--;
                count[(int)sc[l]]--;
                if(count[(int)sc[l]]==1){
                    diff--;
                }
            }

            maxLen=Math.max(maxLen,len);


        }

        return maxLen;

    }
}