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
    
    private boolean isNodeSame(TreeNode left, TreeNode right) {
        
        if (left == null && right == null) {
            return true;
        }
        
        if ((left == null && right != null) || (left != null && right == null) || (left.val != right.val)) {
            return false;
        }
        
        if (!isNodeSame(left.left, right.right)) {
            return false;
        }
        
        return isNodeSame(left.right, right.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }
        
        return isNodeSame(root.left, root.right);
    }
}