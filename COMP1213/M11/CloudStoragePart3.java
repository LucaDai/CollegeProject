import java.io.FileNotFoundException;
/**
 * Driver for CloudStorage.
 *
 * Project 11
 * @author Meiwen Dai - COMP 1213
 * @version 7/31/2020
 */
public class CloudStoragePart3 {
  /**
   * @param args - not used.
   */
   public static void main(String[] args) {
      CloudStorageList list = new CloudStorageList();
      if (args.length == 0) {
         System.out.println("File name expected as command line argument.");
         System.out.println("Program ending.");
      }
      else {
         try {
            list.readFile(args[0]);
            System.out.print(list.generateReport());
            System.out.print(list.generateReportByName());
            System.out.print(list.generateReportByMonthlyCost());
            System.out.print(list.generateInvalidRecordsReport());
         }
         catch (FileNotFoundException e) {
            System.out.println("*** Attempted to read file: "
               + args[0] + " (No such file or directory)");
         }
      }
   }
}
