class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        if (citations.length == 1) {
            if (citations[0] == 0) {
                return 0;
            }
            return 1;
        }
        
        Arrays.sort(citations);
        
        return binarySearch(citations);
    }
    
    public int binarySearch(int[] citations) {
        int start = 0;
        int end = citations.length - 1;
        int mid = 0;
        int numCitationsFromMid = 0;
        int hIndexIndex = -1;
        
        while(start <= end) {
            mid = start + (end - start) / 2;
            numCitationsFromMid = citations.length - mid;
            if (citations[mid] < numCitationsFromMid) {
                hIndexIndex = mid;
                start = mid + 1;
            } else if (citations[mid] == numCitationsFromMid) {
                return citations[mid];
            } else {
                end = mid - 1;
            }
        }
        
        if (hIndexIndex == citations.length - 1) {
            mid = hIndexIndex;
        } else {
            mid = hIndexIndex + 1;
        }
        numCitationsFromMid = citations.length - mid;
        if (hIndexIndex == -1 || citations[mid] - citations[hIndexIndex] > 1) {
            return numCitationsFromMid;
        }
        return citations[hIndexIndex];
    }
}