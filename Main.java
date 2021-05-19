import java.util.*;

public class Main {
	public static void main(String[] args) {
	}
}

class MergeSort<T extends Comparable<T>> {
	public void sort(T[] x) {
	    sort(x, 0, x.length-1);
	}
	
	private void sort(T[] x, int start, int end) {
	    if (start < end) {
	        int middle = (start+end)/2; // middle-start = end-middle, so middle = (start+end)/2
	        sort(x, start, middle); // sort left half | [start, middle]
	        sort(x, middle+1, end); // sort right half | [middle+1, end]
	        merge(x, start, end, middle);
	    }
	}
	
	public void merge(T[] x, int start, int end, int middle) {
	    int left = middle-start+1; // size of a left half
	    int right = end-middle; // size of a right half
	    
	    Object[] a = (T[]) new Object[left];  
	    Object[] b = (T[]) new Object[right];
	    
	    // take a copy of a left half
	    for (int j = 0; j < left; j++) {
	        a[j] = x[j+start]; // j-th next number from start | [start, middle]
	    }
	    // take a copy of a right half  
	    for (int j = 0; j < right; j++) {
	        b[j] = x[j+middle+1]; // j-th next number from middle+1 | [middle+1, end]
	    }
	    
	    int ptr_a = 0, ptr_b = 0;
	    
	    for (int j = 0; j < end-start+1; j++) { // end-start+1 is the size of a whole segment
	        if (ptr_b == right || ((T)a[ptr_a]).compareTo((T) b[ptr_b]) <= 0) { // if there's no elements left in b, or min. element
	            x[j+start] = (T) a[ptr_a];                   // of a is less than b's, then put that element in
	            ptr_a++;                                 // the beginning of a new array
	        } else {
	            x[j+start] = (T) b[ptr_b];                   // otherwise put min. element of b
	            ptr_b++;
	        }
	    }
	}
}
