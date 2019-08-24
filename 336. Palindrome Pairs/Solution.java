class Solution {
    class Node {
        HashMap<Character, Node> children = new HashMap<>();
        HashSet<Integer> palinIndexes = new HashSet<>();
        int index = -1;
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length < 2) {
            return null;
        }
        
        Node root = new Node();
        
        int length = words.length;
        // Enter all words in reverse order in trie
        for (int i = 0; i < length; i++) {
            String word = words[i];
            if (isPalindrome(word, 0, word.length() - 1)) {
                root.palinIndexes.add(i);
            }
            populateTrie(word, word.length() - 1, i, root);
        }
        
        // Parse through each word in the array and find if any palindrome exists
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String word = words[i];
            findPalindrome(word, 0, i, root, result);
        }
        
        return result;
    }
    
    private void populateTrie(String word, int index, int indexInArr, Node node) {
        HashMap<Character, Node> children = node.children;
        if (index == -1) {
            Node underscore = new Node();
            underscore.index = indexInArr;
            children.put('_', underscore);
            return;
        }
        char c = word.charAt(index);
        Node child = children.get(c);
        if (child == null) {
            child = new Node();
            children.put(c, child);
        }
        // Check if there is any palindrome from next character onwards
        if (isPalindrome(word, 0, index-1)) {
            child.palinIndexes.add(indexInArr);
        }
        
        populateTrie(word, index-1, indexInArr, child);
    }
    
    private boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    private void findPalindrome(String word, int index, int indexInArr, Node node, List<List<Integer>> result) {
        if (index == word.length()) {
            Iterator it = node.palinIndexes.iterator();
            while(it.hasNext()) {
                int ind = (Integer) it.next();
                if (ind != indexInArr) {
                    List<Integer> list = new ArrayList<>();
                    list.add(indexInArr);
                    list.add(ind);
                    result.add(list);
                }
            }
            return;
        }
        
        char c = word.charAt(index);
        HashMap<Character, Node> children = node.children;
        Node eow = children.get('_');
        if (eow != null) {
            if (isPalindrome(word, index, word.length() - 1)) {
                if (indexInArr != eow.index) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(indexInArr);
                    pair.add(eow.index);
                    result.add(pair);
                }
            }            
        }
        Node next = children.get(c);
        if (next == null) {
            // We did not find any match.
            // Either the word in trie ended or there is no such string that can be a palindrom to this one
            // Check if word in trie reached its end
            if (eow == null) {
                // The string in trie does not match the word
                return;
            }
            return;
        }
        findPalindrome(word, index+1, indexInArr, next, result);
    }
}