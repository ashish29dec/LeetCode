/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String num1 = convertListToString(l1);
        String num2 = convertListToString(l2);
        String result = add(num1, num2);
        return convertStringToList(result);
    }
    
    public String convertListToString(ListNode l) {
        ListNode node = l;
        StringBuffer num = new StringBuffer();
        while (node != null) {
            num.append("" + node.val);
            node = node.next;
        }
        return num.toString();
    }
    
    public ListNode convertStringToList(String num) {
        int digit0 = Integer.parseInt("" + num.charAt(0));
        ListNode root = new ListNode(digit0);
        ListNode l = root;
        for (int i = 1; i < num.length(); i++) {
            int digit = Integer.parseInt("" + num.charAt(i));
            l.next = new ListNode(digit);
            l = l.next;
        }
        return root;
    }
    
    public String add(String num1,String num2) {
        if (num1 == null && num2 == null) {
            return "0";
        }
        if (num1.length() == 0 && num2.length() == 0) {
            return "0";
        }
        
        int carryover = 0;
        StringBuffer result = new StringBuffer();
        
        int len = num1.length();
        if (num2.length() > len) {
            len = num2.length();
        }
        
        for (int i = 0; i < len; i++) {
            int index = num1.length() - i - 1;
            int n1 = 0;
            if (index >= 0) {
                n1 = Integer.parseInt("" + num1.charAt(index));
            }
            
            index = num2.length() - i - 1;
            int n2 = 0;
            if (index >= 0) {
                n2 = Integer.parseInt("" + num2.charAt(index));
            }
            
            int res = n1 + n2 + carryover;
            
            result.insert(0, "" + res%10);
            carryover = res/10;
        }
        if (carryover > 0) {
            result.insert(0, "" + carryover);
        }
        return result.toString();
    }
}