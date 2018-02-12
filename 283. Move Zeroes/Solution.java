class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int backIndex = 0;
        int frontIndex = 0;
        int len = nums.length;
        
        while(frontIndex < len) {
            if (nums[frontIndex] == 0) {
                frontIndex++;
                continue;
            }
            if (backIndex != frontIndex) {
                nums[backIndex] = nums[frontIndex];
            }
            backIndex++;
            frontIndex++;
        }
        
        if (backIndex != 0) {
            for (int i = backIndex; i < len; i++) {
                nums[i] = 0;
            }
        }
    }
}