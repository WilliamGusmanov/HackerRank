import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Tester {

    HashMap<Integer, Integer> frequencyMap = new HashMap<>();

    public static void printArray(Node[] array){
        for (Node n : array){
            System.out.print(n.getFrequency() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
    /*
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        Node a = new Node("ABCD", 400);
        Node b = new Node("ABCE", 300);
        Node c = new Node("ABCF", 350);
        Node d = new Node("FFFF", 20);

        priorityQueue.add(a);
        priorityQueue.add(b);
        priorityQueue.add(c);
        priorityQueue.add(d);

        Iterator itr = priorityQueue.iterator();
//
//        while (itr.hasNext()){
//            Node x = (Node) itr.next();
//            System.out.println(x.getFrequency());
//        }
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

*/
    }
}
