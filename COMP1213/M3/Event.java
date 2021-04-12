import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;
/**
 * Simple program getting message and change it into different ways.
 *
 * Project 3 - Part B
 * @author Meiwen Dai - COMP 1213
 * @version 5/29/2020
 */
public class Event {
   /**
    * Change input and output it using string class methods.
    *
    * @param args - not used.
    */ 
   public static void main(String[] args) {
      // getting code.
      Scanner input = new Scanner(System.in);
      System.out.print("Enter your event code: ");
      String eventCode = input.nextLine();
      eventCode = eventCode.trim();
      
      //change formate.
      DecimalFormat money = new DecimalFormat("$#,###.00");
      DecimalFormat prizeForm = new DecimalFormat("0000");
      DecimalFormat discountP = new DecimalFormat("0%");
         
      Random pNumber = new Random();
      
      if (eventCode.length() >= 26) { // print the event ticket.
         // Seperate from code.
         String date = eventCode.substring(0, 8);
         String time = eventCode.substring(8, 12);
         String discount = eventCode.substring(17, 19);
         String section = eventCode.substring(19, 21);
         String row = eventCode.substring(21, 23);
         String seat = eventCode.substring(23, 25);
         String event = eventCode.substring(25);
         
         // calculate
         double discountDouble = Double.parseDouble(discount) / 100;
         double price = Double.parseDouble(eventCode.substring(12, 17)) / 100;
         double cost = price * (1 - discountDouble);
         int prize = pNumber.nextInt(9999) + 1;
         // print out.
         System.out.println("\nEvent: " + event
            + "   Date: " + date.substring(0, 2) + "/" 
               + date.substring(2, 4) + "/"
               + date.substring(4)
            + "   Time: " + time.substring(0, 2) + ":"
               + time.substring(2));
         System.out.println("Section: " + section
            + "   Row: " + row
            + "   Seat: " + seat);
         System.out.println("Price: " + money.format(price)
            + "   Discount: " + discountP.format(discountDouble)
            + "   Cost: " + money.format(cost));         
         // random  
         System.out.println("Prize Number: " + prizeForm.format(prize));  
         
      }
      else {
         System.out.println("\nInvalid Event Code."
            + "\nEvent code must have at least 26 characters.");
            
      }
   
   }
   
}