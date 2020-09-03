class Solution {
    /*
    we can do a 2 pass approach. 1 for the A, 2nd for the B
    create a count for A
    Make a HashMap of digits and frequencies. 
    1st pass: add digits and frequencies in the secret to hashmap.
    
    create a count to hold misplaced correct digits B
    2nd pass: check if the char is in the hashmap, if so, decrement count, and increment B
    
    check how many digits are in the correct place. negate the frequency everytime these are discovered. 
    
    */
    
    
    public String getHint(String secret, String guess) {
        int A = 0, B = 0, n = secret.length();
        HashMap<Character, Integer> map = new HashMap(n);
        //first pass 
        for (int i = 0; i < n; i++){
            char curr = secret.charAt(i);        
            if (map.containsKey(curr)){
                map.put(curr, map.get(curr) + 1);
            }
            else map.put(curr, 1);
            
            if (curr == guess.charAt(i)){
                map.put(curr, map.get(curr) - 1);
                ++A;
            }
        }
        
        //second pass 
        for (int i = 0; i < n; i++){
            char curr = guess.charAt(i);
            if (curr == secret.charAt(i)) continue; 
            
            if (map.containsKey(curr) && map.get(curr) > 0){
                map.put(curr, map.get(curr) - 1);
                ++B;
            }
        }
        return A + "A" + B + "B"; 
    }
}
