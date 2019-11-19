
import javax.swing.text.AttributeSet;
import java.util.*;
        import java.lang.*;
        import java.io.*;
class GFG {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int testcases = in.nextInt();
        for (int i = 0; i < testcases; i++){
          bitPermutation(in.next());
        }
    }
    public static void bitPermutation(String s) {
        if (s.length() == 0){
            return;
        }
        Queue<char []> q = new LinkedList<>();
        q.add(s.toCharArray());
        while (!q.isEmpty()){
            char[] current = q.peek();
            int indexQ = -1;
            boolean found = false;
            for (int i = 0; i < current.length && !found; i++){
                if (current[i] == '?'){
                    indexQ = i;
                    found = true;
                }
            }
            if (indexQ > 0){
                current[indexQ] = '0';
                q.add(current.clone());
                current[indexQ] = '1';
                q.add(current.clone());
            }
            //if no question marks were found
            else {
                System.out.println(new String(current));
            }
            q.remove();
        }
    }
}
