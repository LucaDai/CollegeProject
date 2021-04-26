/**
 * A class hold an array of InventoryItem objects.
 *
 * Activity 10
 * @author Meiwen Dai - COMP 1213
 * @version 7/22/2020
 */
public class ItemsList {
   private InventoryItem[] inventory;
   private int count;
   /**
    * Constructor no params.
    */
   public ItemsList() {
      inventory = new InventoryItem[20];
      count = 0;
   }
   /**
    * @param itemIn represent item put in inventory.
    */
   public void addItem(InventoryItem itemIn) {
      inventory[count] = itemIn;
      count++;
   }
   /**
    * @param electronicsSurcharge - extra fee for electronics.
    * @return total - total cost.
    */
   public double calculateTotal(double electronicsSurcharge) {
      double total = 0;
      for (int i = 0; i < count; i++) {
         if (inventory[i] instanceof ElectronicsItem) {
            total += inventory[i].calculateCost() + electronicsSurcharge;
         }
         else {
            total += inventory[i].calculateCost();
         }
      }
      return total;
   }
   /**
    * @return output.
    */
   public String toString() {
      String output = "All inventory:\n\n";
      
      for (int i = 0; i < count; i++) {
         output += inventory[i].toString() + "\n";
      }
      return output;
   }
}