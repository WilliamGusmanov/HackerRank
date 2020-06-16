import java.util.ArrayList;
import java.util.Arrays;

public class Node implements Comparable<Node> {

    Node[] array;
    String letter = null; //if it is a leaf, this will have a value
    int frequency = 0; //the count, this will be used to sort.
    int arraySize = 0;

    Node(String letter, int frequency, int k){
        this.letter = letter;
        this.frequency = frequency;
        array = new Node[k];
    }

    public Node(int k) {
        array = new Node[k];
    }

    void addNode(Node node){
       array[arraySize] = node;
       arraySize++;
    }


    public int getFrequency() {
        return frequency;
    }

    public String getLetter() {
        return letter;
    }

    public void addToFrequency(int value){
        this.frequency += value;
    }
    /**
     * to be used for sorting by frequency.
     * @param otherNode
     * @return
     */
    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(this.frequency, otherNode.frequency);
    }

    @Override
    public String toString() {
        return this.getLetter() + " " + this.getFrequency();
    }
}
