class Solution {
    public int largestIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        
        int rows = grid.length;
        int columns = grid[0].length;
        if (rows == 0 && columns == 0) {
            return 0;
        }
        
        int[][] islands = new int[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        HashMap<Integer, Integer> islandNumToSizeMap = new HashMap<>();
        
        int islandNum = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if(grid[i][j] == 0) {
                    continue;
                }
                int numCells = markIslands(i, j, grid, visited, islands, islandNum);
                islandNumToSizeMap.put(islandNum, numCells);
                islandNum++;
            }
        }
        
        boolean isAnyZeros = false;
        boolean isAnyOnes = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (visited[i][j]) {
                    isAnyOnes = true;
                    continue;
                }
                isAnyZeros = true;
            }
        }
        
        if (!isAnyZeros) {
            return rows*columns;
        }
        if (!isAnyOnes) {
            return 1;
        }
        
        int maxIsland = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                int maxSize = getMaxIslandSize(i, j, islands, islandNumToSizeMap);
                if (maxSize > maxIsland) {
                    maxIsland = maxSize;
                }
            }
        }
        
        return maxIsland;
    }
    
    private int markIslands(int i, int j, int[][] grid, boolean[][] visited, int[][] islands, int islandNum) {
        visited[i][j] = true;
        islands[i][j] = islandNum;
        
        int numCells = 1;
        int x = i;
        int y = j;
        
        x = x-1;
        if (x >= 0 && grid[x][y] == 1 && !visited[x][y]) {
            numCells += markIslands(x, y, grid, visited, islands, islandNum);
        }
        
        x=x+1;
        y=y+1;
        if (y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {
            numCells += markIslands(x, y, grid, visited, islands, islandNum);
        }
        
        x=x+1;
        y=y-1;
        if (x < grid.length && grid[x][y] == 1 && !visited[x][y]) {
            numCells += markIslands(x, y, grid, visited, islands, islandNum);
        }
        
        x=x-1;
        y=y-1;
        if (y >= 0 && grid[x][y] == 1 && !visited[x][y]) {
            numCells += markIslands(x, y, grid, visited, islands, islandNum);
        }
        
        return numCells;
    }
    
    private int getMaxIslandSize(int i, int j, int[][] islands, HashMap<Integer, Integer> map) {
        int size = 1;
        HashSet<Integer> islandNums = new HashSet<>();
        
        int x = i;
        int y = j;
        
        x=i-1;
        if (x >= 0 && islands[x][y] > 0) {
            int islandNum = islands[x][y];
            size += map.get(islandNum);
            islandNums.add(islandNum);
        }
        
        x=i;
        y=j+1;
        if (y < islands[0].length && islands[x][y] > 0) {
            if (!islandNums.contains(islands[x][y])) {
                size += map.get(islands[x][y]);
                islandNums.add(islands[x][y]);
            }
        }
        
        x=i+1;
        y=j;
        if (x < islands.length && islands[x][y] > 0 && !islandNums.contains(islands[x][y])) {
            size += map.get(islands[x][y]);
            islandNums.add(islands[x][y]);
        }
        
        x=i;
        y=j-1;
        if (y >= 0 && islands[x][y] > 0 && !islandNums.contains(islands[x][y])) {
            size += map.get(islands[x][y]);
            islandNums.add(islands[x][y]);
        }
        
        return size;
    }
}