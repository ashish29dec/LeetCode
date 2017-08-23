class MyStack {
    
    private ArrayList<Integer> queue = null;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int numEl = queue.size();
        for (int i = 1; i < numEl; i++) {
            int x = queue.remove(0);
            queue.add(x);
        }
        
        return queue.remove(0);
    }
    
    /** Get the top element. */
    public int top() {
        int top = pop();
        queue.add(top);
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */