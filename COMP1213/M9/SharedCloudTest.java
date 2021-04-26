import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for SharedCloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class SharedCloudTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test for setDataStored() and getDataStored(). **/
   @Test public void setGetDataStoredTest() {
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      c2.setDataStored(1.00);
      Assert.assertEquals(1.00, c2.getDataStored(), 0.000001);
   }
   
   /** Test for setDataLimit() and getDataLimit(). **/
   @Test public void dataLimitTest() {
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      c2.setDataLimit(1.00);
      Assert.assertEquals(1.00, c2.getDataLimit(), 0.000001);
   }
   
   /** Test for getCostFactor(). **/
   @Test public void getCostFactorTest() {
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      Assert.assertEquals(1.00, c2.getCostFactor(), 0.000001);
   }
   
   /** Test for dataOverage(). **/
   @Test public void dataOverageTest() {
      CloudStorage.resetCount();
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      SharedCloud c3 = new SharedCloud("Cloud Three", 9.00, 25.0, 20.0);
      Assert.assertEquals(0, c2.dataOverage(), 0.000001);
      Assert.assertEquals(5, c3.dataOverage(), 0.000001);
      Assert.assertEquals("CloudStorage count should be 2. ",
         2, CloudStorage.getCount());
   }
   
   /** Test for monthlyCost(). **/
   @Test public void monthlyCostTest() {
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      Assert.assertEquals(9, c2.monthlyCost(), 0.000001);
   }
   
   /** Test for toString(). **/
   @Test public void toStringTest() {
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      String output = "Cloud Two (class SharedCloud) Monthly Cost: $9.00"
            + "\nBase Storage Cost: $9.00"
            + "\nData Stored: 12.000 GB"
            + "\nData Limit: 20.000 GB"
            + "\nOverage: 0.000 GB"
            + "\nCost Factor: 1.0\n";
      Assert.assertEquals(output, c2.toString());
   }
}
