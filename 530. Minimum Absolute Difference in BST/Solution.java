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
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<>();
        
        inorderTraversal(root, tree);
        
        return findMinimumDifference(tree);
    }
    
    public void inorderTraversal(TreeNode node, ArrayList<Integer> tree) {
        if (node == null) {
            return;
        }
        
        inorderTraversal(node.left, tree);
        tree.add(node.val);
        inorderTraversal(node.right, tree);
    } 
    
    public int findMinimumDifference(ArrayList<Integer> tree){
        int min = Math.abs(tree.get(0) - tree.get(1));
        int len = tree.size();
        for (int i = 1; i < len - 1; i++) {
            int diff = Math.abs(tree.get(i) - tree.get(i+1));
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }
}