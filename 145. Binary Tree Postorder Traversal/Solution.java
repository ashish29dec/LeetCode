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
        boolean visited;
        
        Node(TreeNode node) {
            this.node = node;
            visited = false;
        }
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Node> stack = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        Node rNode = new Node(root);
        stack.add(rNode);
        
        while(stack.size() > 0) {
            Node node = stack.remove(stack.size() - 1);
            if (node.visited) {
                result.add(node.node.val);
            } else {
                node.visited = true;
                stack.add(node);
                if (node.node.right != null) {
                    Node riNode = new Node(node.node.right);
                    stack.add(riNode);
                }
                if (node.node.left != null) {
                    Node lNode = new Node(node.node.left);
                    stack.add(lNode);
                }
            }
        }
        
        return result;
    }
}