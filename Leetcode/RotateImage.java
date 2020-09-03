class Solution {
    public void rotate(int[][] matrix) {
        int width = matrix.length;
        //transpose
        for (int row = 0; row < width; row++){
            for (int col = row; col < width; col++){
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];  
                matrix[col][row] = temp; 
            }
        }      
        //reverse each row
        for (int row = 0; row < width; row++){
            int start = 0, end = width - 1;
            while (start < end){
                int temp = matrix[row][start];
                matrix[row][start] = matrix[row][end];
                matrix[row][end] = temp;
                start++;
                end--; 
            }
        }
    }
}
