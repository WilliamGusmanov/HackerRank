class Solution {
    public boolean canJump(int[] nums) {
        //backtracking approach
        //may need a dp table..a seen array to show which indices have already been visited
        int [] seen = new int[nums.length];
        for (int i = 0; i < seen.length; i++){
            seen[i] = -1; 
        }
        return canJumpHelper(0, nums, seen);
    }
    
    //backtracking too slow. We need to memoize
    public boolean canJumpHelper(int index, int[] nums, int[] seen){
        //base case, check if seen, check if we are at the last index
        if (index == nums.length - 1){
            return true; 
        }
        //check to see if the index has been seen.
        if (seen[index] != -1){
            return seen[index] == 1;
        }
        
        int number = nums[index];
        //check the various jump paths
        for (int i = 1; i <= number && index + i < nums.length; i++){
            if (canJumpHelper(index + i, nums, seen)){
                seen[index] = 1;
                return true; //if it is found, we found a path.
            }
        }
        seen[index] = 0; 
        return false;
    }
}
