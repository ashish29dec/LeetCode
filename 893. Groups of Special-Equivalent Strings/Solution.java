class Solution {
    public int numSpecialEquivGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int specialEqCount = 0;
        HashSet<String> specialEqSet = new HashSet();
        for (int i = 0; i < A.length; i++) {
            String input = A[i];
            StringBuffer inputBuf = new StringBuffer();
            // Parsing odd indexed characters
            int[] count = new int[26];
            resetCount(count);
            fillCount(0, input, count);
            inputBuf.append(parseCount(count));
            
            // Parse even indexes characters
            resetCount(count);
            fillCount(1, input, count);
            inputBuf.append(parseCount(count));
            
            // Process the parsed string
            String specialEq = inputBuf.toString();
            if (!specialEqSet.contains(specialEq)) {
                specialEqCount++;
                specialEqSet.add(specialEq);
            }
        }
        
        return specialEqCount;
    }
    
    public void resetCount(int[] count) {
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
    }
    
    public void fillCount(int startIndex, String input, int[] count) {
        for (int j = startIndex; j < input.length(); j=j+2) {
            char c = input.charAt(j);
            count[c-'a']++;
        }        
    }
    
    public String parseCount(int[] count) {
        StringBuffer countBuf = new StringBuffer();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                char c = (char) (i+'a');
                for (int j = 1; j <= count[i]; j++) {
                    countBuf.append(c);
                }
            }
        }
        return countBuf.toString();
    }
}