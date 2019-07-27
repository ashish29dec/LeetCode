class Solution {
    class Tuple {
        int x;
        int y;
        
        Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int coloumns = grid[0].length;
        
        if (grid[0][0] == 1 || grid[rows-1][coloumns-1] == 1) {
            return -1;
        }
        
        if (rows == 1 && coloumns == 1) {
            return 1;
        }
        
        int[][] shortestPath = new int[rows][coloumns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < coloumns; j++) {
                shortestPath[i][j] = -1;
            }
        }
        
        ArrayList<Tuple> queue = new ArrayList();
        
        shortestPath[0][0] = 1;
        Tuple t = new Tuple(0, 0);
        queue.add(t);
        
        while (queue.size() > 0) {
            Tuple t1 = (Tuple) queue.remove(0);
            // This tuple will have 8 neighbors
            // starting from top left
            int x = t1.x;
            int y = t1.y;
            int path = shortestPath[x][y];
            
            Tuple nt = null;
            int nx = x - 1;
            int ny = y - 1;
            if (nx >= 0 && ny >= 0 && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            ny++;
            if (nx >= 0 && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            ny++;
            if (nx >= 0 && ny < coloumns && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            nx++;
            if (ny < coloumns && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                if (nx == rows - 1 && ny == coloumns - 1) {
                    return (path + 1);
                }
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            nx++;
            if (nx < rows && ny < coloumns && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                if (nx == rows - 1 && ny == coloumns - 1) {
                    return (path + 1);
                }
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            ny--;
            if (nx < rows && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                if (nx == rows - 1 && ny == coloumns - 1) {
                    return (path + 1);
                }
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            ny--;
            if (nx < rows && ny >= 0 && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
            
            nx--;
            if (ny >= 0 && shortestPath[nx][ny] == -1 && grid[nx][ny] == 0) {
                shortestPath[nx][ny] = path + 1;
                queue.add(new Tuple(nx, ny));
            }
        }
        
        return -1;
    }
}