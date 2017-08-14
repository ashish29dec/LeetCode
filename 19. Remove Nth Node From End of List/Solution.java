/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        
        if (n == 1 && head.next == null) {
            return null;
        }
        
        ListNode prev = null;
        ListNode curr = null;
        ListNode node = head;
        
        for (int i = 1; i < n; i++) {
            node = node.next;
        }
        
        curr = head;
                
        if (node.next == null) {
            return curr.next;
        }
        
        node = node.next;
        curr = curr.next;
        prev = head;
        
        while(node.next != null) {
            node = node.next;
            curr = curr.next;
            prev = prev.next;
        }
        
        prev.next = curr.next;
        
        return head;
    }
}