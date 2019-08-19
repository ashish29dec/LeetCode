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
    
    class Tuple {
        TreeNode node;
        int level;
        
        public Tuple(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    public int maxLevelSum(TreeNode root) {
        ArrayList<Tuple> nodes = new ArrayList<>();
        
        Tuple t = new Tuple(root, 1);
        nodes.add(t);
        int maxLevel = 0;
        int maxSum = Integer.MIN_VALUE;
        int prevLevel = 0;
        int newSum = Integer.MIN_VALUE;
        int level;
        while(nodes.size() > 0) {
            Tuple t1 = nodes.remove(0);
            TreeNode node = t1.node;
            level = t1.level;
            
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                nodes.add(new Tuple(left, level+1));
            }
            if (right != null) {
                nodes.add(new Tuple(right, level+1));
            }
            
            if (level != prevLevel) {
                if (newSum > maxSum) {
                    maxSum = newSum;
                    maxLevel = prevLevel;
                }
                newSum = node.val;
                prevLevel = level;
            } else {
                newSum += node.val;
            }
        }
        
        if (newSum > maxSum) {
            return prevLevel;
        }
        return maxLevel;
    }
}