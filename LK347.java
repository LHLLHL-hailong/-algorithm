/**
 * 将耗时的操作通过快速选择降低到了O(n)
 */
class Solution {


    void swap(int[][]nums,int from,int to){

        int[]tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    public int[] topKFrequent(int[] nums, int k) {

        int[][]total=new int[nums.length][2];
        int ptr=0;

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            Integer idx=map.get(nums[i]);
            if(idx==null){
                total[ptr++]=new int[]{nums[i],1};//nums count
                map.put(nums[i],ptr-1);
            }else{
                total[idx][1]++;
            }

        }

        int []res=new int[k];
        int cnt=0;

        int left=0;
        int right=ptr-1;

        while(left<=right){

            int pivit=(left+right)/2;
            int countC=total[pivit][1];
            swap(total,pivit,right);

            int index=left;//要从left开始，而不是零开始

            for(int i=left;i<right;i++){//要从left开始，而不是从零开始
                if(total[i][1] >=countC){
                    swap(total,i,index);
                    index++;

                }
            }

            swap(total,index,right);


            if(k<index-left+1){
                right=index-1;
            }else{
                for(int i=left;i<=index;i++){
                    res[cnt++]=total[i][0];
 
                }

                if(k==index-left+1){
                    return res;
                }

                

                k-=(index-left+1);//千万注意这两个的时序关系
                left=index+1;

            }
        }

        return res;


    }
}


/**
 * 非最优
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        int[][]total=new int[nums.length][2];
        int ptr=0;

        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            Integer idx=map.get(nums[i]);
            if(idx==null){
                total[ptr++]=new int[]{nums[i],1};
                map.put(nums[i],ptr-1);
            }else{
                total[idx][1]++;
            }

        }

        PriorityQueue<int[]>queue=new PriorityQueue<>((a,b)->b[1]-a[1]);//从大到小

        for(int i=0;i<ptr;i++){
            queue.add(total[i]);
        }

        int[]res=new int[k];

        for(int i=0;i<k;i++){
            res[i]=queue.remove()[0];
        }

        return res;

    }
}