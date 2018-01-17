class Solution {
	
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        
        if (m == 1 && n == 1) {
            if (matrix[0][0] == target) {
                return true;
            }
            return false;
        }
        
        int rStartIndex = 0;
        int rEndIndex = m - 1;
        
        int cStartIndex = 0;
        int cEndIndex = n - 1;
        
        boolean found = false;
        while (!found) {
            if (isOutOfBounds(rStartIndex, rEndIndex, cStartIndex, cEndIndex, matrix, target)) {
                break;
            }
            if (rStartIndex == rEndIndex) {
                int indexLessThanOrEqualTarget = binarySearchInRow(rStartIndex, cStartIndex, cEndIndex, target, matrix);
                if (matrix[rStartIndex][indexLessThanOrEqualTarget] != target) {
                    break;
                }
                found = true;
            } else if (cStartIndex == cEndIndex) {
                int indexLessThanOrEqualTarget = binarySearchInCol(rStartIndex, rEndIndex, cStartIndex, target, matrix);
                if (matrix[indexLessThanOrEqualTarget][cStartIndex] != target) {
                    break;
                }
                found = true;
            } else {
                int rSIndex = rStartIndex;
                for (; rSIndex <= rEndIndex; rSIndex++) {
                    if (matrix[rSIndex][cEndIndex] >= target) {
                        break;
                    }
                }
                rStartIndex = rSIndex;
                
                int cEIndex = binarySearchInRow(rStartIndex, cStartIndex, cEndIndex, target, matrix);
                if (matrix[rStartIndex][cEIndex] == target) {
                    found = true;
                    continue;
                }
                int rEIndex = binarySearchInCol(rStartIndex, rEndIndex, cStartIndex, target, matrix);
                if (matrix[rEIndex][cStartIndex] == target) {
                    found = true;
                    continue;
                }
                
                cEndIndex = cEIndex;
                rEndIndex = rEIndex;
            }
        }
        
        return found;
    }
    
    public boolean isOutOfBounds(int rS, int rE, int cS, int cE, int[][] matrix, int target) {
        if (target < matrix[rS][cS] || target > matrix[rE][cE]) {
            return true;
        }
        return false;
    }
    
    public int binarySearchInRow(int rS, int cS, int cE, int target, int[][] matrix) {
        int[] arr = matrix[rS];
        int start = cS;
        int end = cE;
        int index = -1;
        while(end >= start) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                index = mid;
                break;
            }
            if (arr[mid] > target) {
                if ((mid - 1 >= cS) && arr[mid-1] < target) {
                    index = mid-1;
                    break;
                }
                end = mid-1;
                continue;
            }
            if (arr[mid] < target) {
                if ((mid+1 <= cE) && arr[mid+1] > target) {
                    index = mid;
                    break;
                }
                start = mid+1;
                continue;
            }
        }
        return (index < 0? cE: index);
    }
    
    public int binarySearchInCol(int rS, int rE, int cS, int target, int[][] matrix) {
        int start = rS;
        int end = rE;
        int index = -1;
        while (end >= start) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][cS] == target) {
                index = mid;
                break;
            }
            if (matrix[mid][cS] > target) {
                if (mid > rS && matrix[mid-1][cS] < target) {
                    index = mid-1;
                    break;
                }
                end = mid-1;
                continue;
            }
            if (matrix[mid][cS] < target) {
                if (mid < rE && matrix[mid+1][cS] > target) {
                    index = mid;
                    break;
                }
                start = mid+1;
                continue;
            }
        }
        return (index < 0? rE: index);
    }
}