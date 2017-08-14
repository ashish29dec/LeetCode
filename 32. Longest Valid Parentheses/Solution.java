public class Solution {
    
    public int combineGoodSequence(ArrayList<String> stack) {
        if (stack.size() != 1) {
            String secondLast = stack.get(stack.size() - 2);
            if (!secondLast.equals("(") && !secondLast.equals(")")) {
                int num1 = Integer.parseInt(stack.remove(stack.size() - 1));
                num1 += Integer.parseInt(stack.remove(stack.size() - 1));
                stack.add("" + num1);
            }
        }
        return Integer.parseInt(stack.get(stack.size() - 1));
    }
    
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean of = false;
        boolean cf = false;
        int ofIndex = 0;
        for (int j = 0; j < len; j++) {
            char c = s.charAt(j);
            if (of && c == ')') {
                cf = true;
            } else if (!of && c == '(') {
                of = true;
                ofIndex = j;
            }
            if (of && cf) {
                break;
            }
        }
        
        if (!of || !cf) {
            return 0;
        }
        ArrayList<String> stack = new ArrayList<>();
        int max = 0;
        for (int i = ofIndex; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add("" + c);
            } else {
                if (stack.size() == 0) {
                    stack.add(")");
                    continue;
                }
                String atTop = stack.get(stack.size() - 1);
                if (atTop.equals(")")) {
                    stack.add(")");
                    continue;
                }
                if (atTop.equals("(")) {
                    stack.remove(stack.size() - 1);
                    stack.add("2");
                    
                    int s1 = combineGoodSequence(stack);
                    if (s1 > max) {
                        max = s1;
                    }
                } else {
                    if (stack.size() == 1) {
                        stack.add(")");
                        continue;
                    }
                    String secondLast = stack.get(stack.size() - 2);
                    if (secondLast.equals(")")) {
                        stack.add(")");
                    } else {
                        int num = Integer.parseInt(stack.remove(stack.size() - 1));
                        stack.remove(stack.size() - 1);
                        num += 2;
                        stack.add("" + num);

                        int s2 = combineGoodSequence(stack);
                        if (s2 > max) {
                            max = s2;
                        }
                    }
                }
            }
        }
        
        return max;
    }
}