class Solution {
    //this is exhaustive, which suggests backtracking
    Map<Character, List<Character>> dictionary = new HashMap<>();
    List<String> answer = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return answer;
        }
        
        //we need a map {2: [a,b,c], 3: [d,e,f]}
        initializeMap();
        //displayMap(); //debug
        
        //backtracking
        backtrack("", digits);
        return answer; 
    }
    public void displayMap(){
        for (Character key : dictionary.keySet()){
            System.out.println(dictionary.get(key));
        }
    }
    
    public void backtrack(String currStr, String digitsLeft){
        if (digitsLeft.length() == 0){
            answer.add(currStr);
            return;
        }
        Character currDigit = digitsLeft.charAt(0);

        List<Character> toBeAdded = dictionary.get(currDigit);
        for (Character c : toBeAdded){
            if (digitsLeft.length() > 1){
                backtrack(currStr + c, digitsLeft.substring(1)); //might give out of bounds        
            }
            else {
                backtrack(currStr + c, ""); //might give out of bounds
            }
            //usually we undo step here
        }
    }
    
    public void initializeMap(){
        //ascii
        List<Character> curr = new ArrayList<>(3);
        int num = 50;
        for (int i = 97; i <= 123; i++){ //a -> z
            if (curr.size() < 3){ 
                curr.add((char)i);
            }
            else if ((num == 57 || num == 55) && curr.size() < 4){
                curr.add((char)i);
            }
            else {
                dictionary.put((char)num++, curr);
                curr = new ArrayList<>(4); //at z, this is made but not used
                curr.add((char)i); //ascii of 123 is added but not used 
            }
        }
    }
}
