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
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null) {
            return false;
        }
        
        TreeNode computer = getComputerNode(root, x);
        if (computer == null) {
            return true;
        }
        
        int lSubTreeCount = getComputerChildrenCount(computer.left);
        int rSubTreeCount = getComputerChildrenCount(computer.right);
        int pTreeCount = (computer == root) ? 0 : (n - (lSubTreeCount + rSubTreeCount + 1));
        
        int minToWin = n/2;
        return ((lSubTreeCount > minToWin) || (rSubTreeCount > minToWin) || (pTreeCount > minToWin));
    }
    
    public int getComputerChildrenCount(TreeNode comp) {
        if (comp == null) {
            return 0;
        }
        
        int lCount = getComputerChildrenCount(comp.left);
        int rCount = getComputerChildrenCount(comp.right);
        return (lCount + rCount + 1);
    }
    
    public TreeNode getComputerNode(TreeNode node, int x) {
        if (node == null) {
            return null;
        }
        if (node.val == x) {
            return node;
        }
        
        TreeNode n = getComputerNode(node.left, x);
        if (n != null) {
            return n;
        }
        n = getComputerNode(node.right, x);
        return n;
    }
}