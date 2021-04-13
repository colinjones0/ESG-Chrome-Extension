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
  private Graph graph;

  /**
   * Constructor for the Top Level.
   */
  public TopLevel() {
    /* Load ESG data */
    List<String[]> esgData = parser.parseCSV(new File("companies2.csv"));
    createGraph(esgData);
  }

  /**
   * Create the graph based off the companies in the data file.
   * @param esgData - data parsed from data file
   */
  private void createGraph(List<String[]> esgData) {
    List<NewCompany> companyList = new ArrayList<>();
    for (String[] companyData: esgData) {
      NewCompany newCompany = new NewCompany(companyData);
      newCompany.setUniqueWords(scraper.getText(newCompany.getUrl()));
      companyList.add(newCompany);
    }
    graph = new Graph(companyList);
  }

}
