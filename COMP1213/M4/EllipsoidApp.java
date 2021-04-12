import java.util.Scanner;
/**
 * A program read values and calculate it ellipsoid volume and surface area. 
 *
 * Project 4 - B 
 * @author Meiwen Dai - COMP 1213
 * @version 6/5/2020
 */
public class EllipsoidApp {
   /**
    * Reads in values for label and the axes a, b, and c.
    * And calculate volume and surface area then print the output.
    *
    * @param args - not used.
    */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter label and axes a, b, c for an ellipsoid.");
      System.out.print("\tlabel: ");
      String label = input.nextLine();
      System.out.print("\ta: ");
      double a = Double.parseDouble(input.nextLine());
      if (a > 0) {
         System.out.print("\tb: ");
         double b = Double.parseDouble(input.nextLine());
         if (b > 0) {
            System.out.print("\tc: ");
            double c = Double.parseDouble(input.nextLine());
            if (c > 0) {
               Ellipsoid values = new Ellipsoid(label, a, b, c);
               System.out.println("\n" + values);
            }
            else {
               System.out.println("Error: axis value must be positive.");
            }
         }
         else {
            System.out.println("Error: axis value must be positive.");
         }
      }
      else {
         System.out.println("Error: axis value must be positive.");
      }
   }
}