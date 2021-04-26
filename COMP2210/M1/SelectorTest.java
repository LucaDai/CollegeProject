import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void kminTest() {
      int[] a = {3, 7, 3, 3, 1, 9, 1, 1, 1, 5};
      int expect = 9;
      
      Assert.assertEquals(expect, Selector.kmin(a, 5));
   }
}
