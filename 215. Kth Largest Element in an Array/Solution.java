class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        if (k == 1) {
            return doSingleElSearch(nums);
        }
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int doSingleElSearch(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
    
    private int quickSelect(int[] nums, int left, int right, int kth) {
        if (left == right) {
            return nums[left];
        }
        // Get pivot Index
        int pivotIndex = left + (right - left) / 2;
        // Swap rightmost element with element at pivotIndex
        int temp = nums[right];
        nums[right] = nums[pivotIndex];
        nums[pivotIndex] = temp;
        
        int lastIndex = right;
        int startIndex = left;
        right = right - 1;
        while (right > left) {
            while(nums[right] >= nums[lastIndex] && right > left) {
                right--;
            }
            while(nums[left] <= nums[lastIndex] && right > left) {
                left++;
            }
            if (right > left) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
            }
        }
        int nextIndex = right;
        if (nums[right] < nums[lastIndex]) {
            nextIndex = right+1;
        }
        int t1 = nums[nextIndex];
        nums[nextIndex] = nums[lastIndex];
        nums[lastIndex] = t1;
        
        if (nextIndex == kth) {
            return nums[nextIndex];
        } else if (kth < nextIndex) {
            right = nextIndex - 1;
            left = startIndex;
        } else {
            left = nextIndex + 1;
            right = lastIndex;
        }
        return quickSelect(nums, left, right, kth);
    }
}