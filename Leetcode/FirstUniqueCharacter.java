class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> set = new HashMap<>(); //holds characters that have been seen.
        //Sieve Attempt with Linked List
        LinkedList<Character> llist = new LinkedList<>(); //holds unique candidates
        //look at each character c
        for (int i=0; i<s.length();i++){
            Character c = s.charAt(i);
            System.out.println(c);
            //check to see if the character has been seen before
            if (set.containsKey(c)){
                //a character is removed from linked list only ever once.
                if (set.get(c) == 1){
                    llist.remove(c);
                    //increment the # of times c has been seen. 
                    set.put(c, set.get(c) + 1); 
                }
            }
            //this means c has not been seen before
            else { 
                set.put(c, 1);
                llist.add(c);
            }        
        }
        //look at head of linked list for answer, if it is null, return -1
        if (llist.size() > 0){
            return s.indexOf(llist.getFirst());
        }
        else {
            return -1; 
        }
    }
}
