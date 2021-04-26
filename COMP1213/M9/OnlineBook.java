/**
 * A class include a variable for the author’s name.
 *
 * Activity 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class OnlineBook extends OnlineTextItem {
   protected String author;
   /**
    * @param nameIn - name.
    * @param priceIn - price.
    */
   public OnlineBook(String nameIn, double priceIn) {
      super(nameIn, priceIn);
      author = "Author Not Listed";
   }
   /**
    * @param authorIn - author.
    * @return isSet - set for author.
    */
   public boolean setAuthor(String authorIn) {
      boolean isSet = false;
      if (authorIn.length() > 0) {
         isSet = true;
         author = authorIn;
      }
      return isSet;
   }
   /**
    * @return name + author + price.
    */
   public String toString() {
      return name + " - " + author + ": $" + price;
   }
}