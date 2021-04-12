import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * Simple program using calculating equation and show information of the result.
 *
 * Project 3 - Part A
 * @author Meiwen Dai - COMP 1213
 * @version 5/29/2020
 */
public class Solver {
   /**
    * Calculate reuslt of x using Math class and show it using String class.
    *
    * @param args - not used.
    */ 
   public static void main(String[] args) {
      Double x;
      // molecule in sqrt and abs.
      Double moleculeIn;
      Double denominator;
      Double result;
      
      Scanner userInput = new Scanner(System.in);
      
      System.out.print("Enter a value for x: ");
      x = userInput.nextDouble();
      
      // calculate result.
      moleculeIn = 11 * Math.pow(x, 4) 
         + 9 * Math.pow(x, 3) 
         + 7 * Math.pow(x, 2) 
         + 5 * x + 4;
      denominator = 2 * x + 4;
      result = Math.sqrt(Math.abs(moleculeIn)) / denominator;
      System.out.println("Result: " 
         + result);
      
      //find characters.
      String resultString = Double.toString(result);
      int dot;
      int count;
      dot = resultString.indexOf(".");
      count = resultString.length();
      System.out.println("# of characters to left of decimal point: " 
         + dot);
      System.out.println("# of characters to right of decimal point: " 
         + (count - dot - 1));
      
      //show formatted result
      DecimalFormat df = new DecimalFormat("#,##0.0####");
      System.out.println("Formatted Result: " 
         + df.format(result));
      
   }
   
}