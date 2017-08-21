class Solution {
    public int thirdMax(int[] nums) {
        int max1 = nums[0];
        int max2 = -1;
        int max3 = -1;
        
        boolean found2 = false;
        boolean found3 = false;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == max1) {
                continue;
            } else if (found2 && nums[i] == max2) {
                continue;
            } else if (found3 && nums[i] == max3) {
                continue;
            } else if (nums[i] > max1) {
                int t = max1;
                max1 = nums[i];
                if (!found2) {
                    max2 = t;
                    found2 = true;
                } else {
                    max3 = max2;
                    max2 = t;
                    found3 = true;
                }
            } else if (found2 && nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
                found3 = true;
            } else if (found3 && nums[i] > max3) {
                max3 = nums[i];
            } else if (found3 && nums[i] < max3) {
                continue;
            } else if (found2 && nums[i] < max2) {
                max3 = nums[i];
                found3 = true;
            } else if (nums[i] < max1) {
                if (!found2) {
                    max2 = nums[i];
                    found2 = true;
                } else {
                    max3 = max2;
                    max2 = nums[i];
                    found3 = true;
                }
            }
        }
        
        if (found3) {
            return max3;
        }
        return max1;
    }
}