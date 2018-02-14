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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<TreeNode> stack = new ArrayList<>();
        
        if (root == null) {
            return result;
        }

        stack.add(root);
        
        while(stack.size() > 0) {
            TreeNode node = stack.remove(stack.size() - 1);
            result.add(node.val);
            
            if (node.right != null) {
                stack.add(node.right);
            }
            
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        
        return result;
    }    
}