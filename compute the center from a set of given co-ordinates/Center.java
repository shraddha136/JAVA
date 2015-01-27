/**
 * Center
 *
 * @version   $Id: Center.java,v 1.1 2014/09/27 07:21:46
 *
 *
 * Revisions:
 * Initial revision
 *
 */

import java.io.File;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * This method defines 5 methods to get the center object of all objects in a 3D
 * co-ordinate system
 */
public class Center {

	// define variables
	public static double[][] points = new double[500000][4];// array to store
															// the xyz
															// co-ordinates and
															// the sum of
															// lengths to each
															// point
	public static int count_points;// count of total points in the space
	public static String number = "[+-]?\\d*.?\\d+\\s[+-]?\\d*.?\\d+\\s[+-]?\\d*.?\\d+";// string
																						// format
																						// to
																						// determine
																						// proper
																						// number
																						// format
	public static double minimum_distance;// the minimum sum of distances of the
											// center object

	/*
	 * Given a file with xyz co-ordinates in a space, this method stores them in
	 * an array for later computation array
	 * 
	 * @param count_points index of the point in the array
	 * 
	 * @param xyz_points string with xyz co-ordinates of the point
	 */
	public static void convert_to_array(int count_points, String xyz_points) {
		String values[]; // temporary string array to split and store elements

		values = xyz_points.split("\\s");// split the string at space to get
											// elements

		// store each co-ordinate set in an array
		points[count_points][0] = Double.valueOf(values[0]);// x co-ordinate
		points[count_points][1] = Double.valueOf(values[1]);// y co-ordinate
		points[count_points][2] = Double.valueOf(values[2]);// z co-ordinate

	}

	/*
	 * Given an array with xyz co-ordinates of all points in the space, this
	 * method computes the sum of distances from each point to the other point
	 * in the space
	 * 
	 * @param points array with xyz co-ordinates of points in space
	 */

	public static void calculate_distance(double[][] points) {
		double x1, x2, y1, y2, z1, z2;// variables to store xyz co-ordinates of
										// 2 points
		double total_dist = 0;// Initialize the variable to store sum of
								// distances
		// compute the sum of distances using the Euclidian formula to compute
		// the distance between 2 points in a 3D co-ordinate system
		// dist((x1, y1, z1), (x2, y2, z2)) = sqrt(pow[(x1 - x2),2] + pow[(y1 -
		// y2),2] + pow[(z1 - z2),2])
		for (int i = 0; i < count_points; i++) {
			// take the co-ordinates of 1 point
			x1 = points[i][0];// x co-ordinate
			y1 = points[i][1];// y co-ordinate
			z1 = points[i][2];// z co-ordinate
			for (int j = 0; j < count_points; j++) {
				// compute distance of 1st point with all others in space
				x2 = points[j][0];// x co-ordinate
				y2 = points[j][1];// y co-ordinate
				z2 = points[j][2];// z co-ordinate

				// compute the distance
				total_dist = min_distance(x1, x2, y1, y2, z1, z2);

				// store the distance between points calculated in the array and
				// keep adding till distance from one point to all points is
				// computed
				points[i][3] = total_dist + points[i][3];
			}
			// Print the sum of distances from point 1 to all others
			System.out.println("Total distance from " + points[i][0] + ", "
					+ points[i][1] + ", " + points[i][2] + " to all points:\t"
					+ points[i][3]);
		}
	}

	/*
	 * Given 2 points in space, this method computes the distance between them
	 * 
	 * @param x1 x co-ordinate of 1st point
	 * 
	 * @param y1 y co-ordinate of 1st point
	 * 
	 * @param z1 z co-ordinate of 1st point
	 * 
	 * @param x2 x co-ordinate of 2nd point
	 * 
	 * @param y2 y co-ordinate of 2nd point
	 * 
	 * @param z2 z co-ordinate of 2nd point
	 */

	public static double min_distance(double x1, double x2, double y1,
			double y2, double z1, double z2) {

		double minimum = 0;// initialize the variable to store distance

		// compute the distance between them as per Euclidian's formula
		// mentioned above
		double xdiff = x1 - x2;
		double ydiff = y1 - y2;
		double zdiff = z1 - z2;
		minimum = Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2)
				+ Math.pow(zdiff, 2));
		return minimum;
	}

	/*
	 * This method gets the minimum distance of all distances for every point in
	 * space
	 */

	public static double get_center() {
		double a = 0, v = 0;// Initialize variables

		for (int i = 0; i < count_points - 1; i++) {
			if (v == 0) {// indicates this is the 1st & 2nd elements being
							// compared
				if (points[i][3] < points[i + 1][3]) {
					minimum_distance = points[i][3];
				} else {
					minimum_distance = points[i + 1][3];// gets the lesser
														// distance
				}
				a = minimum_distance;// assigns minimum distance to a variable
				v = 1;// indicates comparison of 1st and 2nd point is over
			} else {
				minimum_distance = (points[i][3] < points[i + 1][3] ? points[i][3]
						: points[i + 1][3]);// gets the lesser distance

				if (a > minimum_distance)// if the previously obtained minimum
											// distance is
					// greater than the minimum found now, replace value
					a = minimum_distance;
			}
		}
		return a;// return the minimum distance
	}

	/*
	 * Given the minimum distance, this method searches the array of points and
	 * prints the point which is the center i.e. has the minimum sum of
	 * distances
	 * 
	 * @param minimum_distance minimum distance computed
	 */

	public static void print_points(double minimum_distance) {
		for (int i = 0; i < count_points; i++) {
			if (points[i][3] == minimum_distance) {
				// if the point with minimum distance is found, print its
				// co-ordinates
				System.out.println(points[i][0] + " " + points[i][1] + " "
						+ points[i][2]);
			}
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

			// Read the file with co-ordinate values
			Scanner sc = new Scanner(new File("cat file.txt"));
			while (sc.hasNext()) {// if the file is no empty

				String line = sc.nextLine();// read the line

				Scanner input = new Scanner(line);
				// Validate the entries in the file, they should be a number
				input.findInLine(number);
				// get the matched input for each line in the file
				MatchResult result = input.match();

				// Convert the file to an array
				convert_to_array(count_points, result.group(0));

				// increment each point found in the file
				count_points++;

				// close the line
				input.close();
			}

			sc.close();// close the file

			// calculate the total distance from 1 to each point
			calculate_distance(points);

			// Get the minimum of all distances computed above and print it
			minimum_distance = get_center();
			System.out.print("\nThe minimum distance is:" + minimum_distance);

			// Print the center object along with its sum of distances to other
			// points and its co-ordinates
			System.out.print("\nAnd the center object(s) of all objects is: ");
			print_points(minimum_distance);

		} catch (Exception e) {// catch exception when an action fails while
								// processing the code
			System.err.println("Something went wrong!");
		}
	}

}
