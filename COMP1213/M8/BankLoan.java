/**
 * A class reference Bank Loan.
 *
 * Activity 8  
 * @author Meiwen Dai - COMP 1213
 * @version 7/1/2020
 */
public class BankLoan {
	// constant fields
   private static final int MAX_LOAN_AMOUNT = 100000;
   private static int loansCreated = 0;

   // instance variables (can be used within the class)
   private String customerName;
   private double balance, interestRate;
   /**
    * @param customerNameIn - cumstomerName.
    * @param interestRateIn - interestRate.
    */
   public BankLoan(String customerNameIn, double interestRateIn) { 
      customerName = customerNameIn;
      interestRate = interestRateIn;
      balance = 0;
      loansCreated++;
   }
   /**
    * @param amount - amount.
    * @return wasLoanMade.
    */
   public boolean borrowFromBank(double amount) {
      
      boolean wasLoanMade = false;
      
      if (balance + amount < MAX_LOAN_AMOUNT) {
         wasLoanMade = true;
         balance += amount;
      }
   
      return wasLoanMade;
   }
   /**
    * @param amountPaid - amount Paid.
    * @return Math.abs(newBalance) / 0.
    */
   public double payBank(double amountPaid) {
      double newBalance = balance - amountPaid;
      if (newBalance < 0) {
         balance = 0;
         // paid too much, return the overcharge
         return Math.abs(newBalance);
      }
      else {
         balance = newBalance;
         return 0;
      }
   }
   /**
    * @return balance.
    */
   public double getBalance() {
      return balance;
   }
   /**
    * @param interestRateIn - interestRate.
    * @return true / false.
    */
   public boolean setInterestRate(double interestRateIn) {
   
      if (interestRateIn >= 0 && interestRateIn <= 1) {
         interestRate = interestRateIn;
         return true;
      }
      else {
         return false;
      }
   }
   /**
    * @return interestRate.
    */
   public double getInterestRate() {
      return interestRate;
   }
   /**
    * represent interest's charge.
    */
   public void chargeInterest() {
      balance = balance * (1 + interestRate);
   }
   /**
    * @return output
    */
   public String toString() {
      String output = "Name: " + customerName + "\r\n" 
         + "Interest rate: " + interestRate + "%\r\n" 
         + "Balance: $" + balance + "\r\n";
      return output;
   }
   //add code here
   /**
    * @param amount - amount.
    * @return true when amount greater than 0.
    */
   public static boolean isAmountValid(double amount) {
      return amount >= 0;  
   }
   /**
    * @param loan - loan in BankLoan.
    * @return true / false.
    */
   public static boolean isInDebt(BankLoan loan) {
      if (loan.getBalance() > 0) {
         return true;
      }
      return false;
   }
   /**
    * @return loansCreated.
    */
   public static int getLoansCreated() {
      return loansCreated;
   }
   /**
    * Let loansCreated back to 0.
    */
   public static void resetLoansCreated() {
      loansCreated = 0;
   }
}
