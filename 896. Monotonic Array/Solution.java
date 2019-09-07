class Solution {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length < 3) {
            return true;
        } 
        
        boolean isUndecided = true;
        boolean isIncreasingMonotonous = false;
        
        for (int i = 0; i < A.length - 1; i++) {
            int cur = A[i];
            int next = A[i+1];
            if (isUndecided) {
                if (cur == next) {
                    continue;
                }
                isUndecided = false;
                isIncreasingMonotonous = false;
                if (cur < next) {
                    isIncreasingMonotonous = true;
                }
                continue;
            }
            if (isIncreasingMonotonous && cur > next) {
                return false;
            }
            if (!isIncreasingMonotonous && cur < next) {
                return false;
            }
        }
        
        return true;
    }
}