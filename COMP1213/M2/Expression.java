import java.util.Scanner;
/**
 * Simple program calculate xy value.
 *
 * Project 2 - Part A
 * @author Meiwen Dai - COMP 1213
 * @version 5/27/2020
 */
public class Expression {
   /**
    * Reading input for x and y, and output using if-else statement.
    *
    * @param args - not used.
    */
   public static void main(String[] args) {
      Scanner userInput = new Scanner(System.in);
      // Adding variable x and variable y
      double varX, varY, result;
      
      System.out.println("result = (10x + 32.6) (5y - 1.567) / xy");
      // Getting variable x
      System.out.print("\tx = ");
      // Getting variable y
      varX = userInput.nextDouble();
      System.out.print("\ty = ");
      varY = userInput.nextDouble();
      if (varX * varY == 0) {
         System.out.println("result is \"undefined\"");
      }
      else {
         result = (10 * varX + 32.6) * (5 * varY - 1.567) / (varX * varY);
         System.out.println("result = "  +  result);
      }
      
   }

}