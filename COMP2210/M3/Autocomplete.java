import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class Autocomplete {
   private final Term[] terms;
	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) { 
      if (terms == null) {
         throw new NullPointerException();
      }
      this.terms = new Term[terms.length];
      for (int i = 0; i < terms.length; i++) {
         if(terms[i] == null) {
            throw new IllegalArgumentException();
         }
         this.terms[i] = terms[i];
      }
      Arrays.sort(this.terms);
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) { 
      if (prefix == null) {
         throw new NullPointerException();
      }
      if (prefix.length() == 0) {
         Term[] matches = new Term[terms.length];
         for (int i = 0;i < terms.length; i++) {
            matches[i] = terms[i];
         }
         Arrays.sort(matches, Term.byDescendingWeightOrder());
         return matches;
      }
      
      Term p = new Term(prefix, 0);
      int first = BinarySearch.firstIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));
      if (first < 0) {
         return new Term[] {};
      } 
      int last = BinarySearch.lastIndexOf(terms, p, Term.byPrefixOrder(prefix.length()));
      Term[] matches = new Term[last - first + 1];
      for (int i = first; i <= last; i++) {
         matches[i - first] = terms[i];
      }
      Arrays.sort(matches,Term.byDescendingWeightOrder());
      return matches;
   }

}