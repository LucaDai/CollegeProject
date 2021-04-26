import java.text.DecimalFormat;
import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A Test class for Ellipsoid.
 *
 * Project 8 - A 2
 * @author Meiwen Dai - COMP 1213
 * @version 7/2/2020
 */
public class EllipsoidTest {
   /** Fixture initialization (common initialization
    *  for all tests). 
    **/
   @Before public void setUp() {
   }
   
    /** Test of variables. **/
   @Test public void variableTest() {
      //setLabel
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      String label = t1.getLabel();
      Assert.assertEquals(true, t1.setLabel(label));
      Assert.assertEquals(false, t1.setLabel(null));
      //Test for setA/B/C
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2);
      double a = t2.getA();
      double b = t2.getB();
      double c = t2.getC();
      Assert.assertEquals(true, t2.setA(a));
      Assert.assertEquals(false, t2.setA(-1));
      
      Assert.assertEquals(true, t2.setB(b));
      Assert.assertEquals(false, t2.setB(-1));
   
      Assert.assertEquals(true, t2.setC(c));
      Assert.assertEquals(false, t2.setC(-1));
   
      // volume & surface area
      double volume = (4 * Math.PI * a * b * c) / 3;
      Assert.assertEquals(volume, t2.volume(), .000001);
      double surfaceArea = 4 * Math.PI * Math.pow((Math.pow(a * b, 1.6)
         + Math.pow(a * c, 1.6) + Math.pow(b * c, 1.6)) / 3, 1 / 1.6);
      Assert.assertEquals(surfaceArea, 
         t2.surfaceArea(), 0.000001);
   
      //Test of string
      String label2 = t2.getLabel();
      DecimalFormat df = new DecimalFormat("#,##0.0###");
      String s = "Ellipsoid \"" + label2 
         + "\" with axes a = " + df.format(a)
         + ", b = " + df.format(b)
         + ", c = " + df.format(c) + " units has: "
         + "\n\tvolume = " + df.format(volume) + " cubic units"
         + "\n\tsurface area = " + df.format(surfaceArea) + " square units";
      Assert.assertEquals(s, t2.toString());
   
   }

      /** Test of euqals(). **/
   @Test public void euqalsTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("Ex 1", 1, 2, 3);
      Assert.assertEquals("Labels euqal", true, t1.equals(t2));
      Ellipsoid t3 = new Ellipsoid("ex 1", 1, 2.1, 3.2);
      Ellipsoid t5 = new Ellipsoid("ex 1", 1, 2, 3.2);
      Assert.assertEquals("Unequal Labels", false, t1.equals(t3));
      Ellipsoid t4 = new Ellipsoid("ex 2", 1.1, 2, 3);
      Ellipsoid t6 = new Ellipsoid("ex 1", 1.1, 2, 3);
      Assert.assertEquals("Unequal Labels", false, t1.equals(t4));
      Assert.assertEquals(false, t1.equals(1));
      Assert.assertEquals(false, t1.equals(t6));
      Assert.assertEquals(false, t1.equals(t3));
      Assert.assertEquals(false, t1.equals(t5));
      
   }
   /** Test of hashCode(). **/
   @Test public void hashCodeTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Assert.assertEquals(0, t1.hashCode());
   }
   /** Test of count. **/
   @Test public void countTest() {
      Ellipsoid t = new Ellipsoid("ex 1", 1, 2, 3);
      t.resetCount();
      Ellipsoid t2 = new Ellipsoid("ex 1", 1, 2, 3);
      Assert.assertEquals(1, t2.getCount());
   }
}
