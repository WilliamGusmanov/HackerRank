class Solution {
    public int maxArea(int[] height) {
        int answer = 0, left = 0, right = height.length - 1;
        //time: O(n)
        //space: O(n) for the array. Everything else is constant.
        while (left < right){
            answer = Math.max(answer, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]){
                left++;
            }
            else {
                right--;
            }
        }
        return answer;
    }
}
