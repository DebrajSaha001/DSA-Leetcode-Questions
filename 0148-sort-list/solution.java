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
    public ListNode sortList(ListNode head) {
        // Base case
        if(head == null || head.next == null)
            return head;

        // Finding the middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Splitting the list
        prev.next = null;

        // Sorting both the halves
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // Merge the halves
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while(left != null && right != null) {
            if(left.val <= right.val) {
                current.next = left;
                left = left.next;
            }

            else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        // Attaching the remaining nodes
        if(left != null)
            current.next = left;

        else
            current.next = right;

        return dummy.next;
    }
}
