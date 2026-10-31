class MyStack {
    Queue<Integer> q;
    int topElement;

    public MyStack() {
        q = new LinkedList<>();
        topElement = 0;
    }
    
    public void push(int x) {
        q.offer(x);
        topElement = x;
    }
    
    public int pop() {
        int size = q.size();
        while(size > 2) {
            q.offer(q.poll());
            size --;
        }

        topElement = q.peek();
        q.offer(q.poll());
        return q.poll();
    }
    
    public int top() {
        return topElement;
    }
    
    public boolean empty() {
        return q.isEmpty();
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