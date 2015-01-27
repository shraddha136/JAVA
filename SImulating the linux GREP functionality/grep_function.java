/**
 * grep_function
 *
 * @version   $Id: grep_function.java,v 1.1 2014/11/14 05:19:46
 *
 * @author      Shraddha Atrawalkar
 *
 * Revisions:
 * Initial revision
 *
 */

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * 
 * This class simulates the grep function present in the Unix operating system
 * to search files with a particular word in it
 *
 */

public class grep_function {
    public static BufferedReader input;// to get the whole text present in the
                                       // file
    public static PrintWriter output;// the text which matches the word to be
                                     // searched

    /**
     * 
     * Method to scan each file from the current directory path and get the
     * matching text for each word
     * 
     * @param search_word
     *            word to be searched in the files
     * @param file_name
     *            file name in which the word will be searched
     */
    public static void scan_file(String search_word, File file_name) {

        // variable to mark that the matching text is not found
        boolean found = false;
        try {
            String line;
            // point the buffered reader to the file which has to be read
            input = new BufferedReader(new FileReader(file_name));

            // create a file in which the matching text lines will be written
            output = new PrintWriter(new FileWriter("res.txt", true));

            // read each line of the file and check if the word to be searched
            // is present in it
            while ((line = input.readLine()) != null) {
                if (line.indexOf(search_word) >= 0) {
                    found = true;
                    // output.println(line);
                    System.out.println(line);
                }
            }
            // write the name of the file in the output file
            if (found)
                output.println("file matches" + file_name);
            // close both input and output stream
            output.close();

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ExceptionType occurred: " + e.getMessage());
        }
    }

    /**
     * Get each file in the directory path sent
     * 
     * @param path
     *            path of the folder the user has sent
     * @param search_word
     *            word to be searched in the files
     */
    public static void list_files(File path, String search_word) {
        /*
         * Get the list of files
         */
        File[] listOfFiles = path.listFiles();
        for (File file : listOfFiles) {
            /*
             * it can either be a file or directory
             */
            if (file.isFile())
                if (file.getPath().contains(".class")) {
                    System.out.println("Binary File " + file + " matches");
                } else {
                    scan_file(search_word, file);
                }
            if (file.isDirectory())
                list_files(file, search_word);
        }
    }

    /**
     * Main function
     * 
     * @param args
     */
    public static void main(String[] args) {

        /*
         * Check for a valid path
         */

        if (args.length > 0) {

            if (args[0].equals("find") && (args[2].equals("-type"))
                    && (args[3].equals("f")) && (args[4].equals("-exec"))
                    && (args[5].equals("grep")) && (args[7].equals("{}"))
                    && (args[8].equals("\\;"))) {

                // get the path sent by the user
                File path = new File(args[1]);
                if (path.isDirectory()) {
                    /*
                     * Check the dir list
                     */
                    list_files(path, args[6]);

                } else {
                    System.out.println("File path invalid");
                }

            } else {
                System.err
                        .println("Usage: find . -type f -exec grep Hello {} \\;");
                System.exit(1);
            }

        }
    }
}
