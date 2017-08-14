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
    
    int maxOccur = 0;
    ArrayList<Integer> maxOccurSums;
    HashMap<Integer, Integer> sumOccurMap;
    
    private int postorderTraversal(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = postorderTraversal(node.left);
        int right = postorderTraversal(node.right);
        
        int sum = left + right + node.val;
        
        int numOccur = sumOccurMap.get(sum) == null? 0: sumOccurMap.get(sum);
        numOccur++;
        sumOccurMap.put(sum, numOccur);
        
        if (numOccur == maxOccur) {
            maxOccurSums.add(sum);
        } else if (numOccur > maxOccur) {
            maxOccur = numOccur;
            maxOccurSums = new ArrayList<>();
            maxOccurSums.add(sum);
        }
        
        return sum;
    }
    
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        
        if (root.left == null && root.right == null) {
            return new int[] {root.val};
        }
        
        sumOccurMap = new HashMap<>();
        
        postorderTraversal(root);
        
        int[] result = new int[maxOccurSums.size()];
        Iterator<Integer> iterator = maxOccurSums.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            result[i++] = iterator.next();
        }
        
        return result;
    }
}