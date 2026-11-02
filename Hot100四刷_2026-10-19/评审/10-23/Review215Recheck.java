import java.util.*;
/**
 * start: 21:04
 * end: 21:13
 * 独立完成
 * 时间: 最坏（N方） 最好：O(1) 
 * 空间：O(1)
 */
class Q215Corrected {

    void swap(int[]nums,int from ,int to){
        int tmp=nums[from];
        nums[from]=nums[to];
        nums[to]=tmp;
    }

    int search(int[]nums,int left,int right ){




        int mid=left+ new Random().nextInt(right-left+1);

        int piviot=nums[mid];
        swap(nums,mid,right);

        int ptr=left;

        int count=0;

        for(int i=left;i<right;i++){

            if(nums[i]<piviot){
                swap(nums,i,ptr);
                ptr++;

            }else if(nums[i]==piviot){

                if(++count%2==0){
                    swap(nums,i,ptr);
                    ptr++;
                }
                
            }

        }

        swap(nums,ptr,right);

        return ptr;

    }


    public int findKthLargest(int[] nums, int k) {

        int target=nums.length-k;

        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int idx=search(nums,left,right);

            if(idx<target){
                left=idx+1;
            }else if(idx >target){
                right=idx-1;
            }else{
                return nums[idx];
            }
        }

        return -1;

        
        
    }
}
public class Review215Recheck {
    static Q215Corrected solution=new Q215Corrected();
    static int checks=0;
    static void check(int[] a,int k){
        int[] reference=a.clone();Arrays.sort(reference);
        int actual=solution.findKthLargest(a.clone(),k);
        if(actual!=reference[a.length-k])throw new AssertionError("215: "+Arrays.toString(a)+" k="+k+" got="+actual);
        checks++;
    }
    public static void main(String[] args){
        for(int[] a:new int[][]{{1},{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6},{2,2,2,2},{-10000,10000,0}})
            for(int k=1;k<=a.length;k++)check(a,k);
        Random inputRandom=new Random(1023);
        for(int test=0;test<2000;test++){
            int[] a=new int[1+inputRandom.nextInt(100)];
            for(int i=0;i<a.length;i++)a[i]=inputRandom.nextInt(41)-20;
            check(a,1+inputRandom.nextInt(a.length));
        }
        int[] equal=new int[100000];Arrays.fill(equal,7);check(equal,1);check(equal,50000);check(equal,100000);
        int[] ascending=new int[100000];for(int i=0;i<ascending.length;i++)ascending[i]=i/5-10000;
        for(int run=0;run<3;run++){check(ascending,1);check(ascending,50000);check(ascending,100000);}
        int[] descending=ascending.clone();
        for(int i=0;i<descending.length/2;i++){int v=descending[i];descending[i]=descending[descending.length-1-i];descending[descending.length-1-i]=v;}
        check(descending,1);check(descending,50000);check(descending,100000);
        System.out.println(checks);
    }
}
