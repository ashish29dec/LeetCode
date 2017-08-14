public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }
        
        if (nums2 == null || nums2.length == 0) {
            return nums2;
        }
        
        HashSet<Integer> orig = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        
        for (int i = 0; i < nums1.length; i++) {
            if (!orig.contains(nums1[i])) {
                orig.add(nums1[i]);
            }
        }
        
        for (int i = 0; i < nums2.length; i++) {
            if (orig.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        
        Iterator<Integer> iterator = result.iterator();
        int[] res = new int[result.size()];
        int index = 0;
        while(iterator.hasNext()) {
            res[index++] = iterator.next();
        }
        return res;
    }
}