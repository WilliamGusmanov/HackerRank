class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2){
            return s.length();
        }
        //O(n) solution
        HashSet<Character> set = new HashSet<>(); //stores characters that have been seen
        int ans = 0; 
        //sliding window approach
        int start = 0;
        int end = 0;
        for(end=0; end<s.length() && start <= end;){
            char c = s.charAt(end);
            if (!set.contains(c))
                set.add(s.charAt(end++));
            //base case. we found a repeating character
            else 
                set.remove(s.charAt(start++));
            ans = Math.max(set.size(), ans); 
        }
        return ans;
    }
}
