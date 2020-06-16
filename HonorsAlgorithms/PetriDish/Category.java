import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeMap;

/**
 * Category holds all possible shapes for comparison.
 * Contains the check existing method which compares a shape to all previously discovered shapes.
 */
public class Category {
    char [] alphabet; //characters used for categorization
    String name;
    //used to hold found shapes
    //key is a letter a-z, element is the original shape.
    TreeMap<Character, Shape> mapping = new TreeMap<>(); //only one shape needs to be stored, not all rotations.

    //ASCII a:97 - z: 122
    public Category(){
        alphabet = new char[26];
        for (int i = 0; i < 26; i++){
            alphabet[i] = (char)(i + 97);
        }
    }

    /**
     * used to add a new category to mapping if no category was found for the shape.
     */
    public char addNewCategory(Shape originalShape){
       // System.out.println("keyset:" + mapping.keySet());
       // System.out.println("adding new shape to category " + alphabet[mapping.keySet().size()]);
        mapping.put(alphabet[mapping.keySet().size()], originalShape);
       // System.out.println("keyset:" + mapping.keySet());
        return alphabet[mapping.keySet().size() - 1];
    }


    /**
     * Checks to see if a shape is part of a category.
     * @param shape
     * @return the category letter if category shape is found else returns an empty space ' '
     */
    public char checkExisting(Shape shape) {
        int i;
        int[][] inputMatrix = shape.normalizedList; //The Input Shape which will be every rotation
        boolean found = false;
        //for every category
        for (i = 0; i < mapping.keySet().size() && !found; i++) {
            //System.out.println("comparing shape to Category " + alphabet[i]);
            Shape consideredCategory = mapping.get(alphabet[i]); //all the rotations in the category
            // if they don't have the same number of nodes, they are not the same shape.
            if (consideredCategory.getNumberOfNodes() != shape.getNumberOfNodes()) {
                continue;
            }
            int[][] categoryMatrix = consideredCategory.normalizedList;

            if (categoryMatrix.length != inputMatrix.length || categoryMatrix[0].length != inputMatrix[0].length) {
                continue;
            }
            /*
            // - - - - -- - - - -- - - - DEBUG
            System.out.println("Comparing: " + shape);
            for (int a = 0; a < inputMatrix.length; a++) {
                for (int b = 0; b < inputMatrix[0].length; b++) {
                    System.out.print(inputMatrix[a][b]);
                }
                System.out.println();
            }

            System.out.println("with: ");
            for (int a = 0; a < categoryMatrix.length; a++) {
                for (int b = 0; b < categoryMatrix[0].length; b++) {
                    System.out.print(categoryMatrix[a][b]);
                }
                System.out.println();
            }
            */
            // - - - - - - - - - - END DEBUG
            boolean continueSearch = true;
            //Compare the two matrices
            for (int rows = 0; rows < inputMatrix.length && continueSearch; rows++) {
                for (int cols = 0; cols < inputMatrix[rows].length && continueSearch; cols++) {
                    //System.out.print(" " + rows + "," + cols + " ");//DEBUG
                    if (categoryMatrix[rows][cols] != inputMatrix[rows][cols]) {
                        continueSearch = false;
                        //System.out.print("compared shapes are NOT a match.");
                    }
                }//end 4th loop
                //System.out.println(); //DEBUG
            }// end 3rd loop
            if (continueSearch == true) {
                found = true;
            }
        }
        if (found) {
            //System.out.println("matching shape found with category: " + alphabet[i - 1]); //DEBUG
            return alphabet[i - 1];
        } else {
            //System.out.println("no shape found");
            return ' '; //if no shape found
        }
        }
    }
