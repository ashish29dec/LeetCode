public class Solution {
    public String[] findWords(String[] words) {
        
        if(words == null || words.length == 0) {
            return words;
        }
        
        HashSet<Character> row1 = new HashSet<>();
        row1.add('q');
        row1.add('Q');
        row1.add('w');
        row1.add('W');
        row1.add('e');
        row1.add('E');
        row1.add('r');
        row1.add('R');
        row1.add('t');
        row1.add('T');
        row1.add('y');
        row1.add('Y');
        row1.add('u');
        row1.add('U');
        row1.add('i');
        row1.add('I');
        row1.add('o');
        row1.add('O');
        row1.add('p');
        row1.add('P');

        HashSet<Character> row2 = new HashSet<>();
        row2.add('a');
        row2.add('A');
        row2.add('s');
        row2.add('S');
        row2.add('d');
        row2.add('D');
        row2.add('f');
        row2.add('F');
        row2.add('g');
        row2.add('G');
        row2.add('h');
        row2.add('H');
        row2.add('j');
        row2.add('J');
        row2.add('k');
        row2.add('K');
        row2.add('l');
        row2.add('L');

        HashSet<Character> row3 = new HashSet<>();
        row3.add('z');
        row3.add('Z');
        row3.add('x');
        row3.add('X');
        row3.add('c');
        row3.add('C');
        row3.add('v');
        row3.add('V');
        row3.add('b');
        row3.add('B');
        row3.add('n');
        row3.add('N');
        row3.add('m');
        row3.add('M');
        
        ArrayList<String> result = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char first = word.charAt(0);
            HashSet<Character> set = null;
            if (row1.contains(first)) {
                set = row1;
            } else if (row2.contains(first)) {
                set = row2;
            } else {
                set = row3;
            }
            int wordLen = word.length();
            int j = 1;
            for (; j < wordLen; j++) {
                char c = word.charAt(j);
                if (!set.contains(c)) {
                    break;
                }
            }
            
            if (j == wordLen) {
                result.add(word);
            }
        }
        
        return result.toArray(new String[result.size()]);
    }
}