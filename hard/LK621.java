

class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[]count=new int[26];

        int maxV=0;

        for(int i=0;i<tasks.length;i++){
            int idx=tasks[i]-'A';
            count[idx]++;
            if(count[idx]>maxV){
                maxV=count[idx];
                
            }
        }

        int cnt=0;

        for(int i=0;i<26;i++){
            if(count[i]==maxV){
                cnt++;
            }
        }

        return Math.max((maxV-1)*(n+1)+cnt,tasks.length);
        
    }
}

class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[]count=new int[26];

        for(int i=0;i<tasks.length;i++){
            count[tasks[i]-'A']++;
        }

        int[]nextValid=new int[26];
        int[]rest=new int[26];

        for(int i=0;i<26;i++){
            nextValid[i]=1;
            rest[i]=count[i];
        }

        int time=0;

        while(true){
            time++;
            int minTime=Integer.MAX_VALUE/2;
            for(int i=0;i<26;i++){
                if(rest[i]!=0){
                    minTime=Math.min(minTime,nextValid[i]);
                }
            }
            if(minTime==Integer.MAX_VALUE/2){
                break;
            }else if(minTime>time){
                time=minTime;
            }

            int targetIdx=-1;

            for(int i=0;i<26;i++){
                if(rest[i]!=0 && time>=nextValid[i] ){
                    if(targetIdx==-1 || rest[i]>rest[targetIdx]){
                        targetIdx=i;
                    }
                }

            }
            nextValid[targetIdx]=time+n+1;//注意：要从time开始加。而不是nextValid[targetIdx]，否则面试的时候虽然过了，但容易被追问
            rest[targetIdx]--;
            
        }


        return time-1;
   
    }
}
/*
##review:

综合评价:B-
 
 */