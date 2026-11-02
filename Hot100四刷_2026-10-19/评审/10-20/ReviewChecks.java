import java.util.*;

/**
 * 独立完成5min
 * 时间： O(n)
 * 空间： O(1)
 * 
 */
class Q11 {
    public int maxArea(int[] height) {

        int l=0;
        int r=height.length-1;

        int res=(r-l)*Math.min(height[r],height[l]);

        while(l<r){
            if(height[l]<height[r]){

                int tmp=height[l];
                while(height[l]<=tmp && l<r){
                    l++;
                }

            }else{

                int tmp=height[r];

                while(height[r]<=tmp && l<r){
                    r--;
                }

            }

            res=Math.max(res,(r-l)*Math.min(height[r],height[l]));
        }

        return res;
        
    }
}
/**
 * 独立完成
 * sartt: 18:27
 * end: 18:38
 * 时间：O(n2)
 * 空间：O(1)
 */
class Q15 {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>>list=new ArrayList<>();


        for(int first=0;first<nums.length;first++){

            if(first!=0 && nums[first]==nums[first-1]){
                continue;
            }

            int target=-nums[first];

            int third=nums.length-1;

            for(int second=first+1;second<nums.length;second++){

                if(second!=first+1 && nums[second]==nums[second-1]){
                    continue;
                }

                while(nums[second]+nums[third]>target && third>second){
                    third--;
                }

                if(third<=second){
                    break;
                }

                if(nums[second]+nums[third]==target){
                    ArrayList<Integer>res=new ArrayList<>();

                    res.add(nums[first]);
                    res.add(nums[second]);
                    res.add(nums[third]);

                    list.add(res);
                }
            }
        }

        return list;
        
    }
}

/**
 * strat: 18:41
 * end: 18:52
 * 独立完成
 * 时间： O(n)
 * 空间： O(n)
 */
class Q42 {
    public int trap(int[] height) {

        int[]stack=new int[height.length];
        int ptr=0;

        int res=0;

        for(int i=0;i<height.length;i++){

            while(ptr!=0 && height[i]>height[stack[ptr-1]]){
                if(ptr>=2){
                    int hei=Math.min(height[stack[ptr-2]],height[i]);
                    int bottle=height[stack[ptr-1]];

                    res+=((i-stack[ptr-2]-1)*(hei-bottle));

                   // System.out.println(res);


                }

                ptr--;
            }

            stack[ptr++]=i;//中途还加成了height[i]!
        }

        return res;
        
    }
}
/**
 * start: 18:55
 * end: 19:00
 * 时间：O(n)
 * 空间：O(1)
 */
class Q53 {
    public int maxSubArray(int[] nums) {

        int base=nums[0];

        int res=nums[0];

        for(int i=1;i<nums.length;i++){

          //  res=Math.max(res,base);
            base=Math.max(base,0)+nums[i];
            res=Math.max(res,base);
        }

    //    res=Math.max(res,base);


        return res;


        
    }
}
/**
 * start: 19:02
 * end: 19:04
 * 时间：O(n)
 * 空间：O(1)
 */
class Q55 {
    public boolean canJump(int[] nums) {


        int max=0;

        for(int i=0;i<=max && i<nums.length ;i++ ){

            max=Math.max(max,i+nums[i]);

            if(i==nums.length-1){
                return true;
            }
        }

        return false;

        
        
    }
}

