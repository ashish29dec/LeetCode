public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        int index = 0;
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        
        if (index == str.length()) {
            return 0;
        }
        
        boolean negative = false;
        int start = index;
        if (str.charAt(index) == '-') {
            negative = true;
            start = index + 1;
        } else if (str.charAt(index) == '+') {
            start = index + 1;
        }
        
        if (start == str.length()) {
            return 0;
        }
        
        int i = start;
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
        }
        int end = i - 1;
        
        if (start > end) {
            return 0;
        }
        
        long result = 0L;
        int k = 0;
        for (int j = end; j >= start; j--, k++) {
            int pow10 = (int) Math.pow(10, k);
            int digit = str.charAt(j) - 48;
            result += (pow10 * digit);
            long res = result;
            if (negative) {
                res = 0 - res;
            }
            
            if (negative && res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            if (!negative && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }
        
        if (negative) {
            return (int) (0- result);
        }
        
        return (int) result;
    }
}