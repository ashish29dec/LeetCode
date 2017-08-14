/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        ArrayList<UndirectedGraphNode> queue = new ArrayList<>();
        
        queue.add(node);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        
        while(queue.size() > 0) {
            UndirectedGraphNode orig = queue.remove(0);
            if (visited.contains(orig)) {
                continue;
            }
            visited.add(orig);
            UndirectedGraphNode cloned = map.get(orig);
            int numNeighbors = orig.neighbors.size();
            ArrayList<UndirectedGraphNode> clonedNeighbors = (ArrayList<UndirectedGraphNode>) cloned.neighbors;
            for (int i = 0; i < numNeighbors; i++) {
                UndirectedGraphNode neighbor = orig.neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    UndirectedGraphNode clonedNeighbor = map.get(neighbor);
                    if (clonedNeighbor == null) {
                        clonedNeighbor = new UndirectedGraphNode(neighbor.label);
                        map.put(neighbor, clonedNeighbor);
                    }
                    clonedNeighbors.add(clonedNeighbor);
                    queue.add(neighbor);
                } else {
                    clonedNeighbors.add(map.get(neighbor));
                }
            }
        }
        
        return map.get(node);
    }
}