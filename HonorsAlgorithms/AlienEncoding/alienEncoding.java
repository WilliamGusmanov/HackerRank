import java.io.*;
import java.util.*;

public class alienEncoding {
    HashMap<Integer, Integer> frequencyMap = new HashMap<>();
    int min = Integer.MAX_VALUE;

    /*
       Rather than using a binary tree, we use a d-ary tree.
       Otherwise, the formation of the code is exactly as before:
       continue combining character blocks with the smallest frequency,
       but rather than combining the 2 smallest frequencies, combine the d
       smallest frequencies.
     */

    public int stringToNumber(String x){
        String[] y = x.substring(5).split("-");
        return y.length;
    }

 public int DFS(Node root, BufferedWriter writer, String digits) throws IOException {
        int result = 0;
        if (root.arraySize == 0){ //root.nodeArray is all it's children
            String x = root.getLetter() + " " + digits;
            writer.write(x);
            writer.newLine();
            return 0;
        }
        else {
            for (int i = 0; i < root.arraySize; i++){
                String dash = "";
                //if it's not a leaf.
                if (digits != ""){
                    dash = "-";
                }
                int temp = DFS(root.array[i], writer, digits + dash + i);
                if (temp > result){
                    result = temp;
                }
            }
        }
        return result + 1; //to count the depth of the tree.
    }

    public PriorityQueue<Node> processLetters(BufferedReader br, int k) throws IOException {
        String line;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        while ((line = br.readLine()) != null) {
            String [] lineComponents = line.split(" "); //the first component is the letter, 2nd is the frequency
            int frequency = Integer.parseInt(lineComponents[1]);
//            frequencyMap.computeIfPresent(frequency, (key, value) -> value + 1);
//            frequencyMap.putIfAbsent(frequency, 1);
            if (frequency < min) {
                min = frequency;
            }
            priorityQueue.add((new Node(lineComponents[0], frequency, k))); //0: letter, 1: frequency
        }
        return priorityQueue;
    }

    /**
     * takes the priority queue as input
     * and forms a n-ary tree that contains at most k children at each node.
     * This function returns the root node of that tree.
     * @param k
     * @param nodePriorityQueue
     * @return
     */
    public Node encodeLanguage(int k, PriorityQueue<Node> nodePriorityQueue){
        while (nodePriorityQueue.size() > 1){
            //take k-smallest frequencies and add them to the tree
            Node parentNode = new Node(k); //create a parent
            int i = 0;
            for (; i < k && nodePriorityQueue.size() != 0; i++){
                Node currentNode = nodePriorityQueue.poll(); //i'th highest node
                parentNode.addNode(currentNode); //add child to parent
                parentNode.addToFrequency(currentNode.getFrequency());
            }
            nodePriorityQueue.add(parentNode);
        }
        return nodePriorityQueue.poll(); //
    }

    public int processK(BufferedReader br) throws IOException {
        int k = Integer.parseInt(br.readLine());
        return k;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("calculating...");
        long startTime = System.currentTimeMillis();

        alienEncoding a = new alienEncoding();
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int k = a.processK(br);
        PriorityQueue<Node> nodePriorityQueue = a.processLetters(br, k);

        Node root = a.encodeLanguage(k, nodePriorityQueue);

        File outputFile = new File("output.txt");
        FileOutputStream outputFileStream = new FileOutputStream(outputFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));
        //System.out.println("max depth: " + a.DFS(root, writer, ""));
        a.DFS(root, writer, "");
        long endTime = System.currentTimeMillis();
        System.out.println("milliseconds: " + (endTime - startTime));
        System.out.println("total seconds: " + ((endTime - startTime)/1000));
        writer.close();
    }
}