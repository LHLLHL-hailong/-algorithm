/**
 * start:20:49
 * end: 21:01
 * 独立完成
 * 时间O(1)
 * 空间O(n)
 */
class MinStack {

    long minValue=0L;
    long[]stack=new long[30005];//必须都是long
    int ptr=0;

    public MinStack() {


        ptr=0;
        
    }
    
    public void push(int value) {

        if(ptr<=0){
            stack[ptr++]=0L;
            minValue=value;
        }else{
            stack[ptr++]=(0L+value-minValue);
            if(stack[ptr-1]<0L){
                minValue=value;
            }
        }

    }
    
    public void pop() {

        if(stack[ptr-1]>=0L){
            ptr--;
        }else{
            //minValue-target=stack[ptr-1]
            //target=minValue-stack[ptr-1]

            minValue=minValue-stack[ptr-1];
            ptr--;
        }
        
    }
    
    public int top() {

        if(stack[ptr-1]>=0L){
            return (int)(minValue+stack[ptr-1]);
        }else{
            return (int)minValue;
        }

       // return stack[ptr-1];
        
    }
    
    public int getMin() {

        return (int)minValue;
        
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

