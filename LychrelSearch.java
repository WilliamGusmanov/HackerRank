package hwpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ICPCProblem1 {


    public static void main(String[] args) throws IOException {
        BufferedReader stdin;
        	
        int base;
        Long number;
        stdin = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> inputs = new ArrayList<>();
        String s; 
        while ((s = stdin.readLine()) != null){
          inputs.add(s);
        }
        for (int i = 0; i < inputs.size(); i++){
          String[] strings = inputs.get(i).split(" ");
          base = Integer.parseInt(strings[0]);
          number = Long.parseLong(strings[1]);
          System.out.println(ICPCProblem(base,number));
        }
    }
    public static String ICPCProblem(int base, Long number){
    	int count = 0;
    	boolean foundpalindrome = false;
        ArrayList<Integer> currentNum = convert(base, number);
       foundpalindrome = checkPalindrome(currentNum);
        while(!foundpalindrome && count < 501){
            currentNum = add(base, currentNum, ReverseArrayList(currentNum));
            foundpalindrome = checkPalindrome(currentNum);
            count++;
        }
        if (foundpalindrome){
        	return count + " " + currentNum.size();
            //System.out.print(count + " " + currentNum.size());
        }
        else {
        	return ">500";	
           //System.out.println(">500");
        }
    }
    public static Boolean checkPalindrome(ArrayList<Integer> list){
        ArrayList<Integer>rev = ReverseArrayList(list);
        boolean same = true;
        for (int i = 0; i < list.size(); i++){
            if (rev.get(i) != list.get(i)){
                same = false;
            }
        }
        return same;
    }
    public static void displayList(ArrayList<Integer> list){
        for (int i = 0; i < list.size(); i++){
            System.out.print(" " + list.get(i));
        }
    }
    public static ArrayList<Integer> add(int b, ArrayList<Integer> n1, ArrayList<Integer> n2){

       ArrayList<Integer> n3 = new ArrayList<>();
       int carry = 0;
       int r = 0;
       int sum = 0;
        for (int i = n1.size() - 1; i > -1; i--) {
            sum = n1.get(i) + n2.get(i) + carry;
            carry = 0;
            if (sum >= b) {
                carry = 1;
                r = sum % b;
                n3.add(r);
            } else {
                n3.add(sum);
            }
        }
        if (carry == 1){
            n3.add(1);
        }
        return (ReverseArrayList(n3));
    }

   public static ArrayList<Integer> convert(int b, Long number){
        ArrayList<Integer> list = new ArrayList<>();
        while (number != 0){
            list.add((int)(number % b));
            number = number / b;
        }
        return ReverseArrayList(list);
    }
    public static ArrayList<Integer> ReverseArrayList(ArrayList<Integer> L){
        ArrayList<Integer> reversedList = new ArrayList<>();
        for (int i = L.size() - 1; i > -1; i--){
            reversedList.add(L.get(i));
        }
        return reversedList;
    }

}
