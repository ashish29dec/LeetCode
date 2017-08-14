public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 0) {
            return false;
        }
        
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (indexMap.get(num) == null) {
                indexMap.put(num, i);
            } else {
                int index = indexMap.get(num);
                int diff = i - index;
                if (diff <= k) {
                    return true;
                }
                indexMap.put(num, i);
            }
        }
        
        return false;
    }
}