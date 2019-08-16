class Solution {
    public String originalDigits(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int[] occurrences = new int[26];
        for (int j = 0; j < 26; j++) {
            occurrences[j] = 0;
        }
        
        int length = s.length();
        
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            occurrences[c - 'a']++;
        }
        
        int[] numbers = new int[10];
        for (int k = 0; k < 10; k++) {
            numbers[k] = 0;
        }
        
        int n = 0;

        // Checking zero
        if (occurrences['z' - 'a'] > 0) {
            n = occurrences['z' - 'a'];
            numbers[0] = n;
            occurrences['z' - 'a'] -= n;
            occurrences['e' - 'a'] -= n;
            occurrences['r' - 'a'] -= n;
            occurrences['o' - 'a'] -= n;
        }

        // Checking two
        if (occurrences['w' - 'a'] > 0) {
            n = occurrences['w' - 'a'];
            numbers[2] = n;
            occurrences['w' - 'a'] -= n;
            occurrences['t' - 'a'] -= n;
            occurrences['o' - 'a'] -= n;
        }
        
        // Checking four
        if (occurrences['u' - 'a'] > 0) {
            n = occurrences['u' - 'a'];
            numbers[4] = n;
            occurrences['u' - 'a'] -= n;
            occurrences['f' - 'a'] -= n;
            occurrences['o' - 'a'] -= n;
            occurrences['r' - 'a'] -= n;
        }
        
        // Checking six
        if (occurrences['x' - 'a'] > 0) {
            n = occurrences['x' - 'a'];
            numbers[6] = n;
            occurrences['s' - 'a'] -= n;
            occurrences['i' - 'a'] -= n;
            occurrences['x' - 'a'] -= n;
        }
        
        // Checking eight
        if (occurrences['g' - 'a'] > 0) {
            n = occurrences['g' - 'a'];
            numbers[8] = n;
            occurrences['e' - 'a'] -= n;
            occurrences['i' - 'a'] -= n;
            occurrences['g' - 'a'] -= n;
            occurrences['h' - 'a'] -= n;
            occurrences['t' - 'a'] -= n;
        }
        
        // Checking three
        if (occurrences['r' - 'a'] > 0) {
            n = occurrences['r' - 'a'];
            numbers[3] = n;
            occurrences['e' - 'a'] -= (2*n);
            occurrences['t' - 'a'] -= n;
            occurrences['h' - 'a'] -= n;
            occurrences['r' - 'a'] -= n;
        }
        
        // Checking one
        if (occurrences['o' - 'a'] > 0) {
            n = occurrences['o' - 'a'];
            numbers[1] = n;
            occurrences['o' - 'a'] -= n;
            occurrences['e' - 'a'] -= n;
            occurrences['n' - 'a'] -= n;
        }
        
        // Checking five
        if (occurrences['f' - 'a'] > 0) {
            n = occurrences['f' - 'a'];
            numbers[5] = n;
            occurrences['f' - 'a'] -= n;
            occurrences['e' - 'a'] -= n;
            occurrences['i' - 'a'] -= n;
            occurrences['v' - 'a'] -= n;
        }

        // Checking nine
        if (occurrences['i' - 'a'] > 0) {
            n = occurrences['i' - 'a'];
            numbers[9] = n;
            occurrences['n' - 'a'] -= (2*n);
            occurrences['e' - 'a'] -= n;
            occurrences['i' - 'a'] -= n;
        }

        // Checking seven
        if (occurrences['v' - 'a'] > 0) {
            n = occurrences['v' - 'a'];
            numbers[7] = n;
            occurrences['s' - 'a'] -= n;
            occurrences['e' - 'a'] -= (2*n);
            occurrences['n' - 'a'] -= n;
            occurrences['v' - 'a'] -= n;
        }
        
        StringBuffer sb = new StringBuffer();
        
        for (int l = 0; l < 10; l++) {
            if (numbers[l] > 0) {
                for (int m = 1; m <= numbers[l]; m++) {
                    sb.append(""+l);
                }
            }
        }
        
        return sb.toString();
    }
}