public class Solution {
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        HashSet<Integer> intermediates = new HashSet<>();
        intermediates.add(n);
        boolean found = false;
        while(!found) {
            int remainder = n%10;
            n /= 10;
            int sumOfSquares = 0;
            while (n != 0) {
                sumOfSquares += (remainder * remainder);
                remainder = n%10;
                n /= 10;
            }
            sumOfSquares += (remainder * remainder);
            n = sumOfSquares;
            if (n == 1) {
                found = true;
            } else {
                if (intermediates.contains(n)) {
                    break;
                } else {
                    intermediates.add(n);
                }
            }
        }
        return found;
    }
}