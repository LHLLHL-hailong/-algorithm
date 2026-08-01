class Solution {
    public int largestRectangleArea(int[] heights) {

        int[]hei=new int[heights.length+2];

        for(int i=0;i<heights.length;i++){
            hei[i+1]=heights[i];
        }

        int[]stack=new int[heights.length+2];
        int ptr=0;

        stack[ptr++]=0;

        int sum=0;

        for(int i=1;i<hei.length;i++){

            if(ptr>0 && hei[i]==hei[stack[ptr-1]]){
                stack[ptr-1]=i;//注意，在这个逻辑中，必须保留最新的,因为你一旦保留老的，在它旁边更新的值就会出问题
                continue;
            }

            while(ptr>0 && hei[i]<hei[stack[ptr-1]]){

                int withe=i-stack[ptr-2]-1;
                int heih=hei[stack[ptr-1]];

                sum=Math.max(sum,withe*heih);

                ptr--;//不带它会超时

            }
            stack[ptr++]=i;

        }

        return sum;
        
    }
}


/*
#konw
1. 这道题在相等的时候必须显示处理，且只能保留新的，不能保留老的，否则会出问题
2. 做这道题的时候，我忘记ptr--了，导致了死循环

#review 
C+(需要重新复习)

 */