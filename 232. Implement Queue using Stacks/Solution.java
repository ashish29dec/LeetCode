class MyQueue {
    
    private ArrayList<Integer> popStack = null;
    private ArrayList<Integer> pushStack = null;

    /** Initialize your data structure here. */
    public MyQueue() {
        popStack = new ArrayList<>();
        pushStack = new ArrayList<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if (popStack.size() > 0) {
            int numEl = popStack.size();
            for (int i = 0; i < numEl; i++) {
                int y = popStack.remove(popStack.size() - 1);
                pushStack.add(y);
            }
        }
        pushStack.add(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (popStack.size() == 0) {
            int numEl = pushStack.size();
            for (int i = 1; i <= numEl; i++) {
                int x = pushStack.remove(pushStack.size() - 1);
                popStack.add(x);
            }
        }
        return popStack.remove(popStack.size() - 1);
    }
    
    /** Get the front element. */
    public int peek() {
        int front = pop();
        popStack.add(front);
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return pushStack.size() == 0 && popStack.size() == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */