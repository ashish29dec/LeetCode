/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        
        if (head == null) {
            return;
        }
        
        if (head.next == null) {
            return;
        }
        
        if (head.next.next == null) {
            return;
        }
        
        // Count number of nodes
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }
        
        // Reverse the list
        // Starting point of reversal will be count/2 + 1 when counted from 0 (as in index)
        int mid = count/2;
        node = head;
        for (int i = 0; i < mid; i++) {
            node = node.next;
        }
        // The very next node onwards the list will be reversed
        ListNode next = node.next;
        node.next = null;
        while (next != null) {
            ListNode second = next.next;
            next.next = node;
            node = next;
            next = second;
        }
        
        // Now node points to the beginning of reversed list
        ListNode forward = head;
        ListNode backward = node.next;
        while (forward != null && backward != null) {
            node.next = forward.next;
            forward.next = node;
            forward = node.next;
            node = backward;
            backward = node.next;
        }
    }
}