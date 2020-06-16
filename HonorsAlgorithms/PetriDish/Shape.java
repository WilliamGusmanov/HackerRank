import java.util.ArrayList;

public class Shape {
    /**
     * will hold coordinates starting from 0.
     * will require finding max depth and max width.
     */
    int [][] normalizedList;
    /**
     * will hold true coordinates as found in petri dish.
     */
    ArrayList<ArrayList<Integer>> unnormalizedList;

    int numberOfNodes = 0;
    int matrixTop;
    int matrixBottom;
    int matrixLeft;
    int matrixRight;

    public ArrayList<ArrayList<Integer>> initializeUNList(int widthSize,int heightSize){
        unnormalizedList = new ArrayList<ArrayList<Integer>>(heightSize);
        for (int i = 0; i < heightSize; i++) {
            unnormalizedList.add(new ArrayList<Integer>(widthSize));
            for (int j = 0; j < widthSize; j++){
                unnormalizedList.get(i).add(0);
            }
        }
        return unnormalizedList;
    }
    public void initializeCoordinates(int y, int x){
        matrixTop = y;
        matrixBottom = y;
        matrixLeft = x;
        matrixRight = x;
    }
    public int getMatrixTop() {
        return matrixTop;
    }

    public int getMatrixBottom() {
        return matrixBottom;
    }

    public int getMatrixLeft() {
        return matrixLeft;
    }

    public int getMatrixRight() {
        return matrixRight;
    }
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public void setMatrixTop(int matrixTop) {
        this.matrixTop = matrixTop;
    }

    public void setMatrixBottom(int matrixBottom) {
        this.matrixBottom = matrixBottom;
    }

    public void setMatrixLeft(int matrixLeft) {
        this.matrixLeft = matrixLeft;
    }

    public void setMatrixRight(int matrixRight) {
        this.matrixRight = matrixRight;
    }

    @Override
    public String toString() {
        return "Top: " + getMatrixTop() + " Left: " + getMatrixLeft() + " Bottom: " + getMatrixBottom() + " Right: " + getMatrixRight();
    }
}
