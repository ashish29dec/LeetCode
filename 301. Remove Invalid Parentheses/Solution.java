class Solution {
    
    class Sub {
        String str;
        int numLeftIgnored;
        int numRightIgnored;
        int sum;
        int indexInOrigStr;
        
        Sub(String s, int left, int right, int sum, int index) {
            str = s;
            numLeftIgnored = left;
            numRightIgnored = right;
            this.sum = sum;
            indexInOrigStr = index;
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            List<String> output = new ArrayList<>();
            output.add("");
            return output;
        }
        
        int invalidLeft = 0;
        int invalidRight = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                invalidLeft++;
            } else if (c == ')' && invalidLeft == 0) {
                invalidRight++;
            } else if (c == ')') {
                invalidLeft--;
            }
        }
        
        List<Sub> queue = new ArrayList<>();
        queue.add(new Sub("", 0, 0, 0, 0));
        int curIndex = -1;
        HashSet<String> strings = new HashSet<>();
        
        while(queue.size() > 0) {
            Sub item = queue.remove(0);
            if (item.indexInOrigStr > curIndex) {
                strings = new HashSet<>();
                curIndex = item.indexInOrigStr;
                if (curIndex == s.length()) {
                    queue.add(item);
                    break;
                }
            }
            char c = s.charAt(curIndex);
            switch(c) {
                case '(':
                    if(!strings.contains(item.str + "(")) {
                        Sub s1 = new Sub(item.str + "(", item.numLeftIgnored, item.numRightIgnored, item.sum+1, item.indexInOrigStr+1);
                        queue.add(s1);
                        strings.add(item.str+"(");
                    }
                    if (invalidLeft > 0 && item.numLeftIgnored < invalidLeft) {
                        if (!strings.contains(item.str)) {
                            Sub s2 = new Sub(item.str, item.numLeftIgnored+1, item.numRightIgnored, item.sum, item.indexInOrigStr+1);
                            queue.add(s2);
                            strings.add(item.str);
                        }
                    }
                    break;
                case ')':
                    String str1 = item.str + ")";
                    int sum = item.sum - 1;
                    if (sum >= 0) {
                        if(!strings.contains(str1)) {
                            Sub s1 = new Sub(str1, item.numLeftIgnored, item.numRightIgnored, sum, item.indexInOrigStr+1);
                            queue.add(s1);
                            strings.add(str1);
                        }
                    }
                    if (invalidRight > 0 && item.numRightIgnored < invalidRight) {
                        if (!strings.contains(item.str)) {
                            Sub s2 = new Sub(item.str, item.numLeftIgnored, item.numRightIgnored+1, item.sum, item.indexInOrigStr+1);
                            queue.add(s2);
                            strings.add(item.str);
                        }
                    }
                    break;
                default:
                    String str2 = item.str + c;
                    if (!strings.contains(str2)) {
                        Sub s1 = new Sub(str2, item.numLeftIgnored, item.numRightIgnored, item.sum, item.indexInOrigStr+1);
                        queue.add(s1);
                        strings.add(str2);
                    }
                    break;
                    
            }
        }
        
        List<String> output = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            Sub sub = queue.get(i);
            if (sub.sum == 0) {
                output.add(sub.str);
            }
        }
        
        return output;
    }
}