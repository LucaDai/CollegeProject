/**
 * A class have method that get customers information and comparing.
 *
 * Activity 8B
 * @author Meiwen Dai - COMP 1213
 * @version 7/8/2020
 */
public class Customer implements Comparable<Customer> {
   private String name;
   private String location;
   private double balance;
   /**
    * @param nameIn - name.
    */
   public Customer(String nameIn) {
      name = nameIn; // sets name to parameter input
      location = ""; // sets location to empty string
      balance = 0; // sets balance to 0
   }
   /** 
    * @param locationIn - location.
    */
   public void setLocation(String locationIn) { // sets location variable
      location = locationIn;
   }
   /**
    * @param amount - add amount to balance.
    */
   public void changeBalance(double amount) { // add amount to balance
      balance += amount;
   }
   /**
    * @return location - get location.
    */
   public String getLocation() { // returns variable for location
      return location;
   }
   /**
    * @return balance - get balance.
    */
   public double getBalance() { // returns variable for balance 
      return balance;
   }
   /**
    * @param city - location space 1(overload).
    * @param state - location space 2. 
    */
   public void setLocation(String city, String state) {
      location = city + ", " + state;
   }
   /**
    * @return result as output.
    */
   public String toString() {
      String result = name + "\n" + location + "\n$" + balance;
      return result;
   }
   /**
    * @param obj - new Customer wantts to compare with.
    * @return 0/1/-1 as equal, less, greater.
    */
   public int compareTo(Customer obj) {
      if (Math.abs(this.balance - obj.getBalance()) < 0.000001) {
         return 0;
      }
      else if (this.balance < obj.getBalance()) {
         return -1;
      }
      else {
         return 1;
      }
   }
}