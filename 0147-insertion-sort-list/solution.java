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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow;
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;

        left = insertionSortList(left);
        right = insertionSortList(right);

        ListNode curr;
        if (left.val < right.val) {
            curr = left;
            left = left.next;
        } 
        
        else {
            curr = right;
            right = right.next;
        }
        
        ListNode tmp = curr;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tmp.next = left;
                left = left.next;
            }
            
            else {
                tmp.next = right;
                right = right.next;
            }

            tmp = tmp.next;
        }

        if (left != null)
            tmp.next = left;

        else
            tmp.next = right;

        return curr;
    }
}
