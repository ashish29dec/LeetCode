public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        
        int start = 0;
        int end = numbers.length - 1;
        
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum > target) {
                end--;
            } else if (sum < target) {
                start++;
            } else {
                result[0] = start + 1;
                result[1] = end + 1;
                break;
            }
        }
        
        return result;
    }
}