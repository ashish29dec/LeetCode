class Solution {
    public int movesToMakeZigzag(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        if (nums.length == 2) {
            if (nums[0] == nums[1]) {
                return 1;
            }
        }
        
        int eMoves = 0;
        int oMoves = 0;
        
        int prev = -1;
        int next = -1;
        for (int i = 0; i < nums.length; i += 2) {
            if (i == 0) {
                prev = nums[0] + 1;
                next = nums[i+1];
            } else if (i == nums.length - 1) {
                next = nums[i] + 1;
                prev = nums[i-1];
            } else {
                prev = nums[i-1];
                next = nums[i+1];
            }
            
            int num = nums[i];
            if (num >= next) {
                num = next - 1;
            }
            if (num >= prev) {
                num = prev - 1;
            }
            eMoves += nums[i] - num;
        }
        
        prev = -1;
        next = -1;
        for (int j = 1; j < nums.length; j += 2) {
            if (j == nums.length - 1) {
                next = nums[j] + 1;
                prev = nums[j-1];
            } else {
                prev = nums[j-1];
                next = nums[j+1];
            }
            
            int num = nums[j];
            if (num >= next) {
                num = next - 1;
            }
            if (num >= prev) {
                num = prev - 1;
            }
            oMoves += nums[j] - num;
        }
        
        return Math.min (eMoves, oMoves);
    }
}