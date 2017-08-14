public class Solution {
    
    public int removeEl(int[] sorted, int elToRemove) {
        int start = 0;
        int end = sorted.length - 1;
        while (end >= start) {
            int mid = start + (end - start) / 2;
            if (sorted[mid] == elToRemove) {
                return mid;
            } else if (sorted[mid] > elToRemove) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    
    public int insertEl(int[] sorted, int indexRemoved, int elToInsert) {
        if (elToInsert <= sorted[0]) {
            return 0;
        }
        
        if (elToInsert >= sorted[sorted.length - 1]) {
            return sorted.length - 1;
        }
        
        int start = 0;
        int end = sorted.length - 1;
        while(end > start) {
            int mid = start + (end - start)/2;
            if (sorted[mid] == elToInsert) {
                return mid;
            } else if (sorted[mid] < elToInsert) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (sorted[start] > elToInsert) {
            if (indexRemoved >= start) {
                return start;
            } else {
                return start - 1;
            }
        } else if (sorted[start] < elToInsert) {
            if (indexRemoved <= start) {
                return start;
            } else {
                return start + 1;
            }
        }
        
        return start;
    }
    
    public void moveEl(int[] sorted, int indexInsertedAt, int indexRemoved, int elToInsert) {
        if (indexInsertedAt == indexRemoved) {
            sorted[indexRemoved] = elToInsert;
            return;
        } else if (indexInsertedAt < indexRemoved) {
            for (int i = indexRemoved; i > indexInsertedAt; i--) {
                sorted[i] = sorted[i-1];
            }
        } else {
            for (int i = indexRemoved; i < indexInsertedAt; i++) {
                sorted[i] = sorted[i+1];
            }
        }
        sorted[indexInsertedAt] = elToInsert;
    }
    
    public double getMedian(int a, int b) {
        double a_ = (double) a/2.0d;
        double b_ = (double) b/2.0d;
        return (double) (a_+b_);
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        
        if (k <= 0) {
            double[] result = new double[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[i];
            }
            return result;
        }
        
        if (k > 0 && nums.length < k) {
            return new double[0];
        }
        
        double[] result = new double[nums.length + 1 - k];
        
        if (k == 2) {
            for (int i = 0; i < result.length; i++) {
                int a = nums[i];
                int b = nums[i+1];
                result[i] = getMedian(a,b);
            }
        } else {
            int[] sorted = new int[k];
            // Fill up sorted
            for (int i = 0; i < k; i++) {
                sorted[i] = nums[i];
            }
        
            // Sort sorted array
            Arrays.sort(sorted);
            
            boolean isEven = false;
            if (k%2 == 0) {
                isEven = true;
            }
            result[0] = isEven? getMedian(sorted[sorted.length/2 - 1], sorted[sorted.length/2]): sorted[sorted.length / 2];
            
            for (int i = 1; i < result.length; i++) {
                // Since window is sliding
                // Remove the left most from sorted array
                int indexRemoved = removeEl(sorted, nums[i-1]);
                
                // Insert new element in the array
                int indexInsertedAt = insertEl(sorted, indexRemoved, nums[i-1+k]);
                
                // Move elements in sorted array
                moveEl(sorted, indexInsertedAt, indexRemoved, nums[i-1+k]);
                
                result[i] = isEven? getMedian(sorted[sorted.length/2 - 1], sorted[sorted.length/2]): sorted[sorted.length / 2];
            }
        }
        return result;
    }
}