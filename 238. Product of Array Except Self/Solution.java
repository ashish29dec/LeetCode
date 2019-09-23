class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            return null;
        }

        int[] result = new int[nums.length];
        result[0] = 1;
        
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i-1]*nums[i-1];
        }
        
        int temp = nums[nums.length - 1];
        nums[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int n = nums[i];
            nums[i] = nums[i+1]*temp;
            temp = n;
        }
        
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i]*nums[i];
        }
        
        return result;
    }
}