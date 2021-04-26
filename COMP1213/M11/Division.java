/**
 * a class containing the devide methods.
 *
 * Activity 11
 * @author Meiwen Dai - COMP 1213
 * @version 7/29/2020
 */
public class Division {
   /** not used the constructor. */
   public Division() {
   
   }
   /**
    * @param num - int numerator.
    * @param denom - int denominator.
    * @return reuslt int variable - numerator divided by denominator.
    */
   public static int intDivide(int num, int denom) {
      try {
         return num / denom;
      }
      catch (ArithmeticException e) {
         return 0;
      }
   }
   /**
    * @param num - int numerator.
    * @param denom - int denominator.
    * @return reuslt int variable - numerator divided by denominator.
    */
   public static double decimalDivide(int num, int denom) {
      if (denom == 0) {
         throw new IllegalArgumentException("The denominator "
            + "cannot be zero.");
      }
      double result = (double) num / denom;
      return result;
   }
   
}