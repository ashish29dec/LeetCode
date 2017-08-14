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
        TreeNode node;
        int level;
    }
    
    public int maxDepth(TreeNode root) {
        ArrayList<Node> queue = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        
        Node node = new Node();
        node.node = root;
        node.level = 1;
        
        queue.add(node);
        
        int maxDepth = 0;
        while(queue.size() > 0) {
            node = queue.remove(0);
            if (node.level > maxDepth) {
                maxDepth = node.level;
            }
            
            if (node.node.left != null) {
                Node left = new Node();
                left.node = node.node.left;
                left.level = node.level + 1;
                queue.add(left);
            }
            
            if (node.node.right != null) {
                Node right = new Node();
                right.node = node.node.right;
                right.level = node.level + 1;
                queue.add(right);
            }
        }
        
        return maxDepth;
    }
}