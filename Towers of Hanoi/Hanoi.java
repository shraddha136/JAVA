/**
 * Hanoi
 *
 * @version   $Id: Hanoi.java,v 1.1 2014/09/19 07:21:46
 *
 * @author      Shraddha Atrawalkar
 *
 * Revisions:
 * Initial revision
 *
 */

/**
 * This program given a number of discs moves
 *  them from the starting disc 
 *  to an ending disc using 1 disc as an intermediate
 */
import java.util.Scanner;

/*
 * This class defines 5 methods 
 * move defines the pole number and its start,end and intermediete poles
 * fill_poles fills the poles with discs at the start
 * print_poles prints the state of each pole
 * getposition gets the index from where the pole has to be moved
 * getavailableposition get the index where the disc is to be placed on the end pole
 */
public class Hanoi {

	public static String pole0[];// array to determine state of pole0
	public static String pole1[];// array to determine state of pole1
	public static String pole2[];// array to determine state of pole2
	public static int position;// index of the disc on start pole
	public static int avail_position;// index where the disc can be placed on
	// the end pole
	public static int size;// total number of discs we are working with

	/*
	 * This defines the pole number and its start,end and intermediete poles
	 * 
	 * @param n disc number
	 * 
	 * @param poleStart The starting pole for the disc
	 * 
	 * @param poleOver The pole to be used as intermediate
	 * 
	 * @param poleEnd The pole where the disc needs to be moved
	 */
	public static void move(int n, String poleStart, String poleOver,
			String poleEnd) {
		size = Math.max(n, size);// To store the total number of discs
		// throughout the iteration
		// If there is only 1 disk, move it on the end pole
		if (n == 1) {
			System.out.println("\nMove disk 1 from pole" + poleStart
					+ " to pole" + poleEnd);

			// get the index of the disc on the start pole
			int polestartint = Integer.parseInt(poleStart);
			switch (polestartint) {
			case 0:
				position = getposition("" + n + "", pole0);
				pole0[position] = "0";
				break;
			case 1:
				position = getposition("" + n + "", pole1);
				pole1[position] = "0";
				break;
			case 2:
				position = getposition("" + n + "", pole2);
				pole2[position] = "0";
				break;
			}

			// get available position on the end pole
			int poleendint = Integer.parseInt(poleEnd);
			switch (poleendint) {
			case 0:
				avail_position = getavailableposition(pole0);
				pole0[avail_position] = "" + n + "";
				break;
			case 1:
				avail_position = getavailableposition(pole1);
				pole1[avail_position] = "" + n + "";
				break;
			case 2:
				avail_position = getavailableposition(pole2);
				pole2[avail_position] = "" + n + "";
				break;
			}

			print_poles(size, pole0, pole1, pole2);
			return;
		}
		// Move n-1 discs from start pole to middle pole using end as overpole
		move(n - 1, poleStart, poleEnd, poleOver);
		// Move disc n from start to end pole
		System.out.println("\nMove disk" + n + " from pole" + poleStart
				+ " to pole " + poleEnd);

		// get the index of the disc on the start pole
		int polestartint = Integer.parseInt(poleStart);
		switch (polestartint) {
		case 0:
			position = getposition("" + n + "", pole0);
			pole0[position] = "0";
			break;
		case 1:
			position = getposition("" + n + "", pole1);
			pole1[position] = "0";
			break;
		case 2:
			position = getposition("" + n + "", pole2);
			pole2[position] = "0";
			break;
		}

		// get available position on the end pole
		int poleendint = Integer.parseInt(poleEnd);
		switch (poleendint) {
		case 0:
			avail_position = getavailableposition(pole0);
			pole0[avail_position] = "" + n + "";
			break;
		case 1:
			avail_position = getavailableposition(pole1);
			pole1[avail_position] = "" + n + "";
			break;
		case 2:
			avail_position = getavailableposition(pole2);
			pole2[avail_position] = "" + n + "";
			break;
		}

		// print the state of the poles
		print_poles(size, pole0, pole1, pole2);

		// Move n-1 discs from middle pole to end pole using start as overpole
		move(n - 1, poleOver, poleStart, poleEnd);
	}

	/*
	 * This method fills the poles with discs at the start of the program
	 * 
	 * @param n total discs
	 */
	public static void fill_poles(int n) {
		// define 3 arrays for state of the poles
		pole0 = new String[n];
		pole1 = new String[n];
		pole2 = new String[n];
		// initialise the discs in each pole
		for (int i = 0; i < n; i++) {
			int temp = i + 1;
			pole0[i] = "" + temp;
			pole1[i] = "0";
			pole2[i] = "0";
		}
	}

	/*
	 * This method prints the state of the poles with discs at each iteration
	 * 
	 * @param size total number of discs
	 * 
	 * @param pole0[] array with state of pole0
	 * 
	 * @param pole1[] array with state of pole1
	 * 
	 * @param pole2[] array with state of pole2
	 */
	public static void print_poles(int size, String[] pole0, String[] pole1,
			String[] pole2) {

		for (int i = 0; i < size; i++) {
			System.out
					.println(pole0[i] + " " + pole1[i] + " " + pole2[i] + " ");
		}
		System.out.println("-" + " " + "-" + " " + "-" + " ");
		System.out.println("0" + " " + "1" + " " + "2" + " ");
	}

	/*
	 * This method gets the index on the pole from where the disc needs to be
	 * moved
	 * 
	 * @param n disc number
	 * 
	 * @param pole[] array with state of the pole
	 */
	public static int getposition(String n, String[] pole) {
		for (int i = 0; i < size; i++) {
			if (pole[i].contains(n)) {// index of the disc in start pole
				position = i;
				break;
			}
		}
		return position;
	}

	/*
	 * This method gets the index on the pole where the disc needs to be moved
	 * 
	 * @param pole[] array with state of the pole
	 */
	public static int getavailableposition(String[] pole) {
		for (int i = (size - 1); i >= 0; i--) {
			if (pole[i].contains("0")) {
				avail_position = i;// index on the end pole where the disc can
				// be placed
				break;
			}
		}
		return avail_position;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// get the number of discs to be worked with
		System.out.println("Enter the number of discs: ");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		// initialse arrays
		fill_poles(n);
		// print the start state of all poles
		System.out.println("The poles are");
		print_poles(n, pole0, pole1, pole2);
		// print the steps in which all discs can be moved to end pole
		System.out.println("The steps to move all discs from 0 to 2:");
		move(n, "0", "1", "2");
		in.close();
	}

}
