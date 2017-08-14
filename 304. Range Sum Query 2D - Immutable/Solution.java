public class NumMatrix {

    private int[][] matrix = null;
    int numR = 0;
    int numC = 0;
    
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        
        if (this.matrix != null) {
            numR = this.matrix.length;
            if (numR != 0) {
                numC = this.matrix[0].length;
                
                for (int i = 0; i < numR; i++) {
                    int sum = 0;
                    for (int j = 0; j < numC; j++) {
                        sum += this.matrix[i][j];
                        this.matrix[i][j] = sum;
                    }
                }
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (numR == 0 || numC == 0) {
            return 0;
        }
        
        if (row1 < 0){
            row1 = 0;
        }
        
        if (row2 >= numR) {
            row2 = numR - 1;
        }
        
        if (col1 < 0) {
            col1 = 0;
        }
        
        if (col2 >= numC) {
            col2 = numC - 1;
        }
        
        int sum = 0;
        for (int i = row1; i < (row2 + 1); i++) {
            int sumPrevIndex = 0;
            if (col1 != 0) {
                sumPrevIndex = this.matrix[i][col1 - 1];
            }
            sum += this.matrix[i][col2] - sumPrevIndex;
        }
        
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */