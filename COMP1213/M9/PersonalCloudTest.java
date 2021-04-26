import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for PersonalCloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class PersonalCloudTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test for getCostFactor(). **/
   @Test public void getCostFactorTest() {
      PersonalCloud c5 = new PersonalCloud("Cloud Five", 9.00, 21.0, 20.0);
      Assert.assertEquals(3.00, c5.getCostFactor(), 0.000001);
   }
   
   /** Test for monthlyCost(). **/
   @Test public void monthlyCostTest() {
      PersonalCloud c5 = new PersonalCloud("Cloud Five", 9.00, 21.0, 20.0);
      Assert.assertEquals(12.00, c5.monthlyCost(), 0.000001);
   }
}
