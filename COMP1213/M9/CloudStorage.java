import java.text.DecimalFormat;
/**
 * An abstract class that describes cloud storage data 
 * and provides methods to access the data.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public abstract class CloudStorage {
   protected String name;
   protected double storageCost;
   protected static int count = 0;
   /**
    * @param nameIn - name.
    * @param costIn - base storage cost.
    */
   public CloudStorage(String nameIn, double costIn) {
      name = nameIn;
      storageCost = costIn;
      count++;
   }
   /**
    * @return name.
    */
   public String getName() {
      return name;
   }
   /**
    * @param nameIn - name.
    */
   public void setName(String nameIn) {
      name = nameIn;
   }
   /**
    * @return cost.
    */
   public double getBaseStorageCost() {
      return storageCost;
   }
   /** 
    * @param costIn - base storage cost.
    */
   public void setBaseStorageCost(double costIn) {
      storageCost = costIn;
   }
   /** 
    * @return count.
    */
   public static int getCount() {
      return count;
   }
   /** 
    * Reset count to zero.
    */
   public static void resetCount() {
      count = 0;
   }
   /**
    * @return a String describing the CloudStorage object.
    */
   public String toString() {
      String className = this.getClass().getName();
      DecimalFormat df = new DecimalFormat("$#,##0.00");
      return name + " (class " + className 
         + ") Monthly Cost: " + df.format(monthlyCost()) 
         + "\nBase Storage Cost: " + df.format(storageCost) + "\n";
   }
   /** 
    * @return double.
   */
   public abstract double monthlyCost();
 
}