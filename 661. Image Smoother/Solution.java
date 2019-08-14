class Solution {
    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        int columns = M[0].length;
        
        int[][] result = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            int count = 0;
            int average = 0;
            for (int j = 0; j < columns; j++) {
                count = 1;
                average = M[i][j];
                
                int r = i-1;
                int c = j-1;
                if (r >= 0 && c >= 0) {
                    count++;
                    average += M[r][c];
                }
                
                c = j;
                if (r >= 0) {
                    count++;
                    average += M[r][c];
                }
                
                c = j+1;
                if (r >= 0 && c < columns) {
                    count++;
                    average += M[r][c];
                }
                
                r = i;
                if (c < columns) {
                    count++;
                    average += M[r][c];
                }
                
                r = i+1;
                if (r < rows && c < columns) {
                    count++;
                    average += M[r][c];
                }
                
                c = j;
                if (r < rows) {
                    count++;
                    average += M[r][c];
                }
                
                c = j-1;
                if (r < rows && c >= 0) {
                    count++;
                    average += M[r][c];
                }
                
                r = i;
                if (c >= 0) {
                    count++;
                    average += M[r][c];
                }
                
                average = average/count;
                result[i][j] = average;
            }
        }
        return result;
    }
}