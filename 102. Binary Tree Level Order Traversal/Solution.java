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
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        
        ArrayList<List<TreeNode>> nodes = new ArrayList<>();
        List<TreeNode> initial = new ArrayList<>();
        initial.add(root);
        nodes.add(initial);
        while(nodes.size() > 0) {
            List<TreeNode> nodeArr = nodes.remove(0);
            List<Integer> values = new ArrayList<>();
            List<TreeNode> newNodeList = new ArrayList<>();
            int size = nodeArr.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeArr.get(i);
                if (node.left != null) {
                    newNodeList.add(node.left);
                }
                if (node.right != null) {
                    newNodeList.add(node.right);
                }
                values.add(node.val);
            }
            output.add(values);
            if (newNodeList.size() > 0) {
                nodes.add(newNodeList);
            }
        }
        return output;
    }
}