class Solution {
    public List<String> findMissingRanges(int[] nums, int low, int upper) {
        
        List<String> ans = new ArrayList<>();
        
        if (nums.length < 1){
            if (upper == low){
                ans.add(upper + "");
            }
            else {
                ans.add(low + "->" + upper);
            }
            return ans; 
        }
        //start with the 0 and compare to lower
        //then do the middle
        //check if you hit the last index and compare with upper
        //if it's a single digit, return just the value. if it's a range. include -> 
        
       
        int i = 0;
        //starting case
        if (nums[i] > low){
            int lower = low;
            int higher = nums[i] - 1;
            if (lower == higher){
                ans.add(lower + "");
            }
            else {
                ans.add(lower + "->" + higher);
            }
        }
        
        //[1,1,1]
        //general case 
        for (i = 1;  i < nums.length; i++){        
            if (nums[i] != (nums[i - 1] + 1) && nums[i] != nums[i - 1]){ //we have a missing value
                int lower = nums[i - 1] + 1;
                int higher = nums[i] - 1;
                if (lower == higher) {
                    ans.add(lower + "");
                }
                else {
                    ans.add(lower + "->" + higher);
                }
            }
        }
        
        if (nums[i - 1] < upper){ 
                int lower = nums[i - 1] + 1;
                int higher = upper;
                if (lower == higher) {
                    ans.add(lower + "");
                }
                else {
                    ans.add(lower + "->" + higher);
                }
        }
        return ans; 
    }
}
