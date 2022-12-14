package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {



   // TODO: Add member variables needed.
   // Do not use constant values in code, e.g. 10. Instead, define a constant for that. For example:
   private static final int STACK_SIZE = 10;
   private Object array [];
   private int top = -1;


   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size (see member variable!).
      this(STACK_SIZE);
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
         if (capacity < 2){
            throw new StackAllocationException("Too small");
         }
         array = new Object[capacity];
      }
   

   @Override
   public int capacity() {
      // TODO: Implement this
      
      return array.length;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      if (element==null){
         throw new NullPointerException("Value of element is null");
      }

      top++;
      //try {
      // int newCapacity = 2 * capacity();
      //       Object [] newStack = new Object[newCapacity];

      //       for (int i=0; i<array.length; i++){
      //          newStack[i]=array[i];
      //       }
      //       array = newStack;
      //} catch (Ex)
      

      if(top == array.length ){
         int newCapacity = 2 * capacity();
         Object [] newStack;
         try {
            newStack = new Object[newCapacity];

         }
       catch(Exception E){
         throw new StackAllocationException("Reallocation for stack failed");
      }
      for (int i=0; i<array.length; i++){
         newStack[i]=array[i];
      }
      array = newStack;
   }
         array[top] = element;
   }



   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      
         if (top == -1){
            throw new StackIsEmptyException("Stack is empty");
         } 
      
      Object result = array[top];
      array[top] = null;
      top--;
      
      return (E) result;
   
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      
      if(top == -1){
         throw new StackIsEmptyException("Stack is empty");
      }

      return (E) array[top];
   }

   @Override
   public int size() {
      
      return top+1;
   }

   @Override
   public void clear() {
      
      for (int i=0; i<array.length; i++){
         array[i] = null;
      }
      top = -1;

   }

   @Override
   public boolean isEmpty() {
      if (top == -1){
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      //TODO: Implement this
      
      StringBuilder result = new StringBuilder();
      
      result.append("[");

      for (int i=0; i <= top; i++){

         result.append(array[i].toString());
         if (i < top){
            result.append(", ");
         }
       }
      result.append("]");

      return result.toString();
   }
}

