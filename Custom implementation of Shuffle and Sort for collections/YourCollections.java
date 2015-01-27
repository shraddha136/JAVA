/**
 * YourCollections
 *
 * @version   $Id: YourCollections.java,v 1.1 2014/10/11 07:21:46
 *
 * @author      Shraddha Atrawalkar
 *
 * Revisions:
 * Initial revision
 *
 */

/**
 * This program shuffles and sorts a list of elements
 */
import java.util.*;

/**
 * This class defines 2 methods 1. sort: that sorts the elements provided in the
 * list 2. shuffle: that shuffles the elements provided in the list
 */
public class YourCollections<T> {

	public List<T> list_data = new ArrayList<T>();// List to hold the elements
													// to be sorted and shuffled
	public ArrayList<T> shuffled = new ArrayList<T>();// List of shuffled
														// elements
	public List<T> sort_list = new ArrayList<T>();// List of sorted elements
	// Define the Comparator
	public Comparator<T> comp_sort = new Comparator<T>() {
		// Override the compare method of the Comparator Interface
		@SuppressWarnings("rawtypes")
		public int compare(Object o1, Object o2) {
			// Compare the 2 elements in the list and return its difference
			// value
			@SuppressWarnings("unchecked")
			int result = ((Comparable) o1).compareTo((Comparable) o2);
			return result;
		}

	};

	/**
	 * Given a list of elements, this method sorts the elements as per the
	 * comparator defined in main
	 * 
	 * @param list
	 *            List of elements to be sorted
	 * @param c
	 *            Comparator as per which elements are to be sorted
	 */
	@SuppressWarnings("unchecked")
	public void sort(List<T> list, Comparator<T> c) {

		Object temp;// temporary variable to store elements while sorting
		Object a = null;// variable to store 1st element
		Object b = null;// variable to store 2nd element

		// Using the bubble sort algorithm, compare 2 elements and swap them if
		// they are not as per the comparator logic(here ascending order)
		for (int j = 0; j < list.size() - 1; j++) {
			for (int i = 0; i < list.size() - 1; i++) {
				if (c.compare(list.get(i), list.get(i + 1)) > 0) {// call the
																	// overriding
																	// compare
																	// method
																	// defined
																	// in the
																	// comparator
					// swap the elements if they are not in the desired order
					// and update the list
					temp = list.get(i);
					a = list.get(i + 1);
					b = temp;
					list.set(i, (T) a);
					list.set(i + 1, (T) b);
				}
			}
		}
		// Print the sorted list
		System.out.println("The sorted list is:" + list);
	}

	/**
	 * Shuffle the elements in the list using Fisher and Yates' original method
	 * Pencil-and-paper method
	 * 
	 * @param shuffle_list
	 *            the list of elements to be shuffled
	 * @return return the shuffled list of elements
	 */
	public List<T> shuffle(List<T> shuffle_list) {

		T removed_element;// the element randomly chosen to add to the shuffled
							// list
		int size = shuffle_list.size();// size of the list

		// randomly choose an element from the list and add it to a shuffled
		// list
		for (int i = 0; i < size; i++) {
			Random rn = new Random();
			int j = rn.nextInt(shuffle_list.size());// get the random index
			removed_element = shuffle_list.get(j);// get the element at that
													// random index

			shuffled.add(removed_element);// add the element to the shuffled
											// list
			shuffle_list.remove(j);// remove that element from the original list

		}
		// Print the shuffled list
		System.out.println("The shuffled list of elements is :" + shuffled);
		shuffle_list = shuffled;
		return shuffle_list;// return the shuffled list
		
		}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) {
		// Create an object of class Shuffle
		YourCollections<Integer> s = new YourCollections<Integer>();

		// Add Integer data to the list
		s.list_data.add(200);
		s.list_data.add(30);
		s.list_data.add(9);
		s.list_data.add(100);
		s.list_data.add(120);
		s.list_data.add(89);
		s.list_data.add(75);
		s.list_data.add(1);
		s.list_data.add(29);
		s.list_data.add(46);
		s.list_data.add(232);

		// Print the data added to the list
		System.out.println("The elements in your list are: " + s.list_data);

		// Shuffle the data in the list
		List<Integer> sort = s.shuffle(s.list_data);


		// Sort the list of integers
		s.sort(sort, s.comp_sort);

		/*
		 * //Add string data to the list s.sort_list.add("Hello");
		 * s.sort_list.add("afsgo"); s.sort_list.add("wgwg");
		 * s.sort_list.add("he5gs"); s.sort_list.add("werfh");
		 * s.sort_list.add("jttrh"); s.sort(s.sort_list, comp_sort);
		 */

	}
}
