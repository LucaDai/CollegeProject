/**
 * Main class of cloud storage.
 *
 * Project 9
 * @author Meiwen Dai - COMP 1213
 * @version 7/15/2020
 */
public class CloudStoragePart1 {
/**
 * @param args - not used.
 */
   public static void main(String[] args) {
      DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
      SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
      SharedCloud c3 = new SharedCloud("Cloud Three", 9.00, 25.0, 20.0);
      PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
      PersonalCloud c5 = new PersonalCloud("Cloud Five", 9.00, 21.0, 20.0);
      System.out.println(c1.toString());
      System.out.println(c2.toString());
      System.out.println(c3.toString());
      System.out.println(c4.toString());
      System.out.println(c5.toString());
         
   }

}