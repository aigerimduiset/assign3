import java.util.Objects;
import java.util.ArrayList;

public class hash_table {
    public static void main(String[] args) {
 
		HashTable<String, Integer> HT = new HashTable<>();
		HT.add("first", 1);
		HT.add("second", 2);
		HT.add("first", 3);
		HT.add("third", 4);
		System.out.println("size() = " + HT.size());
		System.out.println("remove() = " + HT.remove("second"));
		System.out.println("remove() = " + HT.remove("second"));
		System.out.println("size() = " + HT.size());
		System.out.println("isEmpty() = " + HT.isEmpty());
	}
}

class HashTable<K, V> {
	private ArrayList<HashNode<K, V>> Array;
	private int countOfBuckets;
	private int sz;
	
	public HashTable() {
		sz = 0;
		countOfBuckets = 7;
		Array = new ArrayList<>();
		for (int i = 0; i < countOfBuckets; ++i) {
		    Array.add(null);
		}
	}

	public int size() { 
	    return sz; 
	}
	
	public boolean isEmpty() { 
	    if (sz == 0) { // if size is equal to zero then hast table is empty
	        return true;
        } else {
            return false;
        }
	}
    
	private final int hashCode(K key) {
		return Objects.hashCode(key);
	}

	public V get(K key) {
		int index = getIndex(key);
		int hashCode = hashCode(key);
		HashNode<K, V> cur = Array.get(index);
		while (cur != null) {
			if (cur.key.equals(key) && cur.hashCode == hashCode) { // compare their key and hashcode,
				return cur.value;                                 // if they are equal then we found a right node
			}
			cur = cur.next;
		}
		return null; // if not found then return null
	}

	private int getIndex(K key) {
		int hashCode = hashCode(key);
		int index = hashCode % countOfBuckets;
		if (index < 0) { // index cannot be negative so we take its absolute value
		    index = -index;
		} 
		return index;
	}

	public V remove(K key) {
		int index = getIndex(key);
		int hashCode = hashCode(key);
		HashNode<K, V> cur = Array.get(index);
		HashNode<K, V> prev = null;
		while (cur != null) {
			if (cur.key.equals(key) && hashCode == cur.hashCode) {
			    break;
			}
			prev = cur;
			cur = cur.next;
		}
		if (cur == null) {
		    return null;
		}
		sz--;
		if (prev != null) {
		    prev.next = cur.next;
		} else {
		    Array.set(index, cur.next);
		}
		return cur.value;
	}

	public void add(K key, V value) {
		int index = getIndex(key);
		int hashCode = hashCode(key);
		HashNode<K, V> cur = Array.get(index);
		while (cur != null) {
			if (cur.key.equals(key) && cur.hashCode == hashCode) {
				cur.value = value; // if it's already in hash table, then just update its value
				return;
			}
			cur = cur.next;
		}
		sz++; // otherwise we have to add it
		cur = Array.get(index);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
		newNode.next = cur;
		Array.set(index, newNode);
		if ((1.0*sz)/countOfBuckets >= 0.7) { // if buffer is full, then expand it and rebuild hash table
			ArrayList<HashNode<K, V>> old = Array;
			Array = new ArrayList<>();
			countOfBuckets = 2*countOfBuckets;
			sz = 0;
			for (int i = 0; i < countOfBuckets; ++i) {
			    Array.add(null);
			}
			for (HashNode<K, V> x : old) {
				while (x != null) {
					add(x.key, x.value);
					x = x.next;
				}
			}
		}
	}
}

class HashNode<K, V> { 
	K key;
	V value;
	final int hashCode;
	HashNode<K, V> next;
	
	public HashNode(K newKey, V newValue, int newHashCode) { // constructor 
		this.key = newKey;
		this.value = newValue;
		this.hashCode = newHashCode;
	}
}
