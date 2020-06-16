import java.io.*;
import java.util.Arrays;

//216, 2: 21
//126, 2: Student answer: 16
//1424, 2 : Student answer: 56   Actual: 53 Wrong


public class RomCom {
    public int[] processImage() throws IOException {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String x = br.readLine();
        String y = br.readLine();
        int X = Integer.parseInt(x);
        int Y = Integer.parseInt(y);
        int[] array = new int[]{X,Y};
        return array;
    }

    public void printAnswers(int[] arr) {
        System.out.println("printing array values: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public void outputFile(long result){
        try{
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFileStream));
            writer.write(result + "");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("error occurred");
            e.printStackTrace();
        }
    }
    public void printAnswers(long[] arr, long s, long f) {
        System.out.println("printing array values: ");
        for (int i = (int)s; i <= f; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("completed display");
    }

    public int findRomDynamicCom(int [] array){
        int N = array[0]; //number of days
        int k = array[1]; //number of probes
        //base case do a linear search
        System.out.println("k: " + k);
        System.out.println("N: " + N);

        int [][] matrix = new int[k+1][N+1]; // probes by days
        //probes already initalized to 0.
        //base cases
        //if we only have 1 probe. Then for any amount of days, it will be a 'day' sized linear search
        for (int days = 1; days < N + 1; days++){
            matrix[1][days] = days;
        }
        //if its 1 day, it will be a single search for all # of probes.
        for (int probes = 1; probes < k + 1; probes++){
            matrix[probes][1] = 1;
        }
        //compute the non-trivial searches
        for (int probes = 2; probes < k + 1; probes++){
            for (int days = 2; days < N + 1; days++){
                matrix[probes][days] = Integer.MAX_VALUE;
                //either the probe breaks or it does not.
                //if probe breaks, look at the day before with 1 less probe
                //else if it does not break, look at the days left to search with same # of probes.
               findWorstTrial(matrix, probes, days);
            }
        }
        displayMatrix(matrix);
        return matrix[k][N];
    }

    public void findWorstTrial(int [][] matrix, int probes, int days){
        for (int currentDay = 1; currentDay < days + 1; currentDay++){
            //2 possibilities to check: it breaks || it !breaks
            //want to find the least searches to the worst case day.
            //if it !breaks => same # of probes, check remaining days. => matrix[probes][days - currentDay]
            //ex.) if 25 total days and we are looking at 15th day and it !breaks, must check 10 days.
            //if it breaks => 1 less probe, check 1 day before at worst case. => matrix[probes - 1][currentDay - 1]
            matrix[probes][days] = Math.min(Math.max(matrix[probes][days - currentDay], matrix[probes - 1][currentDay - 1]) + 1, matrix[probes][days]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("calculating...");
        RomCom r = new RomCom();
        long startTime = System.currentTimeMillis();
        //galloping method
        int[] array = r.processImage();
        //r.printAnswers(array);
        //int [] array = new int [] {1424, 2};
        //int result = r.findRomCom(array);
        int result = r.findRomDynamicCom(array);
        System.out.println(result);
        r.outputFile(result);
        long endTime = System.currentTimeMillis();
        System.out.println("milliseconds: " + (endTime - startTime));
        System.out.println("total seconds: " + ((endTime - startTime)/1000));

    }
}
