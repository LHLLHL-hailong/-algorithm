class Solution {
    public int[] maxProductPair(int[] nums, int target) {

        int[] res=new int[]{-1,-1};
        boolean has=false;

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(2*nums[i]!=target && map.containsKey(target-nums[i]) ){
                if(!has){

                    res=new int[]{i,map.get(target-nums[i])};
 
                    has=true;
                }else if(nums[i]*(target-nums[i]) > nums[res[0]]* nums[res[1]]){

                    res=new int[]{i,map.get(target-nums[i])};
                    
                }
            }
            map.put(nums[i],i);
        }

        if(has && nums[res[0]]<nums[res[1]]){
            int tmp=res[0];
            res[0]=res[1];
            res[1]=tmp;
        }

        return res;
    
        
        
    }
}