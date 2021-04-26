import javax.swing.JOptionPane;

/**
 * Division main method.
 *
 * Activity 11
 * @author Meiwen Dai - COMP 1213
 * @version 7/29/2020
 */
public class DivisionDriver {
   /**
    * @param args - not used.
    */
   public static void main(String[] args) {
      String numInput 
         = JOptionPane.showInputDialog("Enter the numerator: ");
      String denomInput 
         = JOptionPane.showInputDialog("Enter the denominator: ");
         
      try {
         int num = Integer.parseInt(numInput);
         int denom = Integer.parseInt(denomInput);
         String result = "Integer division: \n"
            + Division.intDivide(num, denom)
            + "\n\nFloating point division: \n"
            + Division.decimalDivide(num, denom);
         // Print the result in a dialog box:
         JOptionPane.showMessageDialog(null, result,
            "Result", JOptionPane.PLAIN_MESSAGE);
      }
      catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, 
            "Invalid input: enter numerical integer values only.", 
            "Error", JOptionPane.ERROR_MESSAGE);
      }
      catch (IllegalArgumentException e) {
         JOptionPane.showMessageDialog(null,
            e, "Error", JOptionPane.ERROR_MESSAGE);
      }
      
      
   }
   
}