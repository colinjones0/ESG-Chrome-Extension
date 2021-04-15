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
    esgData = parser.parseCSV(new File("companies2.csv"));
    //createGraph(esgData);
  }

  /**
   * Create the graph based off the companies in the data file.
   */
  public String[][] createGraph(String url) {
    List<NewCompany> companyList = new ArrayList<>();
    NewCompany currCompany = new NewCompany(null);
    for (String[] companyData: esgData) {
      NewCompany newCompany = new NewCompany(companyData);
      newCompany.setUniqueWords(scraper.getText(newCompany.getUrl()));
      if (newCompany.getUrl().equals(url)) {
        currCompany = newCompany;
      } else {
        companyList.add(newCompany);
      }
    }
    graph = new Graph();
    String[][] returnData = graph.buildGraph(companyList, currCompany);
    return returnData;
  }

}
