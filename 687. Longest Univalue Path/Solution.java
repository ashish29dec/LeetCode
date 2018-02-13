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
    class Node {
        TreeNode node;
        int maxLength;
        
        Node(TreeNode node) {
            this.node = node;
            maxLength = 1;
        }
    }
    
    int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        
        Node rootNode = new Node(root);
        postorderTraversal(rootNode);
        
        return max - 1;
    }
    
    public void postorderTraversal(Node node) {
        if (node.node == null) {
            return;
        }
        
        Node lNode = new Node(node.node.left);
        Node rNode = new Node(node.node.right);
        postorderTraversal(lNode);
        postorderTraversal(rNode);
        
        int lMax = 0;
        int rMax = 0;
        int lVal, rVal;
        int val = node.node.val;
        if (node.node.left != null) {
            lVal = node.node.left.val;
            if (lVal == val) {
                lMax = lNode.maxLength;
            }
        }
        if (node.node.right != null) {
            rVal = node.node.right.val;
            if (rVal == val) {
                rMax = rNode.maxLength;
            }
        }
        node.maxLength = Math.max(lMax, rMax) + 1;
        
        if (node.node.left != null && node.node.right != null) {
            lVal = node.node.left.val;
            rVal = node.node.right.val;
            if (lVal == val && rVal == val) {
                int maxPath = lNode.maxLength + rNode.maxLength + 1;
                if (maxPath > max) {
                    max = maxPath;
                    return;
                }
            }
        }
        
        if (node.maxLength > max) {
            max = node.maxLength;
        }
    }
}