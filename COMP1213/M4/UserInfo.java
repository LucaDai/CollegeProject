/**
 * UserInfo defined object contains user information. 
 *
 * Activity 4 - B 
 * @author Meiwen Dai - COMP 1213
 * @version 6/4/2020
 */
public class UserInfo {

   // instance variables
   private String firstName, lastName, location;
   private int age, status;
   
   private static final int OFFLINE = 0, ONLINE = 1;
   
   // constructor
   /**
    * @param firstNameIn to create name space.
    * @param lastNameIn to create name space.
    */ 
   public UserInfo(String firstNameIn, String lastNameIn) {
      firstName = firstNameIn;
      lastName = lastNameIn;
      location = "Not specified";
      age = 0;
      status  = OFFLINE;
   }
   
   //methods
   /**
    * @return output
    */ 
   public String toString() {
      String output = "Name: " + firstName + " "
         + lastName + "\n";
      output += "Location: " + location + "\n";
      output += "Age: " + age + "\n";
      output += "Status: ";
      if (status == OFFLINE) {
         output += "Offline";
      }
      else {
         output += "Online";
      }
      
      return output;
   }
   /**
    * @param locationIn to set location.
    */
   public void setLocation(String locationIn) {
      location = locationIn;
   }
   /**
    * @param ageIn to set age.
    * @return isSet
    */
   public boolean setAge(int ageIn) {
      boolean isSet = false;
      if (ageIn > 0) {
         age = ageIn;
         isSet = true;
      }
      return isSet;
   }
   /**
    * @return age
    */
   public int getAge() {
      return age;
   }
   /**
    * @return location
    */
   public String getLocation() {
      return location;
   }
   /** */
   public void logOff() {
      status = OFFLINE;
   }
   /** */
   public void logOn() {
      status = ONLINE;
   }
}