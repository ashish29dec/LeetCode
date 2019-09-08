class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }
        
        HashMap<Character, Integer> numTimes = new HashMap<>();
        int length = p.length();
        
        for (int i = 0; i < length; i++) {
            char c = p.charAt(i);
            Integer num = numTimes.get(c);
            if (num == null) {
                num = 0;
            }
            num++;
            numTimes.put(c, num);
        }
        
        int[] occur = new int[26];
        for (int i = 0; i < occur.length; i++) {
            occur[i] = 0;
        }
        
        int slen = s.length();
        int charsMatched = 0;
        int matchedIndex = -1;
        
        for (int i = 0; i < slen; i++) {
            char c = s.charAt(i);
            Integer numTime = numTimes.get(c);
            if (numTime == null) {
                // Unidentified character. Reset the occur array
                resetOccurArray(occur);
                charsMatched = 0;
                matchedIndex = -1;
                continue;
            }
            
            if (charsMatched != length) {
                int numOccur = occur[c - 'a'];
                if (numOccur < numTime) {
                    if (matchedIndex == -1) {
                        matchedIndex = i;
                    }
                    occur[c - 'a']++;
                    charsMatched++;
                    if (charsMatched == length) {
                        result.add(matchedIndex);
                    }
                } else {
                    for (int j = matchedIndex; j < i; j++) {
                        char d = s.charAt(j);
                        if (d == c) {
                            matchedIndex = j+1;
                            break;
                        }
                        charsMatched--;
                        occur[d - 'a']--;
                    }
                }
            } else {
                int leavingIndex = i - length;
                char l = s.charAt(leavingIndex);
                if (l == c) {
                    matchedIndex = leavingIndex + 1;
                    result.add(matchedIndex);
                } else {
                    occur[l - 'a']--;
                    charsMatched--;
                    matchedIndex = leavingIndex + 1;
                    if (occur[c - 'a'] == numTime) {
                        for (int j = matchedIndex; j < i; j++) {
                            char d = s.charAt(j);
                            if (d == c) {
                                matchedIndex = j+1;
                                break;
                            }
                            charsMatched--;
                            occur[d - 'a']--;
                        }                        
                    }
                }
            }
        }
        
        return result;
    }
    
    private void resetOccurArray(int[] occur) {
        for (int i = 0; i < occur.length; i++) {
            occur[i] = 0;
        }
    }
}