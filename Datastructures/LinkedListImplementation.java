package oy.tol.tra;


public class LinkedListImplementation<E> implements LinkedListInterface<E> {

   private class Node<T> {
      Node(T data) {
         element = data;
         next = null;
      }
      T element;
      Node<T> next;
      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head = null;
   private int size = 0;

   /**
    * Add an element to the end of the list.
    * @param element The element to add, must not be null.
    * @throws NullPointerException If the parameter element is null.
    * @throws LinkedListAllocationException If failed to allocate a new list element.
    */

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      
      if (element == null){
         throw new NullPointerException("Element can't be null");
      }
      try {
         if (head == null){
            head = new Node<E>(element); 
            size++;
         } else { 
            Node<E> current = head;
            
            while (current.next != null){
               current = current.next;
            }
            current.next = new Node<E>(element);
            size++;      
         }
      } catch(Exception E){
         throw new LinkedListAllocationException("Allocation for linked list failed");

      }
   }

   /**
    * Add an element to the specified index in the list.
    * @param index The index where to add the element, must be 0...count().
    * @param element The element to add, must not be null.
    * @throws NullPointerException If the parameter element is null.
    * @throws LinkedListAllocationException If failed to allocate a new list element.
    * @throws IndexOutOfBoundsException If the specified index to the list is out of bounds.
    */

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      
      if (element == null){
         throw new NullPointerException("Element can't be null");
      }

      if (index < 0 || index > size){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }
      try {
         if (index == 0){
            Node<E> newNode = new Node<E>(element);
            newNode.next = head;
            head = newNode;
            size++;
         } else {
            int counter = 0;
            Node<E> current = head;
            Node<E> previous = null;
            while (counter < index){
               previous = current;
               current = current.next;
               counter++;
            }
            Node<E> newNode = new Node<E>(element);
            previous.next = newNode;
            newNode.next = current;
            size++;
         }
      } catch(Exception E){
         throw new LinkedListAllocationException("Allocation for linked list failed");
      }
   }


   /**
    * Removes an element from the list. Element must not be null.
    * @return True if element was found and removed, false otherwise.
    * @throws NullPointerException If the parameter element is null.
    */
   @Override
   public boolean remove(E element) throws NullPointerException {
      // TODO: Implement this.
      if (element == null){
         throw new NullPointerException("Element can't be null");
      }

      if (size < 1){
         return false;
      }
      boolean result = false;
      Node<E> current = head;
      
      
         while (current.next != null){
            current = current.next;
            if (current.element.equals(element)){
               size--;
               result = true;
               break;
            }
         }
               
      
      return result;
   }

   @Override
   public E remove(int index) throws IndexOutOfBoundsException {
      
      if (index < 0 || index >= size){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      if (size < 1){
         throw new IndexOutOfBoundsException("Linked list is empty");
      }

      E rmove = null;

      if (index == 0){
         rmove = head.element;
         head = head.next;
         size--;
      } else {
         int counter = 0;
         
         Node<E> current = head;
         Node<E> previous = null;
         while (counter < index){
            previous = current;
            current = current.next;
            counter++;
            break;
         }
         
         //tässä kesken: pitäisi tallentaa elementti ja returnaa se
         rmove = current.element;
         previous.next = current.next;
         size--;
      }

      return rmove;
   }
   /**
    * Returns the element at the index, not removing it from the list.
    * If index < 0 or >= size(), throws IndexOutOfBoundsException.
    * @return The element in the specified index.
    * @throws IndexOutOfBoundsException If the specified index to the list is out of bounds.
    */
   @Override
   public E get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index > size){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      if (size < 1){
         throw new IndexOutOfBoundsException("Linked list is empty");
      }

      E result = null;
      if (index == 0){
         result = head.element;
         return result;

      } else {
         int counter = 0;
         Node<E> current = head;
         
         
         while (counter < index){
            current = current.next;
            counter++;
         }
         result = current.element;     
      }
      return result;
   }

   /**
    * Queries the index of the element in the list.
    * @param element The element to search for, must not be null.
    * @return The index of the element, or -1 if it was not found.
    * @throws NullPointerException If the parameter element is null.
    */
   @Override
   public int indexOf(E element) throws NullPointerException {
      if (element == null){
         throw new NullPointerException("Element can't be null");
      }

      int index = 0;
      Node<E> current = head;

      while (current != null){
         if (current.element.equals(element)){
            return index;
         }
         current = current.next;
         index++;
      }

      return -1;

   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void clear() {
      head = null;
      size = 0; 
   }
   
   // TODO: implement this in the 2nd task of the exercise.
   /**
    * Reverses the items in the list to opposite order.
    * Reversal happens in place; so the old order in this list is reversed.
    */
   @Override
   public void reverse() {

      Node<E> current = head;
      Node<E> previous = null;
      Node<E> next = null;

      while (current != null){
         next = current.next;
         current.next = previous;
         previous = current;
         current = next;

         //current = current.next;

         // previous = current;
         // current = current.next; alkup

         // current.next = previous;
         // previous = current;
      }
      head = previous;

      // TODO: implement this only when doing the task explained the TASK-2.md.
      // This method is not needed in doing the task in the README.md.
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("[");

      if (size > 0){
         Node<E> current = head;
         while (current.next != null){
            result.append(current.element);
            result.append(", ");
            current = current.next;
         }
         result.append(current.element);
      }

      result.append("]");

      return result.toString();
   }
   
}
