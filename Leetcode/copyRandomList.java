/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
/*
We need to go through the linked list once, create a hashmap of an index to the 'copied' node
then iterate through the linked list again, and connect 
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node newHead = new Node(0); 
        Node oldCurr = head;
        Node newCurr = newHead; 
        
        HashMap<Node, Node> map = new HashMap<>();    
        //make copies with an index to them 
        while (oldCurr != null){
            Node temp = new Node(oldCurr.val); //make a clone of the node
            map.put(oldCurr, temp); //maybe we want a map to the old nodes 
            oldCurr = oldCurr.next;
        }
        //System.out.println(map);
        //the new nodes don't have connections.
        //the old nodes do
        //must iterate the old connections
        oldCurr = head;
        newHead.next = map.get(oldCurr); //set up the head 
        
        while (oldCurr != null){
            Node newNode = map.get(oldCurr);
            newNode.random = map.get(oldCurr.random);
            newNode.next = map.get(oldCurr.next);
            newNode = newNode.next;
            oldCurr = oldCurr.next;
        }
        
        return newHead.next; 
    }
}
