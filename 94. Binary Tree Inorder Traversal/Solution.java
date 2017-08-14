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
        boolean lParsed;
        
        Node(TreeNode node) {
            this.node = node;
            lParsed = false;
        }
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer>result = new ArrayList<>();
        
        ArrayList<Node> stack = new ArrayList<>();
        
        if (root != null) {
            Node node = new Node(root);
            stack.add(node);
        }
        
        while(stack.size() > 0) {
            Node node = stack.remove(stack.size() - 1);
            if (!node.lParsed) {
                stack.add(node);
                node.lParsed = true;
                if (node.node.left != null) {
                    stack.add(new Node(node.node.left));
                }
            } else {
                result.add(node.node.val);
                if (node.node.right != null) {
                    stack.add(new Node(node.node.right));
                }
            }
        }
        
        return result;
    }
}