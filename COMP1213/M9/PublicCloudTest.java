import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for PublicCloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class PublicCloudTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test for getCostFactor(). **/
   @Test public void getCostFactorTest() {
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      Assert.assertEquals(2.00, c4.getCostFactor(), 0.000001);
   }
   
   /** Test for monthlyCost(). **/
   @Test public void monthlyCostTest() {
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      Assert.assertEquals(19.00, c4.monthlyCost(), 0.000001);
   }
}
