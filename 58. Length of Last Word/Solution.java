public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int index = s.length() - 1;
        int length = 0;
        boolean isCharFound = false;
        
        while (index >= 0) {
            char c = s.charAt(index);
            if (!isCharFound && c == ' ') {
                index--;
                continue;
            } else if (isCharFound && c == ' ') {
                break;
            } else if (!isCharFound && c != ' ') {
                isCharFound = true;
            }
            index--;
            length++;
        }
        
        return length;
    }
}