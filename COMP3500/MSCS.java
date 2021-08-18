import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
/**
 * Four Maximum Sum Contiguous Subvector (MSCS) Algorithm
 *
 * @author   Luca(Meiwen) Dai (mzd0108@auburn.edu)
 * @version  7/21/2021
 *
 */
public class MSCS {
   private static final int N = 500;
   
   //Algorithm-1(X : array[P..Q] of integer) 
   public int algorithm_one(int[] x) {
     //1. maxSoFar = 0 
      int maxSoFar = 0;
      //2. for L = P to Q 
      for (int l = 0; l < x.length; l++) {
         //3. for U = L to Q 
         for (int u = l; u < x.length; u++) {
            //4. sum =0
            int sum = 0;
            //5. for I = L to U 
            for (int i = l; i <= u; i++) {
               //6. sum = sum + X[I]
               sum = sum + x[i];
               /* sum now contains the sum of X[L..U]  */
            }
            //7. maxSoFar = max (maxSoFar, sum)
            maxSoFar = Math.max(maxSoFar, sum);
         }
      }
      //8. return maxSoFar
      return maxSoFar;
   }
   
   //Algorithm-2(X : array[P..Q] of integer)
   public int algorithm_two(int[] x) {
      //1. maxSoFar = 0
      int maxSoFar = 0;
      // 2. for L = P to Q
      for (int l = 0; l < x.length; l++){
         //3. sum =0
         int sum = 0;
         //4. for U = L to Q
         for (int u = l; u < x.length; u++) {
            //5. sum = sum + X[U]
            sum = sum + x[u];
            /* sum now contains the sum of X[L..U] */
            //6. maxSoFar = max(maxSoFar,sum)
            maxSoFar = Math.max(maxSoFar, sum);
         }
      }
      return maxSoFar;
   }
   
   //Algorithm-3
   //recursive function MaxSum(X[L..U]: array of integer, L, U: integer)
   public int maxSum(int[] x, int l, int u) {
      //1. if L > U then
      //    return 0   /* zero- element vector */
      if (l > u) {
         return 0;
      }
      //2. if L=U then
      //    return max(0,X[L])  /* one-element vector */
      if (l == u) {
         return Math.max(0, x[l]); 
      }
      //3. M = (L+U)/2  /* A is X[L..M], B is X[M+1..U] */
      int m = (l + u) / 2;
      /* Find max crossing to left */
      //4. sum = 0; maxToLeft =0
      int sum = 0;
      int maxToLeft = 0;
      //5. for I = M downto L do
      for (int i = m; i >= l; i--) {
         //6. sum =sum+X[I]
         sum = sum + x[i];
         //7. maxToLeft = max(maxToLeft,sum)
         maxToLeft = Math.max(maxToLeft, sum);
      }
      /* Find max crossing to right */
      //8. sum=0; maxToRight=0
      sum = 0;
      int maxToRight = 0;
      //9. for I = M+1 to U
      for (int i = m + 1; i <= u; i++) {
         //10. sum=sum+X[I]
         sum = sum + x[i];
         //11. maxToRight = max(maxToRight, sum)
         maxToRight = Math.max(maxToRight, sum);
      }
      //12. maxCrossing = maxToLeft + maxToRight
      int maxCrossing = maxToLeft + maxToRight;
      //13. maxInA = maxSum(X, L, M)
      int maxInA = maxSum(x, l, m);
      //14. maxInB = maxSum(X,M+1,U)
      int maxInB = maxSum(x,m+1,u);
      //Question: Can I add a line myself?
      int maxAB = Math.max(maxInA, maxInB);
      //15. return max(maxCrossing, maxInA, maxInB)
      //three int can't using Math.max() method
      return Math.max(maxCrossing, maxAB);
   }
   //Algorithm-4(X : array[P..Q] of integer)
   public int algorithm_four(int[] x) {
      //1. maxSoFar = 0
      int maxSoFar = 0;
      //2. maxEndingHere= 0
      int maxEndingHere = 0;
      //3. for I = P to Q 
      for (int i = 0; i < x.length; i++) {
         //4. maxEndingHere = max(0, maxEndingHere + X[I])
         maxEndingHere = Math.max(0, maxEndingHere + x[i]);
         //5. maxSoFar = max(maxSoFar, maxEndingHere)	
         maxSoFar = Math.max(maxSoFar, maxEndingHere);
      }
      //6. return maxSoFar
      return maxSoFar;
   }
   
