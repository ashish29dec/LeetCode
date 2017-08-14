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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        HashMap<TreeNode, Boolean> p_parent = new HashMap<>();
        
        inorderTraversal(root, null, parentMap);
        //if (checkIfPQExist(p, q, parentMap)) {
            createPParentMap(p, parentMap, p_parent);
            return getLCA(q, parentMap, p_parent);
        //} else {
        //    return null;
        //}
    }
    
    public void inorderTraversal(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }
        
        inorderTraversal(node.left, node, map);
        map.put(node, parent);
        inorderTraversal(node.right, node, map);
    }
    
    public boolean checkIfPQExist(TreeNode p, TreeNode q, HashMap<TreeNode, TreeNode> map) {
        return (map.get(p) != null && map.get(q) != null);
    }
    
    public void createPParentMap(TreeNode p, HashMap<TreeNode, TreeNode> map, HashMap<TreeNode, Boolean> pMap) {
        pMap.put(p, true);
        TreeNode p1 = map.get(p);
        while (p1 != null) {
            pMap.put(p1, true);
            p1 = map.get(p1);
        }
    }
    
    public TreeNode getLCA(TreeNode q, HashMap<TreeNode, TreeNode> map, HashMap<TreeNode, Boolean> pMap) {
        TreeNode q1 = q;
        while(pMap.get(q1) == null) {
            q1 = map.get(q1);
        }
        return q1;
    }
}