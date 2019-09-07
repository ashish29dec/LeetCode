class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        if (s.length() == 1) {
            if (s.charAt(0) == '0') {
                return 0;
            }
            return 1;
        }
        
        int length = s.length();
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = 0;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                if (s.charAt(i) != '0') {
                    dp[i] = 1;
                }
                continue;
            }
            char c = s.charAt(i);
            if (c == '0') {
                dp[i] = 0;
                continue;
            }
            if (c >= '1' && c <= '9') {
                dp[i] += dp[i+1];
            }
            String st = s.substring(i, i+2);
            int n = Integer.parseInt(st);
            if (n > 0 && n <= 26) {
                if (i != length - 2) {
                    dp[i] += dp[i+2];
                } else {
                    dp[i] += 1;
                }
            }
        }
        
        return dp[0];
    }
}