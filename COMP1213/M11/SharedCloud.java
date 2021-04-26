import java.text.DecimalFormat;
/**
 * A sub class store the use of data.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class SharedCloud extends CloudStorage {
   protected double dataStored;
   protected double dataLimit;
   /** constant - cost factor 1. **/
   public static final double COST_FACTOR = 1.0;
   /**
    * @param nameIn - name.
    * @param strCostIn - base storage cost.
    * @param dsIn - data stored.
    * @param dlIn - data limit.
    */
   public SharedCloud(String nameIn, double strCostIn, 
      double dsIn, double dlIn) {
      super(nameIn, strCostIn);
      dataStored = dsIn;
      dataLimit = dlIn;
   }
   /** 
    * @return dataStored.
    */
   public double getDataStored() {
      return dataStored;
   }
   /**
    * @param dsIn - set data stored.
    */
   public void setDataStored(double dsIn) {
      dataStored = dsIn;
   }
   /**
    * @return dataLimit
    */
   public double getDataLimit() {
      return dataLimit;
   }
   /**
    * @param dlIn - set data limit.
    */
   public void setDataLimit(double dlIn) {
      dataLimit = dlIn;
   }
   /**
    * @return COST_FACTOR - constant.
    */
   public double getCostFactor() {
      return COST_FACTOR;
   }
   /**
    * @return dataOverage if it's over limit, else return zero.
    */
   public double dataOverage() {
      double dataOverage = 0;
      if (dataStored > dataLimit) {
         dataOverage = dataStored - dataLimit;
      }
      return dataOverage;
   }
   /**
    * @return monthlyCost.
    */
   public double monthlyCost() {
      double monthlyCost = storageCost 
         + this.dataOverage() * SharedCloud.COST_FACTOR;
      return monthlyCost;
   }
   /**
    * @return summary of data usage.
    */
   public String toString() {
      DecimalFormat dfCost = new DecimalFormat("$#,##0.00");
      DecimalFormat dfPattern = new DecimalFormat("0.000");
      return super.toString() 
         + "Data Stored: " + dfPattern.format(dataStored) + " GB"
         + "\nData Limit: " + dfPattern.format(dataLimit) + " GB"
         + "\nOverage: " + dfPattern.format(this.dataOverage()) + " GB"
         + "\nCost Factor: " + this.getCostFactor() + "\n";
   }
 
}