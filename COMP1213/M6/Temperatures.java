import java.util.ArrayList;
/**
 * A constructor  hold a set of integer values representing daily temperatures.
 *
 * Activity 6 - A
 * @author Meiwen Dai - COMP 1213
 * @version 6/17/2020
 */
public class Temperatures {
   private ArrayList<Integer> temperatures;
   /**
    * @param temperaturesIn represent the input of temperatures.
    */
   public Temperatures(ArrayList<Integer> temperaturesIn) {
      temperatures = temperaturesIn;
   }
   /**
    * @return low as the lowest temperature.
    */
   public int getLowTemp() {
      if (temperatures.isEmpty()) {
         return 0;      
      }
      int low = temperatures.get(0);
      for (int i = 0; i < temperatures.size(); i++) {
         if (temperatures.get(i) < low) {
            low = temperatures.get(i);
         }
      }
      return low;
   }
   /**
    * @return high as the highest temperature.
    */
   public int getHighTemp() {
      if (temperatures.isEmpty()) {
         return 0;
      }
      int high = temperatures.get(0);
      for (Integer temp : temperatures) {
         if (temp > high) {
            high = temp;
         }
      }
      return high;
   }
   /**
    * @param lowIn represent the temps input and compare to the lowest temp.
    * @return lowIn or getLowTemp().
    */
   public int lowerMinimum(int lowIn) {
      return lowIn < getLowTemp() ? lowIn : getLowTemp();
   }
   /**
    * @param highIn represent the temps input and compare to the highest temp.
    * @return highIn or getHighTemp().
    */
   public int higherMaximum(int highIn) {
      return highIn > getHighTemp() ? highIn : getHighTemp();
   }
   /**
    * @return output.
    */
   public String toString() {
      String output = "\tTemperatures: " + temperatures
         + "\n\tLow: " + getLowTemp()
         + "\n\tHigh: " + getHighTemp();
      return output;
   }
}