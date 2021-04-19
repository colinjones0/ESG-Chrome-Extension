package edu.brown.cs.student.esg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Top Level Object for the Project. Contains both the scraper and the
 * recommendation algorithm.
 */
public class TopLevel {

  private final Scraper scraper = new Scraper();
  private final List<String[]> esgData;
  private static final int DATA_COLS = 10;

  /**
   * Constructor for the Top Level that instantiates the parser and parses the dataset.
   */
  public TopLevel() {
    /* Load ESG data */
    Parser parser = new Parser();
    esgData = parser.parseCSV(new File("data/mock-data.csv"));

    //TODO : Cache Scrape here
  }

  /**
   * Create the graph based off the companies in the data file.
   * @param url - url of page user is on from frontend
   * @return String[][] returnData
   */
  public String[][] createGraph(String url, boolean byESG) throws UserFriendlyException {
    List<Company> companyList = new ArrayList<>();
    Company currCompany = new Company(new String[DATA_COLS]);

    //Find the company, but don't remove
    for (String[] companyData: esgData){
      if(companyData[3].equals(url)){
        currCompany =  new Company(companyData);
        //TODO Cache
        currCompany.setUniqueWords(scraper.getText(url));

        /* Throw an exception if we do not have any data on the currCompany */
        if (currCompany.getUniqueWords() == null) {
          throw new UserFriendlyException("Company not in Database");
        }
      }
    }

    /* Iterate through the rest of the data, create companies, and scrape the pages for each row. */
    boolean firstRow = true; // skips first row of ESG file with titles
    for (String[] companyData: esgData) {
      Company newCompany = new Company(companyData);
      // Ensures we do not reccomend current company
      if(!(currCompany.getCompanyURL() == newCompany.getCompanyURL())) {
        if (!firstRow) { // skip first row of csv
          try {
            newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
            if (byESG) {
              if (Double.parseDouble(companyData[5]) > Double.parseDouble(currCompany.getScore())) {
                companyList.add(newCompany);
              }
            } else {
              companyList.add(newCompany);
            }
          } catch (UserFriendlyException e) { // companies with failed scrapes not included in rec
            continue;
          }
        }
      }
      firstRow = false; // set after first row
    }
    SimilarityAlgorithm graph = new SimilarityAlgorithm();
    return graph.findSimilarities(companyList, currCompany, byESG);
  }
}
