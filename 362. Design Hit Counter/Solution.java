class HitCounter {
    
    HashMap<Integer, Integer> counter;
    int windowStart = 0;
    int windowEnd = 0;
    int totalCount = 0;

    /** Initialize your data structure here. */
    public HitCounter() {
        counter = new HashMap<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        totalCount++;
        Integer count = counter.get(timestamp);
        if (count == null) {
            count = 0;
        }
        count++;
        counter.put(timestamp, count);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int left = timestamp - 300;
        if (left < 0) {
            left = 0;
        }
        Set<Integer> keys = counter.keySet();
        Iterator<Integer> iterator = keys.iterator();
        HashSet<Integer> keysToRemove = new HashSet<>();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            if (key <= left) {
                totalCount -= counter.get(key);
                keysToRemove.add(key);
            }
        }
        Iterator<Integer> keysSet = keysToRemove.iterator();
        while (keysSet.hasNext()) {
            counter.remove(keysSet.next());
        }
        return totalCount;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */