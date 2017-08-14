import java.util.Enumeration;
import java.util.Hashtable;

public class Solution {
    public int majorityElement(int[] nums) {
        Hashtable<Integer, Integer> map = new Hashtable<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.get(num) == null) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        
        Enumeration<Integer> keys = map.keys();
        int threshold = nums.length / 2;
        int t_key = -1;
        int t_value = threshold;
        while (keys.hasMoreElements()) {
            int key = keys.nextElement();
            int value = map.get(key);
            if (value > t_value) {
                t_key = key;
                t_value = value;
            }
        }
        
        return t_key;
    }
}