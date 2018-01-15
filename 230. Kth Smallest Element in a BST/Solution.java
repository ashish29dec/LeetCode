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
    int index = 0;
    int kth = -1;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        
        boolean found = false;
        found = root.left != null? inorderTraversal(root.left, k): false;
        if (!found) {
            index++;
            if (index == k) {
                return root.val;
            }
            found = root.right != null? inorderTraversal(root.right, k): false;
        }
        if (!found) {
            return -1;
        }
        return kth;
    }
    
    public boolean inorderTraversal(TreeNode node, int k) {
        
        if (node.left != null) {
            boolean found = inorderTraversal(node.left, k);
            if (found) {
                return true;
            }
        }
        index++;
        if (index == k) {
            kth = node.val;
            return true;
        }
        if (node.right != null) {
            boolean found = inorderTraversal(node.right, k);
            if (found) {
                return true;
            }
        }
        return false;
    }
}