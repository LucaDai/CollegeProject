/* 
* Luca Dai
* project1_Dai_mzd0108.cpp
* I used Dr.Li's template for project 1
* The program automatically calculates the payment duration,
* total interest and show each term payment for user.
*/
#include <iostream>
using namespace std;
using std::cin;
int main() {
   // VARIABLE INITIALIZATION
   //Properly define your variables……..
   // CURRENCY FORMATTING
   cout.setf(ios::fixed);
   cout.setf(ios::showpoint);
   cout.precision(2);
   
   // USER INPUT
   // NOTE: For valid input, the loan, interest, and monthly payment must
   // be positive. The monthly payment must also be large enough to
   // terminate the loan.
   double loan;
   cout << "\nLoan Amount: ";
   cin >> loan;
   while (loan < 0) {
      cout << "Your loan must be positive, please try again.";
      cout << "\n\nLoan Amount: ";
      cin >> loan;
   }
    
   //Your program will not move forward until a positive load is entered
   double interestRate;
   cout << "Interest Rate (% per year): ";
   cin >> interestRate;
   while (interestRate < 0) {
      cout << "Your Interest Rate must be positive, please try again.\n";
      cout << "\nInterest Rate (% per year): ";
      cin >> interestRate;
   }
   //Your program will not move forward until a positive interest rate is entered
   // GET PROPER INTEREST RATES FOR CALCULATIONS
   interestRate /= 12;
   double interestRateC = interestRate / 100;
   
   cout << "Monthly Payments: ";
   double monthlyPaid;
   cin >> monthlyPaid;
   //Your program will not move forward until a monthly payment is sufficient
   //make sure regular payments are larger than any monthly interest.
   while ((monthlyPaid < 0)||(monthlyPaid <= loan * interestRateC)) {
      if (monthlyPaid < 0) {
         cout << "Your Monthly Payments must be positive, please try again.\n";
         cout << "\nMonthly Payments: ";
         cin >> monthlyPaid;
      }
      else {
         cout << "Your Monthly Payments must larger than the monthly interest, please try again.\n";
         cout << "\nMonthly Payments: ";
         cin >> monthlyPaid;
      }
   }
   
      
   cout << endl;
   
   // AMORTIZATION TABLE
   cout << "*****************************************************************\n"
      << "\tAmortization Table\n"
      << "*****************************************************************\n"
      << "Month\tBalance\t\tPayment\tRate\tInterest\tPrincipal\n";
   // LOOP TO FILL TABLE
   int currentMonth = 0;
   double interestTotal= 0;
   double principal = 0;
   double interest;
   double monthlyInterest;
   double payment = monthlyPaid;
   while (loan > 0) {
      if (currentMonth == 0) {
         cout << currentMonth++ << "\t$" << loan;
         if (loan < 1000) cout << "\t"; // Formatting MAGIC
         cout << "\t" << "N/A\tN/A\tN/A\t\tN/A\n";
      }
      else {
         /* Properly calculate and display “montlypaid” and “principal” when
            (1) loan * (1 + interestRateC) < monthlyPaid
         **/
         interest = loan * interestRateC;
         if (loan >= monthlyPaid) {
            principal = payment - interest;
         }
         //(2) loan * (1 + interestRateC) >= monthlyPaid
         else {
            principal = loan;
            payment = loan + interest;
         }
         loan = loan - principal;
         cout << currentMonth++ << "\t$" << loan
              << "\t\t$" << payment 
              << "\t" << interestRate
              << "\t$" << interest
              << "\t\t$" << principal << "\n";
         interestTotal += interest;
      }
   }
   cout << "****************************************************************\n";
   cout << "\nIt takes " << --currentMonth << " months to pay off "
         << "the loan.\n"
         << "Total interest paid is: $" << interestTotal;
   cout << endl << endl;
   return 0;
}