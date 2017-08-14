/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }
        
        if (intervals.length == 1) {
            int[] result = new int[] {-1};
            return result;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < intervals.length; i++) {
            Interval inter = intervals[i];
            if (inter.start < min) {
                min = inter.start;
            }
            if (inter.end < min) {
                min = inter.end;
            }
            if (inter.end > max) {
                max = inter.end;
            }
            if (inter.start > max) {
                max = inter.start;
            }
        }
        
        int[] indexes = new int[max - min + 1];
        
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = -1;
        }
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < intervals.length; i++) {
            Interval inter = intervals[i];
            int start = inter.start - min;
            if (indexes[start] == -1) {
                indexes[start] = i;
            } else if (map.get(start) == null) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(indexes[start]);
                list.add(i);
                map.put(start, list);
            }
        }
        
        int n = -1;
        for (int i = indexes.length - 1; i >= 0; i--) {
            if (indexes[i] != -1) {
                n = indexes[i];
            }
            if (n != -1) {
                indexes[i] = n;
            }
        }
        
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Interval inter = intervals[i];
            int end = inter.end - min;
            int index = indexes[end];
            if (index == i) {
                ArrayList<Integer> list = map.get(end);
                if (list == null) {
                    if (i == intervals.length - 1) {
                        result[i] = -1;
                    } else {
                        result[i] = result[i+1];
                    }
                } else {
                    if (i == list.get(0)) {
                        result[i] = list.get(1);
                    } else {
                        result[i] = list.get(0);
                    }
                }
            } else {
                result[i] = index;
            }
        }
        
        return result;
    }
}