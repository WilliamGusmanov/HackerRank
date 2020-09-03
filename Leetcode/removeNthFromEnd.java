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
// store the Node that is n + 1 nodes behind the current node if possible. then Once curr reaches the end, we cut the n node. 

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null){
            return null; 
        }
        
        //find the 'curr' node
        ListNode curr = head;
        
        for (int i = 0; i < n; i++){
                curr = curr.next; 
        }
        ListNode nthplusOneBack = head; 
        
        //until the curr.next is null
        if (curr != null){
            while (curr.next != null){
                curr = curr.next;
                nthplusOneBack = nthplusOneBack.next; 
            }

            //cut the nth node
            nthplusOneBack.next = nthplusOneBack.next.next; 
        }
        else {
            head = head.next;
        }
        return head; 
    }
}
