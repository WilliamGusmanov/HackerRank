class Solution {
    //maybe we can sort the intervals by first index and then last index. O(nlogn) 
    //pull out 2 min values.
    //check if the last index of 2 is less than the first index of 1. if so, merge them and put back into the data structure. Min heap. 
    //if they cannot be merged, then you know that the first value won't be merged with anything else. add the first value to the answer array. 
    
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0){
            return new int[0][]; 
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(intervals.length, new Comparator<int[]>() {
            public int compare(int[] arr1, int [] arr2){
                int cmp = Integer.compare(arr1[0], arr2[0]);
                if (cmp == 0){
                    return Integer.compare(arr1[1], arr2[1]);
                }
                return cmp; 
            }
        });
        for (int[] x : intervals){
            pq.add(x);
        }
        ArrayList<int[]> answers = new ArrayList<>(intervals.length);
        
        while (pq.peek() != null){
            int[] first = pq.poll();
            if (pq.peek() != null){
                int[] second = pq.peek();
                //[1,3] [2,5]
                System.out.println("comparing" + Arrays.toString(first) + " " + Arrays.toString(second));
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
