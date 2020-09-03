class Solution {
    public boolean backspaceCompare(String S, String T) {
        //start from the end, and move left
        //have an incrementer that skips letters when you come across a '#' 
        //have pointers at both words
        int ptrT = T.length() - 1, ptrS = S.length() - 1; 
        int incrementerT = 0, incrementerS = 0; 
        
        while (ptrT >= 0 || ptrS >= 0){ 
            while (ptrT >= 0){
                if (T.charAt(ptrT) == '#') {
                    incrementerT++;
                    ptrT--;
                }
                else if (incrementerT > 0){
                    incrementerT--;
                    ptrT--;
                }
                else { //the incrementerT is 0 or negative 
                    break; 
                }
            }
            
            while (ptrS >= 0){
                if (S.charAt(ptrS) == '#') {
                    incrementerS++;
                    ptrS--;
                }
                else if (incrementerS > 0){
                    incrementerS--;
                    ptrS--;
                }
                else { //the incrementerT is 0 or negative 
                    break; 
                }
            }
                    
            //exclusive or. 
            if ((ptrT < 0 ^ ptrS < 0) || (ptrT >= 0 && ptrS >= 0 && T.charAt(ptrT) != S.charAt(ptrS))){
                return false;
            }
            else {
                ptrT--;
                ptrS--;   
            }
        }   
        return true; 
    } 
}

