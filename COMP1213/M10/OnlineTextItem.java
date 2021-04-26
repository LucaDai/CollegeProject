/**
 * A class represents an online text item that users can buy.
 *
 * Activity 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public abstract class OnlineTextItem extends InventoryItem {
   /**
    * @param nameIn - name.
    * @param priceIn - price.
    */
   public OnlineTextItem(String nameIn, double priceIn) {
      super(nameIn, priceIn);
   }
   /**
    * @return price.
    */
   public double calculateCost() {
      return price;
   }
   
}