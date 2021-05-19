import java.util.*;

public class BST {
    public static void main(String[] args) {
    }
}

class Node {
    int key;
    Node l, r;
    
    public Node(int value) {
        key = value;
        l = null;
        r = null;
    }
}

class BinarySearchTree {
    Node root;
    
    BinarySearchTree() { 
        root = null; 
    }
    
    Node delete(Node cur, int key) { 
        if (cur == null) {
            return cur;
        }
        if (key < cur.key) {
            cur.l = delete(cur.l, key); 
        } else if (key > cur.key) {
            cur.r = delete(cur.r, key);
        } else {
            if (cur.l == null) {
                return cur.r;
            } 
            if (cur.r == null) {
                return cur.l;
            }
            cur.key = minSubtree(cur.r);
            cur.r = delete(cur.r, cur.key);
        }
        return cur;
    }
    
    void delete(int key) { // just for convenience 
        root = delete(root, key); 
    }
    
    int minSubtree(Node cur) { // minimum value in subtree of given node (which is the leftmost node)
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.key;
    }
    
    Node insert(Node cur, int key) {
        if (cur == null) {
            cur = new Node(key);
            return cur;
        }
        if (key < cur.key) {
            cur.l = insert(cur.l, key);
        } else if (key > cur.key) {
            cur.r = insert(cur.r, key);
        }
        return cur;
    }
    
    void insert(int key) { // just for convenience 
        root = insert(root, key); 
    }
    
    public Node search(Node cur, int key) {
        if (cur == null || cur.key == key) { // if keys are identical then we found the right node
            return cur;
        }
        if (cur.key > key) { // otherwise, if key is less than current key, then it must be on the left side
            return search(cur.l, key);
        } else {
            return search(cur.r, key); // else, on the right side
        }
    }
}