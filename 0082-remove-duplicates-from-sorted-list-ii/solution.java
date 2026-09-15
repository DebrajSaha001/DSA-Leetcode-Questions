/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        while(curr != null) {
            // Duplicate found
            if(curr.next != null && curr.val == curr.next.val) {
                // Move curr to the last node of this duplicate group
                while(curr.next != null && curr.val == curr.next.val)
                    curr = curr.next;

                // Removing the entire duplicate group
                prev.next = curr.next;
            } else {
                // Current value occurs only once
                prev = prev.next;
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}

