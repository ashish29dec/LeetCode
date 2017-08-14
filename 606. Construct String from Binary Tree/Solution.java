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
    
    private String preorderTraversal(TreeNode t) {
        if (t == null) {
            return "()";
        }
        
        String output = "(";
        output += t.val;
        
        if (t.left == null && t.right == null) {
            output += ")";
            return output;
        }
        
        if (t.right == null) {
            output += preorderTraversal(t.left) + ")";
            return output;
        }
        
        output += preorderTraversal(t.left);
        output += preorderTraversal(t.right) + ")";
        
        return output;
    }
    
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        
        if (t.left == null && t.right == null) {
            return ("" + t.val);
        }
        
        String output = "";
        output += preorderTraversal(t);
        
        // TODO: Remove 1st and last bracket
        output = output.substring(1, output.length() - 1);
        return output;
    }
}