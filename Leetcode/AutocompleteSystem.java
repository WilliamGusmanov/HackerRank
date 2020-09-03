class AutocompleteSystem {
    //implement a trie data structure
    
    static TrieNode root;
    static TrieNode curr;
    StringBuilder strSoFar; 
    
    class TrieNode {
        //size is the number of letters + ' ' + # = 54
        Character value;
        Map<Character, TrieNode> children;
        int times;
        String word; 
        
        TrieNode(){
            times = 0;
            children = new HashMap(54);
        }
        TrieNode(Character value){
            times = 0;
            children = new HashMap(54);
            this.value = value;
        }
    }
    
    private class ACScomparator implements Comparator<TrieNode>{
        public int compare(TrieNode node1, TrieNode node2){
            if (node1.times == node2.times){
                //compare by ASCII
                return node1.word.compareTo(node2.word);
            }
            else {
                //if x > y, return 1
                return Integer.compare(node2.times, node1.times);
            }
        }
    }

    
   public void insertIntoTrie(String key, int times){
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++){
            Character c = key.charAt(i);
            TrieNode next = curr.children.get(c);
            if (next == null){
                next = new TrieNode(c);
                curr.children.put(c, next);
            }
            curr = next; 
        }
        curr.times += times; 
        curr.word = key;
    }
    
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        strSoFar = new StringBuilder();
        curr = root;
        for (int i = 0; i < sentences.length; i++)
            insertIntoTrie(sentences[i], times[i]);
    }
    
    public List<String> input(char c) {
        List<TrieNode> answer = new ArrayList<>();  
        List<String> answerStr = new ArrayList(3);
        if (c == '#'){
            insertIntoTrie(strSoFar.toString(), 1);
            strSoFar = new StringBuilder();
            curr = root;
            return answerStr;
        }
        else { //c is either " " or 
            strSoFar.append(c);
            Stack<TrieNode> stack = new Stack<>();            
            TrieNode oldNode = curr; 
            curr = curr.children.get(c);

            if (curr != null) {
                stack.push(curr); //curr is the letter before c.
                while (!stack.isEmpty()){
                    TrieNode node = stack.pop();
                    if (node.times > 0)
                        answer.add(node); //this is the word being added.
                    for (Character key : node.children.keySet())
                        stack.push(node.children.get(key));
                }
                Collections.sort(answer, new ACScomparator()); //have to create a new comparator
                for (int i = 0; i < 3 && i < answer.size(); i++)
                    answerStr.add(answer.get(i).word);
                return answerStr;
            }
            else {
                oldNode.children.put(c, new TrieNode(c));
                curr = oldNode.children.get(c);
            }
            return answerStr;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
