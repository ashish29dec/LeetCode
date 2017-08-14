/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        ListNode result = null;
        ListNode first = null;
        ListNode second = head;
        ListNode third = head.next;
        boolean duplicatesFound = false;
        
        while(third != null) {
            if (second.val != third.val) {
                if (duplicatesFound){
                    if (first != null) {
                        first.next = third;
                    }
                } else {
                    first = second;
                    if (result == null) {
                        result = first;
                    }
                }
                duplicatesFound = false;
                second = third;
                third = third.next;
            } else {
                third = third.next;
                duplicatesFound = true;
            }
        }
        
        if (duplicatesFound && first != null) {
            first.next = null;
        } else if (!duplicatesFound && second != null && first == null) {
            result = second;
        }
        
        return result;
    }
}