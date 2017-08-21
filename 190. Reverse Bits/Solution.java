public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        boolean isMSBSet = false;
        if (n < 0) {
            isMSBSet = true;
        }
        
        int rMask = 32768;
        int lMask = 65536;
        
        int maxValue = 2147483647;
        
        boolean lBitSet = false;
        boolean rBitSet = false;
        
        for (int i = 1; i <= 15; i++) {
            lBitSet = ((n & lMask) == lMask);
            rBitSet = ((n & rMask) == rMask);
            
            if ((!lBitSet && rBitSet) || (lBitSet && !rBitSet)) {
                if (!lBitSet && rBitSet) {
                    n |= lMask;
                    n &= (maxValue - rMask);
                } else {
                    n |= rMask;
                    n &= (maxValue - lMask);
                }
            }
            
            rMask /= 2;
            lMask *= 2;
        }
        
        boolean isLSBSet = (n & 1) == 1;
        int x = n >> 1;
        boolean newMSBSet = (x & 1073741824) == 1073741824; // 2^31
        
        if ((isLSBSet && !isMSBSet) || (!isLSBSet && isMSBSet)) {
            if (isLSBSet && !isMSBSet) {
                n &= 2147483646;
                n |= lMask;
            } else {
                n &= maxValue;
                n |= 1;
            }
        } else if ((newMSBSet != isMSBSet) && (isLSBSet && isMSBSet)) {
            return (n + 2147483647 + 1);
        }
        
        return n;
    }
}