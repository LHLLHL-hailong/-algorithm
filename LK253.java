




 /*
 * 双指针的方法稍微快一些
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {

        int[]start=new int[intervals.size()];
        int[]end=new int[intervals.size()];



        for(int i=0;i<intervals.size();i++){
            start[i]=intervals.get(i).start;
            end[i]=intervals.get(i).end;

        }
        Arrays.sort(start);
        Arrays.sort(end);

        int rooms=0;
        int endIdx=0;

        for(int i=0;i<start.length;i++){
            
            if(start[i]<end[endIdx]){
                
                rooms++;
            }else{
                // System.out.println(start[i]);
                // System.out.println(end[endIdx]);
                endIdx++;
            }

        }

        return rooms;

    }
}


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
/**
 * 堆的做法
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here

        if(intervals.isEmpty()) return 0;

        PriorityQueue<Integer>queue=new PriorityQueue<>((a,b)->a-b);

        Collections.sort(intervals,(a,b)->a.start-b.start);

        queue.add(0);

        for(Interval now:intervals){

            int preTime=queue.peek();
            if(preTime<=now.start){
                queue.remove();
                queue.add(now.end);

            }else{
                queue.add(now.end);
            }


        }

        return queue.size();
    }
}


/**
 * 差分法,但是使用的时候要慎重,一个是最大范围不确定，另一个是便利的太多了。虽然是线性的，但是性能很慢
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {

        int[] diff = new int[1_000_002];

        for(Interval now:intervals){
            diff[now.start]++;
            diff[now.end]--;
        }

        int cur=0;
        int max=0;

        for(int i=0;i<diff.length;i++){

            cur+=diff[i];
            max=Math.max(max,cur);
        }

        return max;

    }
}
