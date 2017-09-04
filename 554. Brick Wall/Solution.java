class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        
        int numLayers = wall.size();
        if (numLayers == 1) {
            if (wall.get(0).size() == 1) {
                return 1;
            }
            return 0;
        }
        
        List<Integer> row = wall.get(0);
        int length = 0;
        for (int i = 0; i < row.size(); i++) {
            length += row.get(i);
        }
        
        HashMap<Integer, Integer> edges = new HashMap<>();
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> layer = wall.get(i);
            int edge = 0;
            for (int j = 0; j < layer.size(); j++) {
                edge += layer.get(j);
                if (edges.get(edge) == null) {
                    edges.put(edge, 1);
                } else {
                    edges.put(edge, edges.get(edge) + 1);
                }
            }
        }
        
        Set<Integer> keys = edges.keySet();
        Iterator<Integer> iterator = keys.iterator();
        int maxEdge = 0;
        while(iterator.hasNext()) {
            int key = iterator.next();
            if (key != length) {
                if (edges.get(key) > maxEdge) {
                    maxEdge = edges.get(key);
                }
            }
        }
        
        if (maxEdge == 0) {
            return wall.size();
        }
        
        return wall.size() - maxEdge;
    }
}