class MinStack {

    Deque<Long>stack;

    int minValue;

    public MinStack() {

        stack=new ArrayDeque<>();
        
    }
    
    public void push(int value) {

        if(stack.isEmpty()){
            minValue=value;
            stack.addLast(0L);
        }else{
            long now=0L+value-minValue;

            if(now>=0L){
                stack.addLast(now);
            }else{
                stack.addLast(now);
                minValue=value;
            }

        }
        
    }

    //newMin-lastMin=last
    //newMin-last=lastMin
    
    public void pop() {
        long last=stack.removeLast();

        if(last<0L){
            minValue=(int)(minValue-last);
        }
        
    }
    

    public int top() {

        if(stack.getLast()<0){//需要分类
            return minValue;
        }else{
            return (int) (stack.getLast()+minValue);

        }

        

        
    }
    
    public int getMin() {
        
        return minValue;
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

/*
inta+intb+0L,依旧存在内存溢出风险，因为前两个按int加
0L+inta+intb 不存在内存溢出风险,因为先转成long进行加

这道题超过int，需要自己考虑

综合评价：C+

 */