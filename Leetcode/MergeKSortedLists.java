
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //Complexity: always have a head and curr
        // Time:
        // PQ insertion: log(n)
        // PQ removal: log(n)
        //  OVERALL we perform insertions and deletions N times, therefore, we have nlog(n)
        // Space: O(n), we store N nodes in the priority queue.

        ListNode head = new ListNode(); //represents the answers list
        ListNode curr = head;//initialize the iterators
        //find the minimum node
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b){
                Integer aInt = a.val;
                Integer bInt = b.val;
                return aInt.compareTo(bInt);
            }
        });

        //load the priority queue
        //O(N)
        for (ListNode node : lists){
            if (node != null)
                pq.add(node);
        }

        //take advantage of heapify: O()
        //we pop one node at a time and add the next node back in.
        while (pq.size() != 0){
            ListNode poppedNode = pq.poll();
            curr.next = poppedNode;
            curr = curr.next;
            if (poppedNode.next != null){
                pq.add(poppedNode.next);
            }
        }
        return head.next;
    }
}