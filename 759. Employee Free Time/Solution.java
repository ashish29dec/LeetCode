/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) {
            return null;
        }
        
        List<Interval> output = new ArrayList<>();
        PriorityQueue<Interval> queue = new PriorityQueue(10, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                    return 0;
                }
                return i1.start < i2.start ? -1 : 1;
            }
        });
        
        int endingTime = -1;
        
        for (int i = 0; i < schedule.size(); i++) {
            List<Interval> emp = schedule.get(i);
            for (int j = 0; j < emp.size(); j++) {
                Interval in = emp.get(j);
                queue.add(in);
            }
        }
        
        Interval sch = queue.poll();
        while (sch != null) {
            if (endingTime == -1) {
                endingTime = sch.end;
            }
            if (sch.start > endingTime) {
                Interval interval = new Interval(endingTime, sch.start);
                output.add(interval);
                endingTime = sch.end;
            } else if (sch.end > endingTime) {
                endingTime = sch.end;
            }
            sch = queue.poll();
        }
        
        return output;
    }
}