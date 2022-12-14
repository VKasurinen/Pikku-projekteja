package oy.tol.tra;
import java.util.function.Predicate;

public class Algorithms {

   //01-arrays
   
    public static <T> void reverse(T [] array) {

      int i = 0;
      while (i < array.length/2) {
        swap(array, i, array.length-i-1);
        i++;
        
     }
   }
   
   public static <T extends Comparable<T>> void sort(T [] array) {
      
      int i = array.length-1;      

      while (i > 0) {
         for (int index = i - 1; index >= 0; index--){
         if (array[i].compareTo(array[index])< 0) {
            swap(array, i, index);
         }
      }
         i--;
      }

   }
   
      public static <T> void swap(T [] array, int first, int second){
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
      }


   //02-mode

      public static class ModeSearchResult<T> {
         public T theMode;
         public int count = 0;
      }
   
      public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
         ModeSearchResult<T> result = new ModeSearchResult<>();
         result.theMode = null;
         result.count = -1;

         if (array == null || array.length < 2){
            return result;
         }
         sort(array);

         result.theMode=array[0];
         result.count = 1;

         int topFreq = 1;
         int tempFreq = 1;
         int index = 1;

         while (index < array.length){
            if(array[index].compareTo(array[index - 1])==0){
               tempFreq++;

            } else {
               if (tempFreq > topFreq){
                  result.count = tempFreq;
                  result.theMode = array[index-1];
                  topFreq = tempFreq;
               }
               tempFreq = 1;
            }
            index++;
         }
         if (tempFreq > topFreq){
            result.count = tempFreq;
            result.theMode = array[index-1];
            topFreq = tempFreq;
         }
            return result;
      }


      //03-draw

      public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule){

         int index = 0;

         for ( ; index < count; index++){
            
            if(rule.test(array[index])){
               break;
            }

         }
         if(index >= count){
            return count;
         }
         int nextIndex = index + 1;
         while (nextIndex < count){
            if(!rule.test(array[nextIndex])){
               swap(array, index, nextIndex);
               index++;
            }
            nextIndex++;
            
         }
         return index;
      }


      //05-binsearch

      public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
         // TODO Implement this in step 2.
         
         while (fromIndex <= toIndex){
            int middle = fromIndex + (toIndex - fromIndex) / 2;
            if (fromArray[middle].compareTo(aValue) == 0){
               return middle;
            }
            if (aValue.compareTo(fromArray[middle]) < 0){
               toIndex = middle -1;
            } else {
               fromIndex = middle+1;
            }
         }
         return -1;
         //return (int) aValue;
         
         //sillä välin kun vasen puoli <= oikea
            //määritellään keskikohta
            // ja jos keskikohta == haettava niin palautetaan se

            // jos haettava on < keskikohta niin 
            //oikea = keski-1

            //muutoin vasen = keski+1
      }

      
      //quicksort

      public static <E extends Comparable <E>> int partition(E [] array, int low, int high){

         E pivot = array[high];

         int i = (low - 1);

         for (int j = low; j <= high -1; j++){
            if (array[j].compareTo(pivot) < 0){
               i++;
               swap(array, i, j);
            }
         }
         swap(array, i + 1, high);

         return (i + 1);
      }

      
      public static <E extends Comparable<E>> void Quicksort(E [] array, int low, int high){
              
         if (low < high){
            int pi = partition(array, low, high);

            Quicksort(array, low, pi - 1);
            Quicksort(array, pi + 1, high);
         }
      }


      //heapsort

      public static <E extends Comparable<E>> void heapsort(E [] array){

         int size = array.length;

         for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
         }

        
        for (int i = size - 1; i >= 0; i--) {
        
            
            swap(array, 0, i);

           heapify(array, i, 0);
        }
      }

      public static <E extends Comparable <E>> void heapify(E [] array, int size, int i){
         int max = i;
         int left = 2 * i + 1;
         int right = 2 * i + 2;

         if (left < size && array[left].compareTo(array[max]) > 0){
            max = left;
         }
         if (right < size && array[right].compareTo(array[max]) > 0){
            max = right;
         }
         
         if (max != i) {
            
            swap(array, i, max);
         
            heapify(array, size, max);
         }
      }


      public static <E extends Comparable<E>> void fastSort(E [] array){
        Quicksort(array, 0, array.length - 1);
        // heapsort(array);
         

      } 
}

