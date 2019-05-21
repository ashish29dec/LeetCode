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

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        
        if (root.left == null && root.right == null) {
            return root;
        }
        
        ArrayList<Integer> values = new ArrayList<>();
        
        inorder(root, values);
        
        return constructTree(values);
    }
    
    public TreeNode constructTree(ArrayList<Integer> values) {
        TreeNode node = new TreeNode(values.get(0));
        TreeNode chain = node;
        for (int i = 1; i < values.size(); i++) {
            TreeNode right = new TreeNode(values.get(i));
            chain.right = right;
            chain.left = null;
            chain = chain.right;
        }
        return node;
    }
    
    public void inorder(TreeNode node, ArrayList<Integer> values) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }
}