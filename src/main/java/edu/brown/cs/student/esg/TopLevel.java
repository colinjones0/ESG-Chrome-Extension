package edu.brown.cs.student.esg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Top Level Object for the Project.
 */
public class TopLevel {

  private final Scraper scraper = new Scraper();
  private final List<String[]> esgData;

  /**
   * Constructor for the Top Level.
   */
  public TopLevel() {
    /* Load ESG data */
    Parser parser = new Parser();
    esgData = parser.parseCSV(new File("data/mock-data.csv"));
  }

  /**
   * Create the graph based off the companies in the data file.
   */
  public String[][] createGraph(String url) throws UserFriendlyException {
    List<Company> companyList = new ArrayList<>();
    Company currCompany = new Company(new String[9]);
    boolean firstRow = true;
    System.out.println(esgData.size());
    for (int i = 0; i < esgData.size(); i++) {
      if (esgData.get(i)[3].equals(url)) {
        Company newCompany = new Company(esgData.get(i));
        esgData.remove(esgData.get(i));
        currCompany = newCompany;
        System.out.println(currCompany);
        currCompany.setUniqueWords(scraper.getText(url));
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
          try {
            newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
            companyList.add(newCompany);
          } catch (UserFriendlyException e) {
            continue;
          }
        }
      }
      firstRow = false;
    }

    Graph graph = new Graph();
    return graph.buildGraph(companyList, currCompany);
  }
}
