class Solution {
    public void nextPermutation(int[] nums) {
        
        int i = nums.length - 2;
        //look for a decreasing value 
        while (i > -1 && nums[i] >= nums[i + 1]) {
            i--; 
        }
        //now look for the number that is strictly higher than i
        if (i > -1){
            int j = nums.length - 1; 
            //take advantage of the fact that the numbers to the right of i are 'sorted'
            while (j > -1 && nums[i] >= nums[j]){
                j--;
            }
            //swap the two values
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            //reverse the values from i + 1 to length.
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end){
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                end--;
                start++;
            }
        }
        else { //reverse the whole thing because i is -1 which means no decreasing value found.
            int start = i + 1;
            int end = nums.length - 1;
            int temp; 
            while (start < end){
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                end--;
                start++;
            }
        }
    }
}

