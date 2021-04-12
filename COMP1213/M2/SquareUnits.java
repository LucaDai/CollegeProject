import java.util.Scanner;
/**
 * Simple program calcultate unite of measurement from user input.
 *
 * Project 2 - Part B
 * @author Meiwen Dai - COMP 1213
 * @version 5/27/2020
 */
public class SquareUnits {
  /**
   *Reading input of square inches, and output it by other measurement.
   *
   * @param args - not used.
   */
   public static void main(String[] args) {
      Scanner userInput = new Scanner(System.in);
      int squareInches, acres, squareYards, squareFeet, squareInches2;
      // Input square inches
      System.out.print("Enter the area in square inches: ");
      squareInches = userInput.nextInt();
      if (squareInches <= 1000000000) {
         // Storing the variable in int.
         acres = squareInches / 6272640;
         squareYards = (squareInches % 6272640) / 1296;
         squareFeet = ((squareInches % 6272640) % 1296) / 144;
         squareInches2 = ((squareInches % 6272640) % 1296) % 144;
         System.out.println("Number of Units: ");
         System.out.println("\tAcres: " + acres);
         System.out.println("\tSquare Yards: " + squareYards);
         System.out.println("\tSquare Feet: " + squareFeet);
         System.out.println("\tSquare Inches: " + squareInches2);
         
      }
      else {
         System.out.println("Limit of 1,000,000,000 square inches exceeded!");
      
      }
   
   }

}