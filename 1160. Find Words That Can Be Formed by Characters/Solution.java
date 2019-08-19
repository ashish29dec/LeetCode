class Solution {
    public int countCharacters(String[] words, String chars) {
        if (words == null || words.length == 0) {
            return 0;
        }
        
        if (chars == null || chars.length() == 0) {
            return 0;
        }
        
        int length = 0;
        int charLength = chars.length();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            HashMap<Character, Integer> charMap = new HashMap<>();
            for (int j = 0; j < charLength; j++) {
                char c = chars.charAt(j);
                Integer occur = (Integer) charMap.get(c);
                if (occur == null) {
                    charMap.put(c, 1);
                } else {
                    charMap.put(c, occur + 1);
                }
            }
            
            int k = 0;
            int wordLen = word.length();
            for (; k < wordLen; k++) {
                char c = word.charAt(k);
                Integer occur = charMap.get(c);
                if (occur == null || occur == 0) {
                    break;
                }
                charMap.put(c, occur - 1);
            }
            
            if (k == wordLen) {
                length += wordLen;
            }
        }
        
        return length;
    }
}