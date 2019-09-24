class PhoneDirectory {
    
    class BitArray {
        private long[] set;
        private long mask = 0x3f;
        private Random random;
        private int max;
        
        public BitArray(int max) {
            int length = max >> 6;
            if ((max & mask) != 0) {
                length++;
            }
            set = new long[length];
            for (int i = 0; i < length; i++) {
                set[i] = 0L;
            }
            random = new Random(max);
            this.max = max;
        }
        
        public int nextRandomClearBit() {
            boolean found = false;
            while (!found) {
                int next = random.nextInt();
                if (!isBitSet(next)) {
                    setBit(next);
                    return next;
                }
            }
            return -1;
        }
        
        public int nextFirstClearBit() {
            int length = set.length;
            int i = 0;
            for (; i < length - 1; i++) {
                long number = set[i];
                if ((number & 0xffffffffffffffffL) != 0xffffffffffffffffL) {
                    break;
                }
            }
            if (i < length - 1) {
                // A bit is available in index i
                long n = set[i];
                boolean found = false;
                int counter = 0;
                while (!found) {
                    long modif = n & (1L << counter);
                    if (modif == 0) {
                        break;
                    }
                    counter++;
                }
                int unsetBitNumber = (i << 6) + counter;
                setBit(unsetBitNumber);
                return unsetBitNumber;
            } else {
                // Unset bit not available in any index. Check in the last index
                int offset = i - 1;
                if (i-1 < 0) {
                    offset = 0;
                } else {
                    offset = ((i-1) << 6) - 1;
                }
                int numBits = max - offset;
                long n = set[length - 1];
                for (int j = 0; j < numBits; j++) {
                    long modif = n & (1L << j);
                    if (modif == 0) {
                        int unsetBitNumber = (offset + j);
                        setBit(unsetBitNumber);
                        return unsetBitNumber;
                    }
                }
                return -1;
            }
        }
        
        public boolean isBitSet(int num) {
            int index = num >> 6;
            int bitIndex = (int) (num & mask);
            long numberAtIndex = set[index];
            long modif = numberAtIndex >> bitIndex;
            return ((modif & 1L) != 0);
        }
        
        public void setBit(int num) {
            int index = num >> 6;
            int bitIndex = (int) (num & mask);
            long numberAtIndex = set[index];
            long localMask = 1L << bitIndex;
            numberAtIndex |= localMask;
            set[index] = numberAtIndex;
        }
        
        public void resetBit(int num) {
            if (num > max) {
                return;
            }
            int index = num >> 6;
            int bitIndex = (int) (num & mask);
            long number = set[index];
            long localMask = 1L << bitIndex;
            number &= ~localMask;
            set[index] = number;
        }
    }
    
    private BitArray bitSet;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        bitSet = new BitArray(maxNumbers);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        return bitSet.nextFirstClearBit();
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !bitSet.isBitSet(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        bitSet.resetBit(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */