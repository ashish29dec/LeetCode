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
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        ArrayList<TreeNode> stackOdd = new ArrayList<>();
        ArrayList<TreeNode> stackEven = new ArrayList<>();
        
        ArrayList<Integer> list = new ArrayList<>();
        
        stackOdd.add(root);
        
        boolean odd = true;
        
        while(stackOdd.size() > 0 || stackEven.size() > 0) {
            if (odd) {
                if (stackOdd.size() == 0) {
                    odd = false;
                    result.add(list);
                    list = new ArrayList();
                } else {
                    TreeNode node = stackOdd.remove(stackOdd.size() - 1);
                    list.add(node.val);
                    if (node.left != null) {
                        stackEven.add(node.left);
                    }
                    if (node.right != null) {
                        stackEven.add(node.right);
                    }
                }
            } else {
                if (stackEven.size() == 0) {
                    odd = true;
                    result.add(list);
                    list = new ArrayList();
                } else {
                    TreeNode node = stackEven.remove(stackEven.size() - 1);
                    list.add(node.val);
                    if (node.right != null) {
                        stackOdd.add(node.right);
                    }
                    if (node.left != null) {
                        stackOdd.add(node.left);
                    }
                }
            }
        }
        
        result.add(list);
        
        return result;
    }
}