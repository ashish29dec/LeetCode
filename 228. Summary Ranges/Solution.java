public class Solution {
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        if (nums.length == 1) {
            result.add("" + nums[0]);
            return result;
        }
        
        int start = 0;
        int i = 1;
        for (; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                // Save the range
                if (i == start + 1) {
                    result.add("" + nums[start]);
                } else {
                    result.add(nums[start] + "->" + nums[i-1]);
                }
                start = i;
            }
        }
        
        if (i == start + 1) {
            result.add("" + nums[start]);
        } else {
            result.add(nums[start] + "->" + nums[i-1]);
        }
        
        return result;
    }
}