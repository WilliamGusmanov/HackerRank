class Solution {
    //iterate through the matrix, check if visited, if not visited, perform a DFS or BFS (doesn't matter). With every Search call, increment the count. 
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0){
            return 0;
        }
        
        boolean [][] bgrid = new boolean [grid.length][grid[0].length]; 
        int count = 0;
    
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                if (grid[row][col] == '1' && bgrid[row][col] == false){
                    ++count;
                    DFS(row, col, grid, bgrid);
                }
            }
        }
        return count; 
    }
                
    public void DFS(int sRow, int sCol, char[][] grid, boolean[][] bgrid){
        //must check for out of bounds
        Stack<Pair<Integer, Integer>> stack = new Stack<>();    
        stack.add(new Pair(sRow, sCol));    
        
        while (!stack.isEmpty()){
            Pair<Integer, Integer> coordinates = stack.pop(); 
            int row = coordinates.getKey();
            int col = coordinates.getValue();
            bgrid[row][col] = true; //mark as visited
            
            //move up
            int newRow = row - 1; //up
            int newCol = col; //right
            if (newRow >= 0 && grid[newRow][newCol] == '1' && bgrid[newRow][newCol] == false){
                stack.add(new Pair(newRow, newCol));
            }
            
            //move right
            newRow = row;
            newCol = col + 1;
            if (newCol < grid[newRow].length && grid[newRow][newCol] == '1' && bgrid[newRow][newCol] == false){
                stack.add(new Pair(newRow, newCol));
                System.out.println("DFS entered at " + newRow + " " + newCol);
            }
            //move down
            newRow = row + 1;
            newCol = col;
            if (newRow < grid.length && grid[newRow][newCol] == '1' && bgrid[newRow][newCol] == false){
                stack.add(new Pair(newRow, newCol));
            }
            
            //move left
            newRow = row;
            newCol = col - 1;
            if (newCol >= 0 && grid[newRow][newCol] == '1' && bgrid[newRow][newCol] == false){
                stack.add(new Pair(newRow, newCol));
            }
        }
    }
}
