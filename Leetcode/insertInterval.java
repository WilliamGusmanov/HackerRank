class Solution {
    //take the intervals, put them into a priority queue nlog(n)
    //then add the new interval
    //then perform the same logic as merge intervals problem...
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
         if (intervals.length == 0){
            return new int[][] {newInterval}; 
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                int cmp = Integer.compare(arr1[0],arr2[0]); //compare the first digit 
                if (cmp == 0) {
                    return Integer.compare(arr1[1], arr2[1]);
                }
                return cmp; 
            }
        });
        for (int[] x : intervals){
            pq.add(x);
        }
        pq.add(newInterval);
        
        ArrayList<int[]> answers = new ArrayList<>(intervals.length + 1);
        
         while (pq.peek() != null){
            int[] first = pq.poll();
            if (pq.peek() != null){
                int[] second = pq.peek();
                if (second[0] <= first[1]){
                    //the second might be entirely inside of first
                    //merge
                    pq.poll(); //remove second from pq 
                    pq.add(new int[] {first[0], Math.max(first[1],second[1])});
                }
                //else, don't merge, add first to answers 
                else {
                    answers.add(first);
                }
            }
            else {
                answers.add(first);
                //probably add first to answers 
            }
            //System.out.println(Arrays.toString(pq.poll()));
        }
        int[][] answerArray = new int[answers.size()][2];
        int index = 0;
        for (int[] x : answers){
            answerArray[index++] = x; 
        }
        return answerArray;         
    }
}

