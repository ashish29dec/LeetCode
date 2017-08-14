public class Solution {
    public int nextGreaterElement(int n) {
        if (n < 12) {
            return -1;
        }
        
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        
        int digit = n%10;
        int quotient = n/10;
        int prev = digit;
        count[digit]++;
        
        digit = quotient%10;
        quotient /= 10;
        
        while (quotient > 0 && digit >= prev) {
            count[digit]++;
            prev = digit;
            digit = quotient%10;
            quotient /= 10;
        }
        count[digit]++;
        
        int nextBiggerDigit = -1;
        for (int i = digit+1; i < 10; i++) {
            if (count[i] > 0) {
                nextBiggerDigit = i;
                count[i]--;
                break;
            }
        }
        
        if (nextBiggerDigit < 0) {
            return -1;
        }
        
        int newQuo = quotient * 10;
        int diff = Integer.MAX_VALUE - newQuo;
        if (diff >= nextBiggerDigit) {
            quotient = newQuo + nextBiggerDigit;
        } else {
            return -1;
        }
        
        int i = 0;
        while (i < 10) {
            if (count[i] > 0) {
                diff = Integer.MAX_VALUE - quotient;
                if (diff / 10 >= quotient) {
                    newQuo = quotient * 10;
                    diff = Integer.MAX_VALUE - newQuo;
                    if (diff >= i) {
                        quotient = newQuo + i;
                        count[i]--;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                i++;
            }
        }
        
        return quotient;
    }
}