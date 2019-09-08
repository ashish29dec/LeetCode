/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    class Node{
        TreeNode node;
        int level;
        public Node (TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    int maxLevel;
    int maxNodes;
    TreeNode result;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        if (root.left == null && root.right == null) {
            return root;
        }
        
        List<Node> queue = new ArrayList<>();
        maxLevel = 0;
        maxNodes = 1;
        queue.add(new Node(root, 0));
        while (queue.size() > 0) {
            Node n = queue.remove(0);
            TreeNode l = n.node.left;
            TreeNode r = n.node.right;
            int level = n.level;
            if (l != null) {
                if (maxLevel == level+1) {
                    maxNodes++;
                } else if (maxLevel < level+1) {
                    maxLevel = level+1;
                    maxNodes = 1;
                }
                queue.add(new Node(l, level+1));
            }
            if (r != null) {
                if (maxLevel == level+1) {
                    maxNodes++;
                } else if (maxLevel < level+1) {
                    maxLevel = level+1;
                    maxNodes = 1;
                }
                queue.add(new Node(r, level+1));
            }
        }
        
        result = null;
        getLCA(root, 0);
        return result;
    }
    
    private int getLCA(TreeNode node, int level) {
        if (level == maxLevel) {
            if (maxNodes == 1) {
                result = node;
            }
            return 1;
        }
        
        int n = 0;
        if (node.left != null) {
            n += getLCA(node.left, level+1);
        }
        if (result != null) {
            return n;
        }
        if (node.right != null) {
            n += getLCA(node.right, level+1);
        }
        if (result != null) {
            return n;
        }
        if (n == maxNodes) {
            result = node;
        }
        return n;
    }
}