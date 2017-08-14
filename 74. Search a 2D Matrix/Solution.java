public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        
        int total = m * n;
        if (m == 1) {
            total = n;
        } else if (n == 1) {
            total = m;
        }
        int start = 0;
        int end = total - 1;
        int mid = (end - start) / 2;
        
        while (end >= start) {
            int row = mid / n;
            int col = mid % n;
            
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            
            mid = start + (end - start) / 2;
        }
        
        return false;
    }
}