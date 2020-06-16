import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
public class complexMultiplication
{
    /**
     *   matrix interpretation of FFT in world of mod P
     */
    public long[] inverseMatrixFFT(long[] array, long root, long p){
        // Returns the inverse number-theoretic transform of the given vector with
        // respect to the given primitive nth root of unity under the given modulus.

            int n = array.length;
            long conjugate_n = findInverse(n, p); //conjugate of the array size. The same as 1/n in complex plane.
            long conjugate_r = findInverse(root, p); //conjugate of r. The Same as inverse omega.

             System.out.println("n is : " + n);
             System.out.println("conjugate r : " + conjugate_r);
             System.out.println("p is: " + p);
            long[] answer = new long[n];
            //transform the vector
            for (int row = 0; row < n; row++) {
                long kthSum = 0;
                for (int col = 0; col < n; col++) {
                    long superscript = (((long)row * (long)col) % n); //w^0*0, w^0*1.....w^(n-1)*(n-1)
                    long powerOmega = power(conjugate_r, superscript, p);
                    kthSum = ((array[col] * powerOmega) + kthSum) % p;
                }
                answer[row] = kthSum; //The sum of k groupings. Still needs to be multiplied by conjugate of matrix size
                System.out.println("kth sum: " + kthSum);
            }

            System.out.println("transform: ");
            for (long i : answer){
                System.out.println(i + " "); //DEBUG
            }
           System.out.println("end transform. ");
            //apply conjugate '(1/n)' to every answer
            for (int i = 0; i < answer.length; i++)
                answer[i] = ((answer[i] * conjugate_n) % p);


            System.out.println("inverse transform: ");
            for (long i : answer){
                System.out.println(i + "");
            }
            return answer;
        }

    /*
    public long biggerPower(long number, long superscript, long p){
        long result = 1;
        for (int i = 0; i < superscript; i++){
            result = (result * number) % p;
            }
        return result;
        }
*/
    /*

     * inverse FFT with modular p
     * returns the coefficients of the 'complex' coefficients
     */
    //inverwse w sub n ^ (-kj /n)
    //p = (2^k) * c + 1 where c & k are arbitary constants.

    /*
    public long[] inverseFFT(long[] array, long w, long p) {
        int n = array.length;
        System.out.println("n: " + n);
        //System.out.println("n is: " + n);
        if (n == 1){
            return array; //arr[i] * r^0
        }

        long r = findInverse(w, p);

        //long k = (p - 1) / n; //verify theory
        //long wSubN = ((long)Math.pow(r, k)) % p; //verify theory
        //long w = 1; //r ^ (-ck) ?
        //System.out.println("inverse: " + r);

        long[] evenArray = new long [n/2];
        long[] oddArray = new long [n/2];

        EvenAndOddArray(array, evenArray, oddArray); //initialize even and odd arrays

        //DEBUG
        System.out.println("even array: ");
        printAnswers(evenArray);
        System.out.println("odd array: ");
        printAnswers(oddArray);


        long [] transformedEven = inverseFFT(evenArray, r, p);
        long [] transformedOdd = inverseFFT(oddArray, r, p);

        System.out.println("Teven array: ");
        printAnswers(transformedEven);
        System.out.println("Todd array: ");
        printAnswers(transformedOdd);

        long [] resultArray = new long[n];

        for (int k = 0; k < n/2; k++){
            //lower half of array: yk = yk(even) + w * yk(odd)
            //w = 30
            //upper half of array: yk + n/2 = yk(even) - w * yk(odd)
            //System.out.println("k: " + k + " k + n/2: " + (k + n/2));
            System.out.println("r: " + r);
            resultArray[k] = (w * (transformedEven[k] + (r * transformedOdd[k]))) % p;
            resultArray[k + n/2] = (w * (transformedEven[k] - (r * transformedOdd[k]))) % p;
            r = (long) (Math.pow(r, k) % p); //increment w
            System.out.println("r: " + r);
        }
        System.out.println("result array: ");
        printAnswers(resultArray);
        return resultArray;
    }
    /**
     *
     * returns inverse of a mod p
     */
    static long findInverse(long input, long p)
    {
        long inverse = (long)power(input,p - 2, p);
        return inverse; 
    }

    //39285 39719 4505 45733 44236 10560 28989 3815 1287 30494 27884 37069 10504 8495 43530 31990
    static long power(long x, long y, long p)
    {
        if (y == 0)
            return 1;

        long a =  power(x, y / 2, p) % p;
         a = ( a *  a) % p;

        if (y % 2 == 0)
            return a;
        else
            return (x * a) % p;
    }

    public void EvenAndOddArray(long [] array, long [] evenArray, long [] oddArray){
        for (int i = 0; i < array.length; i++){
            System.out.println("i: " + i );
            if (i % 2 == 0) //if even
                evenArray[i/2] = array[i];
            else { //if odd
                oddArray[(i - 1)/ 2] = array[i];
            }
        }
    }

   public ArrayList<Long> processFile() throws IOException {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Long> input = new ArrayList<>();
       String line = null;
       while ((line = br.readLine()) != null) {
           input.add((Long.parseLong(line)));
       }
       return input;
    }

    public void printAnswers(long[] arr) {
        System.out.println();
        for (long i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    //25, 28, 9
    public void outputFile(long[] array){
        try{
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));

            for (int i = array.length - 2; i > -1; i--){
                int x = (int)array[i];
                writer.write(x + "");
                writer.newLine();
            }
            writer.close();
            System.out.println("wrote to file");
        }
        catch (IOException e) {
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }

     public static void main(String[] args) throws IOException {



       complexMultiplication c = new complexMultiplication();
         System.out.println(c.power(41495, 2, 47569));


       ArrayList<Long> inputList = c.processFile();

       long p = inputList.get(0); //get p
       long r = inputList.get(1); //get r
         System.out.println("p:" + p);
         System.out.println("r: " + r);
       long [] inputArray = new long [inputList.size() - 2];
       for (int i = 0; i < inputList.size() - 2; i++){
                inputArray[i] = inputList.get(i + 2);
       }
       c.printAnswers(inputArray);
       long [] answers = c.inverseMatrixFFT(inputArray, r, p);
       c.printAnswers(answers);
       c.outputFile(answers);

       //System.out.println("final array: ");
       //c.printAnswers(answers);
       //c.applyInverseCoefficient(answers, p);
       //c.printAnswers(answers);
    }
}
