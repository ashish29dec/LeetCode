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
    
    private void inorderTraversal(TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }
    
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) {
            return false;
        }
        
        inorderTraversal(root, arr);
        
        int start = 0;
        int end = arr.size() - 1;
        
        while (end > start) {
            int sum = arr.get(start) + arr.get(end);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                start++;
            } else {
                end--;
            }
        }
        
        return false;
    }
}