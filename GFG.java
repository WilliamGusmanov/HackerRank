
import java.util.*;
        import java.lang.*;
        import java.io.*;
class GFG {
    public static void main (String[] args) {
        /*
        Scanner in = new Scanner(System.in);
        int testcases = in.nextInt();
        for (int i = 0; i < testcases; i++){
          bitPermutation(in.next());
        }
        */
        bitPermutation("?11?1001???1");
    }
    public static void bitPermutation(String s){
        if (s.length() == 0){
            return;
        }
        ArrayList<Character[]>_list = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();
        Character[] t1 = new Character[s.length()];
        t1[0] = s.charAt(0);
        _list.add(t1);
        //O(size of string * size of _list)
        for (int i=0; i < s.length(); i++){
            if (s.charAt(i) == '?'){
                ArrayList<Character[]>_tempList = new ArrayList<>();
                for (Character[] element : _list){
                    element[i] = '0';
                    Character[] copy1 = element.clone();
                    copy1[i] = '1';
                    _tempList.add(copy1);
                }
                for (Character[] element : _tempList){
                    _list.add(element);
                }
            }
            else if (s.charAt(i) == '0' || s.charAt(i) == '1'){
                for (Character [] element : _list) {
                    element[i] = s.charAt(i);
                }
            }
        }
        for (Character[] element : _list){
            for (int i = 0; i < element.length; i++){
                System.out.print(element[i]);
            }
            System.out.println();
        }
    }
}
