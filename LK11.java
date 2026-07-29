/**
 * 这个题我写的就很次了,这个是容器中能装更多的水，而不是在使用单调栈的接雨水问题
 */

class Solution {
    public int maxArea(int[] height) {

        int l=0;
        int r=height.length-1;

        int res=0;

        while(l<r){

            int sum=Math.min(height[l],height[r])*(r-l);
            res=Math.max(res,sum);


            if(height[l]<height[r]){

                int now=height[l];

                while(l+1<=r && height[l+1]<=now){
                    l++;
                }
                l++;

            }else{

                int now=height[r];
                while(r-1>=l && height[r-1]<=now){
                    r--;
                }
                r--;
            }

        }

        return res;



        
    }
}