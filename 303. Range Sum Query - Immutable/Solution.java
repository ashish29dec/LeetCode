public class NumArray {
    
    int[] nums;
    int[] sums;

    public NumArray(int[] nums) {
        this.nums = nums;
        if (nums != null && nums.length > 0) {
            sums = new int[nums.length];
            sums[0] = 0;
            for (int i = 1; i < sums.length; i++) {
                sums[i] = sums[i-1] + nums[i-1];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        if (sums == null) {
            return 0;
        }
        
        return (sums[j] + nums[j] - sums[i]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */