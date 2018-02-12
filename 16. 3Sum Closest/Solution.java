class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int closestSum = 0;
        int remaining = target;
        Arrays.sort(nums);
        
        int diff = target - (nums[0] + nums[1]);
        int thirdIndex = binarySearch(nums, 2, nums.length - 1, diff);
        closestSum = nums[0] + nums[1] + nums[thirdIndex];
        
        int len = nums.length;
        for (int i = 0; i < len-2; i++) {
            for (int j = i+1; j < len-1; j++) {
                int first2 = nums[i] + nums[j];
                remaining = target - first2;
                thirdIndex = binarySearch(nums, j+1, len-1, remaining);
                int third = nums[thirdIndex];
                int sum = first2 + third;
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
            }
        }
        
        return closestSum;
    }
    
    public int binarySearch(int[] nums, int start, int end, int target) {
        int initStart = start;
        int initEnd = end;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
                end = mid;
                break;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        int sNum = nums[start];
        if (sNum == target) {
            return start;
        } else if (sNum < target) {
            int diff = Math.abs(target - sNum);
            if (end == initEnd) {
                return end;
            }
            int next = nums[end+1];
            if (diff < Math.abs(target - next)) {
                return end;
            }
            return end+1;
        } else {
            int diff = Math.abs(target - sNum);
            if (start == initStart) {
                return start;
            }
            int prev = nums[start-1];
            if (diff > Math.abs(target - prev)) {
                return start-1;
            }
            return start;
        }
    }
}