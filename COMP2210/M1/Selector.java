import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Meiwen Dai (mzd0108@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  9/5/2020
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         int result = a[0];
         for (int i = 0; i < a.length; i++) {
            if (result > a[i]) {
               result = a[i];
            }
         }
         return result;
      }  
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         int result = a[0];
         for (int i = 0; i < a.length; i++) {
            if (result < a[i]) {
               result = a[i];
            }
         }
         return result;
      }
   
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1  || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int[] list = Arrays.copyOf(a, a.length);
      Arrays.sort(list);
      int count = 0;
      // count the repeat numbers.
      for (int i = 0; i < list.length - 1; i++) {
         if(list[i] == list[i+1]) {
            count++;
         }
      }
      if (count == 0) {
         return list[k - 1];
      }
      else {
         if (k > (list.length - count)) {
            throw new IllegalArgumentException();
         }
         int[] list2 = new int[list.length - count];
         int index = 0;
         for (int i = 0; i < list.length; i++) {
            while (i < list.length - 1 && list[i] == list[i+1]) {
               i++;
            }
            list2[index] = list[i];
            index++;
         }
         return list2[k - 1];
      }
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1  || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] list = Arrays.copyOf(a, a.length);
      Arrays.sort(list);
      int count = 0;
      // count the repeat numbers.
      for (int i = 0; i < list.length - 1; i++) {
         if(list[i] == list[i+1]) {
            count++;
         }
      }
      if (count == 0) {
         return list[list.length - k];
      }
      else {
         if (k > (list.length - count)) {
            throw new IllegalArgumentException();
         }
         int[] list2 = new int[list.length - count];
         int index = 0;
         for (int i = 0; i < list.length; i++) {
            while (i < list.length - 1 && list[i] == list[i+1]) {
               i++;
            }
            list2[index] = list[i];
            index++;
         }
         return list2[list2.length - k];
      }
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("There have to be a list.");
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if ((a[i] >= low) && (a[i] <= high)) {
            count++;
         }
      }
      int[] list = new int[count];
      int index = 0;
      for (int i = 0; i < a.length; i++) {
         if ((a[i] >= low) && (a[i] <= high)) {
            list[index] = a[i];
            index++;
         }
      }
      
      return list;
   }   

   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            count++;
         }
      }
      if (count == 0) {
         throw new IllegalArgumentException();
      }
      int[] list = new int[count];
      int index = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            list[index] = a[i];
            index++;
         }
      }
      return min(list);
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            count++;
         }
      }
      if (count == 0) {
         throw new IllegalArgumentException();
      }
      int[] list = new int[count];
      int index = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            list[index] = a[i];
            index++;
         }
      }
      return max(list);
   }
}
