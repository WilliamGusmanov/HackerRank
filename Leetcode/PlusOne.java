class Solution {
    public int[] plusOne(int[] digits) {
        //we must add a value to the far right
        //however we must account for the remainder running left if a sum > 9
        int index = digits.length - 1; //we start at the end 
        int remainder = 1;
        //move left
        while (index > -1 && remainder != 0){
            int temp = digits[index] + remainder;
            remainder = 0; 
            if (temp > 9){
                digits[index] = 0;
                remainder = 1;
                index--; 
            }
            else digits[index] = temp;
        }
        //if remainder != 0, insert the remainder to the head
        if (remainder != 0){
            return insertToHead(digits, remainder);
        }
        else return digits; 
    }
    public int[] insertToHead(int[] digits, int value){
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = value;
        for (int i = 0; i < digits.length; i++){
            newDigits[i + 1] = digits[i];
        }
        return newDigits; 
    }
}
