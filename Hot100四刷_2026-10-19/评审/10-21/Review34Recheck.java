/**
 * start: 19:17
 * end:19:28
 * 独立完成
 * 时间：O(log(n))
 * 空间：O(1)
 * 
 */
/**
 * tip：又改限制了，这个只有左右边界用二分才能ac,找到一个后两边扫描不再被允许
 */
class Q34Rechecked {
    public int[] searchRange(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;
        int l1=-1;
        int r1=-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{  
                right=mid-1;
                l1=mid;
                
            }

        }

        left=0;
        right=nums.length-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(target>nums[mid]){

                left=mid+1;

            }else if(target<nums[mid]){
                right=mid-1;

            }else{
                left=mid+1;
                r1=mid;
                
            }

        }

        return new int[]{l1,r1};
        
    }
}
public class Review34Recheck {
    static int checks=0;
    static Q34Rechecked solution=new Q34Rechecked();
    static void check(int[] a,int target) {
        int first=-1,last=-1;
        for(int i=0;i<a.length;i++) if(a[i]==target) { if(first==-1)first=i; last=i; }
        int[] actual=solution.searchRange(a.clone(),target);
        if(!java.util.Arrays.equals(actual,new int[]{first,last}))
            throw new AssertionError(java.util.Arrays.toString(a)+" target="+target);
        checks++;
    }
    public static void main(String[] args) {
        for(int[] a:new int[][]{{},{1},{2,2,2},{1,2,2,2,3},{-1000000000,1000000000}})
            for(int target=-1;target<=3;target++)check(a,target);
        java.util.Random random=new java.util.Random(1021);
        for(int test=0;test<1000;test++) {
            int[] a=new int[random.nextInt(50)];
            for(int i=0;i<a.length;i++)a[i]=random.nextInt(11)-5;
            java.util.Arrays.sort(a);check(a,random.nextInt(15)-7);
        }
        int[] a=new int[100000]; java.util.Arrays.fill(a,5);check(a,5);check(a,6);
        System.out.println(checks);
    }
}
