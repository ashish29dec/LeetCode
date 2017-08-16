public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            return new ArrayList<>();
        }
        
        int numRows = matrix.length;
        if (numRows == 0) {
            return new ArrayList();
        }
        
        int numCols = matrix[0].length;
        if (numCols == 0) {
            return new ArrayList();
        }
        
        int totalEl = numRows * numCols;
        char dir = 'r';
        
        int x = 0;
        int y = 0;
        int numLoop = 0;
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 1; i <= totalEl; i++) {
            result.add(matrix[x][y]);
            
            switch(dir) {
                case 'r':
                    y++;
                    if (y == (numCols - numLoop)) {
                        dir = 'd';
                        x++;
                        y--;
                    }
                    break;
                case 'd':
                    x++;
                    if (x == (numRows - numLoop)) {
                        dir = 'l';
                        x--;
                        y--;
                    }
                    break;
                case 'l':
                    y--;
                    if (y < numLoop) {
                        dir = 'u';
                        x--;
                        y++;
                    }
                    break;
                case 'u':
                    // dir == 'u'
                    x--;
                    if (x == numLoop) {
                        dir = 'r';
                        x++;
                        y++;
                        numLoop++;
                    }
                    break;
            }
        }
        
        return result;
    }           
}