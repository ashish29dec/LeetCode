class Solution {
    public int findMinDifference(List<String> timePoints) {
        // There are max of 60 min in an hour and max 24 hours
        // So 24*60 minutes = 1440 min
        // Hence create an array of this size and then parse through the input timePoints to mark
        // the corresponding minute in the array
        boolean[] setTimes = new boolean[24*60];
        for (int i = 0; i < setTimes.length; i++) {
            setTimes[i] = false;
        }
        
        if (timePoints == null || timePoints.size() == 0) {
            return -1;
        }
        
        int length = timePoints.size();
        for (int i = 0; i < length; i++) {
            String timePoint = timePoints.get(i);
            int colon = timePoint.indexOf(':');
            int hour = Integer.parseInt(timePoint.substring(0, colon));
            int minute = Integer.parseInt(timePoint.substring(colon + 1));
            int index = (hour * 60) + minute;
            if (setTimes[index]) {
                return 0;
            }
            setTimes[index] = true;
        }
        
        int findex = 0;
        for (; findex < setTimes.length; findex++) {
            if (setTimes[findex]) {
                break;
            }
        }
        
        int min = 1440;
        int prevMinute = findex;
        int lastIndex = 0;
        for (int i = findex+1; i < setTimes.length; i++) {
            if (setTimes[i]) {
                int newMin = i - prevMinute;
                prevMinute = i;
                lastIndex = i;
                if (newMin < min) {
                    min = newMin;
                }
            }
        }
        
        int lastMinute = 1439 - lastIndex + findex + 1;
        
        if (lastMinute < min) {
            return lastMinute;
        }
        return min;
    }
}