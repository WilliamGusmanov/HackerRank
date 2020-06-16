import java.io.*;
import java.util.*;
/*

Here are some cycles that beat yours:
Cycle: [263, 298, 93, 225, 283, 115, 63, 174, 31, 193, 254, 77, 89,
157, 257, 183, 149, 6, 62, 221, 202, 215, 38, 263] Profit:
0.8114683003331402% per trade
Cycle: [4, 236, 70, 4] Profit: 0.8119242379498681% per trade
Cycle: [184, 237, 160, 115, 63, 174, 31, 193, 254, 77, 89, 157, 257,
183, 149, 6, 62, 9, 184] Profit: 0.8129694483947736% per trade
Cycle: [31, 193, 254, 77, 89, 157, 257, 183, 149, 6, 62, 221, 202,
215, 160, 115, 63, 174, 31] Profit: 0.8258755983181487% per trade
Cycle: [31, 193, 254, 77, 89, 157, 257, 183, 149, 6, 62, 221, 202, 9,
184, 237, 160, 115, 63, 174, 31] Profit: 0.8260181657728571% per trade
Cycle: [6, 62, 9, 183, 149, 6] Profit: 0.8402752748238429% per trade
Cycle: [31, 193, 254, 77, 89, 157, 257, 31] Profit:
0.8416700914630759% per trade
Cycle: [6, 62, 221, 202, 9, 183, 149, 6] Profit: 0.8697665997588588% per trade
Student: [175, 6, 5, 21, 38, 30, 10, 19, 175] Profit:
-35.891003457652474% per trade

*/
class _Arbitrage
{
    double bestEfficiencyPerTrade = Double.MIN_VALUE;
    LinkedList<Integer> bestPath; // *  *  * path needs to be adjusted for index + 1 during output

    public void displayList(LinkedList<Integer> path){
        Iterator iterator = path.iterator();
        System.out.println();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    public double[][] negativeLogMatrix(double[][] matrix){
        int N = matrix.length;
        double [][] newMatrix = new double[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                newMatrix[i][j] = -(Math.log(matrix[i][j])/Math.log(2)); //using log rule: logbx == logkx / logkb
                if (newMatrix[i][j] == -0.0){
                    newMatrix[i][j] = 0.0;
                }
            }
        }
        return newMatrix;
    }

