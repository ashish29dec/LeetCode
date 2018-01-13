import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int[] majorityEl = new int[2];
        int[] counts = new int[2];
        
        // Filling majorityEl with some dummy values and their counts to be 0.
        // The algorithm will automatically replace these dummy values with right values
        majorityEl[0] = 0;
        majorityEl[1] = 0;
        counts[0] = 0;
        counts[1] = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int index = getIndexOfNumInMajArr(nums[i], majorityEl);
            if (index == -1) {
                int zIndex = getIndexOfMajElWithZeroCount(counts);
                if (zIndex == -1) {
                    // All elements have counts > 0
                    // Reduce counts of all majEl array elements
                    counts[0]--;
                    counts[1]--;
                } else {
                    // Replace this zero count element with our new element
                    counts[zIndex] = 1;
                    majorityEl[zIndex] = nums[i];
                }
            } else {
                // This number already exists in majorityEl. Increment its count
                counts[index]++;
            }
        }
        
        // We are left with possible majority elements. But there still might be possibility that
        // one of the number in majorityEl is not occurring > n/3 times because another element is
        // occurring way many more times
        // Hence, so solve this we will go through the array once more and find actual count of 
        // numbers in majorityEl
        counts[0] = 0;
        counts[1] = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == majorityEl[0]) {
                counts[0]++;
            } else if (num == majorityEl[1]) {
                counts[1]++;
            }
        }
        
        // Now we have actual counts of these numbers. We see if they are > n/3
        ArrayList<Integer> majList = new ArrayList<>();
        int threshold = nums.length / 3;
        if (counts[0] > threshold) {
            majList.add(majorityEl[0]);
        }
        if (counts[1] > threshold) {
            majList.add(majorityEl[1]);
        }
        
        return majList;
    }
    
    /**
     * Returns index of the passed element in the majorityEl array
     * else -1, if not present
     */
    public int getIndexOfNumInMajArr(int num, int[] majEl) {
        if (majEl[0] == num) {
            return 0;
        } else if (majEl[1] == num) {
            return 1;
        }
        return -1;
    }
    
    /**
     * Returns index where count is 0, else returns -1
     */
    public int getIndexOfMajElWithZeroCount(int[] counts) {
        if (counts[0] == 0) {
            return 0;
        } else if (counts[1] == 0) {
            return 1;
        }
        return -1;
    }
}