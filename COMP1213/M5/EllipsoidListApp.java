import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * A class that stores the label and three axes a, b, and c.
 *
 * Project 5 - C 
 * @author Meiwen Dai - COMP 1213
 * @version 6/12/2020
 */
public class EllipsoidListApp {
   /**
    * @param args - not used
    * @throws FileNotFoundException required by Scanner for File.
    */
   public static void main(String[] args) throws FileNotFoundException {
      // Entering file name.
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter file name: ");
      String fileName = scan.nextLine();
      
      // Scanning the file and put in arraylist.
      Scanner list = new Scanner(new File(fileName));
      ArrayList<Ellipsoid> inputList;
      inputList = new ArrayList<Ellipsoid>();
      
      // Put data in constructor.
      String listName = list.nextLine();
      while (list.hasNext()) { // Put in Ellipsoid.
         double a, b, c;
         String label = list.nextLine();
         a = Double.parseDouble(list.nextLine());
         b = Double.parseDouble(list.nextLine());
         c = Double.parseDouble(list.nextLine());
         Ellipsoid example = new Ellipsoid(label, a, b, c);
         inputList.add(example);
      }
      list.close();
      // Put in EllipsoidList.
      EllipsoidList eList = new EllipsoidList(listName, inputList);
      
      // Printing the result.
      System.out.println("\n" + eList.toString() + eList.summaryInfo());
   }

}