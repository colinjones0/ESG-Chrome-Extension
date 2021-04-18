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
  private static final int DATA_COLS = 9;

  /**
   * Constructor for the Top Level that instantiates the parser and parses the dataset.
   */
  public TopLevel() {
    /* Load ESG data */
    Parser parser = new Parser();
    esgData = parser.parseCSV(new File("data/mock-data.csv"));
  }

  /**
   * Create the graph based off the companies in the data file.
   * @param url - url of page user is on from frontend
   * @return String[][] returnData
   */
  public String[][] createGraph(String url) throws UserFriendlyException {
    List<Company> companyList = new ArrayList<>();
    Company currCompany = new Company(new String[DATA_COLS]); // company whose website we are on
    // use a map/set?
    /* Iterate through dataset and remove the company whose website we are on */
    for (int i = 0; i < esgData.size(); i++) {
      if (esgData.get(i)[3].equals(url)) { // find the matching row
        Company newCompany = new Company(esgData.get(i));
        esgData.remove(esgData.get(i));
        currCompany = newCompany;
        /* scrape currCompany page to set its unique words. */
        currCompany.setUniqueWords(scraper.getText(url));
      }
    }
    /* Throw an exception if we do not have any data on the currCompany */
    if (currCompany.getUniqueWords() == null) {
      throw new UserFriendlyException("Company not in Database");
    }
    boolean firstRow = true; // skips first row of ESG file with titles
    /* Iterate through the rest of the data, create companies, and scrape the pages for each row. */
    for (String[] companyData: esgData) {
      if (!firstRow) { // skip first row of csv
        /* Never look at companies with worse ESG scores */
        if (Double.parseDouble(companyData[5]) > Double.parseDouble(currCompany.getScore())) {
          Company newCompany = new Company(companyData);
          try {
            newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
            companyList.add(newCompany); // not reached if the scrape fails
          } catch (UserFriendlyException e) { // companies with failed scrapes not included in rec
            continue;
          }
        }
      }
      firstRow = false; // set after first row
    }
    SimilarityAlgorithm graph = new SimilarityAlgorithm();
    return graph.findSimilarities(companyList, currCompany);
  }
}
