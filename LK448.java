/**
 * 很简单，但是记得别用HashSet的版本，很慢
 */
// class Solution {
//     public List<Integer> findDisappearedNumbers(int[] nums) {
        
//     }
// }
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        boolean []is=new boolean[nums.length+1];

        for(int now:nums){
            is[now]=true;
        }

        ArrayList<Integer>list=new ArrayList<>();

        for(int i=1;i<=nums.length;i++){
            if(!is[i]){
                list.add(i);

            }
        }

        return list;


        
        
    }
}