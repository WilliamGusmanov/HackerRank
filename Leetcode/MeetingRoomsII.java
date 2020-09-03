class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length < 2){
            return intervals.length;
        }
        //sort by first index
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                Integer aInt = a[0];
                Integer bInt = b[0];
                return aInt.compareTo(bInt);
            }    
        });
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(intervals[0][1])); //guaranteed to need at least one room
        //for each interval 
        for (int i = 1; i < intervals.length; i++){ 
            int startTime = intervals[i][0];
            //find a room where the start time is after the end time, then add the end time to that room. 
            boolean found = false; 
            for (Room r : rooms){
                //if the start time begins at or after the end time, room is vacant. 
                if (r.endtimes.peek() <= startTime){
                    r.endtimes.add(intervals[i][1]); //add the end time 
                    found = true; 
                    break; 
                }
            }
            if (!found){
                rooms.add(new Room(intervals[i][1]));
            }
        }     
        return rooms.size(); 
    }
    
    private class Room {
        PriorityQueue<Integer> endtimes = new PriorityQueue<>(Collections.reverseOrder()); //min heap
        Room(int endTime){
            endtimes.add(endTime);
        }
    }
}
