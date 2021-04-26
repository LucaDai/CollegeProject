import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Meiwen Dai (mzd0108@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if (element == null || contains(element) == true) {
         return false;
      }
      else if (size() == 0) {
         front = new Node(element);
         rear = front;
         size++;
      }
      else if (size() == 1 && front.element.compareTo(element) < 0) {
         front.next = new Node(element);
         rear = front.next;
         rear.prev = front;
         size++;
      }
      else if (size() == 1 && front.element.compareTo(element) > 0) {
         Node f = front;
         f.prev = new Node(element);
         front = f.prev;
         front.next = f;
         f.prev = front;
         rear = front.next;
         size++;
      }
      else {
         Node n = front;
         while (n.next != null) {
            if (n.element.compareTo(element) < 0) {
               n = n.next;
            }
            else {
               if (first(element) == true) {
                  Node m = n;
                  m.prev = new Node(element);
                  n = m.prev;
                  n.next = m;
                  front = n;
                  size++;
                  return true;
               }
               Node m = new Node(element);
               m.prev = n.prev;
               n.prev.next = m;
               m.next = n;
               n.prev = m;
               size++;
               return true;
            }
         }
            
         if (n.next == null) {
            if (size() == 2 && rear.element.compareTo(element) > 0) {
               Node middle = new Node(element);
               rear.prev = middle;
               front.next = middle;
               middle.next = rear;
               middle.prev = front;
               size++;
            }
            else if (rear.element.compareTo(element) > 0) {
               Node m = new Node(element);
               m.prev = n.prev;
               m.next = n;
               n.prev.next = m;
               n.prev = m;
               size++;
            }
            else {
               Node m = new Node(element);
               n.next = m;
               m.prev = n;
               rear = m;
               size++;
            }
         }
      }
      return true;
   }
   
   private boolean first(T element) {
   
      Node n = new Node(element);
      Node m = front;
      while (m.next != null) {
         if (n.element.compareTo(m.element) < 0) {
            m = m.next;
         }
         else {
            return false;
         }
      }
      return true;
   } 
  
   private Node locate(T element) {
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return n;
         }
         n = n.next;
      }
      return null;
   }
   /**
    * Ensures the collection does not contain the specif ied element.
    * If the specif ied element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if  collection is changed, false otherwise.
    */
    
   public boolean remove(T element) {
      Node n = locate(element);
      
      if (isEmpty() || n == null) {
         return false;
      }
      else {
         if (n.element == front.element && size() == 1) {
            front = null;
            rear = null;
            size--;
         }
         else if (n.element == front.element && size() > 1) {
            front = front.next;
            front.prev = null;
            size--;
            return true;
         }
         else {
            n.prev.next = n.next;
            if (n.next == null) {
               rear = n.prev;
               n.prev.next = null;
            }
            else {
               n.next.prev = n.prev;
            }
            size--;
            return true;
         }
      }
      return true;
   }


   /**
    * Searches for specif ied element in this collection.
    *
    * @param T  element  
    * @return  true 
    */
   public boolean contains(T element) {
      return locate(element) != null;
      
   }

   private void addAll(Set<T> s) {
      Iterator<T> scan = s.iterator();
   
      while (scan.hasNext()) {
         add(scan.next());
      }
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if  this set contains exactly the same elements
    * as the parameter set, regardless of order.
    * @param s a.
    * @return  true if  this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      
      Iterator<T> scan = s.iterator();
      while (scan.hasNext()) {
         if (!this.contains(scan.next())) {
            return false;
         }
      }
      if (this.size() == size()) {
         return true;
      }
         
      return false;
   
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if  this set contains exactly the same elements
    * as the parameter set, regardless of order.
    * @param s a.
    * @return  true if  this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      Node m = s.front;
      int i = 0;
      while (i < size()) {
         if (!this.contains(m.element)) {
            return false;
         }
         m = m.next;
         i++;
      }
      return true;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    * @param s a.
    * @return  a set 
    */
   public Set<T> union(Set<T> s) {
      Iterator<T> itrThis = this.iterator();
      Iterator<T> itrSet = s.iterator();
      
      Set<T> result = new LinkedSet();
      
      while (itrThis.hasNext()) {
         T element = itrThis.next();
         result.add(element);
      }
      
      while (itrSet.hasNext()) {
         T element = itrSet.next();
         if (!result.contains(element)) {
            result.add(element);
         }
         
      }
      return result;
            
                  
               
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    * @param s a.
    * @return  a set 
    */
    
   public Set<T> union(LinkedSet<T> s) {
      Iterator<T> itrThis = this.iterator();
      Iterator<T> itrSet = s.iterator();
      
      Set<T> result = new LinkedSet();
      
      while (itrThis.hasNext()) {
         T element = itrThis.next();
         result.add(element);
      }
      
      while (itrSet.hasNext()) {
         T element = itrSet.next();
         if (!result.contains(element)) {
            result.add(element);
         }
         
      }
      return result;
   
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    * @param s a.
    * @return  a set 
    */
    
   public Set<T> intersection(Set<T> s) {
      Iterator<T> itr = s.iterator();
     
      Set<T> result = new LinkedSet<T>();
      
      while (itr.hasNext()) {
         T element = itr.next();
         if (this.contains(element)) {
            result.add(element);
         }
      }
     
      return result;  
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    * @param s a.
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
    
   public Set<T> intersection(LinkedSet<T> s) {
      Iterator<T> itr = s.iterator();
     
      Set<T> result = new LinkedSet<T>();
      
      while (itr.hasNext()) {
         T element = itr.next();
         if (this.contains(element)) {
            result.add(element);
         }
      }
     
      return result;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    * @param s a.
    * @return  a set 
    */
    
   
   public Set<T> complement(Set<T> s) {
      Iterator<T> itrThis = this.iterator();
      Iterator<T> itrSet = s.iterator();
      
      Set<T> result = new LinkedSet();
      
      if (this.size() == 0) {
         return result;
      }
      else {
         while (itrThis.hasNext()) {
            T element = itrThis.next();
            result.add(element);
         }
      
         while (itrSet.hasNext()) {
            T element = itrSet.next();
            if (s.contains(element)) {
               result.remove(element);
            }
         }
      }
      
      return result;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    * @param s a.
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
    
   public Set<T> complement(LinkedSet<T> s) {
      Iterator<T> itrThis = this.iterator();
      Iterator<T> itrSet = s.iterator();
      
      Set<T> result = new LinkedSet();
      
      if (this.size() == 0) {
         return result;
      }
      else {
         while (itrThis.hasNext()) {
            T element = itrThis.next();
            result.add(element);
         }
      
         while (itrSet.hasNext()) {
            T element = itrSet.next();
            if (s.contains(element)) {
               result.remove(element);
            }
         }
      }
      
      return result;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      Iterator itr = new LinkedIterator();
      return itr;
   }
      /**
    * @param<T> a.
    * @return 0.
   */
   
   private class LinkedIterator implements Iterator<T> {
   
      private Node current = front;
   
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new MyDescendingtIterator(rear);
   }
      /**
    * @param<T> a.
    * @return 0.
   */
   
   private class MyDescendingtIterator implements Iterator<T> {
      Node t;
      
      public MyDescendingtIterator(Node rear) {
         t = rear;
      }
   
      public boolean hasNext() {
         return t != null && t.element != null;
      }
   
      public T next() {
         if (t != null) {            
            T r = t.element;
            t = t.prev;
            return r;
         }
         
         return null;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }

   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specif ic order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerIterator(front,size);
   }
      /**
    * @param<T> a.
    * @return 0.
   */
   
   private class PowerIterator implements Iterator<Set<T>> {
      Node n;
      int c;
      int current;
      
      public PowerIterator(Node front,int size) {
         n = front;
         c = size;
         current = 0;  
      }
      
      public boolean hasNext() {
         return (current < (int) Math.pow(2, c));
      }
            
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
      public Set<T> next() {
         n = front;
         Set<T> s = new LinkedSet<T>();
         int m = 1;
         for (int k = 0; k < c; k++) {
            if ((k & m) != 0) {
               s.add(n.element);
            }
            else {
               n = n.next;
            }
         }
         current++;
         return s;
      }
   }

   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
