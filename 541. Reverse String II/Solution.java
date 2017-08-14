public class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = k - 1;
        if (end >= chars.length) {
            end = chars.length - 1;
        }
        while (start < chars.length) {
            int endIndex = end;
            while (start < end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
                start++;
                end--;
            }
            start = endIndex + k + 1;
            end = start + k - 1;
            if (end >= chars.length) {
                end = chars.length - 1;
            }
        }
        return new String(chars);
    }
}