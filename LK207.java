/**
 * 难度一般
 */
class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            ArrayList<ArrayList<Integer>>list=new ArrayList<>(numCourses+10);

            int[]indeg=new int[numCourses];

            for(int i=0;i<numCourses;i++){
                list.add(new ArrayList<>());
            }

            for(int[] now:prerequisites){
                //now[1]--->now[0]

                list.get(now[1]).add(now[0]);
                indeg[now[0]]++;
            }

            Queue<Integer>queue=new LinkedList<>();

            for(int i=0;i<numCourses;i++){
                if(indeg[i]==0){
                    queue.add(i);
                }

            }

            while(!queue.isEmpty()){

               int now=queue.remove();

                for(int cnt:list.get(now)){
                    if(--indeg[cnt]==0){
                        queue.add(cnt);
                    }

                }

            }

            for(int i=0;i<numCourses;i++){
                if(indeg[i]>0){
                    return false;
                }

            }

            return true;

        }
}
