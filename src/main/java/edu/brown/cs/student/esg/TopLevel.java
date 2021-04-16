package edu.brown.cs.student.esg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Top Level Object for the Project.
 */
public class TopLevel {

  private final Parser parser = new Parser();
  private final Scraper scraper = new Scraper();
  private List<String[]> esgData;
  private Graph graph;

  /**
   * Constructor for the Top Level.
   */
  public TopLevel() {
    /* Load ESG data */
    esgData = parser.parseCSV(new File("data/mock-data copy.csv"));
  }

  /**
   * Create the graph based off the companies in the data file.
   */
  public String[][] createGraph(String url) throws UserFriendlyException {
    List<Company> companyList = new ArrayList<>();
    Company currCompany = new Company(new String[9]);
    boolean firstRow = true;
    for (String[] companyData: esgData) {
      if (companyData[3].equals(url)) {
        Company newCompany = new Company(companyData);
        esgData.remove(companyData);
        currCompany = newCompany;
      }
    }
    if (currCompany.getUniqueWords() == null) {
      throw new UserFriendlyException("Company not in Database");
    }
    for (String[] companyData: esgData) {
      if (!firstRow) { // skip first row of csv
        /* Never look at companies with worse ESG scores */
        if (Double.parseDouble(companyData[5]) > Double.parseDouble(currCompany.getScore())) {
          Company newCompany = new Company(companyData);
          newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
          companyList.add(newCompany);
        }
      }
      firstRow = false;
    }

    graph = new Graph();
    return graph.buildGraph(companyList, currCompany);
  }
}
