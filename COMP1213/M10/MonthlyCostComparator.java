import java.util.Comparator;
/**
 * Comparator of CloudStorage.
 *
 * Project 10
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class MonthlyCostComparator implements Comparator<CloudStorage> {
   /**
    * @param c1 - first object.
    * @param c2 - second object.
    * @return 1/-1/0.
    */
   public int compare(CloudStorage c1, CloudStorage c2) {
   
      if (c1.monthlyCost() > c2.monthlyCost()) {
         return -1;
      }
      else if (c1.monthlyCost() < c2.monthlyCost()) {
         return 1;
      }
      else {
         return 0;
      }
   }
}