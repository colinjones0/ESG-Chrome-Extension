package edu.brown.cs.student.esg;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REPL {
   private ArrayList<String[]> data;

    public REPL() {
        data = new ArrayList<String[]>();
    }

    /**
     * REPL to Read. Input passed through InputChecker, then passed
     * to the appropriate Command Class.
     */
    public void readUserInput() throws IOException, SQLException, ClassNotFoundException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        while (true) {
            String userInput = bf.readLine();
            if(userInput == null) {
                break;
            }
            if (userInput.equals("")){
                System.out.println("ERROR: Please enter a command");
            }
            else if(userInput != null) {
                //tokenizes the userInput by spaces.
                List<String> tokens = new ArrayList<String>();
                Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(userInput);
                while (m.find()) {
                    tokens.add(m.group(1).replace("\"", ""));
                }


                if(tokens.get(0).equals("load")) {
                    System.out.println("loading data");
                    File filename = new File(tokens.get(1));
                    if(filename.exists()) {
                        System.out.println("exists");
                        Parser parser = new Parser();
                        data = parser.parseCSV(filename);
                        //System.out.println(data);
                    }
                    this.convertToCompanies();
                }
            }
        }
    }

    private void convertToCompanies() {
        ArrayList<Company> convertedData = new ArrayList<Company>();
        for(String[] values: data) {
            convertedData.add(new Company(values));
        }
        System.out.println(convertedData.get(1).getCompanyName());
        System.out.println(convertedData.get(1).getScore());
    }
}