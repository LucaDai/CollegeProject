import java.text.DecimalFormat;
/**
 * A sub class calculates monthly cost and stores server cost.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class DedicatedCloud extends CloudStorage {
   private double serverCost;
   /**
    * @param nameIn - name.
    * @param strCostIn - stroage cost.
    * @param serCostIn - server cost.
    */
   public DedicatedCloud(String nameIn, double strCostIn, double serCostIn) {
      super(nameIn, strCostIn);
      serverCost = serCostIn;
   }
   /**
    * @return serverCost.
    */
   public double getServerCost() {
      return serverCost;
   }
   /**
    * @param serCostIn - server cost.
    */
   public void setServerCost(double serCostIn) {
      serverCost = serCostIn;
   }
   /**
    * @return monthlyCost - sum of server cost and stroage cost.
    */
   public double monthlyCost() {
      double monthlyCost = serverCost + storageCost;
      return monthlyCost;
   }
   /**
    * @return summary of data.
    */
   public String toString() {
      DecimalFormat df = new DecimalFormat("$#,##0.00"); 
      return super.toString() + "Server Cost: " + df.format(serverCost) + "\n";
   }
 
}