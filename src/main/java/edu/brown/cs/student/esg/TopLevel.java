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
      if (!firstRow) { // skip first row of csv
        Company newCompany = new Company(companyData);
        newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
        if (newCompany.getCompanyURL().equals(url)) {
          currCompany = newCompany;
        } else {
          companyList.add(newCompany);
        }
      }
      firstRow = false;
    }
    if (currCompany.getUniqueWords() == null) {
      throw new UserFriendlyException("Company not in Database");
    }
    graph = new Graph();
    return graph.buildGraph(companyList, currCompany);
  }
}
