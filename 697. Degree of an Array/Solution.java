import java.util.HashMap;

class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> numToStartIndexMap = new HashMap<>();
        HashMap<Integer, Integer> numToEndIndexMap = new HashMap<>();
        int[] count = new int[50000];
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        
        int maxDegree = 0;
        for(int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (count[num] == 0) {
                numToStartIndexMap.put(num, i);
            }
            count[num]++;
            if (count[num] > maxDegree) {
                maxDegree = count[num];
            }
        }
        
        int shortest = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (count[num] == maxDegree) {
                Integer endIndex = numToEndIndexMap.get(num);
                if (endIndex == null) {
                    endIndex = i;
                    numToEndIndexMap.put(num, endIndex);
                    int startIndex = numToStartIndexMap.get(num);
                    int range = endIndex - startIndex + 1;
                    if (range < shortest) {
                        shortest = range;
                    }
                }
            }
        }
        
        return shortest;
    }
}