    public void outputFile(){
        try {
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));
            Iterator iterator = bestPath.iterator();
            while (iterator.hasNext()){
                int result = (int) iterator.next();
                result++;
                writer.write(result + "");
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

    //We should add a check to see once a diagonal contains a
    public void min_plus_max(double[][] adjacencyMatrix, int N, double[][] originalMatrix){
        int k = 0;
        double[][][] allDirectedMatrices = new double[N][N][N];
        int [][][] allPathIndexes = new int [N][N][N];

        //the adjacency matrix is the weight matrix.
        //The weight matrix holds the 'weight' of each single path
        //the directed matrix holds the shortest distance so far using k + 1 steps.

        double [][] directedMatrix = adjacencyMatrix;
        allDirectedMatrices[0] = directedMatrix;

        for (int row = 0; row < N; row++){
            for (int col = 0; col < N; col++){
                if (row == col) {
                    allPathIndexes[0][row][col] = row;
                }
            }
        }
        //displayMatrix(allPathIndexes[0]);

        for (k = 1; k < N - 1; k++){
            double [][] newDirectedMatrix = new double[N][N];
            int [][] newPathMatrix = new int[N][N];
            double [][] priorDirectedMatrix = allDirectedMatrices[k-1];
            allDirectedMatrices[k] = newDirectedMatrix;
            allPathIndexes[k] = newPathMatrix;

            for (int row = 0; row < N; row++){
                for (int col = 0; col < N; col++){
                    Double min = Double.MAX_VALUE;
                    int minIndex = 0;
                    for (int x = 0; x < N; x++){
                        if (priorDirectedMatrix[row][x] + adjacencyMatrix[x][col] <= min){
                            min = priorDirectedMatrix[row][x] + adjacencyMatrix[x][col];
                            minIndex = x;
                        }
                    }
                    newDirectedMatrix[row][col] = min;
                    newPathMatrix[row][col] = minIndex;

                    //check for efficiency and store this information.
                    if (min < 0 && row == col){
                        //System.out.println("arbitrage found." + " row: "  + (row + 1) + " col: " + (col + 1) + " min: " + min);

                        LinkedList<Integer> path = findPath(allPathIndexes, k, row, col); //we need the path to find the result.

                        //need result of multiplying weight matrix.
                        double result = findResult(originalMatrix, path); //we need the result to perform an efficiency check

                        double efficiency = Math.pow(result, 1.0 / (k + 1)); //after finding efficiency
                        efficiency = (efficiency - 1)*100; //96 192 96

//                        if (row == 6){
////                            displayList(path);
////                            System.out.println(efficiency + " using k: " + k + " result: " + result);
////                        }
                        //System.out.println("efficiency: " + efficiency );
                        if (efficiency > bestEfficiencyPerTrade){
                            bestEfficiencyPerTrade = efficiency;
                            bestPath = path;

                            displayList(bestPath);
                            System.out.println("\n"+bestEfficiencyPerTrade + "% using k: " + k + " result: " + result);
                        }
                    }
                }
            }
            //displayMatrix(newDirectedMatrix);
            //displayMatrix(newPathMatrix);
        }
    }


    private double findResult(double[][] originalMatrix, LinkedList<Integer> path) {
        Iterator iterator = path.iterator();
        int row = (int) iterator.next();
        int col = (int) iterator.next();
        double result = originalMatrix[row][col];
        while (iterator.hasNext()){
            row = col;
            col = (int) iterator.next();
            result *= originalMatrix[row][col];
        }
        //System.out.println("\n result: " + result);
        return result;
    }


    public LinkedList<Integer> findPath(int [][][] allPathIndexes, int n, int row, int col){
        int currentRow = row;
        int currentCol = col;
        LinkedList<Integer> path = new LinkedList<>();
        path.push(col); //because it is the same as row
        //System.out.print(" " + (col + 1)); //DEBUG
        //boolean cycle = false;

        for (int k = n; k > 0; k--){
            currentCol = allPathIndexes[k][currentRow][currentCol];
            path.push(currentCol);
           // System.out.print(" " + (currentCol + 1));
            /*if (currentCol == currentRow){
                cycle = true;
            }
             */
        }
        path.push(col);
        //System.out.print(" " + (col + 1));
        return path;
    }
    public double[][] processImage() throws IOException {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String x = br.readLine();

        int N = Integer.parseInt(x);
        System.out.println("N: " + N);
        double[][] matrix = new double[N][N];
        int row = 0;
        while ((st = br.readLine()) != null) {
            String[] singleLine = st.split(" ");
            for (int col = 0; col < singleLine.length; col++) {
                matrix[row][col] = Double.parseDouble(singleLine[col]);
            }
            row++;
        }
        return matrix;
    }

    public static void main(String[] args) throws IOException {

        _Arbitrage test = new _Arbitrage();

        System.out.println("calculating...");
        long startTime = System.currentTimeMillis();
        double[][] originalMatrix = test.processImage();
        double[][] adjacencyMatrix = test.negativeLogMatrix(originalMatrix);

        test.min_plus_max(adjacencyMatrix, adjacencyMatrix.length, originalMatrix);
        test.outputFile();
        test.displayList(test.bestPath);
        System.out.println(test.bestEfficiencyPerTrade);

        long endTime = System.currentTimeMillis();
        System.out.println("milliseconds: " + (endTime - startTime));
        System.out.println("total seconds: " + ((endTime - startTime)/1000));


        //adjacency matrix
        /*
        double[][] lectureWithArbitrage = new double [][] {
                {0, -0.648613, -0.327216, 0.0305971},
                {0.648614, 0, 0.321387, 0.67922},
                {0.327227, -0.321386, 0, 0.357833},
                {-0.0305903, -0.679217, -0.357822, 0}
        };

        double [][] originalCurrencies = new double [][] {
                {1, 1.56766, 1.25459, 0.97901},
                {0.637893, 1, 0.8003, 0.624503},
                {0.797067, 1.24953, 1, 0.780336},
                {1.02143, 1.60127, 1.28149, 1}
        };

        double [][] originalCurrenciesAdjusted = new double [][] {
                {1, 1.56766, 1.25459, 0.979015},
                {0.637893, 1, 0.8003, 0.624503},
                {0.797067, 1.24953, 1, 0.780336},
                {1.02143, 1.60127, 1.28149, 1}
        };

        double [][] adjacencyMatrix = test.negativeLogMatrix(originalCurrenciesAdjusted);

        test.min_plus_max(adjacencyMatrix, adjacencyMatrix.length, originalCurrenciesAdjusted);
        */
        //final int N = 4;
        //int index = test.FloydWarshell(lectureWithArbitrage, originalCurrencies);
        // System.out.println("index: " + index);
        //test.outputFile(test.path, index);
     //*/
    }
}
