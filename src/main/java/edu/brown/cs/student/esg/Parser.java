package edu.brown.cs.student.esg;

import java.io.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing Parsing algorithms.
 * Contains generic CSV Parser.
 * Contains userInput Parser.
 */
public class Parser {

    private static Pattern _doublePattern;
    private static Pattern _integerPattern;

    public Parser() {
        //set up regex patterns
        _doublePattern = Pattern.compile("-?\\d+(\\.\\d*)?");
        _integerPattern = Pattern.compile("-?(0|[1-9]\\d*)");
    }

    /**
     * Generic.
     * Parses a CSV File into ArrayList<String[]>
     * Does not handle validating data input.
     * @param: a CSV file of type File
     * @return: an ArrayList<String[]>
     */
    public ArrayList<String[]> parseCSV(File file) {
        // TODO: check that argument is a CSV file and not a different type of File ?
        ArrayList<String[]> toReturn = new ArrayList<String[]>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = br.readLine()) != null) {
                toReturn.add(line.split(","));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