   public static void main(String[] args) {
      //read integers from phw_input.txt
      String[] input = new String[10];
      try {
         Scanner sc = new Scanner(new File("phw_input.txt")).useDelimiter(",|$");
         int i = 0;
         while(sc.hasNext()) {
            input[i] = sc.next();
            i++;
         }
      } 
      catch(Exception e) {
         System.out.println("FileNotFoundException");
      }
      int[] list_one = new int[input.length];
      for(int i = 0; i < input.length; i++) {
         list_one[i] = Integer.parseInt(input[i]);
      }
      MSCS test = new MSCS();
      int one = test.algorithm_one(list_one);
      int two = test.algorithm_two(list_one);
      int three = test.maxSum(list_one, 0, list_one.length - 1);
      int four = test.algorithm_four(list_one);
      System.out.println("Maximum Sum Contiguous Subvector Results from phw_input.txt:");
      System.out.println("Algorithm-1: " + one);
      System.out.println("Algorithm-2: " +two);
      System.out.println("Algorithm-3: " +three);
      System.out.println("Algorithm-4: " +four);
      
      //Create 19 integer sequences of length 10, 15, 20, 25...90, 95, 100
      int[][] matrix = new int[19][];
      for(int i = 0; i < 19; i++) {
         matrix[i] = new int[i*5 + 10];
         //Enter random numbers.
         randomArray(matrix[i]);
      }         
      
      long t1;
      long t2;
      long start;
      long average;
      long avgAlgorithmOne[] = new long[19];
      long avgAlgorithmTwo[] = new long[19];
      long avgAlgorithmThree[] = new long[19];
      long avgAlgorithmFour[] = new long[19];
      
      //Measure average time of algorithm-1.
      for (int i = 0; i < 19; i++) {
         start = System.nanoTime();
         for (int j = 0; j < N; j++) {
            test.algorithm_one(matrix[i]);
         }
         t1 = System.nanoTime() - start;
         start = System.nanoTime();
         for (int k = 0; k < N; k++) {
            test.algorithm_one(matrix[i]);
         }
         t2 = System.nanoTime() - start;
         average = (t1 + t2) / 2;
         avgAlgorithmOne[i] = average;
      }
      
      //Measure average time of algorithm-2.
      for (int i = 0; i < 19; i++) {
         start = System.nanoTime();
         for (int j = 0; j < N; j++) {
            test.algorithm_two(matrix[i]);
         }
         t1 = System.nanoTime() - start;
         start = System.nanoTime();
         for (int k = 0; k < N; k++) {
            test.algorithm_two(matrix[i]);
         }
         t2 = System.nanoTime() - start;
         average = (t1 + t2) / 2;
         avgAlgorithmTwo[i] = average;
      }
      
      //Measure average time of algorithm-3.
      for (int i = 0; i < 19; i++) {
         start = System.nanoTime();
         for (int j = 0; j < N; j++) {
            test.maxSum(matrix[i], 0, matrix[i].length - 1);
         }
         t1 = System.nanoTime() - start;
         start = System.nanoTime();
         for (int k = 0; k < N; k++) {
            test.maxSum(matrix[i], 0, matrix[i].length - 1);
         }
         t2 = System.nanoTime() - start;
         average = (t1 + t2) / 2;
         avgAlgorithmThree[i] = average;
      }
      
      //Measure average time of algorithm-4.
      for (int i = 0; i < 19; i++) {
         start = System.nanoTime();
         for (int j = 0; j < N; j++) {
            test.algorithm_four(matrix[i]);
         }
         t1 = System.nanoTime() - start;
         start = System.nanoTime();
         for (int k = 0; k < N; k++) {
            test.algorithm_four(matrix[i]);
         }
         t2 = System.nanoTime() - start;
         average = (t1 + t2) / 2;
         avgAlgorithmFour[i] = average;
      }
      
      //Calculate predicted time
      int inputSize;
      int[] predictOne = new int[19];
      int[] predictTwo = new int[19];
      int[] predictThree = new int[19];
      int[] predictFour = new int[19];
      for (int a = 0; a < 19; a++) {
         inputSize = a * 5 + 10;
         predictOne[a] = predictOne(inputSize) * 500;
      }
      for (int b = 0; b < 19; b++) {
         inputSize = b * 5 + 10;
         predictTwo[b] = predictTwo(inputSize) * 500;
      }
      for (int c = 0; c < 19; c++) {
         inputSize = c * 5 + 10;
         predictThree[c] = predictThree(inputSize) * 500;
      }
      for (int d = 0; d < 19; d++) {
         inputSize = d * 5 + 10;
         predictFour[d] = predictFour(inputSize) * 500;
      }
      //Write the matrix of integers into file.
      try {
         File output = new File("MeiwenDai_phw_output.txt");
         FileWriter fw = new FileWriter(output);
         fw.write("Input Size,algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)\n");
         for(int i = 0; i<19; i++) {
            fw.write((i*5+10)+ "," + avgAlgorithmOne[i] + "," + avgAlgorithmTwo[i] + "," + avgAlgorithmThree[i] + "," + avgAlgorithmFour[i] + "," +
               predictOne[i] + "," + predictTwo[i] + "," + predictThree[i] + "," + predictFour[i] + "\n");
         }
         fw.close();
      }
      catch (Exception e) {
         System.out.println("Exception thrown.");
      }
     
   }
    //Create a random array 
   public static void randomArray(int[] X) {
      Random r = new Random();
      for(int i = 0; i < X.length; i++) {
         X[i] = r.nextInt();
      }
   }
   public static int predictOne(int n) {
      int result = 0;
      result = n * n *n / 3 + 5 * n *n / 2 + 25 * n / 6 + 4;
      return result;
   } 
   public static int predictTwo(int n) {
      int result= 0;
      result = 7 * n * n + 10 * n + 5;
      return result;
   }
   public static int predictThree(int n) {
      int result = 0;
      result = 17 * n - 24;
      return result;
   }
   public static int predictFour(int n) {
      int result;
      result = 18 * n + 22;
      return result;
   }

    
}
