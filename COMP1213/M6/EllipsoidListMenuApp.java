import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 * A class let user calling 8 method showing output for user.
 *
 * Project 6 - C
 * @author Meiwen Dai - COMP 1213
 * @version 6/19/2020
 */
public class EllipsoidListMenuApp {
/**
 * Let user calling 8 method showing output for user.
 * @param args - not used.
 * @throws FileNotFoundException - file not found.
 */
   public static void main(String[] args) throws FileNotFoundException {
      Scanner scan = new Scanner(System.in);
      ArrayList<Ellipsoid> eList;
      eList = new ArrayList<Ellipsoid>();
      char choice = 'Q';
      String listName = "*** no list name assigned ***";
      EllipsoidList ellipsoidList = new EllipsoidList(listName, eList);
      
      System.out.println("Ellipsoid List System Menu"
            + "\nR - Read File and Create Ellipsoid List"
            + "\nP - Print Ellipsoid List"
            + "\nS - Print Summary"
            + "\nA - Add Ellipsoid"
            + "\nD - Delete Ellipsoid"
            + "\nF - Find Ellipsoid"
            + "\nE - Edit Ellipsoid"
            + "\nQ - Quit");
      do {
         System.out.print("Enter Code [R, P, S, A, D, F, E, or Q]: ");
         choice = scan.nextLine().toUpperCase().charAt(0);
         switch (choice) {
            // Read
            case 'R':
               System.out.print("\tFile Name: ");
               String fileName = scan.nextLine();
               ellipsoidList = ellipsoidList.readFile(fileName);
               System.out.println("\tFile read in and Ellipsoid List" 
                  + " created\n");
               break;
            
            // Print
            case 'P':
               System.out.print(ellipsoidList.toString());
               break;
            
            // Summary   
            case 'S':
               System.out.println("\n" + ellipsoidList.summaryInfo() + "\n");
               break;
         
            // Add
            case 'A':
               System.out.print("\tlabel: ");
               String label = scan.nextLine();
               double a, b, c;
               System.out.print("\ta: ");
               a = Double.parseDouble(scan.nextLine());
               System.out.print("\tb: ");
               b = Double.parseDouble(scan.nextLine());
               System.out.print("\tc: ");
               c = Double.parseDouble(scan.nextLine());
               ellipsoidList.addEllipsoid(label, a, b, c);
               System.out.println("\t*** Ellipsoid added ***\n");
               break;
               
            // Delete
            case 'D':
               System.out.print("\tlabel: ");
               String dLabel = scan.nextLine();
               if (ellipsoidList.findEllipsoid(dLabel) != null) {
                  System.out.println("\t\"" 
                     + ellipsoidList.deleteEllipsoid(dLabel).getLabel() 
                     + "\" deleted\n");
               }
               else {
                  System.out.println("\t\"" + dLabel + "\" not found\n");
               }
               break;
               
            // Find   
            case 'F':
               System.out.print("\tlabel: ");
               String fLabel = scan.nextLine();
               if (ellipsoidList.findEllipsoid(fLabel) != null) {
                  System.out.println(ellipsoidList.findEllipsoid(fLabel) 
                     + "\n");
               }
               else {
                  System.out.println("\t\"" + fLabel + "\" not found\n");
               }
               break;
               
            // Edit
            case 'E':
               System.out.print("\tlabel: ");
               String eLabel = scan.nextLine();
               double ea, eb, ec;
               System.out.print("\ta: ");
               ea = Double.parseDouble(scan.nextLine());
               System.out.print("\tb: ");
               eb = Double.parseDouble(scan.nextLine());
               System.out.print("\tc: ");
               ec = Double.parseDouble(scan.nextLine());
               if (ellipsoidList.findEllipsoid(eLabel) != null) {
                  System.out.println("\t\"" 
                     + ellipsoidList.editEllipsoid(eLabel, 
                        ea, eb, ec).getLabel() 
                     + "\" successfully edited\n");
               }
               else {
                  System.out.println("\t\"" + eLabel + "\" not found\n");
               }
               break;
               
            case 'Q':
               break;
               
            default :
               System.out.println("\t*** invalid code ***\n");
         }
      } while (choice != 'Q');
      
   }
}