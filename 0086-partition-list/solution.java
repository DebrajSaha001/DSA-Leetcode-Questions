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
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greater = greaterDummy;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = null;

            if(curr.val < x) {
                less.next = curr;
                less = less.next;
            }

            else {
                greater.next = curr;
                greater = greater.next;
            }

            curr = next;
        }

        // Connecting the 2 partitions
        less.next = greaterDummy.next;
        return lessDummy.next;
    }
}

