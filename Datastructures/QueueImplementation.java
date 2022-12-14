package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    private int tail = 0;
    private int head = 0;
    private int count = 0;
    private Object array [];


    /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */

    public QueueImplementation() throws QueueAllocationException {
        // TODO: call the constructor with size parameter with default size (see member variable!).
        this(10);
     }
  
     /** TODO: Implement so that
      * - if the size is less than 2, throw StackAllocationException
      * - if the allocation of the array throws with Java exception,
      *   throw StackAllocationException.
      * @param capacity The capacity of the stack.
      * @throws StackAllocationException If cannot allocate room for the internal array.
      */
     public QueueImplementation(int capacity) throws QueueAllocationException {
           if (capacity < 2){
              throw new QueueAllocationException("Too small");
           }
           array = new Object[capacity];
        }
  


public int capacity(){

    return array.length;
}
   
   /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throws NullPointerException If the element is null.
    */
public void enqueue(E element) throws QueueAllocationException, NullPointerException{

    if (element == null){
        throw new NullPointerException("Element can't be null");
    }

    if (count >= capacity()){
        int newCapacity = 2 * capacity();
        Object [] newQue; 
    try {
        newQue = new Object[newCapacity];

    } catch (Exception E){
        throw new QueueAllocationException("Reallocation for queue array failed.");
    } 
        int index = head;
        int counter = count;
        int newarrayitem = 0;
        while (counter > 0){
            newQue[newarrayitem] = array[index];
            newarrayitem++;
            index++;
            counter--;

            if (index >= capacity()) {
                index = 0;
            }
        }
        head = 0;
        tail = count;
        array = newQue;
    

    }
    if (tail >= capacity() && head > 0){
        tail = 0;
    }
    array[tail] = element;
    tail++;
    count++;

}

   /**
    * Removes an element from the queue.
    * @return The element from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
    
public E dequeue() throws QueueIsEmptyException{
    if (count == 0){
        throw new QueueIsEmptyException("Queue is empty");
    }
    Object x = array[head];
    array[head] = null;

    head++;
    count--;
    if (head >= capacity()){
        head = 0;
    }    

    return (E) x;
}

   /**
    * Returns the element at the head of the queue, not removing it from the queue.
    * @return The element in the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
public E element() throws QueueIsEmptyException{
    if (isEmpty() == true){
        throw new QueueIsEmptyException("Queue is empty");
    }

    return (E) array[head];
}

   /**
    * Returns the count of elements currently in the queue.
    * @return Count of elements in the queue.
    */
public int size(){
    return count;
}

   /**
    * Can be used to check if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
public boolean isEmpty(){
    if(count == 0){
        return true;
    } else {
        return false;
    }
}

   /**
    * Resets the queue to empty state, removing the objects.
    * There is no need to change the capacity, just keep it as it is.
    */
public void clear(){
    for (int i=0; i<array.length; i++){
        array[i] = null;
     }
     head = 0;
     tail = 0;
     count = 0;
}
@Override
public String toString(){

    int h = head;
    int counter = count;

    StringBuilder result = new StringBuilder();
      result.append("[");

    while (counter > 0){

        result.append(array[h].toString());
        
        
        h++;
        counter--;
        if (counter > 0){
            result.append(", ");
        }

        if (h >= capacity()) {
            h = 0;
        } 
    }
       result.append("]");
      
    return result.toString();
}
}

