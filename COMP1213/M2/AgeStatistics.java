import java.util.Scanner;
/**
 * Simple program getting user's name and age.
 *
 * Activity 2 - Part A
 * @author Meiwen Dai - COMP 1213
 * @version 5/26/2020
 */
public class AgeStatistics {
   /**
    * Read name and age from the user and print it.
    *
    * @param args - not used.
    */ 
    
   public static void main(String[] args) {
      Scanner userInput = new Scanner(System.in);
      String name = "";
      int ageInYears = 0;
      int gender = 0;
      double maxHeartRate = 0;
      //Prompt the user for their name:
      System.out.print("Enter your name: ");
      name = userInput.nextLine();
      //Prompt the user for their age:
      System.out.print("Enter your age in years: ");
      ageInYears = userInput.nextInt();
      //Prompt the user for their gender():
      System.out.print("Enter your gender (1 for female and 0 for male): ");
      gender = userInput.nextInt();
      //convert age:
      System.out.println("\tYour age in minutes is "
            + ageInYears * 525600 + " minutes.");
      System.out.println("\tYour age in centuries is "
            + (double) ageInYears / 100 + " centuries.");
      //display max heart rate
      System.out.print("Your max heart rate is ");
      if (gender == 1) { //calculate female MHR
         maxHeartRate = 209 - (0.7 * ageInYears);
      }
      else { //calculate male MHR
         maxHeartRate = 214 - (0.8 * ageInYears);
      }
      System.out.println(maxHeartRate + " beats per minute.");
   }
}
