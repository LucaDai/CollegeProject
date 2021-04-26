import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for DedicatedCloud.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class DedicatedCloudTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /** Test for set and getName(). **/
   @Test public void setGetNameTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      c1.setName("One");
      Assert.assertEquals("One", c1.getName());
   }
   
   /** Test for set and getBaseStorageCost(). **/
   @Test public void setGetBaseStorageCost() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      c1.setBaseStorageCost(1.5);
      Assert.assertEquals(1.5, c1.getBaseStorageCost(), 0.000001);
   }
   
   /** Test for getName(). **/
   @Test public void getName() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      c1.setServerCost(1.00);
      Assert.assertEquals("Cloud One", c1.getName());
   }
   
   /** Test for setServerCost() and getServerCost(). **/
   @Test public void setGetServerCostTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      c1.setServerCost(1.00);
      Assert.assertEquals(1.00, c1.getServerCost(), 0.000001);
   }
   
   /** Test for monthlyCost(). **/
   @Test public void monthlyCostTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00); 
      Assert.assertEquals(50, c1.monthlyCost(), 0.000001);
   }
   
   /** Test for toString(). **/
   @Test public void toStringTest() {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00); 
      Assert.assertEquals("Cloud One (class DedicatedCloud)"
         + " Monthly Cost: $50.00"
         + "\nBase Storage Cost: $40.00" 
         + "\nServer Cost: $10.00\n", c1.toString());
   }
   
}
