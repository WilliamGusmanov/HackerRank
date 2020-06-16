import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;




public class main {


    // Dynamic algorithm for 1D array can be used to reduce the time complexity to O(n^3).
    // The idea is to fix the left and right columns one by one and find the maximum sum contiguous
    // rows for every left and right column pair. We find top and bottom row numbers (which have maximum sum)
    // for every fixed left and right column pair. To find the top and bottom row numbers,
    // calculate sum of elements in every row from left to right and store these sums in an array say temp[].
    // So temp[i] indicates sum of elements from left to right in row i.
    // If we apply Kadane’s 1D algorithm on temp[], and get the maximum sum subarray of temp,
    // this maximum sum would be the maximum possible sum with left and right as boundary columns.
    // To get the overall maximum sum, we compare this sum with the maximum sum so far.


    // Function to find maximum sum rectangular
    // submatrix
    private int maxSumRectangle(int [][] mat) {
        int m = mat.length;
        int preSum[][] = new int[m+1][m];
        int rStart = 0, rEnd = 0, cStart = 0, cEnd = 0;
        int negRow = 0, negCol = 0;
        int minSum = Integer.MIN_VALUE;
        int maxSum = -1;

        //To reduce redundant summations. all total sums are calculated.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                preSum[i+1][j] = preSum[i][j] + mat[i][j];
            }
        }
        System.out.println("regular matrix");
        displayMatrix(mat);

        System.out.println("pre summed matrix: ");
        displayMatrix(preSum);

        for(int rowStart = 0; rowStart < m; rowStart++) {
            for(int row = rowStart; row < m; row++){ //

                int sum = 0;
                int curColStart = 0;


                for(int col = 0; col < m; col++) {
                    sum += preSum[row+1][col] - preSum[rowStart][col];

                    //don't get it. Possibly kudanes.
                    if(sum < 0) {
                        if(minSum < sum) {
                            minSum = sum;
                            negRow = row;
                            negCol = col;
                        }
                        sum = 0;
                        curColStart = col+1;
                    }
                    else if(maxSum < sum) {
                        maxSum = sum;
                        rStart = rowStart;
                        rEnd = row;
                        cStart = curColStart;
                        cEnd = col;
                    }
                }
            }
        }

        // Printing final values
        if(maxSum == -1) {
            System.out.println("from row - " + negRow +
                    " to row - " + negRow);
            System.out.println("from col - " + negCol +
                    " to col - " + negCol);
        }
        else {
            System.out.println("from row - " + rStart + " to row - " + rEnd);
            System.out.println("from col - " + cStart + " to col - " + cEnd);
        }
        return maxSum == -1 ? minSum : maxSum;
    }


    public void displayFinalMatrix(ArrayList<ArrayList<Node>> theDish) {
        System.out.println();
        for (int rows = 0; rows < theDish.size(); rows++) {
            for (int cols = 0; cols < theDish.get(rows).size(); cols++) {
                Node node = theDish.get(rows).get(cols);
                if (node != null) {
                    System.out.print(node.getLetter());
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    public void displayList(ArrayList<ArrayList<Node>> lists) {
        System.out.println("displaying Node matrix");
        //display column values
        System.out.print("  ");
        for (int a = 0; a < lists.get(0).size();a++){
            if (a < 10) {
                System.out.print(a + "  ");
            }
            else {
                System.out.print(a + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < lists.size(); i++) {
            System.out.print(i);
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (lists.get(i).get(j) != null) {
                    Node temp = lists.get(i).get(j);
                    //System.out.print(temp.getX() + ",");
                    //System.out.print(temp.getY() + " ");
                    System.out.print(" * ");
                }
                else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    public void displayUNShape(Shape shape) {
        System.out.println("Displaying unnormalized shape");
        ArrayList<ArrayList<Integer>> lists  = shape.unnormalizedList;
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j));
                }
            System.out.println();
        }
    }
    public void displayNormShape(Shape shape) {
        System.out.println("Displaying normalized shape");
       int [][] lists = shape.normalizedList;
        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].length; j++) {
                System.out.print(lists[i][j]);
            }
            System.out.println();
        }
    }
    public void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
//    public void testingObjectMemory(ArrayList<ArrayList<Node>> petriDish){
//        Node tempNode = petriDish.get(0).get(11);
//        System.out.println("test " + tempNode);
//        tempNode.setVisited(true);
//
//    }
    //find mirror image
    public Shape findMirror(Shape shape){
        //System.out.print(" finding mirror "); //DEBUG
        Shape mirror = new Shape();
        int[][] shapeMatrix = shape.normalizedList;
        int[][] mirrorMatrix = new int[shapeMatrix.length][shapeMatrix[0].length]; //mirror
        int rightMost = shapeMatrix[0].length - 1;
        for (int rows = 0; rows < shapeMatrix.length; rows++){
            for (int cols = 0; cols < shapeMatrix[0].length; cols++){
                mirrorMatrix[rows][rightMost - cols] = shapeMatrix[rows][cols];
            }
        }
        mirror.normalizedList = mirrorMatrix;
        mirror.matrixRight = shape.getMatrixLeft(); //bottom becomes new right
        mirror.matrixLeft = shape.getMatrixRight(); //top becomes new left
        mirror.matrixTop = shape.getMatrixTop();
        mirror.matrixBottom = shape.getMatrixBottom();
        mirror.setNumberOfNodes(shape.getNumberOfNodes());
        return mirror;
    }

    /**
     * returns a new shape that represents the 90 degree rotation of the input shape
     * @param shape
     * @return
     */
    public Shape rotateMatrix90(Shape shape) {
        Shape newRotation = new Shape();
        //int depth = shape.matrixBottom - shape.matrixTop;
        int width = Math.abs(shape.matrixRight - shape.matrixLeft);

        int[][] matrix = shape.normalizedList;
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int[][] rotatedMatrix = new int[colSize][rowSize]; //every 90 degree rotation swaps matrix size

        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (matrix[j][i] == 1){
                    rotatedMatrix[width - i][j] = 1;
                }
            }
        }
        newRotation.matrixRight = shape.getMatrixBottom(); //bottom becomes new right
        newRotation.matrixLeft = shape.getMatrixTop(); //top becomes new left
        newRotation.matrixTop = shape.getMatrixRight();
        newRotation.matrixBottom = shape.getMatrixLeft();
        newRotation.setNumberOfNodes(shape.getNumberOfNodes());
        newRotation.normalizedList = rotatedMatrix;
        //System.out.println("finished rotating");
        return newRotation;
    }
    /**
     * reduces matrix size to minimally fit the shape where leftmost and top most nodes start at 0's.
     * @param shape
     */
    public void normalizeShape(Shape shape){
        shape.normalizedList = new int [shape.matrixBottom - shape.matrixTop + 1][shape.matrixRight - shape.matrixLeft + 1];
        for (int i = shape.matrixTop; i < shape.matrixBottom + 1; i++){
            for (int j = shape.matrixLeft; j < shape.matrixRight + 1; j++){
                if (shape.unnormalizedList.get(i).get(j) == 1) {
                    shape.normalizedList[i - shape.matrixTop][j - shape.matrixLeft] = 1;
                }
                else {
                    shape.normalizedList[i - shape.matrixTop][j - shape.matrixLeft] = 0;
                }
            }
        }
    }
    /**
     * takes in the petriDish as a full matrix
     * will find shape using breadth first search and return the shape for comparison...save normalized shape coordinates into an array of Shapes.
     * returns shape in found coordinates.
     */
    public Shape findShape(ArrayList<ArrayList<Node>> petriDish, Node firstNode) {
        //System.out.println("performing Breadth first search");
        Shape currentShape = new Shape();
        //System.out.println("petri dish size" + petriDish.size()); //REMOVE LATER
        ArrayList<ArrayList<Integer>> shapeMatrix = currentShape.initializeUNList(petriDish.get(0).size(),petriDish.size()); //stores just the shape into a matrix represented as a 1
        Queue<Node> queue = new LinkedList<Node>(); //to go to next value.
        queue.add(firstNode); //add first node to the queue.
        currentShape.initializeCoordinates(firstNode.getX(),firstNode.getY());

        while (!queue.isEmpty()) {
            queue.peek().setVisited(true); //say that the node is visited
            Node currentNode = queue.poll();
            currentShape.numberOfNodes++; //increase the number of nodes found
            int height = currentNode.getX();
            int width = currentNode.getY();

            //finding bounds for shape
            //check top
            if (height < currentShape.matrixTop) currentShape.matrixTop = height;
            //check bottom
            else if (height > currentShape.matrixBottom) currentShape.matrixBottom = height;
            //check left
            if (width < currentShape.matrixLeft) currentShape.matrixLeft = width;
            //check right
            else if (width > currentShape.matrixRight) currentShape.matrixRight = width;

            shapeMatrix.get(height).set(width, 1); //update the shape with the current coordinates

            //check left
            if (width - 1 > -1) { //check bound
                Node left = petriDish.get(height).get(width - 1);
                if (left != null && !left.isVisited()) {
                    left.setVisited(true);
                    queue.add(left);
                }
            }

            //check upper left
            if (width - 1 > -1 && height - 1 > -1){
                Node upperLeft = petriDish.get(height - 1).get(width - 1);
                if (upperLeft != null && !upperLeft.isVisited()){
                    upperLeft.setVisited(true);
                    queue.add(upperLeft);
                }
            }
            //check up
            if (height - 1 > -1){
                Node up = petriDish.get(height - 1).get(width);
                if (up != null && !up.isVisited()){
                    up.setVisited(true);
                    queue.add(up);
                }
            }
            //check upper right
           // System.out.println("width + 1: " + width + 1 + " height - 1: " + (height - 1));
            if (width + 1 < petriDish.get(height).size() && height - 1 > -1){
                Node upperRight = petriDish.get(height - 1).get(width + 1);
                if (upperRight != null && !upperRight.isVisited()){
                    upperRight.setVisited(true);
                    queue.add(upperRight);
                }
            }

            //check right
            if (width + 1 < petriDish.get(height).size()){
                Node right = petriDish.get(height).get(width + 1);
                if (right != null && !right.isVisited()){
                    right.setVisited(true);
                    queue.add(right);
                }
            }
            //check lower right
            if (width + 1 < petriDish.get(height).size() && height + 1 < petriDish.size()){
                Node lowerRight = petriDish.get(height + 1).get(width + 1);
                if (lowerRight != null && !lowerRight.isVisited()){
                    lowerRight.setVisited(true);
                    queue.add(lowerRight);
                }
            }
            //check down
            if (height + 1 < petriDish.size()){
                Node down = petriDish.get(height + 1).get(width);
                if (down != null && !down.isVisited()){
                    down.setVisited(true);
                    queue.add(down);
                }
            }
            //check lower left
            if (width - 1 > -1 && height + 1 < petriDish.size()){
                Node lowerLeft = petriDish.get(height + 1).get(width - 1);
                if (lowerLeft != null && !lowerLeft.isVisited()){
                    lowerLeft.setVisited(true);
                    queue.add(lowerLeft);
                }
            }
        }//end while loop
        //shape is found, return shape.
        return currentShape; //returns shape as giant matrix of 1's and 0's
    }//end function def

    /**
     * Processes text file of petri dish, converting into a 2d list.
     * returns the list as a 2d list of nodes.
     * @throws IOException
     */
    public ArrayList<ArrayList<Node>> processImage() throws IOException {
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<ArrayList<Node>> theDish = new ArrayList<>(); //2d list

        String st;
        int depth = 0;
        while ((st = br.readLine()) != null){
            ArrayList<Node> line = new ArrayList<>(); //each line of the petri dish
            //System.out.println("width is " + st.length()); //width is constant
            for (int width = 0; width < st.length(); width++){
                if (st.charAt(width) == '*'){
                    line.add(new Node(depth, width)); //add Node to List
                }
                else {
                    line.add(null);
                }
            } //a 2d matrix is now created.
            depth++;
            theDish.add(line);
            System.out.println(st); //display Petri Dish
        }
        //displayList(theDish); //REMOVE LATER
        return theDish;
    }

    /**
     * once a shape is found and categorized. We go back into the petri dish and update the nodes to contain letter with the help of a reduced search space.
     * @param letter
     * @param shape
     * @param theDish
     */
    public void updateLetters(char letter, Shape shape, ArrayList<ArrayList<Node>> theDish){
        //System.out.println("updating letters with " + letter);
        for (int i = shape.matrixTop; i < shape.matrixBottom + 1; i++){
            for (int j = shape.matrixLeft; j < shape.matrixRight + 1; j++){
                if (shape.unnormalizedList.get(i).get(j) == 1){
                    theDish.get(i).get(j).setLetter(letter);
                }
            }
        }
    }

    /**
     *
     */
    public void outputFile(ArrayList<ArrayList<Node>> theDish){
        try{
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));
            for (int rows = 0; rows < theDish.size(); rows++){
                for (int cols = 0; cols < theDish.get(rows).size(); cols++){
                    Node node = theDish.get(rows).get(cols);
                    if (node != null){
                        writer.write(node.getLetter());
                    }
                    else {
                        writer.write(' ');
                    }
                }
                writer.newLine();
            }
            writer.close();
        //    System.out.println("wrote to file");
        }
        catch (IOException e) {
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

    /**
     * guaranteed to be entered with a node that is unvisited
     * Adds new shapes to Categories and checks to see if a shape can be placed into a Category
     * Otherwise a new category is created.
     * @param category
     * @param theDish
     * @param i
     * @param j
     */
    public void checkForCategory(Category category, ArrayList<ArrayList<Node>> theDish, int i, int j){
        Shape [] shapeArray = new Shape[8];
        //returns unnormalized shape
        Shape originalShape = findShape(theDish, theDish.get(i).get(j)); //shape found using BFS, this also creates the unnormalized matrix.
       // System.out.println("shape found using BFS");
        normalizeShape(originalShape); //shapes normalized list is now updated.
        int k = 0; //k represents # of transformations added to shape array.
        boolean foundCategory = false; //true if a matching transformation found.
        Shape foundShape = originalShape;
        //System.out.println(foundShape.hashCode());
        do{
          //  System.out.println("searching categories for a matching transformation.");//DEBUG
            char letter = category.checkExisting(foundShape); //returns if shape is already categorized.
            if (letter != ' '){ //if shape has a category.
           //     System.out.println("a matching transformation found. updating matrix w/ letter:" + letter);
                updateLetters(letter, originalShape, theDish);
                foundCategory = true;
                continue;
            }
         //   System.out.print(" adding a transformation to shapeArray[" + k + "] "); //DEBUG
            shapeArray[k] = foundShape; //store all the rotations into this array.
            foundShape = rotateMatrix90(foundShape); //rotate matrix
            k++;
        }while(k < 4 && !foundCategory); //while rotations have not been completed
        /*
        //DEBUG
        System.out.println("Matrix inside of shape array [0]: ");
        for (int a = 0; a < shapeArray[0].normalizedList.length; a++){
            for (int b = 0; b < shapeArray[0].normalizedList[0].length;b++){
                System.out.print(shapeArray[0].normalizedList[a][b]);
            }
            System.out.println();
        }
        //DEBUG
        */
        //if still not found, consider the mirror images
        while(!foundCategory && k < 8){
           // System.out.print("k in 2nd while loop: " + k + " --accessing array index: " + (k - 4) + " "); //debug
            foundShape = findMirror(shapeArray[k - 4]);
            char letter = category.checkExisting(foundShape); //check to see if the mirror image is
            if (letter != ' '){ //if shape has a category.
                updateLetters(letter, originalShape, theDish);
                foundCategory = true;
                continue;
            }
           // System.out.print(" adding a transformation to shapeArray[" + k + "] "); //DEBUG
            //shapeArray[k] = foundShape;
            k++;
        }
        if (!foundCategory) {
            //add new category and update the matrix with the new letter.
            updateLetters(category.addNewCategory(originalShape), originalShape, theDish); //add the original shape to this category.
        }
    }
    /**
     * main algorithmic method that traverses the dish, finds shapes and categorizes them.
     * @param theDish
     */
    public void traverseDish(ArrayList<ArrayList<Node>> theDish){
        Category category = new Category();
        for (int i = 0; i < theDish.size(); i++) {
            for (int j = 0; j < theDish.get(i).size(); j++) {
                //System.out.print(" traversing dish at :" + i + "," + j); //debug
                if (theDish.get(i).get(j) != null && !theDish.get(i).get(j).isVisited()) { //an unvisited node.
                   // System.out.println("entering check for category.");
                    // System.out.println("i,j: "+ i + " " + j);
                   checkForCategory(category, theDish, i, j); //find the shape associated with unvisited node
                }
            }
           // System.out.println();
        } //end first for loop
        // by this point, all shapes have been found and documented.
    }
    public static void main(String[] args)throws Exception {
        long startTime = System.currentTimeMillis();
        main x = new main();
        System.out.println("input matrix: ");
        ArrayList<ArrayList<Node>> theDish = x.processImage();
        x.displayList(theDish);

        x.traverseDish(theDish);

        x.outputFile(theDish);
        System.out.println("output matrix:");
        x.displayFinalMatrix(theDish);
        long endTime = System.currentTimeMillis();
        System.out.println("milliseconds: " + (endTime - startTime));
        System.out.println("total seconds: " + ((endTime - startTime)/1000));
        //testing code
        /*
        Shape shape = x.findShape(theDish,theDish.get(4).get(10)); //at this point, the shape has to be rotated and compared to be put into a category
        x.displayUNShape(shape);
        x.normalizeShape(shape);
        x.displayNormShape(shape); //1
        System.out.println(shape);
        //System.out.println();
        //x.displayNormShape(x.findMirror(shape));
        Shape shape2 = x.rotateMatrix90(shape);
        x.displayNormShape(shape2);
        System.out.println(shape2);

        Shape shape3 = x.rotateMatrix90(shape2);
        x.displayNormShape(shape3);
        System.out.println(shape3);
        Shape shape4 = x.rotateMatrix90(shape3);
        x.displayNormShape(shape4);
        System.out.println(shape4);
        */
    }
}