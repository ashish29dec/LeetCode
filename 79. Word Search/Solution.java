public class Solution {
    
    int[][] matrix = null;
    int numRows = -1;
    int numCols = -1;
    String w = null;
    char[][] b = null;
    
    private boolean dfs(int i, int j, int index) {
        if (index == w.length()-1) {
            return true;
        }
        
        boolean found = false;
        if (i < numRows - 1) {
            if (matrix[i+1][j] == 0 && w.charAt(index+1) == b[i+1][j]) {
                matrix[i+1][j] = 1;
                found = dfs(i+1, j, index+1);
            }
        }
        
        if (!found && i > 0) {
            if (matrix[i-1][j] == 0 && w.charAt(index+1) == b[i-1][j]) {
                matrix[i-1][j] = 1;
                found = dfs(i-1, j, index+1);
            }
        }
        
        if (!found && j < numCols - 1) {
            if (matrix[i][j+1] == 0 && w.charAt(index+1) == b[i][j+1]) {
                matrix[i][j+1] = 1;
                found = dfs(i, j+1, index+1);
            }
        }
        
        if (!found && j > 0) {
            if (matrix[i][j-1] == 0 && w.charAt(index+1) == b[i][j-1]) {
                matrix[i][j-1] = 1;
                found = dfs(i, j-1, index+1);
            }
        }
        
        if (!found) {
            matrix[i][j] = 0;
        }
        
        return found;
    }
    
    public boolean exist(char[][] board, String word) {
        if (board == null) {
            return false;
        }
        
        if (word == null || word.length() == 0) {
            return false;
        }
        
        numRows = board.length;
        numCols = board[0].length;
        w = word;
        b = board;
        
        if (numRows == 1 && numCols == 1) {
            if (word.length() == 1) {
                if (word.charAt(0) == board[0][0]) {
                    return true;
                }
            }
            return false;
        }
        
        if (word.length() > (numRows * numCols)) {
            return false;
        }
        
        matrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = 0;
            }
        }
        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    matrix[i][j] = 1;
                    if (dfs(i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}