public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] cloneAr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            cloneAr[i] = nums[i];
        }
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int[] result = {-1,-1};
        while (end > start) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                result[0] = nums[start];
                result[1] = nums[end];
                break;
            } else if (target > sum) {
                start++;
            } else {
                end--;
            }
        }
        
        int[] indexes = {-1,-1};
        for (int i=0; i<nums.length; i++) {
            if (cloneAr[i] == result[0] || cloneAr[i] == result[1]) {
                if (indexes[0] == -1) {
                    indexes[0] = i;
                } else if (indexes[1] == -1) {
                    indexes[1] = i;
                }
            }
        }
        return indexes;
    }
}