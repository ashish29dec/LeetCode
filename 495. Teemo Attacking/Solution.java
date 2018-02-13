class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) {
            return 0;
        }
        if (timeSeries.length == 1) {
            return duration;
        }
        int total = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            int currTime = timeSeries[i];
            int nextTime = timeSeries[i+1];
            int nextAttackIn = nextTime - currTime;
            int poisonedTime = duration;
            if (nextAttackIn < duration) {
                poisonedTime = nextAttackIn;
            }
            total += poisonedTime;
        }
        
        total += duration;
        return total;
    }
}