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
    public ListNode rotateRight(ListNode head, int k) {
        // List is empty
        if(head == null || head.next == null || k == 0)
            return head;

        // Finding the length and tail
        int len = 1;
        ListNode tail = head;

        while(tail.next != null) {
            tail = tail.next;
            len++;
        }

        // Reducing unnecessary rotations
        k = k % len;

        // If no rotation is required
        if(k == 0)
            return head;

        // Making the list circular
        tail.next = head;

        // Finding the new tail
        int steps = len - k - 1;
        ListNode newTail = head;

        for(int i = 0; i < steps; i++)
            newTail = newTail.next;

        // New head is after new tail
        ListNode newHead = newTail.next;

        // Breaking the circle
        newTail.next = null;

        return newHead;
    }
}

