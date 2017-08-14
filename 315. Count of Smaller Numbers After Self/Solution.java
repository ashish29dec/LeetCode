public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int len = nums.length;
        if (len == 1) {
            result.add(0);
            return result;
        }
        int min = nums[0];
        int max = nums[1];
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }
        for (int i = 2; i < len; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        
        int length = max - min + 1;
        int[] less= new int[length];
        for (int i = 0; i < length; i++) {
            less[i] = 0;
        }
        result.add(0);
        less[nums[len - 1] - min] = 1;
        for (int j = len - 2; j >= 0; j--) {
            int num = nums[j];
            int numLess = 0;
            int k = 0;
            for (; k < num - min; k++) {
                numLess += less[k];
            }
            less[k] += 1;
            result.add(0, numLess);
        }
        return result;
    }
}