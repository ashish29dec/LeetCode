public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        if (nums.length == 3){
            return Math.max(nums[0] + nums[2], nums[1]);
        }

        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = nums[1];
        max[2] = nums[0] + nums[2];
        
        for (int i = 3; i < nums.length; i++) {
            max[i] = nums[i] + Math.max(max[i-2], max[i-1] - nums[i-1]);
        }
        
        int maxSum = max[0];
        for (int i = 1; i < nums.length; i++) {
            if (max[i] > maxSum) {
                maxSum = max[i];
            }
        }
        
        return maxSum;
    }
}