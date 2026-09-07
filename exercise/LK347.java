class Solution {


    void swap(int[][]nums,int from,int to){
        int[]tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer,Integer>map=new HashMap<>();
        int idx=0;

        int [][]count=new int[nums.length][2];

        for(int i=0;i<nums.length;i++){
            Integer index=map.get(nums[i]);
            if(index==null){
                index=idx;
                map.put(nums[i],idx);
                count[idx][0]=nums[i];
                count[idx][1]=1;
                idx++
            }else{
                count[idx][1]++;
            }

        }

        int left=0;
        int right=index-1;

        int []res=new int[k];
        int cnt=0;

        while(left<=right){

            int pivit=(left+right)/2;
            swap(count,pivit,right);
            int l=left;

            for(int j=left+1;j<right;j++){

                if(count[j][1]<=count[right][1]){
                    swap(count,j,l);
                    l++;
                }

            }

            swap(nums,l,right);

            if(k>l){//todo:是坐标还是应该是对少个

            }else if(k<l)else{}




        }
        
        
    }
}