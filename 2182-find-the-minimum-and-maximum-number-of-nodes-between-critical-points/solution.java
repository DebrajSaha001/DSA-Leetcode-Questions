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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int last = -1;
        int minDist = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int pos = 1; // 0-based position

        while (curr != null && curr.next != null) {
            // Check whether the curr is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                // First critical point
                if (first == -1)
                    first = pos;

                else
                    // Distance from the previous critical point
                    minDist = Math.min(minDist, pos - last);

                // Updating the previous critical point
                last = pos;
            }

            // Moving the pointers forward
            prev = curr;
            curr = curr.next;
            pos++;
        }

        // Fewer than 2 critical points
        if (first == -1 || first == last)
            return new int[]{-1, -1};

        return new int[]{minDist, last - first};
    }
}
