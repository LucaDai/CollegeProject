import java.text.DecimalFormat;
import org.junit.Assert;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * A Test class for EllipsoidList2.java.
 *
 * Activity 8B - Test
 * @author Meiwen Dai - COMP 1213
 * @version 7/8/2020
 */

public class EllipsoidList2Test {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test for getName(). **/
   @Test public void getNameTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      Assert.assertEquals("Test", test.getName());
   }
   /** A test for numberOfEllipsoids(). **/
   @Test public void numberOfEllipsoidsTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      Assert.assertEquals(2, test.numberOfEllipsoids());
   }
   /** A test for totalVolume(). **/
   @Test public void totalVolumeTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      double volume = t1.volume() + t2.volume();
      Assert.assertEquals(volume, test.totalVolume(), 0.0000001);
   }
   /** A test for totalSurfaceArea(). **/
   @Test public void totalSurfaceAreaTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      double surfaceArea = t1.surfaceArea() + t2.surfaceArea();
      Assert.assertEquals(surfaceArea, test.totalSurfaceArea(), 0.000001);
   }
   /** A test for averageVolume(). **/
   @Test public void averageVolumeTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      double volume = (t1.volume() + t2.volume()) / 2;
      Assert.assertEquals(volume, test.averageVolume(), 0.0000001);
   }
   /** A test for averageVolume(). **/
   @Test public void averageVolumeTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(0, test.averageVolume(), 0.0000001);
   }
   /** A test for averageSurfaceArea(). **/
   @Test public void averageSurfaceAreaTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      double surfaceArea = (t1.surfaceArea() + t2.surfaceArea()) / 2;
      Assert.assertEquals(surfaceArea, test.averageSurfaceArea(), 0.000001);
   }
   /** A test for averageSurfaceArea(). **/
   @Test public void averageSurfaceAreaTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(0, test.averageSurfaceArea(), 0.0000001);
   }

   /** A test for toString(). **/
   @Test public void toStringTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      String testS = "Test\n\n";
      int i = 0;
      while (i < 2) {
         testS += tArray[i].toString() + "\n\n";
         i++;
      }
      Assert.assertEquals(testS, test.toString());
   }
      /** A test for summaryInfo(). **/
   @Test public void summaryInfoTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 0, 0, 0);
      Ellipsoid t2 = new Ellipsoid("ex 2", 0, 0, 0); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      DecimalFormat df = new DecimalFormat("#,##0.0###");
      double volume = t1.volume() + t2.volume();
      double surfaceArea = t1.surfaceArea() + t2.surfaceArea();
      EllipsoidList2 test = new EllipsoidList2("", tArray, 2);
      String testInfo = "----- Summary for  -----"
         + "\nNumber of Ellipsoid Objects: 2" 
         + "\nTotal Volume: " + df.format(volume) + " cubic units"
         + "\nTotal Surface Area: " + df.format(surfaceArea) 
            + " square units"
         + "\nAverage Volume: " + df.format(volume / 2) + " cubic units"
         + "\nAverage Surface Area: " + df.format(surfaceArea / 2) 
            + " square units";
      Assert.assertEquals(testInfo, test.summaryInfo());
   }
   /** A test for getList(). **/
   @Test public void getListTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      Assert.assertEquals(tArray, test.getList());
   }
   /** A test for findEllipsoid(). **/
   @Test public void findEllipsoidTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 1);
      Assert.assertEquals(t1, test.findEllipsoid("ex 1"));
   }
   /** A test for findEllipsoid(). **/
   @Test public void findEllipsoidTest2() {
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.findEllipsoid("ex 1"));
   }
   /** A test for deleteEllipsoid(). **/
   @Test public void deleteEllipsoidTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      Assert.assertEquals(t1, test.deleteEllipsoid("Ex 1"));
   }
   /** A test for deleteEllipsoid(). **/
   @Test public void deleteEllipsoidTest2() {
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.deleteEllipsoid("Ex 1"));
   }
   /** A test for readFile(). 
   * @throws FileNotFoundException -FileNotFoundException.
   */
   @Test public void readFileTest() throws FileNotFoundException {
      Scanner scan = new Scanner(new File("Ellipsoid_data_1.txt"));
      Ellipsoid[] test = new Ellipsoid[100];
      int num = 0;
      String testName = scan.nextLine();
      String label;
      double a;
      double b;
      double c;
      while (scan.hasNext()) {
         label = scan.nextLine();
         a = Double.parseDouble(scan.nextLine());
         b = Double.parseDouble(scan.nextLine());
         c = Double.parseDouble(scan.nextLine());
         test[num] = new Ellipsoid(label, a, b, c);
         num++;
      }
      EllipsoidList2 tArray = new EllipsoidList2("Ellipsoid list", 
         new Ellipsoid[100], 0); 
      Assert.assertArrayEquals("readFile test", 
         test, tArray.readFile("Ellipsoid_data_1.txt").getList());
   }
   /** A test for editEllipsoid(). **/
   @Test public void editEllipsoidTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      Assert.assertEquals(t1, test.editEllipsoid("Ex 1", 2, 2, 3));
   }
   /** A test for editEllipsoid(). **/
   @Test public void editEllipsoidTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.editEllipsoid("Ex 1", 2, 2, 3));
   }
   /** A test for findEllipsoidWithSmallestVolume(). **/
   @Test public void findEllipsoidWithSmallestVolumeTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 40, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2);
      Ellipsoid t3 = new Ellipsoid("ex 3", 100, 100, 100); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      tArray[2] = t3;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 3);
      Assert.assertEquals(t2, test.findEllipsoidWithSmallestVolume());
   }
   /** A test for findEllipsoidWithSmallestVolume(). **/
   @Test public void findEllipsoidWithSmallestVolumeTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.findEllipsoidWithSmallestVolume());
   }
   /** A test for findEllipsoidWithSmallestSurfaceArea(). **/
   @Test public void findEllipsoidWithSmallestSurfaceAreaTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 50, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid t3 = new Ellipsoid("ex 2", 10, 2, 2);
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      tArray[2] = t3;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 3);
      Assert.assertEquals(t2, test.findEllipsoidWithSmallestSurfaceArea());
   }
   /** A test for findEllipsoidWithSmallestSurfaceArea(). **/
   @Test public void findEllipsoidWithSmallestSurfaceAreaTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.findEllipsoidWithSmallestSurfaceArea());
   }
   /** A test for findEllipsoidWithLargestVolume(). **/
   @Test public void findEllipsoidWithLargestVolumeTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 1, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 20, 20, 20);
      Ellipsoid t3 = new Ellipsoid("ex 3", 5, 5, 5); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      tArray[2] = t3;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 3);
      Assert.assertEquals(t2, test.findEllipsoidWithLargestVolume());
   }
   /** A test for findEllipsoidWithLargestVolume(). **/
   @Test public void findEllipsoidWithLargestVolumeTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.findEllipsoidWithLargestVolume());
   }
   /** A test for findEllipsoidWithLargestSurfaceArea(). **/
   @Test public void findEllipsoidWithLargestSurfaceAreaTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 200, 200, 200);
      Ellipsoid t3 = new Ellipsoid("ex 1", 50, 2, 3); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      tArray[2] = t3;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 3);
      Assert.assertEquals(t2, test.findEllipsoidWithLargestSurfaceArea());
   }
   /** A test for findEllipsoidWithLargestSurfaceArea(). **/
   @Test public void findEllipsoidWithLargestSurfaceAreaTest2() { 
      Ellipsoid[] tArray = new Ellipsoid[100];
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 0);
      Assert.assertEquals(null, test.findEllipsoidWithLargestSurfaceArea());
   }

   /** A test for addEllipsoid(). **/
   @Test public void addEllipsoidTest() {
      Ellipsoid t1 = new Ellipsoid("ex 1", 1, 2, 3);
      Ellipsoid t2 = new Ellipsoid("ex 2", 2, 2, 2); 
      Ellipsoid[] tArray = new Ellipsoid[100];
      tArray[0] = t1;
      tArray[1] = t2;
      EllipsoidList2 test = new EllipsoidList2("Test", tArray, 2);
      test.addEllipsoid("ex 3", 1, 1, 1);
      Assert.assertEquals(3, test.numberOfEllipsoids(), 0.0000001);
   }

   


}
