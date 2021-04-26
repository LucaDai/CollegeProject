import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * A class that uses array to stores Ellipsoids and calculate it.
 *
 * Project 8 - B
 * @author Meiwen Dai - COMP 1213
 * @version 7/10/2020
 */
public class EllipsoidList2 {
   //field
   private String nameList;
   private Ellipsoid[] eArray = new Ellipsoid[100];
   private int num;
   //Constructor
   /**
    * @param nameListIn represent name of the list.
    * @param eIn represent the ellipsoid store in array.
    * @param numIn represent the number of ellipsoids.
    */
   public EllipsoidList2(String nameListIn, Ellipsoid[] eIn, int numIn) {
      nameList = nameListIn;
      eArray = eIn;
      num = numIn;
   }
   //Method
   /**
    * @return nameList for name.
    */
   public String getName() {
      return nameList;
   }
   /**
    * @return num represent the objects number.
    */
   public int numberOfEllipsoids() {
      return num;
   }
   /**
    * @return totalVolume of calculated ellipsoids.
    */
   public double totalVolume() {
      double totalVolume = 0;
      int i = 0;
      while (i < num) {
         totalVolume += eArray[i].volume();
         i++;
      }
      return totalVolume;   
   }
   /**
    * @return totalSurfaceArea of calculated ellipsoids.
    */
   public double totalSurfaceArea() {
      double totalSurfaceArea = 0;
      int i = 0;
      while (i < num) {
         totalSurfaceArea += eArray[i].surfaceArea();
         i++;
      }
      return totalSurfaceArea;
   }
   /**
    * @return averageVolume of calculated ellipsoids.
    */
   public double averageVolume() {
      
      double averageVolume = 0;
      if (num != 0) {
         averageVolume = totalVolume() / num;
      }
      return averageVolume;
   }
   /**
    * @return averageSurfaceArea of calculation.
    */
   public double averageSurfaceArea() {
      double averageSurfaceArea = 0;
      if (num != 0) {
         averageSurfaceArea = totalSurfaceArea() / num;
      }
      return averageSurfaceArea;
   }
   /**
    * @return output of the ellipsoid list.
    */
   public String toString() {
      String output = nameList + "\n\n";
      int i = 0;
      while (i < num) {
         output += eArray[i].toString() + "\n\n";
         i++;
      }
      return output;
   }
   /**
    * @return output of the ellipsoids calculation.
    */
   public String summaryInfo() {
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String output = "----- Summary for " + nameList + " -----"
         + "\nNumber of Ellipsoid Objects: " + numberOfEllipsoids()
         + "\nTotal Volume: " + df.format(totalVolume()) + " cubic units"
         + "\nTotal Surface Area: " + df.format(totalSurfaceArea()) 
            + " square units"
         + "\nAverage Volume: " + df.format(averageVolume()) + " cubic units"
         + "\nAverage Surface Area: " + df.format(averageSurfaceArea()) 
            + " square units";
      return output;
   }
   /**
    * @return eList.
    */
   public Ellipsoid[] getList() {
      return eArray;
   }
   /**
    * @throws FileNotFoundException - file not found.
    * @param fileNameIn is the file name entered by user.
    * @return file as an ellipsoid list scan from file.
    */
   public EllipsoidList2 readFile(String fileNameIn) 
      throws FileNotFoundException {
      Scanner sList = new Scanner(new File(fileNameIn));
      String name = sList.nextLine();
      //Ellipsoid[] example = new Ellipsoid[100];
      while (sList.hasNext()) {
         double a, b, c;
         String label = sList.nextLine();
         a = Double.parseDouble(sList.nextLine());
         b = Double.parseDouble(sList.nextLine());
         c = Double.parseDouble(sList.nextLine());
         eArray[num] = new Ellipsoid(label, a, b, c);
         num++;
      }
      //eArray = example;
      EllipsoidList2 file = new EllipsoidList2(name, eArray, num);
      return file;
   }
   /**
    * @param lIn - label.
    * @param aIn - a.
    * @param bIn - b.
    * @param cIn - c.
    */
   public void addEllipsoid(String lIn, double aIn, double bIn, double cIn) {
      eArray[num] = new Ellipsoid(lIn, aIn, bIn, cIn);
      num++;
   }
   /**
    * @param labelIn - label.
    * @return example.
    */
   public Ellipsoid findEllipsoid(String labelIn) {
      Ellipsoid example = null;
      for (int i = 0; i < num; i++) {
         if (eArray[i].getLabel().equalsIgnoreCase(labelIn)) {  
            example = eArray[i];
         }
      } 
      return example;
   }
   /**
    * @param labelIn - label.
    * @return example.
    */
   public Ellipsoid deleteEllipsoid(String labelIn) {
      Ellipsoid example = findEllipsoid(labelIn);
      if (example != null) {
         for (int i = 0; i < eArray.length; i++) {
            if (example.equals(eArray[i])) {
               for (int j = i; j < eArray.length - 1; j++) {
                  eArray[j] = eArray[j + 1];
               }
            }
         } 
      } 
      num--;
      return example;
   }
   /**
    * @param l - label.
    * @param a - a.
    * @param b - b.
    * @param c - c.
    * @return example.
    */
   public Ellipsoid editEllipsoid(String l, double a, double b, double c) {
      Ellipsoid example = findEllipsoid(l);
      if (example != null) {
         example.setA(a);
         example.setB(b);
         example.setC(c);
      }
      return example;
   }
   /**
    * @return null or example as the smalest volume.
    */
   public Ellipsoid findEllipsoidWithSmallestVolume() {
      Ellipsoid example = eArray[0];
      if (num == 0) {
         return null;
      }
      else {
         for (int i = 1; i < num; i++) {  
            if (eArray[i].volume() < example.volume()) {
               example = eArray[i];
               
            }
         }
         return example;
      }
   }
   /**
    * @return null or example as the largest volume.
    */
   public Ellipsoid findEllipsoidWithLargestVolume() {
      Ellipsoid example = eArray[0];
      if (num == 0) {
         return null;
      }
      else {
         for (int i = 1; i < num; i++) {  
            if (eArray[i].volume() > example.volume()) {
               example = eArray[i];
               
            }
         }
         return example;
      }
   
   }
   /**
    * @return null or example as the smalest surface Area.
    */
   public Ellipsoid findEllipsoidWithSmallestSurfaceArea() {
      Ellipsoid example = eArray[0];
      if (num == 0) {
         return null;
      }
      else {
         for (int i = 1; i < num; i++) {  
            if (eArray[i].surfaceArea() < example.surfaceArea()) {
               example = eArray[i];
               
            }
         }
         return example;
      }
   }
   /**
    * @return null or example as the largest surface area.
    */
   public Ellipsoid findEllipsoidWithLargestSurfaceArea() {
      Ellipsoid example = eArray[0];
      if (num == 0) {
         return null;
      }
      else {
         for (int i = 1; i < num; i++) {  
            if (eArray[i].surfaceArea() > example.surfaceArea()) {
               example = eArray[i];
               
            }
         }
         return example;
      }
   
   }
}