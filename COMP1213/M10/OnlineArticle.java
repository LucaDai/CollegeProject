/**
 * A class represents an online text item that users can buy(sub class).
 *
 * Activity 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class OnlineArticle extends OnlineTextItem {
   private int wordCount;
   /**
    * @param nameIn - name.
    * @param priceIn - price.
    */
   public OnlineArticle(String nameIn, double priceIn) {
      super(nameIn, priceIn);
      wordCount = 0;
   }
   /**
    * @param wordCountIn - word.
    * @return isSet.
    */
   public boolean setWordCount(int wordCountIn) {
      boolean isSet = false;
      if (wordCountIn > 0) {
         wordCount = wordCountIn;
         isSet = true;
      }
      return isSet;
   }

}