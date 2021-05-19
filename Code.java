import java.util.*;

public class Code {
    public static void main(String args[]) {
        
    }
}

class QuickSort<T extends Comparable<T>> {	
    
	public void quickSort(T[] x) {
	    quickSort(x, 0, x.length-1);
	}
	
	private void quickSort(T[] x, int start, int end) {
	    if (start < end) {
	        int pi = partition(x, start, end);
	        quickSort(x, start, pi-1); // sort left part before pivot 
	        quickSort(x, pi+1, end); // sort right part after pivot
	    }
	}
	
	private void swap(T[] x, int a, int b) { // just for convenience, function that swaps two given indices in an array
	    T c = x[a];
	    x[a] = x[b];
	    x[b] = c;
	}
	
	private int partition(T[] x, int start, int end) {
        T pivot = x[end]; // pivot (Element to be placed at right position)
        int pi = (start-1);  
        // Index of smaller element and indicates the right position of pivot found so far
        for (int j = start; j < end; j++) {
            // If current element is smaller than the pivot
            if (x[j].compareTo(pivot) < 0) {
                pi++;    // increment index of smaller element
                swap(x, pi, j);
            }
        }
        swap(x, pi+1, end);
        // Finally we place pivot at correct position by swapping
        // x[pi+1] and x[end] 
        return (pi+1);
	}
}
