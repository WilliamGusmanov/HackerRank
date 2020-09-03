class Logger {
    /*
    a hashmap with a word as key, last recorded time as element.
    to check if printable. subtract the current word with the word in the map. ex.) 16 - 7 = 9 if difference is less than 10, then return true. 
    */
    
    
    /** Initialize your data structure here. */
    
    Map<String, Integer> map;
    
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.get(message) == null) {
            map.put(message, timestamp); //put in the "printed time"
            return true;
        }
        int oldtime = map.get(message);
        if (timestamp - oldtime >= 10) {
            map.put(message, timestamp); //replace old time w/ new time.
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
