class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] qFrequencies = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            qFrequencies[i] = getFrequency(queries[i]);
        }
        
        int[] wFreqOccurrences = new int[11];
        for (int i = 0; i < wFreqOccurrences.length; i++) {
            wFreqOccurrences[i] = 0;
        }
        for (int i = 0; i < words.length; i++) {
            int freq = getFrequency(words[i]);
            wFreqOccurrences[freq]++;
        }
        
        int temp = wFreqOccurrences[wFreqOccurrences.length - 1];
        wFreqOccurrences[wFreqOccurrences.length - 1] = 0;
        for (int i = wFreqOccurrences.length - 2; i >= 0; i--) {
            int temp2 = wFreqOccurrences[i];
            wFreqOccurrences[i] = temp + wFreqOccurrences[i+1];
            temp = temp2;
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = wFreqOccurrences[qFrequencies[i]];
        }
        
        return result;
    }
    
    public int getFrequency(String word) {
        int freq = 0;
        if (word == null || word.length() == 0) {
            return freq;
        }
        int len = word.length();
        char c = word.charAt(0);
        freq = 1;
        for (int i = 1; i < len; i++) {
            char c1 = word.charAt(i);
            if (c1 == c) {
                freq++;
            } else if (c1 < c) {
                c = c1;
                freq = 1;
            }
        }
        return freq;
    }
}