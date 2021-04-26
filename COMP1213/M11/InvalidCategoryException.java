/**
 * Invalid Category Exception.
 *
 * Project 11
 * @author Meiwen Dai - COMP 1213
 * @version 7/31/2020
 */
public class InvalidCategoryException extends Exception {
   /**
    * @param object - invalid category.
    */
   public InvalidCategoryException(String object) {
      super("For category: " + object);
   }
}
