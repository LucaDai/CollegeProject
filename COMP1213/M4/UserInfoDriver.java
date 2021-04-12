/**
 *UserInfoDriver contains method to call userInfo class and output user data. 
 *
 * Activity 4 - A
 * @author Meiwen Dai - COMP 1213
 * @version 6/4/2020
 */
public class UserInfoDriver {
   /**
    * import Userinput and output it using UserInfo class.
    *
    * @param args - not used.
    */ 
   public static void main(String[] args) {
      UserInfo user1 = new UserInfo("Pat", "Doe");
      System.out.println("\n" + user1);
      user1.setLocation("Auburn");
      user1.setAge(19);
      user1.logOn();
      System.out.println("\n" + user1);
      
      UserInfo user2 = new UserInfo("Sam", "Jones");
      System.out.println("\n" + user2);
      user2.setLocation("Atlanta");
      user2.setAge(21);
      user2.logOn();
      System.out.println("\n" + user2);
   
   }
}