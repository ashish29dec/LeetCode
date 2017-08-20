class Solution {
    
    class Node {
        String var;
        double val;
        
        Node(String c, double v) {
            var = c;
            val = v;
        }
    }
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (queries == null || queries.length == 0) {
            return new double[0];
        }
        
        if (equations == null || equations.length == 0) {
            double[] result = new double[queries.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = -1.0d;
            }
            return result;
        }
        
        int numEq = equations.length;
        
        HashSet<String> variables = new HashSet<>();
        int numVars = 0;
        for (int i = 0; i < numEq; i++) {
            String a = equations[i][0];
            String b = equations[i][1];
            if (!variables.contains(a)) {
                numVars++;
                variables.add(a);
            }
            
            if (!variables.contains(b)) {
                numVars++;
                variables.add(b);
            }
        }
        
        HashMap<String, Integer> indexMap = new HashMap<>();
        HashMap<String, ArrayList<Node>> map = new HashMap<>();
        
        // Populates indexMap, map
        int varIndex = 0;
        for (int i = 0; i < numEq; i++) {
            String a = equations[i][0];
            String b = equations[i][1];
            if (indexMap.get(a) == null) {
                indexMap.put(a, varIndex++);
            }
            if (indexMap.get(b) == null) {
                indexMap.put(b, varIndex++);
            }
            
            // add a
            ArrayList<Node> listA = null;
            if (map.get(a) == null) {
                listA = new ArrayList<>();
                map.put(a, listA);
            } else {
                listA = map.get(a);
            }
            Node nodeA = new Node(b, values[i]);
            listA.add(nodeA);
            
            // add b
            ArrayList<Node> listB = null;
            if (map.get(b) == null) {
                listB = new ArrayList<>();
                map.put(b, listB);
            } else {
                listB = map.get(b);
            }
            Node nodeB = new Node(a, (double) (1.0d / values[i]));
            listB.add(nodeB);
        }
        
        int[] visited = new int[numVars];
        double[] result = new double[queries.length];
        ArrayList<Node> queue = null;
        
        for (int i = 0; i < queries.length; i++) {
            String qVA = queries[i][0];
            String qVB = queries[i][1];
            
            if (indexMap.get(qVA) == null || indexMap.get(qVB) == null) {
                result[i] = -1.0d;
                continue;
            }
            
            if (qVA.equals(qVB)) {
                result[i] = 1.0d;
                continue;
            }
            
            // Reset visited
            for (int j = 0; j < numVars; j++) {
                visited[j] = 0;
            }
            
            // Reset Queue
            queue = new ArrayList<>();
            
            ArrayList<Node> list = map.get(qVA);
            visited[indexMap.get(qVA)] = 1;
            boolean found = false;
            for (int j = 0; j < list.size(); j++) {
                Node n = list.get(j);
                if (n.var.equals(qVB)) {
                    found = true;
                    result[i] = n.val;
                    break;
                }
                queue.add(n);
                visited[indexMap.get(n.var)] = 1;
            }
            
            if (found) {
                continue;
            }
            
            while (queue.size() > 0 && !found) {
                Node n = queue.remove(0);
                ArrayList<Node> listB = map.get(n.var);
                if (listB != null) {
                    for (int j = 0; j < listB.size(); j++) {
                        Node nB = listB.get(j);
                        if (visited[indexMap.get(nB.var)] == 0) {
                            if (nB.var.equals(qVB)) {
                                result[i] = (double) (n.val * nB.val);
                                found = true;
                                break;
                            }
                            visited[indexMap.get(nB.var)] = 1;
                            Node nNext = new Node(nB.var, n.val * nB.val);
                            queue.add(nNext);
                        }
                    }
                }
            }
            
            if (found) {
                continue;
            }
            
            result[i] = -1.0d;
        }
        
        return result;
    }
}