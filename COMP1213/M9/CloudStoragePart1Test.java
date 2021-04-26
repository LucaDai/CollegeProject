import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for CloudStoragePart1.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class CloudStoragePart1Test {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test for CloudStoragePart1. **/
   @Test public void mainTest() {
      CloudStorage.resetCount();
      CloudStoragePart1.main(null);
      Assert.assertEquals("CloudStorage count should be 5. ",
            5, CloudStorage.getCount());
   }
}
