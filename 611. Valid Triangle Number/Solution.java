class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length - 1; j++) {
                int index3 = nums.length - 1;
                while(index3 > j && nums[i] + nums[j] <= nums[index3]) {
                    index3--;
                }
                if (index3 <= j) {
                    continue;
                }
                count += (index3 - j);
            }
        }
        
        return count;
    }
}