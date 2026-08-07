class Solution {


    void swap(int []nums,int a,int b){

        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;

    }

    //荷兰国旗问题 0 1 2
    public void sortColors(int[] nums) {
        int left=-1;
        int right=nums.length;
        
        int mid=0;

        while(mid<right){
            if(nums[mid]==1){
                mid++;
            }else if(nums[mid]==0){

       
                swap(nums,mid,left+1);
                left++;
                mid++;//这个和下边的逻辑是不一样的，同边是能保证的，可以直接mid++(不加还不行，不然可能会出错)，但是异边是不能保证的


            }else{
                swap(nums,mid,right-1);
                right--;
            }

        }
        

    }
}



// class Solution {


//     void swap(int []nums,int a,int b){

//         int temp=nums[a];
//         nums[a]=nums[b];
//         nums[b]=temp;

//     }

//     //荷兰国旗问题
//     public void sortColors(int[] nums) {


//         //循环不变量
//         //[0,p1]             0
//         //[p2, len-1]        1
//         //(p2,i)             2

//         int len=nums.length;

//         int p1=-1;
//         int p2=len;
//         int i=0;

//         while(i<p2){

//             if(nums[i]==0){
//                 p1++;
//                 swap(nums,p1,i);
//                 i++;//因为和之前的已经遍历过，所以可以直接移动（交换过来的一定是1）
//             }else if(nums[i]==1){
//                 i++;
//             }else{
//                 p2--;
//                 swap(nums,i,p2);

//             }




//         }







//     //     int []cnt=new int[3];

        

//     //     for(int i=0;i<nums.length;i++){
//     //         cnt[nums[i]]++;

//     //     }

//     //     int ptr=0;


//     //     for(int j=0;j<3;j++){
//     //     while(cnt[j]-- >0){
//     //         nums[ptr++]=j;
//     //     }
//     //     }

        

//     }
// }