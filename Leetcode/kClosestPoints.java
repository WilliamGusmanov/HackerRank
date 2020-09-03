class Solution {
    //it appears we need the formula a^2 + b^2 = c^2 where c will be our distance from the origin
    //keep a max heap of size k. will store the closest 7 points
    //every insert/deletion operation will be log(k)
    //so we are doing n insertions so this algorithm will be O(n*log(k)) 
    
    
    public int[][] kClosest(int[][] points, int K) {
        if (points.length == 0 || points[0].length == 0){
            return points;
        }
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(//Collections.reverseOrder(), 
        (a, b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]))); 
        
        for (int i = 0; i < points.length; i++){
            maxHeap.add(points[i]);
            
            if (maxHeap.size() > K){
                //maxHeap.poll();
                System.out.println(Arrays.toString(maxHeap.poll()));
            }
        }
        
        int[][] answerArray = new int[K][2];
        for (int j = 0; j < K; j++){
            answerArray[j] = maxHeap.poll(); 
        }    
        return answerArray; 
    }
}
