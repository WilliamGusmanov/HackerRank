class Solution {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        
        //iterate through the given emails
        for (int i = 0; i < emails.length; i++){
            //create a string builder for each email found.
            StringBuilder str = new StringBuilder(emails[0].length()); //max size is given email
            for (int j = 0; j < emails[i].length();j++){
                char letter = emails[i].charAt(j);
                if (letter == '.'){
                    continue;
                }
                else if (letter == '+'){
                    str.append(skipToDomain(j, emails[i]));
                    break;
                }
                else if (letter == '@') { //add the entire rest of the substring
                    str.append(emails[i].substring(j));
                    break; //end the outer loop condition 
                }
                else {
                    str.append(letter);
                }
            }
            set.add(str.toString());
        }
        return set.size();
    }
    //we need a skip to domain method to find '@' sign after finding '+' signs.
    public String skipToDomain(int startIndex, String str){
        int i = 0;
        for (i = startIndex; str.charAt(i) != '@'; i++){}
        return str.substring(i);
    }
    
    //if '.', we ignore the value. We will use a stringbuilder to build the strings without '.' and add these emails to a set. 
    
    //we will use the set size property to return the answer
}
