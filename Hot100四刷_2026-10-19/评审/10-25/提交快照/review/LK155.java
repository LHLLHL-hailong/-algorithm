/**
 * strat:22:15
 * end:22:25
 * 独立完成
 * 时间: O(1)
 * 空间: O(30005)
 */
class MinStack {

    long[]stack=new long[30005];
    int ptr=0;

    long minValue=-1;

    public MinStack() {

        ptr=0;
        minValue=-1;
        
    }
    
    public void push(int value) {

        if(ptr==0){
            minValue=value;
            stack[ptr++]=0;
        }else{
            stack[ptr++]=(value-minValue);//tip 按照long计算
            if(stack[ptr-1]<0){
                minValue=value;
            }
        }
        
    }
    
    public void pop() {

        if(stack[ptr-1]<0){


            //minvalue-premin=stack[ptr-1];

            minValue=minValue-stack[ptr-1];
        }

        ptr--;
    }
    
    public int top() {

        if(stack[ptr-1]<0) return (int)minValue;//tip
        return(int) (stack[ptr-1]+minValue);
        
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