/**
 * A sub class represents using personal cloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class PersonalCloud extends SharedCloud {
   /** constant - cost factor 3. **/
   public static final double COST_FACTOR = 3.0;
   /**
    * @param nameIn - name.
    * @param strCostIn - base storage cost.
    * @param dsIn - data storage.
    * @param dlIn - data Limit.
    */
   public PersonalCloud(String nameIn, double strCostIn, 
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
         + super.dataOverage() * PersonalCloud.COST_FACTOR;
      return monthlyCost;
   }
 
}