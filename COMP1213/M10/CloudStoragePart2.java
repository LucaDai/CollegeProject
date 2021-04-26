import java.io.FileNotFoundException;
/**
 * Main class of cloud storage.
 *
 * Project 10
 * @author Meiwen Dai - COMP 1213
 * @version 7/25/2020
 */
public class CloudStoragePart2 {
/**
 * @param args - String array.
 * @throws FileNotFoundException if file was not found.
 */
   public static void main(String[] args) throws FileNotFoundException {
      CloudStorageList list = new CloudStorageList();
      if (args.length == 0) {
         System.out.println("File name expected as command line argument.");
         System.out.println("Program ending.");
      }
      else {
         list.readFile(args[0]);
         System.out.print(list.generateReport());
         System.out.print(list.generateReportByName());
         System.out.print(list.generateReportByMonthlyCost());
      }
   }
}