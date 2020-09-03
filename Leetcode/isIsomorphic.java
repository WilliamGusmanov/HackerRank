class Solution {
    //is this property symmetric?
    /*
    greedy solution: 
    egg
    adt

    We start with a character, try and map it to the same character in the second string using a hashmap. if this value from the key is not the same, return false. 
    
    */
    //HashMap<Character, Character> map;
    
    public boolean isIsomorphic(String s, String t) {
        
        if (s.length() < 2) return true; 
        
        if (!isIsoHelper(s, t)) return false;
        if (!isIsoHelper(t, s)) return false;
        return true; 
    }
    
    private boolean isIsoHelper(String s, String t){
        HashMap<Character, Character> map = new HashMap(s.length());
        for (int i = 0; i < s.length(); i++){
            char S = s.charAt(i);
            char T = t.charAt(i);
            
            if (map.containsKey(S)) {
                if (map.get(S) != T)
                    return false;
            }
            else map.put(S, T);
        }
        return true;
    }
}
