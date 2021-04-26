/**
 * A class represents an inventory electronics item in a store.
 *
 * Activity 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class ElectronicsItem extends InventoryItem {
   protected double weight;
   /** Constant of shipping cost. **/
   public static final double SHIPPING_COST = 1.5;
   /**
    * @param nameIn - name.
    * @param priceIn - price.
    * @param weightIn - weight.
    */
   public ElectronicsItem(String nameIn, double priceIn, double weightIn) {
      super(nameIn, priceIn);
      weight = weightIn;
   }
   /**
    * @return Cost + tax + shipping cost.
    */
   public double calculateCost() {
      return super.calculateCost() + (SHIPPING_COST * weight);
   }

}