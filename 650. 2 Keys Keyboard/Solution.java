class Solution {
    
    class Node {
        int stepsSoFar;
        int numCopied;
        
        Node(int num, int steps) {
            stepsSoFar = steps;
            numCopied = num;
        }
    }
    
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        
        ArrayList<Node> queue = new ArrayList<>();
        Node first = new Node(1, 1);
        queue.add(first);
        
        int minSteps = n + 1;
        
        while (queue.size() > 0) {
            Node node = queue.remove(0);
            int steps = node.stepsSoFar;
            int num = node.numCopied;
            while (num != n) {
                num += node.numCopied;
                steps++;
                if (num != n && (n-num)%num == 0) {
                    Node newNode = new Node(num, steps + 1);
                    queue.add(newNode);
                }
            }
            if (steps < minSteps) {
                minSteps = steps;
            }
        }
        
        return minSteps;
    }
}