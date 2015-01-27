/**
 * HpTreeSet
 *
 * @version   $Id: HpTreeSet.java,v 1.1 2014/10/18 07:21:46
 *
 * @author      Shraddha Atrawalkar
 *
 * Revisions:
 * Initial revision
 *
 */

/**
 * This program implements a custom TreeSet Class
 */
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 
 * This class creates a node of a Tree in the Treeset
 *
 */
class Node {
    Object value; // value of the node
    Node Left; // left child of the node
    Node right; // right child of the node

    Node(Object value) {
        this.value = value;// assign the current value to the node
    }

}

/**
 * 
 * This class implements the following methods of a custom TreeSet class boolean
 * add(Object e), void clear(), boolean contains(Object o), boolean isEmpty(),
 * Iterator iterator(), int size()
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class HpTreeSet extends TreeSet {

    Node root; // define root of the tree
    int size; // size of the treeset

    /**
     * method to add new nodes
     * 
     * @param value
     *            value of the new node to be created
     */
    @SuppressWarnings({ "unchecked" })
    public boolean add(Object value) {

        Node newnode = new Node(value);// create a new node

        if (root == null) { // This is the first node of the tree
            root = newnode; // make the new node as the root of the tree
            root.Left = null;// it has no children as of now
            root.right = null;
            size++;
            return true;
        } else {

            Node parent;// create a node to keep a track of the parent
            Node current_node = root;// start at the root node

            while (true) {
                parent = current_node;// make the current node as the parent
                if (((Comparable) value).compareTo(((Comparable) parent.value)) < 0) {// Add
                                                                                      // left
                    // child
                    current_node = current_node.Left;// traverse to the left
                    if (current_node == null) {// if there is no left child of
                                               // the current node, assign one
                        parent.Left = newnode;// Assign the new node as the left
                                              // child of the parent
                        size++;// increment the size of the tree
                        return true;
                    }

                } else if (((Comparable) value)
                        .compareTo(((Comparable) parent.value)) > 0) {// Add
                    // right
                    // child
                    current_node = current_node.right;// traverse to the right
                    if (current_node == null) {// if there is no right child if
                                               // the current node, assign one
                        parent.right = newnode;// assign the new node as the
                                               // right child of the parent node
                        size++;// increment the size of the tree
                        return true;
                    }
                } else
                    return false;
            }

        }

    }

    /**
     * Method to clear the tree
     */
    public void clear() {
        // Clear the root
        root = null;
        size = 0;// set the size of the tree to 0
    }

    /**
     * 
     * @param o
     *            The object that needs to be checked if present in the tree
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public boolean contains(Object o) {

        Node current = root;// start at the root node
        while (current != null) {
            if (((Comparable) o).compareTo(((Comparable) current.value)) == 0)// if
                                                                              // object
                                                                              // found
                                                                              // at
                                                                              // the
                                                                              // node,
                                                                              // return
                                                                              // true
                return true;
            else if (((Comparable) o).compareTo(((Comparable) current.value)) < 0) {// if
                                                                                    // object
                                                                                    // is
                                                                                    // less
                                                                                    // than
                                                                                    // the
                                                                                    // root
                                                                                    // node,
                                                                                    // move
                                                                                    // left
                current = current.Left;

            } else
                // if object is greater than the root node, move right
                current = current.right;
        }

        return false;// if object was not found, return false
    }

    /**
     * 
     * Method checks if the treeset is empty
     */
    public boolean isEmpty() {
        if (root == null) {// A treeset is empty if its root is null
            return true;
        }
        return false;
    }

    /**
     * 
     * This method returns the size of the treeset
     */
    public int size() {
        return size;
    }

    /**
     * This method returns the iterator of the treeset
     */
    public Iterator iterator() {
        MyIterator mt = new MyIterator();
        return mt;
    }

    /**
     * 
     * This class implements a custom iterator for the treeset
     *
     */
    class MyIterator implements Iterator {
        String[] elements;// array to store elements in the tree in a sorted
                          // order
        int size;// size of the tree
        int i;// temporary variable to denote index in the array
        int index;// index of the array

        /**
         * constructor to initialize the array of the elements and store them in
         * sorted order
         */
        public MyIterator() {
            this.size = size();// Get the size
            if (this.size != 0) {
                elements = new String[this.size];// Initialize the array of
                                                 // elements
                traverse(root);// Traverse from the root node of the tree
            }

        }

        /**
         * This method implements an Inorder traversal of the tree and stores it
         * in the array (Left-Root-Right)
         * 
         * @param node
         *            Node at which traversal of the tree should begin
         */
        public void traverse(Node node) {

            if (node.Left != null) {
                traverse(node.Left);
            }
            elements[i] = ((String) node.value);
            i++;
            if (node.right != null) {
                traverse(node.right);
            }

        }

        /**
         * Define the hasNext method of the Iterator;returns true if an element
         * is present next to the current
         */
        @Override
        public boolean hasNext() {
            if (index < size)
                return true;
            else
                return false;
        }

        /**
         * Define the next method of the Iterator;returns the next element
         * present;
         */
        @Override
        public Object next() {
            return ((Object) elements[index++]);

        }
    }

}
