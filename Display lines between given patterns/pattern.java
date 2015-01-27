/**
 * Pattern
 *
 * @version   $Id: pattern.java,v 1.1 2014/11/120 05:19:46
 *
 * @author      Shraddha Atrawalkar
 * Revisions:
 * Initial revision
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class defines methods to read a html file and remove its tags and get
 * the lines between specified patterns
 *
 */
public class pattern {

    public static BufferedReader input;// data read from file
    public static String line;// each line read from the file
    public static String fileoutput;// output file containing lines between the
                                    // patterns
    public static String fileinput;// input file-html
    public static String temp_file = "filewithouttags.txt";// temporary file
                                                           // containing html
                                                           // file data with no
                                                           // tags

    /**
     * Method to validate the command prompt input
     * 
     * @param args
     */
    public void validate(String args[]) {

        // If the number of parameters entered is more than 4
        if (args.length != 4) {
            System.err
                    .println("Usage: Pattern1  Pattern2 filename output.txt (Use _ if patterns have spaces)");
            System.exit(1);
        }

        // if the file mentioned is not an html file
        if (!args[2].endsWith(".html")) {
            System.err.println("Not a valid input File (Should be .html file)");
            System.exit(1);
        } else {
            fileinput = args[2];
        }

        // if the output file mentioned is not a txt file
        if (!args[3].endsWith(".txt")) {
            System.err.println("Not a valid output file (Should be .txt file)");
            System.exit(1);
        } else {
            fileoutput = args[3];
        }

    }

    /**
     * This method set the 2 pattern variables according to those entered on the
     * command prompt
     * 
     * @param args
     */
    public void setpattern(String args[]) {
        String arg1 = "", arg2 = "";// Temporary variabls to store patterns
        String args1[], args2[];// array to store strings after splitting
                                // pattern strings if they are more than 1 word
                                // long
        // set the first pattern
        if (args[0].contains("_")) {
            args1 = args[0].split("_", args[0].length());

            for (int i = 1; i < args1.length; i++) {
                args1[0] += "\\s" + args1[i];
            }
            arg1 = args1[0];

        } else
            arg1 = args[0];
        // set the second pattern
        if (args[1].contains("_")) {
            args2 = args[1].split("_", args[1].length());

            for (int i = 1; i < args2.length; i++) {
                args2[0] += "\\s" + args2[i];
            }
            arg2 = args2[0];
        } else
            arg2 = args[1];

        // set a word boundary to the patterns
        String pattern = "\\b" + arg1 + "\\b";
        String pattern1 = "\\b" + arg2 + "\\b";

        // find the patterns in the files and if found, print the lines from
        // pattern 1 to pattern 2
        printlines(pattern, pattern1);
    }

    /**
     * Method to read the html file and removes its tags and move the content to
     * a new file
     */
    public void remove_tag() {
        // create a new file to write the input file data
        File newfile = new File(temp_file);
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder();
        boolean flag_start = false;

        // read each line and remove the tags
        try {
            pw = new PrintWriter(newfile);
            input = new BufferedReader(new FileReader(fileinput));
            // skip lines which contain single tags and write the remaining
            // lines in the tempporary file
            while ((line = input.readLine()) != null) {

                if (line.contains("<") && line.contains(">") && !flag_start) {
                    sb.append(line)
                            .append(System.getProperty("line.separator"));

                }
                if ((!line.contains("<")) && (!line.contains(">"))
                        && !flag_start) {
                    sb.append(line)
                            .append(System.getProperty("line.separator"));

                }
                if ((line.contains("<")) && (!line.contains(">"))) {
                    flag_start = true;
                }
                if ((!line.contains("<")) && (line.contains(">"))) {
                    flag_start = false;
                }
            }
            // create a string removing all the tags from the file
            String nohtml = sb.toString().replaceAll("<!--.*?-->|<[^>]+>", "");
            pw.println(nohtml);
            pw.flush();
            input.close();
            pw.close();

        } catch (Exception e) {// catch exception when an action fails while
            // processing the code
            System.err.println("Something went wrong!");
        }

    }

    /**
     * This method reads the temporary file and writes all the contents between
     * pattern1 and pattern2 mentioned
     * 
     * @param pattern
     *            first pattern to be searched
     * @param pattern1
     *            second pattern to be searched
     */
    public static void printlines(String pattern, String pattern1) {
        // create a file to which the output needs to be written
        File newfile = new File(fileoutput);
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader input1;
        String line1;

        try {
            boolean print = false;
            boolean p2found = false;
            // read the contents from the temporary file
            input1 = new BufferedReader(new FileReader(temp_file));
            pw = new PrintWriter(newfile);
            // Create a Pattern objects
            Pattern start = Pattern.compile(pattern);
            Pattern end = Pattern.compile(pattern1);

            // Read the contents of temporary file
            while ((line1 = input1.readLine()) != null) {

                // Now create matcher objects
                Matcher m = start.matcher(line1);
                Matcher e = end.matcher(line1);
                if (e.find()) {
                    p2found = true;
                    // if pattern2 is found, write it to the output file
                    sb.append(line1).append(
                            System.getProperty("line.separator"));
                    break;
                }
                if (m.find()) {
                    print = true;
                }
                if (print == true)
                    // if pattern2 is found, write it to the output file
                    sb.append(line1).append(
                            System.getProperty("line.separator"));
            }

            // if either pattern1 or 2 is not found in the file, display error
            // message
            if (print == false) {
                System.err.println("Pattern: "
                        + pattern.substring(2, pattern.length() - 2)
                        + " not found");
                System.exit(1);
            } else if (p2found == false) {
                System.err.println("Pattern: "
                        + pattern1.substring(2, pattern1.length() - 2)
                        + " not found");
                System.exit(1);
            }

            // write the whole matching text to the output file
            String finalfile = sb.toString();
            pw.println(finalfile);
            pw.flush();
            input1.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Main function
     * 
     * @param args
     */
    public static void main(String[] args) {

        // create objects of the class and call the required methods
        pattern p = new pattern();
        p.validate(args);
        p.remove_tag();
        p.setpattern(args);

    }
}