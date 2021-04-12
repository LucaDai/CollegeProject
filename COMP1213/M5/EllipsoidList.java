import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 * A class that stores a list of Ellipsoid and calculate it.
 *
 * Project 5 - B
 * @author Meiwen Dai - COMP 1213
 * @version 6/12/2020
 */
public class EllipsoidList {
   //field
   private String nameList;
   private ArrayList<Ellipsoid> eList;
   //Constructor
   /**
    * @param nameListIn represent name of the list.
    * @param ellipsoidIn represent the ellipsoid store in arraylist.
    */
   public EllipsoidList(String nameListIn, ArrayList<Ellipsoid> ellipsoidIn) {
      nameList = nameListIn;
      eList = ellipsoidIn;
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
      int num = 0;
      num = eList.size();
      return num;
   }
   /**
    * @return totalVolume of calculated ellipsoids.
    */
   public double totalVolume() {
      double totalVolume = 0;
      int i = 0;
      while (i < eList.size()) {
         totalVolume += eList.get(i).volume();
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
      while (i < eList.size()) {
         totalSurfaceArea += eList.get(i).surfaceArea();
         i++;
      }
      return totalSurfaceArea;
   }
   /**
    * @return averageVolume of calculated ellipsoids.
    */
   public double averageVolume() {
      
      double averageVolume = 0;
      if (eList.size() != 0) {
         averageVolume = totalVolume() / eList.size();
      }
      return averageVolume;
   }
   /**
    * @return averageSurfaceArea of calculation.
    */
   public double averageSurfaceArea() {
      double averageSurfaceArea = 0;
      if (eList.size() != 0) {
         averageSurfaceArea = totalSurfaceArea() / eList.size();
      }
      return averageSurfaceArea;
   }
   /**
    * @return output of the ellipsoid list.
    */
   public String toString() {
      String output = nameList + "\n\n";
      int i = 0;
      while (i < eList.size()) {
         output += eList.get(i).toString() + "\n\n";
         i++;
      }
      output += "\n";
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
}