public class ReviewChecks {
    static Random random = new Random(1020);
    static int[] checks = new int[5];
    static Q11 q11=new Q11(); static Q15 q15=new Q15(); static Q42 q42=new Q42();
    static Q53 q53=new Q53(); static Q55 q55=new Q55();
    static void require(boolean value,String message) { if(!value) throw new AssertionError(message); }
    static void area(int[] a) {
        int best=0;
        for(int i=0;i<a.length;i++) for(int j=i+1;j<a.length;j++) best=Math.max(best,(j-i)*Math.min(a[i],a[j]));
        require(q11.maxArea(a.clone())==best,"11: "+Arrays.toString(a)); checks[0]++;
    }
    static String triple(int a,int b,int c) { int[] t={a,b,c}; Arrays.sort(t); return Arrays.toString(t); }
    static void threeSum(int[] a) {
        Set<String> expected=new HashSet<>();
        for(int i=0;i<a.length;i++) for(int j=i+1;j<a.length;j++) for(int k=j+1;k<a.length;k++)
            if(a[i]+a[j]+a[k]==0) expected.add(triple(a[i],a[j],a[k]));
        Set<String> actual=new HashSet<>();
        List<List<Integer>> result=q15.threeSum(a.clone());
        for(List<Integer> t:result) { require(t.size()==3,"15 tuple length"); actual.add(triple(t.get(0),t.get(1),t.get(2))); }
        require(actual.size()==result.size() && actual.equals(expected),"15: "+Arrays.toString(a)+" got="+actual+" expected="+expected);
        checks[1]++;
    }
    static void water(int[] a) {
        int expected=0;
        for(int i=0;i<a.length;i++) {
            int l=0,r=0;
            for(int j=0;j<=i;j++) l=Math.max(l,a[j]);
            for(int j=i;j<a.length;j++) r=Math.max(r,a[j]);
            expected+=Math.min(l,r)-a[i];
        }
        require(q42.trap(a.clone())==expected,"42: "+Arrays.toString(a)); checks[2]++;
    }
    static void subarray(int[] a) {
        int best=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++) { int sum=0; for(int j=i;j<a.length;j++) { sum+=a[j]; best=Math.max(best,sum); } }
        require(q53.maxSubArray(a.clone())==best,"53: "+Arrays.toString(a)); checks[3]++;
    }
    static void jump(int[] a) {
        boolean[] reachable=new boolean[a.length]; reachable[0]=true;
        for(int i=0;i<a.length;i++) if(reachable[i]) for(int j=1;j<=a[i]&&i+j<a.length;j++) reachable[i+j]=true;
        require(q55.canJump(a.clone())==reachable[a.length-1],"55: "+Arrays.toString(a)); checks[4]++;
    }
    public static void main(String[] args) {
        for(int[] a:new int[][]{{1,1},{0,0},{1,8,6,2,5,4,8,3,7},{5,5,5,5},{9,1,1,1,1,8},{1,2,3,4},{4,3,2,1}}) area(a);
        for(int[] a:new int[][]{{-1,0,1,2,-1,-4},{0,0,0,0},{-2,0,0,2,2},{1,2,3},{-4,-2,-2,0,2,2,4},{-100000,0,100000}}) threeSum(a);
        for(int[] a:new int[][]{{0},{3,1,1,3},{4,2,0,3},{0,1,0,2,1,0,1,3,2,1,2,1},{5,4,3,2,1},{1,2,3,4,5},{4,4,4},{5,0,5}}) water(a);
        for(int[] a:new int[][]{{-1},{-2,-1},{1},{-2,1,-3,4,-1,2,1,-5,4},{0,0},{1,2,3},{-4,-5,-1}}) subarray(a);
        for(int[] a:new int[][]{{0},{0,1},{2,3,1,1,4},{3,2,1,0,4},{2,0,0},{1,0,1},{1,1,1,1}}) jump(a);
        for(int i=0;i<2000;i++) {
            int n=3+random.nextInt(14);
            int[] a=new int[n], heights=new int[n], jumps=new int[n];
            for(int j=0;j<n;j++) { a[j]=random.nextInt(31)-15; heights[j]=random.nextInt(12); jumps[j]=random.nextInt(6); }
            area(heights); threeSum(a); water(heights); subarray(a); jump(jumps);
        }
        int[] large=new int[100000]; Arrays.fill(large,10000);
        require(q11.maxArea(large.clone())==999990000,"11 large"); checks[0]++;
        require(q53.maxSubArray(large.clone())==1000000000,"53 large"); checks[3]++;
        int[] flat=new int[3000]; require(q15.threeSum(flat).equals(List.of(List.of(0,0,0))),"15 large repeats"); checks[1]++;
        int[] pit=new int[20000]; pit[0]=pit[pit.length-1]=100000;
        require(q42.trap(pit)==1999800000,"42 large"); checks[2]++;
        int[] jumpLarge=new int[10000]; jumpLarge[0]=9999;
        require(q55.canJump(jumpLarge),"55 large reachable"); checks[4]++;
        jumpLarge[0]=0; require(!q55.canJump(jumpLarge),"55 large unreachable"); checks[4]++;
        System.out.printf("{\"11\":%d,\"15\":%d,\"42\":%d,\"53\":%d,\"55\":%d}%n",checks[0],checks[1],checks[2],checks[3],checks[4]);
    }
}
