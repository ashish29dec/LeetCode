/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
class NestedElement {
    NestedInteger nested;
    char c;
    
    NestedElement(char c) {
        this.c = c;
        nested = null;
    }
    
    NestedElement(NestedInteger nested) {
        this.nested = nested;
        c = 0;
    }
}

public class Solution {
    
    private boolean isOpeningBracket(NestedElement d) {
        if (d.nested == null && d.c == '[') {
            return true;
        }
        return false;
    }
    
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        
        String number ="";
        NestedElement[] stack = new NestedElement[s.length()];
        int index = -1;
        int length = s.length();
        
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack[++index] = new NestedElement(c);
            } else if (c == ',') {
                if (number.length() != 0) {
                    stack[++index] = new NestedElement(new NestedInteger(Integer.parseInt(number)));
                    number = "";
                }
            } else if (c == ']') {
                if (number.length() != 0) {
                    int num = 0;
                    try {
                        num = Integer.parseInt(number);
                    } catch (NumberFormatException nfe) {
                        
                    }
                    stack[++index] = new NestedElement(new NestedInteger(num));
                    number = "";
                }
                NestedInteger n = new NestedInteger();
                NestedElement d = stack[index--];
                while(!isOpeningBracket(d)) {
                    n.add(d.nested);
                    d = stack[index--];
                }
                stack[++index] = new NestedElement(n);
                
                n = stack[index--].nested;
                NestedInteger a = new NestedInteger();
                if (!n.isInteger()) {
                    List<NestedInteger> list = n.getList();
                    int len = list.size();
                    for (int k = len - 1; k >= 0; k--) {
                        a.add(list.get(k));
                    }
                } else {
                    a = n;
                }
                stack[++index] = new NestedElement(a);
            } else {
                number += c;
            }
        }
        
        if (number.length() != 0) {
            int num = 0;
            try {
                num = Integer.parseInt(number);
            } catch (NumberFormatException nfe) {
                
            }
            stack[++index] = new NestedElement(new NestedInteger(num));
            number = "";
        }
        
        if (index != 0) {
            return null;
        }
        return stack[index].nested;
    }
}