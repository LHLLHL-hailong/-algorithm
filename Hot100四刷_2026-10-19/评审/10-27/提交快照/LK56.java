/**
 * start: 18:27
 * end: 18:35
 * 独立完成
 * 时间：O(nlogn)
 * 空间: O(m) m：最终剩余的
 */
class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,(a,b)->a[0]-b[0]);

        ArrayList<int[]>list=new ArrayList<>();

        int start=intervals[0][0];
        int end=intervals[0][1];

        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<=end){
                end=Math.max(intervals[i][1],end);
            }else{
                list.add(new int[]{start,end});
                start=intervals[i][0];
                end=intervals[i][1];

            }
        }

        list.add(new int[]{start,end});

        int[][]res=new int[list.size()][2];

        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }

        return res;

        
        
    }
}