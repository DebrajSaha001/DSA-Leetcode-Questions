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
    public ListNode swapPairs(ListNode head) {
        // Dummy node helps to handle swapping the first pair
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Need atleast two nodes to swap
        while(prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Swap the nodes
            // Connecting the first to the rest of the list
            first.next = second.next;

            // Putting the second before first
            second.next = first;

            // Connecting the previous part to the second
            prev.next = second;

            // Move prev to the end of the swapped pair
            prev = first;
        }
        return dummy.next;
    }
}
