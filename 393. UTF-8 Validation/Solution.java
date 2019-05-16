class Solution {
    
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        
        int numBytes = 0;
        for (int i = 0; i < data.length; i++) {
            if (numBytes == 0) {
                numBytes = getNumBytes(data[i]);
                if (numBytes < 0 || numBytes == 1 || numBytes > 4) {
                    return false;
                } else if (numBytes > 1) {
                    numBytes--;
                }
            } else {
                if (isN_1_Byte(data[i])) {
                    numBytes--;
                } else {
                    return false;
                }
            }
        }
        
        if (numBytes > 0) {
            return false;
        }
        
        return true;
    }
    
    public int getNumBytes(int data) {
        boolean isZeroFound = false;
        int numOnes = -1;
        if (data <= 127) {
            // 1 byte character as msb is a 0
            return 0;
        }
        do {
            int remainder = data%2;
            data = data/2;
            if (remainder == 0) {
                isZeroFound = true;
                numOnes = 0;
            } else if (remainder == 1 && isZeroFound) {
                numOnes++;
            }
        } while(data > 0);
        
        return numOnes;
    }
    
    public boolean isN_1_Byte(int data) {
        return (data >= 128 && data <= 193);
    }
}