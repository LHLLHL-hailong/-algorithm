/**
 * 难点是理解他的这种思想,当然，代码上的记住也很重要
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[]map=new int[27];

        int maxCount=0;
        int maxCountCount=0;


        for(int i=0;i<tasks.length;i++){
            map[tasks[i]-'A']++;
            maxCout==Math.max(maxCount,map[tasks[i]-'A']);
        }

        for(int i=0;i<26;i++){
            if(map[i]==maxCount){
                maxCountCount++;
            }
        }

        return Math.max((maxCount-1)*(n+1)+maxCountCount,tasks.length);

        
    }
}