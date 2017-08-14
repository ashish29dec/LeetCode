public class Solution {
    
    public int remove(int[] sorted, int elRemove) {
        int start = 0;
        int end = sorted.length - 1;
        while(end >= start) {
            int mid = start + (end-start)/2;
            if (sorted[mid] == elRemove) {
                return mid;
            } else if (sorted[mid] > elRemove) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    
    public int insert(int[] sorted, int indexRemove, int elInsert) {
        int start = 0;
        int end = sorted.length - 1;
        
        while (end > start) {
            int mid = start + (end-start)/2;
            if (sorted[mid] == elInsert) {
                return mid;
            } else if (sorted[mid] > elInsert) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        if (indexRemove == start) {
            return start;
        }
        
        if (sorted[start] < elInsert && indexRemove > start) {
            return start+1;
        }
        
        if (sorted[start] > elInsert && indexRemove < start) {
            return start-1;
        }
        
        return start;
    }
    
    public void move(int[] sorted, int indexInsert, int indexRemove, int elInsert) {
        if (indexInsert == indexRemove) {
            sorted[indexInsert] = elInsert;
            return;
        }
        
        if (indexInsert > indexRemove) {
            for (int i = indexRemove; i < indexInsert; i++) {
                sorted[i] = sorted[i+1];
            }
        }
        
        if (indexInsert < indexRemove) {
            for (int i = indexRemove; i > indexInsert; i--) {
                sorted[i] = sorted[i-1];
            }
        }
        
        sorted[indexInsert] = elInsert;
    }
    
    public boolean isAlmostDuplicate(int a, int b, int t) {
        
        if (a < 0) {
            if ((a + Integer.MAX_VALUE) < b) {
                return false;
            }
        }
        
        if (a >= 0) {
            if ((a - Integer.MAX_VALUE) > b) {
                return false;
            }
        }
        
        if (a == Integer.MIN_VALUE && b >= 0) {
            return false;
        }
        
        if (b == Integer.MIN_VALUE && a >= 0) {
            return false;
        } else if (b == Integer.MIN_VALUE) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        if (a == Integer.MAX_VALUE && b < 0) {
            return false;
        }
        
        if (b == Integer.MAX_VALUE && a < 0) {
            return false;
        } else if (b == Integer.MAX_VALUE) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        return Math.abs(a-b) <= t;
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k == 0) {
            return false;
        }
        
        if (nums.length == 2) {
            if (isAlmostDuplicate(nums[0], nums[1], t)) {
                return true;
            }
            return false;
        }
        
        int start = 0;
        int end = k;
        if (end >= nums.length) {
            end = nums.length - 1;
        }
        int[] sorted = new int[end + 1];
        
        for (int i = start; i <= end; i++) {
            sorted[i] = nums[i];
        }
        
        Arrays.sort(sorted);
        
        for (int i = 0; i < sorted.length - 1; i++) {
            if (isAlmostDuplicate(sorted[i], sorted[i+1], t)) {
                return true;
            }
        }

        start++;
        end++;
        
        while (end < nums.length) {
            int indexRemoved = remove(sorted, nums[start-1]);
            int indexInsertedAt = insert(sorted, indexRemoved, nums[end]);
            move(sorted, indexInsertedAt, indexRemoved, nums[end]);
            
            if (indexInsertedAt == 0) {
                if (isAlmostDuplicate(sorted[0], sorted[1], t)) {
                    return true;
                }
            } else if (indexInsertedAt == sorted.length-1) {
                if (isAlmostDuplicate(sorted[sorted.length-1], sorted[sorted.length-2], t)) {
                    return true;
                }
            } else {
                boolean prev = isAlmostDuplicate(sorted[indexInsertedAt], sorted[indexInsertedAt-1], t);
                boolean next = isAlmostDuplicate(sorted[indexInsertedAt], sorted[indexInsertedAt+1], t);
                if (prev || next) {
                    return true;
                }
            }
            start++;
            end++;
        }
        
        return false;
    }
}