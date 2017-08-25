class Solution {
    
    class Node {
        String str;
        int steps;
        
        Node(String str, int step) {
            this.str = str;
            steps = step;
        }
    }
    
    private boolean isMutated(String first, String second) {
        int len = first.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                num++;
            }
            if (num > 1) {
                return false;
            }
        }
        
        return num == 1;
    }
    
    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) {
            return -1;
        }
        
        boolean exists = false;
        for (int i = 0; i < bank.length; i++) {
            if (bank[i].equals(end)) {
                exists = true;
                break;
            }
        }
        
        if (!exists) {
            return -1;
        }
        
        ArrayList<Node> queue = new ArrayList<>();
        int[] visited = new int[bank.length];
        
        for (int i = 0; i < bank.length; i++) {
            visited[i] = 0;
        }
        
        for (int i = 0; i < bank.length; i++) {
            String bankStr = bank[i];
            if (isMutated(start, bankStr)) {
                if (bankStr.equals(end)) {
                    return 1;
                }
                queue.add(new Node(bankStr, 1));
                visited[i] = 1;
            }
        }

        while (queue.size() > 0) {
            Node node = queue.remove(0);
            for (int i = 0; i < bank.length; i++) {
                String bankStr = bank[i];
                if (visited[i] == 0 && isMutated(node.str, bankStr)) {
                    if (bankStr.equals(end)) {
                        return (node.steps + 1);
                    }
                    queue.add(new Node(bankStr, node.steps + 1));
                    visited[i] = 1;
                }
            }
        }
        
        return -1;
    }
}