/**
 * A driver class and holds main method.
 *
 * Activity 10
 * @author Meiwen Dai - COMP 1213
 * @version 7/22/2020
 */
public class InventoryListApp {
   /**
    * @param args - no used.
    */
   public static void main(String[] args) {
      InventoryItem.setTaxRate(0.05);
      ItemsList myItems = new ItemsList();
      myItems.addItem(new ElectronicsItem("laptop", 1234.56, 10));
      myItems.addItem(new InventoryItem("motor oil", 9.8));
      myItems.addItem(new OnlineBook("All Things Java", 12.3));
      myItems.addItem(new OnlineArticle("Useful Acronyms", 3.4));
      
      System.out.println(myItems.toString());
      System.out.println("Total: " + myItems.calculateTotal(2.0));
      
   }

}