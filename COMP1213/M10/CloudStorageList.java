import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Storing data in array list and sorting data.
 *
 * Project 10
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
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
    * @param fileNameIn - accepts the data file name.
    * @throws FileNotFoundException if file was not found.
    */
   public void readFile(String fileNameIn) 
      throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileNameIn));
      sc.useDelimiter(",|\\n");
      while (sc.hasNext()) {
         String catergory = sc.next();
         String name;
         double cost1, cost2, cost3;
         switch (catergory.charAt(0)) {
            case 'D':
               name = sc.next();
               cost1 = Double.parseDouble(sc.next());
               cost2 = Double.parseDouble(sc.next());
               DedicatedCloud d = new DedicatedCloud(name, cost1, cost2);
               addCloudStorage(d);
               break;
               
            case 'S':
               name = sc.next();
               cost1 = Double.parseDouble(sc.next());
               cost2 = Double.parseDouble(sc.next());
               cost3 = Double.parseDouble(sc.next());
               SharedCloud s = new SharedCloud(name, cost1, cost2, cost3);
               addCloudStorage(s);
               break;
               
            case 'C':
               name = sc.next();
               cost1 = Double.parseDouble(sc.next());
               cost2 = Double.parseDouble(sc.next());
               cost3 = Double.parseDouble(sc.next());
               PublicCloud c = new PublicCloud(name, cost1, cost2, cost3);
               addCloudStorage(c);
               break;
            
            case 'P':
               name = sc.next();
               cost1 = Double.parseDouble(sc.next());
               cost2 = Double.parseDouble(sc.next());
               cost3 = Double.parseDouble(sc.next());
               PersonalCloud p = new PersonalCloud(name, cost1, cost2, cost3);
               addCloudStorage(p);
               break;
            default: 
               addInvalidRecord(sc.nextLine());
         }
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
   
   
}