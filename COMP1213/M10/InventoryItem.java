/**
 * A class represents an inventory item in a store.
 *
 * Activity 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class InventoryItem {
   protected String name;
   protected double price;
   private static double taxRate = 0;
   /**
    * Constructor.
    * @param nameIn - set for name.
    * @param priceIn - set for price.
    */
   public InventoryItem(String nameIn, double priceIn) {
      name = nameIn;
      price = priceIn;
   }
   // Method.
   /**
    * @return name.
    */
   public String getName() {
      return name;
   }
   /**
    * @return price * (1 + taxRate) - total cost.
    */
   public double calculateCost() {
      return price * (1 + taxRate);
   }
   /**
    * @param taxRateIn - set for tax rate.
    */
   public static void setTaxRate(double taxRateIn) {
      taxRate = taxRateIn;
   }
   /**
    * @return name + total cost.
    */
   public String toString() {
      return name + ": $" + this.calculateCost();
   }
   
   
}