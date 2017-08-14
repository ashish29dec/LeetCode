public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        if (nums.length == 1) {
            return true;
        }
        
        int index = nums.length - 2;
        int leastJumpNeeded = 1;
        boolean canJump = true;
        
        while (index >= 0) {
            if (nums[index] < leastJumpNeeded) {
                leastJumpNeeded++;
                index--;
                canJump = false;
            } else {
                index--;
                canJump = true;
                leastJumpNeeded = 1;
            }
        }
        
        return canJump;
    }
}