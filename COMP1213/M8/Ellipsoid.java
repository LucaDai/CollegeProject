import java.text.DecimalFormat;
/**
 * A class that stores the label and three axes a, b, and c.
 *
 * Project 8 - A 1
 * @author Meiwen Dai - COMP 1213
 * @version 7/2/2020
 */
public class Ellipsoid {
   // instance variables
   private double a, b, c;
   private String label = "";
   private static int count = 0;
   
   // constructor
   /**
    * @param labelIn represent label.
    * @param aIn represent a.
    * @param bIn represent b.
    * @param cIn represent c.
    */
   public Ellipsoid(String labelIn, double aIn, double bIn, double cIn) {
      setLabel(labelIn);     
      setA(aIn);
      setB(bIn);
      setC(cIn);
      count++; 
   }
   //methods
   /**
    * @param labelIn to returns a boolean.
    * @return isSet true if labelIn is not null.
    */
   public boolean setLabel(String labelIn) {
      boolean isSet = false;
      if (labelIn != null) {
         label = labelIn.trim();
         isSet = true;
      }
      return isSet;
   }
   /** 
    * @return label.
    */
   public String getLabel() {
      return label;
   }
   /**
    * @param aIn represent a.
    * @return isSet true if aIn is greater than zero.
    */
   public boolean setA(double aIn) {
      boolean isSet = false;
      if (aIn > 0) {
         a = aIn;
         isSet = true;
      }
      return isSet;
   }
   /**
    * @return a.
    */
   public double getA() {
      return a;
   }
   /**
    * @param bIn represent b
    * @return isSet true if bIn is greater than zero.
    */
   public boolean setB(double bIn) {
      boolean isSet = false;
      if (bIn > 0) {
         b = bIn;
         isSet = true;
      }
      return isSet;
   }
   /**
    * @return b.
    */
   public double getB() {
      return b;
   }
   /**
    * @param cIn represent c.
    * @return isSet true if cIn is greater than zero.
    */
   public boolean setC(double cIn) {
      boolean isSet = false;
      if (cIn > 0) {
         c = cIn;
         isSet = true;
      }
      return isSet;
   }
   /**
    * @return c.
    */
   public double getC() {
      return c;
   }  
   /**
    * @return volume
    */
   public double volume() {
      double volume = (4 * Math.PI * a * b * c) / 3;
      return volume;
   }
   /**
    * @return surfaceArea
    */
   public double surfaceArea() {
      double surfaceArea = 4 * Math.PI * Math.pow((Math.pow(a * b, 1.6) 
         + Math.pow(a * c, 1.6) + Math.pow(b * c, 1.6)) / 3, 1 / 1.6);
      return surfaceArea;
   }
   /**
    * @return output in the right decimal format.
    */
   public String toString() {
      DecimalFormat df = new DecimalFormat("#,##0.0###");
      String output = "Ellipsoid \"" + label 
         + "\" with axes a = " + df.format(a)
         + ", b = " + df.format(b)
         + ", c = " + df.format(c) + " units has: "
         + "\n\tvolume = " + df.format(volume()) + " cubic units"
         + "\n\tsurface area = " + df.format(surfaceArea()) + " square units";
      return output;
   }
   /**
    * @return count.
    */
   public static int getCount() {
      return count;
   }
   /**
    * Reset count.
    */
   public static void resetCount() {
      count = 0;
   }
   /**
    * @param obj represent the object type in.
    * @return false if obj and Ellipsoid not match.
    */
   public boolean equals(Object obj) {
      if (!(obj instanceof Ellipsoid)) {
         return false;
      }
      else {
         Ellipsoid e = (Ellipsoid) obj;
         return (label.equalsIgnoreCase(e.getLabel()) 
            && Math.abs(a - e.getA()) < .000001
            && Math.abs(b - e.getB()) < .000001
            && Math.abs(c - e.getC()) < .000001);
      }
   }
   /**
    * @return 0 if equals implemented.
    */
   public int hashCode() {
      return 0;
   }
}