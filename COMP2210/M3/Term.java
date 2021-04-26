import java.util.Comparator;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
public class Term implements Comparable<Term> {
   public String query;
   public long weight;
   /**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   public Term(String query, long weight) {
      if (query == null) {
         throw new NullPointerException();
      }
      if (weight < 0) {
         throw new IllegalArgumentException();
      }
      this.query = query;
      this.weight = weight;
   }

   /**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
      return 
         new Comparator<Term>() {
            public int compare(Term a, Term b) {
               if (a.weight > b.weight) {
                  return -1;
               }
               if (a.weight < b.weight) {
                  return 1;
               }
               else 
                  return 0;
            }
         };
   }

   /**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) {
      if (length <= 0) {
         throw new IllegalArgumentException();
      }
      return 
         new Comparator<Term>() {
            public int compare(Term a, Term b) {
               String prefixA;
               String prefixB;
               if(a.query.length() < length) {
                  prefixA = a.query;
               }
               else prefixA = a.query.substring(0, length);
               if (b.query.length() < length) {
                  prefixB = b.query;
               }
               else prefixB = b.query.substring(0, length);
               
               return prefixA.compareTo(prefixB);
            }
         };
   }

   /**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
   public int compareTo(Term other) {
      return this.query.compareTo(other.query);
   }

   /**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
   public String toString(){ 
      return this.query + "\t" + this.weight;
   }

}