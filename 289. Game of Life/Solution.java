class Solution {
    public void gameOfLife(int[][] board) {
        populateBoard(0, 0, board);
    }
    
    private void populateBoard(int x, int y, int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int val = board[x][y];
        
        int num1 = 0;
        
        int i = x-1;
        int j = y-1;
        
        if (i >= 0 && j >= 0 && board[i][j] == 1) {
            num1++;
        }
        
        j = y;
        if (i >= 0 && board[i][j] == 1) {
            num1++;
        }
        
        j = y+1;
        if (i >= 0 && j < board[0].length && board[i][j] == 1) {
            num1++;
        }
        
        i = x;
        if (j < board[0].length && board[i][j] == 1) {
            num1++;
        }
        
        i = x+1;
        if (i < board.length && j < board[0].length && board[i][j] == 1) {
            num1++;
        }
        
        j = y;
        if (i < board.length && board[i][j] == 1) {
            num1++;
        }
        
        j = y-1;
        if (i < board.length && j >= 0 && board[i][j] == 1) {
            num1++;
        }
        
        i = x;
        if (j >= 0 && board[i][j] == 1) {
            num1++;
        }
        
        int ny = y+1;
        int nx = x;
        if (ny == board[0].length) {
            nx++;
            ny = 0;
        }
        
        if (nx < board.length) {
            populateBoard(nx, ny, board);
        }
        
        switch(val) {
            case 1:
                switch(num1) {
                    case 0:
                    case 1:
                        val = 0;
                        break;
                    case 2:
                    case 3:
                        val = 1;
                        break;
                    default:
                        val = 0;
                }
                break;
            default:
                if (num1 == 3) {
                    val = 1;
                }
        }
        
        board[x][y] = val;
    }
}