import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
/**
 * Storing data in array list and sorting data.
 *
 * Project 11
 * @author Meiwen Dai - COMP 1213
 * @version 7/31/2020
 */
public class CloudStorageList {
   private CloudStorage[] list;
   private String[] invalid;
   /**
    * Set array length to zero.
    */
   public CloudStorageList() {
      list = new CloudStorage[0];
      invalid = new String[0];   
   }
   /**
    * @return list - a list of CloudStorage.
    */
   public CloudStorage[] getCloudStorageArray() {
      return list;
   }
   /**
    * @return invalid - return the invalid record.
    */
   public String[] getInvalidRecordsArray() {
      return invalid;
   }
   /**
    * @param objIn - new element add into list.
    */
   public void addCloudStorage(CloudStorage objIn) {
      list = Arrays.copyOf(list, list.length + 1);
      list[list.length - 1] = objIn;
   }
   /**
    * @param objIn - new element add into invalid list.
    */
   public void addInvalidRecord(String objIn) {
      invalid = Arrays.copyOf(invalid, invalid.length + 1);
      invalid[invalid.length - 1] = objIn;
   }
   /**
    * @param fileName - accepts the data file name.
    * @throws FileNotFoundException if file was not found.
    */
   public void readFile(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      String category, cloudName;
      double baseStorageCost;
      while (sc.hasNextLine()) {
         String nextLine = sc.nextLine();
         Scanner scanLine = new Scanner(nextLine);
         scanLine.useDelimiter(",");
         try {
            category = scanLine.next();
            switch (category.charAt(0)) {
               case 'D':
                  cloudName = scanLine.next();
                  baseStorageCost = Double.parseDouble(scanLine.next());
                  double serverCost = Double.parseDouble(scanLine.next());
                  addCloudStorage(new DedicatedCloud(cloudName, 
                     baseStorageCost, serverCost));
                  break;
               case 'S':
                  cloudName = scanLine.next();
                  baseStorageCost = Double.parseDouble(scanLine.next());
                  double dataS = Double.parseDouble(scanLine.next());
                  double dataLimitS = Double.parseDouble(scanLine.next());
                  addCloudStorage(new SharedCloud(cloudName, 
                     baseStorageCost, dataS, dataLimitS));
                  break;
               case 'C':
                  cloudName = scanLine.next();
                  baseStorageCost = Double.parseDouble(scanLine.next());
                  double dataC = Double.parseDouble(scanLine.next());
                  double dataLimitC = Double.parseDouble(scanLine.next());
                  addCloudStorage(new PublicCloud(cloudName, 
                     baseStorageCost, dataC, dataLimitC));
                  break;
               case 'P':
                  cloudName = scanLine.next();
                  baseStorageCost = Double.parseDouble(scanLine.next());
                  double dataP = Double.parseDouble(scanLine.next());
                  double dataLimitP = Double.parseDouble(scanLine.next());
                  addCloudStorage(new PersonalCloud(cloudName, 
                     baseStorageCost, dataP, dataLimitP));
                  break;
               default:
                  throw new InvalidCategoryException(category);
            }
         }
         catch (InvalidCategoryException e) {
            String invalidIn = nextLine + "\n" + e;
            addInvalidRecord(invalidIn);
         }
         catch (NumberFormatException e) {
            String invalidIn = nextLine + "\n" + e;
            addInvalidRecord(invalidIn);
         }
         catch (NoSuchElementException e) {
            String invalidIn = nextLine + "\n" + e
               + ": For missing input data";
            addInvalidRecord(invalidIn);
         }
         // finally {
            // scanLine.close();
         // }
      }
   }

   /**
    * @return result - original order monthly report.
    */
   public String generateReport() {
      String result = "-------------------------------\n";
      result += "Monthly Cloud Storage Report\n";
      result += "-------------------------------\n";
      for (int i = 0; i < list.length; i++) {
         result += list[i].toString() + "\n";
      }
      return result;
   }
   /**
    * @return result - list sort by name.
    */
   public String generateReportByName() {
      Arrays.sort(list);
      String result = "-----------------------------------------\n";
      result += "Monthly Cloud Storage Report (by Name)\n";
      result += "-----------------------------------------\n";
      for (CloudStorage item : list) {
         result += item.toString() + "\n";
      }
      return result;
   }
   /**
    * @return result - sort by monthly cost.
    */
   public String generateReportByMonthlyCost() {
      String result = "-------------------------------------------------\n";
      result += "Monthly Cloud Storage Report (by Monthly Cost)\n";
      result += "-------------------------------------------------\n";
      MonthlyCostComparator monthlyComp = new MonthlyCostComparator();
      Arrays.sort(list, monthlyComp);
      for (CloudStorage item : list) {
         result += item.toString() + "\n";
      }
      return result;
   }
   /**
    * @return result - the invalid records report.
    */
   public String generateInvalidRecordsReport() {
      String result = "----------------------\n"
         + "Invalid Records Report\n"
         + "----------------------\n";
      for (String i : invalid) {
         result += i + "\n\n";
      }
      return result;
   }
   
   
}