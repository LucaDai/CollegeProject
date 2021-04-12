/**
 * Constructor of NumberOpsDriver that support method.
 * And calculate odd and powers under.
 *
 * Activity 5 - A 
 * @author Meiwen Dai - COMP 1213
 * @version 6/10/2020
 */

public class NumberOperations {
   private int number;
   /**
    * @param numberIn input value.
    */
   public NumberOperations(int numberIn) {
      number = numberIn;
   }
   /**
    * @return number
    */
   public int getValue() {
      return number; 
   }
   /**
    * @return output
    */
   public String oddsUnder() {
      String output = "";
      int i = 0;
      while (i < number) {
         if (i % 2 != 0) {
            output += i + "\t";
         }
         i++;
      }
      return output;
   }
   /**
    * @return output
    */
   public String powersTwoUnder() {
      String output = "";
      int powers = 1;
      while (powers < number) {
         output += powers + "\t";
         powers = powers * 2;   
      }
      return output;
   }
   /**
    * @param compareNumber comparing numbers.
    * @return 1 || -1 || 0.
    */
   public int isGreater(int compareNumber) {
      if (number > compareNumber) {
         return 1;
      }
      else if (number < compareNumber) {
         return -1;
      }
      else {
         return 0;
      }
   }
   /**
    * @return number
    */
   public String toString() {
      return number + "";
   }
}