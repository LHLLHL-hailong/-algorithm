
/**
 * 对于不用辅助栈的做法，记得要使用long类型,不然会精度溢出
 */
class MinStack {



  long[]diff;
  int ptr;
  long minValue=0;


    public MinStack() {

      diff=new long[30001];
      ptr=0;
    }
    
    public void push(int val) {
        if(ptr==0){
            diff[ptr++]=0L;
            minValue=val+0L;
            return ;
        }

        if(val>=minValue){
            diff[ptr++]=val-minValue;
        }else{
            diff[ptr++]=val-minValue;
            minValue=val;
        }

    }
    
    public void pop() {
        if(diff[ptr-1]>=0L){
            ptr--;
        }else{

            minValue=minValue-diff[ptr-1];
            ptr--;
        }

    }
    
    public int top() {
       // return stack[ptr-1];

       if(diff[ptr-1]<0L){
        return (int)minValue;
       }else{
        return (int)(minValue+diff[ptr-1]);
       }

    }
    
    public int getMin() {
       // printArray(minStack);
      // System.out.println(minStack[ptrS-1]);
       // return minStack[ptrS-1];

       return (int)minValue;

    }
}















/**
 * 使用辅助栈的做法
 */
class MinStack {



  int[]stack;
  int ptr;
  int[]minStack;
  int ptrS;

    public MinStack() {

      stack=new int[30001];
      minStack=new int[30001];
      ptr=0;
      ptrS=0;
    }
    
    public void push(int val) {
        stack[ptr++]=val;
        if(ptrS==0){
            minStack[ptrS++]=val;
        }else{
            if(minStack[ptrS-1]<val){
                minStack[ptrS]=minStack[ptrS-1];
                ptrS++;
            }else{
                minStack[ptrS++]=val;
            }
        }


    }
    
    public void pop() {
        ptr--;
        ptrS--;


    }
    
    public int top() {
        return stack[ptr-1];

    }
    
    public int getMin() {
       // printArray(minStack);
      // System.out.println(minStack[ptrS-1]);
        return minStack[ptrS-1];

    }
}