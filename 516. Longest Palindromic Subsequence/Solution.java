public class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        if (s.length() == 1) {
            return 1;
        }
        
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) {
            return 2;
        }
        
        int len = s.length();
        int[][] matrix = new int[len][len];
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = 0;
            }
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; i + j < len; j++) {
                if (i == 0) {
                    matrix[j][j] = 1;
                } else {
                    char start = s.charAt(j);
                    char end = s.charAt(j+i);
                    if (start == end) {
                        if (i == 1) {
                            matrix[j][j+i] = 2;
                        } else {
                            matrix[j][j+i] = 2 + matrix[j+1][i+j-1];
                        }
                    } else {
                        matrix[j][i+j] = Math.max(matrix[j][i+j-1], matrix[j+1][i+j]);
                    }
                }
            }
        }
        
        return matrix[0][len-1];
    }
}