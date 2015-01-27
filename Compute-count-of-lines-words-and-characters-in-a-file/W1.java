/**
 * W1
 *
 * @version   $Id: Combination.java,v 1.1 2014/09/06 15:21:46 ssa2923 $
 *
 * @author      Shraddha Atrawalkar
 * @author      Tobin Pereira
 *
 * Revisions:
 * Initial revision
 *
 */

/**
 * This program using a file as input count the number of lines,
 * words and count of each character in the file
 */

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

/**
 * This class defines 3 methods one that converts the file into a string second
 * that takes the entire file string into an array and third that computes the
 * count of each string in the file
 */

public class W1 {
	// Define variables to be used for counting
	public static int line_count; // to count number of lines
	public static int word_count; // to count number of words
	public static int char_count; // to count number of each character
	public static String words; // to store words of a line
	public static String complete; // to store the complete file in a string
	public static char[] complete_copy; // to store a copy of the file string
	public static int char_counter;//to count the number of printable characters
	// public static char[] chars; //to store each character
	// public static int[] count; //to store each character count

	/*
	 * Given the string of each line this method combines each line of the file
	 * into a string
	 * 
	 * @param line_count line number
	 * 
	 * @param s string of the line
	 */
	public static void create_string(int line_count, String s) {
		// if first line
		if (line_count == 1) {
			complete = s;// move the first line
		} else if (line_count != 1)// if second line onwards
		{
			complete = complete + s;// concatenate the previous line and current
		}
	}

	/*
	 * Given the string of the file this method stores the entire string into an
	 * array
	 * 
	 * @param complete complete file string
	 * 
	 * @param complete_copy array of characters of the file
	 */
	public static void convert_to_array(String complete, char[] complete_copy) {
		int c_length = complete.length();// get length of the string
		complete_copy = new char[complete.length()];// array to store the file
													// string

		// move each character from the string into an array
		for (int i = 0; i < c_length; i++) {
			complete_copy[i] = complete.charAt(i);
		}

		// method to get the count of each character in the file
		getcharcount(complete, complete_copy);
	}

	/*
	 * Given the file string and a character array this method computes the
	 * count of each character in the file
	 * 
	 * @param complete complete file string
	 * 
	 * @param complete_copy array of characters of the file
	 */
	public static void getcharcount(String complete, char[] complete_copy) {

		// count = new int[complete.length()];
		// chars = new char[complete.length()];
		int i = 0;// counter variable
		int j = 0;// counter variable

		// Sort the character array
		Arrays.sort(complete_copy);

		while (i < complete_copy.length - 1) {
			i = j;
			// get the ith character to be compared
			char c = complete_copy[i];

			// Till the end of the array keep comparing the current character to
			// the next
			while (complete_copy[j] == c && j <= complete_copy.length) {
				// increment the character count when current and next
				// characters are same
				char_count++;
				//Get the number of printable characters
				char_counter++;
				j++;
				// reached end of array
				if (j == complete_copy.length)
					break;
			}
			
			// Print the count of the character
			System.out.println(c + ":" + char_count);
			char_count = 0;// reset counter
			// reached end of array
			if (j == complete_copy.length)
				break;
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		try {
			// System.out.print(System.getProperty("user.dir"));
			Scanner sc = new Scanner(new File("macbeth.txt"));
			// Scan the file to see if there are lines in the file
			while (sc.hasNext()) {
				line_count++;// increment the line count
				String line = sc.nextLine();// take the line into a string

				Scanner inLine = new Scanner(line);
				while (inLine.hasNext()) {
					words = inLine.next();// take the words of the line into a
											// variable
					word_count++;// increment the count of words
				}

				// create a string of the entire file by passing each line
				create_string(line_count, line);
				inLine.close();// close the line
			}
			// Convert the entire string into an array
			convert_to_array(complete, complete_copy);

			// Print the counts of lines and words
			System.out.println("Total number of lines :" + line_count);
			System.out.println("Total number of words :" + word_count);
			System.out.println("Total number of printable characters :" + char_counter);
			sc.close();// close the file

			// handle the exceptions
		} catch (Exception e) {
			System.err.println("Something went wrong!");
		}
	}
}
