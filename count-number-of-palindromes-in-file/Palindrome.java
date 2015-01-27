/**
 * Palindrome
 *
 * @version   $Id: Palindrome.java,v 1.1 2014/09/14 07:21:46
 *
 * @author      Shraddha Atrawalkar
 * @author      Tobin Pereira
 *
 * Revisions:
 * Initial revision
 *
 */

/*
 * Assumption: Space(' ') is also a character and the program will only 
 * make a count of unique palindromes in the file
 */

/**
 * This program using a file as input counts the number of
 * palindromes in the file
 */

import java.util.Scanner;
import java.io.File;

/**
 * This class defines 4 methods 1. converts the string of the file into an array
 * 2. method that checks whether the word is a palindrome 3. prepares the
 * substrings that need to checked if a palindrome 4. does a character by
 * character comparison to check the palindrome
 */

public class Palindrome {

	// Define variables to be used for counting
	public static int count; // to count number of palindromes
	public static String complete; // to store the complete file in a string
	public static char[] complete_copy; // to store a copy of the file string
	public static char[] reverse; // to store a reverse of the file string
	// public static char[] temp; // to store a reverse of the file string
	// public static String words; // to store words of a line
	public static int end;// indicates the end of the string being considered
	public static int start;// indicates the start of the string being
							// considered
	public static String all_palindromes = new String();// stores all unique
														// palindromes

	/*
	 * Given the string of the file this method stores the entire string into an
	 * array
	 * 
	 * @param complete      complete file string
	 * 
	 * @param complete_copy array of characters of the file
	 * 
	 * @param count         count of the palidromes
	 */
	public static void convert_to_array(String complete, char[] complete_copy,
			int count) {
		
		int c_length = complete.length();// get length of the string
		complete_copy = new char[complete.length()];// array to store the file
							 						// string

		// move each character from the string into an array
		for (int i = 0; i < c_length; i++) {
			complete_copy[i] = complete.charAt(i);
		}

		// call the method to check if the file has palindromes
		char_palin(complete, complete_copy);
	}

	/*
	 * Given the string of the file this method checks if the string has
	 * palindromes
	 * 
	 * @param complete      complete file string
	 * 
	 * @param complete_copy array of characters of the file
	 * 
	 * @param end           end indicator of the substring considered
	 * 
	 * @param start         start indicator of the substring considered
	 */
	public static void check_palindrome(String complete, char[] complete_copy,
			int end, int start) {
		int l = complete_copy.length;// get the length of the file string

		if (l > 2) {// palindromes are to be computed only if strings are
					// greater than 2

			// define an array to store reverse of the string considered
			reverse = new char[(end - start) + 1];
			int x = 0;
			// add values of the original string in reverse to the reverse
			// string
			for (int i = end; i >= start; i--) {
				reverse[x] = complete_copy[i];
				// System.out.println("rev" + reverse[x]);
				x++;
			}
			int i;
			// get the substring of the original string being checked for
			// palindromes
			String org_comp = complete.substring(start, end + 1);
			// System.out.print("Substring of org compared"+org_comp);

			// Compare the reverse and original string char by char
			for (i = 0; i < reverse.length; i++) {
				boolean result = check_char(org_comp.charAt(i), reverse[i]);
				// if the string is not a palindrome go out of the loop
				if (result == false)
					break;
			}

			// if entire string has been checked and the above loop has reached
			// the end
			if (i == reverse.length) {
				// if this is the first unique palindrome;store it in a string
				// to check later
				if (all_palindromes.isEmpty()) {
					all_palindromes = all_palindromes + "," + org_comp + ",";
					count++;// increase the count of the palindromes
					System.out.println(count + "." + org_comp);// print the
																// palindrome
				} else if (!(all_palindromes.contains("," + org_comp + ","))) {
					// if this is not the first unique palindrome and is not
					// present in the pool of unique palindromes;add it to the
					// string
					all_palindromes = all_palindromes + org_comp + ",";
					count++;// increase the count of the palindromes
					System.out.println(count + ". " + org_comp);					
				}
			}			
		}
	}

	/*
	 * Given the string of the file this traverses character by character and
	 * prepares substrings from the file to be checked for palindromes eg. if
	 * the sentence is "hello world";this method will prepare 'hello
	 * world','ello world','hell','ell' etc i.e. all possible substrings
	 * 
	 * @param complete      complete file string
	 * 
	 * @param complete_copy array of characters of the file
	 */
	public static void char_palin(String complete, char[] complete_copy) {

		for (int i = 0; i < complete.length(); i++) {
			// traverse each character from the start of the line
			
			for (int j = i; j < complete.length(); j++) {
				// create a substring from each letter in the line as starts
				if ((j - i) > 1) {
					end = j;// end indicator of the substring
					start = i;// start indicator if the substring
					check_palindrome(complete, complete_copy, end, start);
				}
			}
		}
	}

	/*
	 * Given the reverse and original files' substring, this method checks if
	 * both are identical
	 * 
	 * @param complete_i original substring from file
	 * 
	 * @param reverse_i  reverse substring checked for palindrome
	 */
	public static boolean check_char(char complete_i, char reverse_i) {
		// check if mirrored characters match
		if (Character.toLowerCase(complete_i) == Character
				.toLowerCase(reverse_i)) {
			return true;
		}
		return false;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("palindrome_1.txt"));
			System.out.println("\nPalindromes in the file:\n");
			while (sc.hasNext()) {
			
				String line = sc.nextLine();
				// System.out.println(line);// your code goes here
				// create a string of the entire file by passing each line
				convert_to_array(line, complete_copy, count);
			}
			sc.close();// close the file
			System.out.println("\nTotal :" + count);
		} catch (Exception e) {// catch exception when an action fails while
								// processing the code
			System.err.println("Something went wrong!");
		}
	}
}
