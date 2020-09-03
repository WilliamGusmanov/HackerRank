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
    
    //same idea as addition with carry 
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        int carry = 0; 
        ListNode curr = head;
        
        //base operation
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + carry;
            curr.next = new ListNode(sum % 10);
            carry = sum/10;
            if (l1.next != null) l1 = l1.next;
            else l1 = null; 
            if (l2.next != null) l2 = l2.next;
            else l2 = null;
            curr = curr.next; 
        }
        //if one or the other list has finished
        ListNode remaining = l1 != null ? l1 : l2 != null ? l2 : null;
        while (remaining != null){
            int sum = remaining.val + carry;
            curr.next = new ListNode(sum % 10);
            carry = sum/10; 
            remaining = remaining.next; 
            curr = curr.next; 
        }
        if (carry != 0){
            curr.next = new ListNode(carry);
        }
        return head.next; 
    }
}
