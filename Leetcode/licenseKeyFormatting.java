class Solution {
    public String licenseKeyFormatting(String S, int K) {
        assert K >= 0 && S.length() != 0;
        //reverse the string S into a String Builder of size S.length
        //greedy approach by filling groups until the end in K-groups.
        //reverse again.
        StringBuilder answer = new StringBuilder(S.length());
        StringBuilder str = new StringBuilder(S);
        str = str.reverse();
        int k = 0;
        for (int i = 0; i < str.length(); i++){
            if (k == K){ //end of group, add a dash
                k = 0;
                answer.append("-");
            }
            if (str.charAt(i) != '-'){
                if (Character.isLetter(str.charAt(i)))
                    answer.append(Character.toUpperCase(str.charAt(i)));
                else 
                    answer.append(str.charAt(i));
                k++; 
            }
        }
        answer = cleanString(answer);
        return answer.reverse().toString();
    }
    private StringBuilder cleanString(StringBuilder str){
        for (int i = str.length() - 1; i > -1 && str.charAt(i) == '-'; i--)
            str.deleteCharAt(i);
        for (int i = 0; i < str.length() && str.charAt(i) == '-'; i++)
            str.deleteCharAt(i);
        return str;
    }
}
