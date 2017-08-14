public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        
        if (nums.length < 2) {
            return nums.length;
        }
        
        int num = nums[0];
        int index = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                continue;
            }
            nums[++index] = nums[i];
            num = nums[i];
        }
        
        return (index + 1);
    }
}