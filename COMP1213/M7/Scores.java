/**
 * A class that stores the label and three axes a, b, and c.
 *
 * Activity 7 
 * @author Meiwen Dai - COMP 1213
 * @version 6/26/2020
 */
public class Scores {
   private int[] numbers;
   /**
    * @param numbersIn represent numbers insert.
    */
   public Scores(int[] numbersIn) {
      numbers = numbersIn;
   }
   /**
    * @return evens represent even numbers in the list.
    */
   public int[] findEvens() {
     //count even numbers
      int numberEvens = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 0) {
            numberEvens++;
         }
      }
      //put in array
      int[] evens = new int[numberEvens];
      int count = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 0) {
            evens[count] = numbers[i];
            count++;
         }
      }
      return evens;
   }
   /**
    * @return odds represent odd numbers in the list.
    */
   public int[] findOdds() {
      int numberOdds = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 1) {
            numberOdds++;
         }
      }
      int[] odds = new int[numberOdds];
      int count = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 1) {
            odds[count] = numbers[i];
            count++;
         }
      }
      return odds;
   }
   /**
    * @return average number of the list.
    */
   public double calculateAverage() {
      int sum = 0;
      for (int i = 0; i < numbers.length; i++) {
         sum += numbers[i];
      }
      return (double) sum / numbers.length;
   }
   /**
    * @return result of the list.
    */
   public String toString() {
      String result = "";
      for (int i = 0; i < numbers.length; i++) {
         result += numbers[i] + "\t";
      }
      return result;
   }
   /**
    * @return result represent the reverse of the list.
    */
   public String toStringInReverse() {
      String result = "";
      for (int i = numbers.length - 1; i >= 0; i--) {
         result += numbers[i] + "\t";
      }
      return result;
   }
}