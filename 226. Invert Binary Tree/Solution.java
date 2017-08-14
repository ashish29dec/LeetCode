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
    
    private void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        
        TreeNode left = node.left;
        TreeNode right = node.right;

        invert(left);
        invert(right);
        
        node.left = right;
        node.right = left;
    }
    
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        invert(root);
        
        return root;
    }
}