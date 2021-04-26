import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
 /**
 * A class reference BankLoan's Test class.
 *
 * Activity 8 - Test 
 * @author Meiwen Dai - COMP 1213
 * @version 7/1/2020
 */
public class BankLoanTest {

   @Test public void chargeInterestTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      loan1.chargeInterest();   
      Assert.assertEquals("Testing chargeInterest(): ", 1100, loan1.getBalance(), .000001);
   }
}
