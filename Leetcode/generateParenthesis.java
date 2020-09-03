class Solution {
    //everytime a ( is added, it must be closed.
    //if we add a (, we then have the choice of closing or opening a new parenthesis
    List<String> answer = new ArrayList<>();
    int maxLength;
    
    public List<String> generateParenthesis(int n) {
        maxLength = n * 2; 
        backtrack("(", 1);
        return answer; 
    }
    
    public void backtrack(String curr, int unclosed){
        if (curr.length() == maxLength){
            if (unclosed == 0){
                answer.add(curr);
            }
            return;
        }
        System.out.println(unclosed);
        if (unclosed > 0){ //this means that we have something not closed
            backtrack(curr + ")", unclosed - 1);
        }
        backtrack(curr + "(", unclosed + 1);
    }
}
