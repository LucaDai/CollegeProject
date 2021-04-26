/**
 * A sub class represent using public cloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class PublicCloud extends SharedCloud {
/** constant of cost factor 2. **/
   public static final double COST_FACTOR = 2.0;
/**
 * @param nameIn - name.
 * @param strCostIn - base storage cost.
 * @param dsIn - data storage.
 * @param dlIn - data Limit.
 */
   public PublicCloud(String nameIn, double strCostIn, 
      double dsIn, double dlIn) {
      super(nameIn, strCostIn, dsIn, dlIn);
   }
   /**
    * @return COST_FACTOR - constant.
    */
   public double getCostFactor() {
      return COST_FACTOR;
   }
   /**
    * @return monthlyCost.
    */
   public double monthlyCost() {
      double monthlyCost = storageCost 
         + super.dataOverage() * PublicCloud.COST_FACTOR;
      return monthlyCost;
   }
 
}