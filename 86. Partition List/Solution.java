/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        
        ListNode node = head;
        ListNode prev = null;
        
        ListNode minHead = null;
        
        boolean isBigFound = false;
        
        while (node != null) {
            if (node.val < x) {
                if (isBigFound) {
                    prev.next = node.next;
                    if (minHead == null) {
                        node.next = head;
                        head = node;
                    } else {
                        node.next = minHead.next;
                        minHead.next = node;
                    }
                    minHead = node;
                    node = prev.next;
                    continue;
                }
            } else {
                if (prev != null && !isBigFound) {
                    minHead = prev;
                }
                isBigFound = true;
            }
            prev = node;
            node = node.next;
        }
        
        return head;
    }
}