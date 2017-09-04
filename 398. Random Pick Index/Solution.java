class Solution {
    
    HashMap<Integer, ArrayList<Integer>> map = null;
    Random random = null;

    public Solution(int[] nums) {
        map = new HashMap<>();
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> indexes = map.get(nums[i]);
            if (indexes == null) {
                indexes = new ArrayList<>();
                map.put(nums[i], indexes);
            }
            indexes.add(i);
        }
    }
    
    public int pick(int target) {
        ArrayList<Integer> indexes = map.get(target);
        return indexes.get(random.nextInt(indexes.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */