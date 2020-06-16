/*

 10  X1                                    X2
       0   1   2   3   4   5   6   7   8  9
      - - - - - - - - - - - - - - - - - -
  0 | 20  11 -25  19  14   1  -7   7   2  14 Y1
  1 | -2  17 -23  12  -9   9  -4  21  20 -17
  2 | 22  13 -23  16  23 -11   2  -1 -17   2
  3 | -1 -20   0   4  22 -19 -20   1 -10  -7
  4 | 10   6 -15 -12  -1   7 -11   9 -10 -15
  5 | 25  11  15  23  -5  11 -19  14  25  -5
  6 |  8 -25 -20   4  -8   9   1 -14 -13  -9
  7 | 11   3  15  -6  18  23  12  14   2   6
  8 |-18  21  11   2  21 -9   13  19  16 -21 Y2
  9 | 11  21  25 -23  25 -23   8 -15  -8   2

 */

//RETURN THE LOWER LEFT CORNER Form: (ROW, COL)
//RETURN THE UPPER RIGHT CORNER Form: (ROW, COL)

import java.io.*;
import java.lang.Math;
import java.util.ArrayList;

public class Excavation {


    public void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int[][] createSummationMatrix(int N, int[][] matrix){
        int summationMatrix[][] = new int [N][N + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                summationMatrix[i][j+1] = summationMatrix[i][j] + matrix[i][j];
            }
        }
        return  summationMatrix;
    }

    public int[] excavateMatrix(int[][] matrix) {
        int maxSum = 0;
        int[] maxCoordinates; //Stores the 2 coordinates that hold rectangle.
        int upperLeftX = 0;
        int upperLeftY = 0;
        int lowerRightX = 0;
        int lowerRightY = 0;

        int N = matrix.length;

        //matrix that holds total sums from row 0 up to i'th row.
        int [][] summationMatrix = createSummationMatrix(N, matrix);

        /*
        System.out.println("regular matrix");
        displayMatrix(matrix);

        System.out.println("summed matrix: ");
        displayMatrix(summationMatrix);
        */
        //For Every starting Left Point
        for (int left = 0; left < N; left++) {
            //every ending right point
            for (int right = left; right < N; right++) {
                int startingRow = 0;
                int sum = 0;

                for (int row = 0; row < N; row++){
                    //the difference of right - left in summation matrix = summation of left to right in original matrix
                    sum += summationMatrix[row][right+1] - summationMatrix[row][left];
                    if (sum < 0){ //if this occurs, reset 0 as according to algorithm
                        sum = 0;
                        startingRow = row + 1; //summation is negative. ignore the current row. start anew.
                    }
                    else if (sum > maxSum){
                        upperLeftY = left; //Y is column
                        upperLeftX = startingRow; //X is row
                        lowerRightY = right;
                        lowerRightX = row;
                        maxSum = sum;
                        System.out.println("current max sum: " + maxSum);
                    }
                }
            }
        }
        System.out.println("max sum is: " + maxSum); //DEBUG
        maxCoordinates = new int[]{upperLeftX, upperLeftY, lowerRightX, lowerRightY};
        return maxCoordinates;
    }

    public void outputFile(int[] array){
        try{
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));
            writer.write((array[0] + 1) + " ");
            writer.write((array[1] + 1) + "");
            writer.newLine();
            writer.write((array[2] + 1) + " ");
            writer.write((array[3] + 1) + "");

            writer.close();
            System.out.println("wrote to file");
        }
        catch (IOException e) {
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

    public int[][] processImage() throws IOException {
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String x = br.readLine();
        int N = Integer.parseInt(x);
        int[][] matrix = new int[N][N];
        int row = 0;
        while ((st = br.readLine()) != null) {
            String[] singleLine = st.split(" ");
            for (int col = 0; col < singleLine.length; col++) {
                matrix[row][col] = Integer.parseInt(singleLine[col]);
            }
            row++;
        }
        //displayMatrix(matrix); //debug
        return matrix;
    }

    public void printAnswers(int[] arr) {
        System.out.println("printing array values: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        
        long startTime = System.currentTimeMillis();
        Excavation e = new Excavation();
        System.out.println("calculating...");
        int [] y = e.excavateMatrix(e.processImage());
        e.printAnswers(y);
        e.outputFile(y);
        long endTime = System.currentTimeMillis();
        System.out.println("milliseconds: " + (endTime - startTime));
        System.out.println("total seconds: " + ((endTime - startTime)/1000));

        //*/
      /*
        int[] x = new int [] {19,-15,8,7,-15,-10,-11,20,8,11,-5,6,-22,-23,-14,19,14,5,17,23,-19,-12,-20,-3,20,-24,-10,6,16,11,3,0,-4,-5,-4,-14,-14,17,9,6,-18,20,-20,-18,-8,1,-15,22,-10,24,0,-22,11,-2,-21,-1,-21,-22,-18,-11,-21,5,-24,-4,17,8,-7,-10,-13,23,-3,1,-9,13,-17,20,18,-20,-25,-14,21,-17,-7,-16,-3,2,-5,22,15,10,-19,-10,-18,-24,-1,19,-23,19,15,-21,12,17,14,-6,13,24,-7,23,-21,-3,6,19,-18,-22,23,1,22,-23,-22,24,12,-25,9,-22,-15,-15,-23,-6,-24,-12,15,14,-23,-1,20,20,-24,-5,19,-17,20,-25,-1,24,4,8,-13,25,10,9,17,7,16,-4,-21,25,5,-25,7,4,-7,-23,23,-7,17,-4,3,-10,3,-20,-14,22,-7,7,-14,-6,-1,12,-12,-6,16,2,-22,-20,14,-7,15,8,8,23,1,3,25,-15,-15,21,-6,-17,-7,20,11,-9,-21,17,-17,7,15,18,-22,9,0,-15,-11,9,-5,13,-5,14,-1,-7,-15,22,7,-5,-21,19,25,19,-9,20,-6,14,-18,-2,10,16,17,-10,3,9,18,-13,-18,14,15,25,-12,-2,1,-20,17,13,4,-24,6,-24,-5,-25,-20,-16,-16,-19,0,10,-3,-19,15,14,0,-25,25,-8,6,19,16,-22,6,-4,5,13,-23,17,15,19,-14,6,3,4,-21,4,16,-11,24,-4,-14,-18,10,21,-12,-4,3,-11,-24,-23,-22,6,3,22,19,0,-10,7,14,-15,19,-22,23,12,12,-10,-8,1,11,0,-2,20,16,-7,6,23,-21,11,17,16,-8,9,18,-2,-18,8,-5,-25,9,15,-12,4,11,3,5,-23,-18,7,-4,-11,-7,12,24,-13,-5,15,-15,-18,5,-13,-20,-21,-24,19,-25,13,25,14,-22,-5,-11,-8,-24,10,-15,-2,22,2,-9,-12,6,3,7,-24,1,14,-2,-4,0,6,-22,21,-15,-5,14,19,-14,-11,7,-14,-15,0,23,-17,-15,-15,23,-7,-25,25,5,15,7,-16,-13,5,-13,-19,14,-4,13,-5,-22,4,16,25,4,19,20,5,18,21,-4,-7,19,-11,25,-12,-11,-25,9,-15,-22,1,12,7,8,-1,0,16,-1,-8,-10,17,11,10,-17,1,-10,-2,10,-16,-6,24,1,-6,20,25,15,12,-21,6,6,7,-25,-11,-3,15,-17,-6,24,-11,-20,24,-8,-15,-20,-9,-20,15,-12,14,-12,23,-8,14,-10,-16,12,-4,24,-8,4,-3,21,12,6,-2,-20,-11,-18,-22,-5,15,-10,20,-8,-22,8,4,-19,-18,12,20,2,-7,-16,8,6,-12,0,15,-13,-19,-19,-14,16,0,23,-13,9,-3,-14,-11,11,25,-19,5,-6,17,7,18,-1,-12,3,17,-21,-25,13,-4,4,-19,-11,-10,-14,-8,15,-3,-1,-23,-3,0,10,-20,-25,-23,-4,0,-15,14,15,4,11,-7,-25,-2,22,22,10,-7,-22,-25,11,0,11,0,18,6,4,18,15,-5,-23,-15,21,15,-14,-14,-7,21,-17,16,-5,23,-22,4,8,-22,-25,11,-3,19,3,-2,8,4,-11,17,-21,-4,-8,-11,-13,13,17,18,-5,-8,-3,25,-21,-6,-16,-11,14,-24,-2,-21,-14,-10,-18,-12,-8,13,9,7,-11,0,12,20,-15,0,-10,-14,0,4,-6,-18,3,25,-17,9,-9,21,-11,-10,10,-15,-24,25,13,0,-5,18,-10,-12,17,5,-1,-23,2,2,23,-21,21,23,18,-17,-1,-1,6,-17,7,-2,0,11,-23,11,-8,-10,25,-2,-15,-22,8,-11,-3,21,-17,-14,-9,-9,19,10,22,21,14,20,22,-5,-8,21,11,1,16,25,8,2,-11,14,-8,-21,-23,-19,10,-16,4,1,8,-5,14,12,14,-20,5,2,16,8,-14,0,16,8,-11,-16,14,24,-15,-18,6,-4,3,8,-19,-12,-17,20,5,17,-12,-8,-17,19,-10,-16,22,-19,-10,16,-2,9,-16,-5,20,12,-14,19,4,4,19,25,12,11,8,23,-22,20,-20,11,8,-3,-2,21,19,20,-9,19,-10,5,-12,24,21,-18,-14,0,6,-22,5,10,-20,20,11,13,16,-6,18,15,15,18,-10,-13,1,22,-16,-11,6,-7,5,-25,22,-25,-17,-17,-25,21,-21,0,0,-3,19,9,25,-17,-4,0,3,20,10,13,0,12,-22,24,-15,-7,24,-24,-19,-5,-7,23,-10,-5,1,-8,2,19,-15,-24,1,16,15,-19,24,-2,-10,16,18,15,-15,-6,18,11,-24,10,-24,-1,-7,-24,-24,-1,10,16,1,-13,0,13,-12,5,4,9,11,22,-25,-22,8,13,14,11,0,1,16,6,17,-9,-5,-24,-24,22,-1,-1,4,15,5,-8,-19,-9,-10,7,-3,-13,10,-6,-6,20,22,-10,-3,15,1,1,11,-3,17,-13,14,-6,-15,9,-18,-18,-24,12,14,-3,-19,-7,-10,-10,7,-14,3,25,13,-6,6,23,7,24,19,3,-19,-25,3,23,16,-14};
        //int []x = new int [] {19,-15, 8, 7, -15};
        System.out.println(x.length);
        int [] z = e.findMaxSubArray(x, 0,x.length - 1);
        int [] y = e.findMaxSubArray(x);
        int [] w = e.maxSubArraySum(x);
        e.printAnswers(z);
        e.printAnswers(w);
       */
    }
}