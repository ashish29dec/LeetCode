/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    class Node {
        Node(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
        TreeNode node;
        int level;
    }
    
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        ArrayList<Node> queue = new ArrayList<>();
        if (root.left != null) {
            Node left = new Node(root.left, 1);
            queue.add(left);            
        }
        
        if (root.right != null) {
            Node right = new Node(root.right, 1);
            queue.add(right);
        }
        
        int curLevel = 0;
        int maxNodeVal = root.val;
        while(queue.size() > 0) {
            Node node = queue.remove(0);
            
            int nodeLevel = node.level;
            if (nodeLevel > curLevel) {
                curLevel = nodeLevel;
                result.add(maxNodeVal);
                maxNodeVal = node.node.val;
            } else if(node.node.val > maxNodeVal) {
                maxNodeVal = node.node.val;
            }
            
            if (node.node.left != null) {
                Node left = new Node(node.node.left, node.level + 1);
                queue.add(left);
            }
            
            if (node.node.right != null) {
                Node right = new Node(node.node.right, node.level + 1);
                queue.add(right);
            }
        }
        
        result.add(maxNodeVal);
        
        return result;
    }
}