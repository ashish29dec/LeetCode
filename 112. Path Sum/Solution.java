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
    
    private boolean isSumFound(TreeNode node, int sum, int sumSoFar) {
        if (node == null) {
            return false;
        }
        
        sumSoFar += node.val;
        if (node.left == null && node.right== null) {
            return sumSoFar == sum;
        }
        
        boolean found = isSumFound(node.left, sum, sumSoFar);
        if (!found) {
            found = isSumFound(node.right, sum, sumSoFar);
        }
        
        return found;
    }
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                return true;
            } else {
                return false;
            }
        }
        
        boolean isFound = isSumFound(root.left, sum, root.val);
        if (!isFound) {
            isFound = isSumFound(root.right, sum, root.val);
        }
        return isFound;
    }
}