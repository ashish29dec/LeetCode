/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        boolean found = false;
        
        while(true) {
            if (fast == null || fast.next == null) {
                break;
            }
            
            fast = fast.next.next;
            slow = slow.next;
            
            if (fast == slow) {
                found = true;
                break;
            }
        }
        
        return found;
    }
}