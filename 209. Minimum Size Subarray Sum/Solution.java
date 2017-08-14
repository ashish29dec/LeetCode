public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        
        if (nums== null || nums.length == 0) {
            return 0;
        }
        
        int min = nums.length + 1;
        int start = 0;
        int end = 0;
        int sum = 0;
        
        while(start < nums.length && end < nums.length) {
            if (start == end){
                sum = nums[start];
            } else {
                sum += nums[end];
            }
            
            if (sum == s) {
                int num = end - start + 1;
                if (num < min) {
                    min = num;
                }
                if (start == end) {
                    end++;
                } else {
                    sum -= nums[end];
                }
                sum -= nums[start++];
            } else if (sum < s) {
                if (end == nums.length - 1) {
                    sum -= nums[start++];
                    sum -= nums[end];
                } else {
                    end++;
                }
            } else {
                int num = end - start + 1;
                if (num < min) {
                    min = num;
                }
                if (end == start) {
                    end++;
                } else {
                    sum -= nums[end];
                }
                sum -= nums[start++];
            }
        }
        
        if (min > nums.length) {
            return 0;
        }
        return min;
    }
}