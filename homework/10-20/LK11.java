/**
 * 独立完成5min
 * 时间： O(n)
 * 空间： O(1)
 * 
 */
class Solution {
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