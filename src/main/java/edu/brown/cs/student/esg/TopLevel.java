package edu.brown.cs.student.esg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Top Level Object for the Project. Contains both the scraper and the
 * recommendation algorithm.
 */
public class TopLevel {

  private final Scraper scraper = new Scraper();
  private final List<String[]> esgData;
  private static final int DATA_COLS = 10;
  private ArrayList<HashMap<String, Integer>> scrapeCache;
  private HashMap<String, Company> companiesCache;


  /**
   * Constructor for the Top Level that instantiates the parser and parses the dataset.
   */
  public TopLevel() {
    /* Load ESG data */
    Parser parser = new Parser();
    esgData = parser.parseCSV(new File("data/mock-data.csv"));
    scrapeCache = new ArrayList<HashMap<String, Integer>>();
    companiesCache = new HashMap<String, Company>();
  }


  /**
   * Parent companies method
   */
  public String[][] getParentOwned() {
    

    return null;
  }

  /**
   * Create the graph based off the companies in the data file.
   * @param url - url of page user is on from frontend
   * @return String[][] returnData
   */
  public String[][] createGraph(String url, boolean byESG) throws UserFriendlyException {
    List<Company> companyList = new ArrayList<>();
    Company currCompany = new Company(new String[DATA_COLS]);

    //given url, Find the company in cache or make new, but don't remove
    for (String[] companyData: esgData){
      if(companyData[3].equals(url)){
        if(!companiesCache.containsKey(url)){
          currCompany =  new Company(companyData);
          currCompany.setUniqueWords(scraper.getText(url));
          companiesCache.put(url, currCompany);
          /* Throw an exception if we do not have any data on the currCompany */
          if (currCompany.getUniqueWords() == null) {
            throw new UserFriendlyException("Company not in Database");
          }
        }
        else {
          currCompany = companiesCache.get(url);
        }
      }
    }

    /* Iterate through the rest of the data, create companies, and scrape the pages for each row. */
    boolean firstRow = true; // skips first row of ESG file with titles
    for (String[] companyData: esgData) {
      //comment this out, don't create new company object every time
      //Company newCompany = new Company(companyData);
      String curLoopURL = companyData[3];
      // Ensures we do not reccomend current company, and skips first row
      if(!(currCompany.getCompanyURL() == curLoopURL) && !firstRow) {
          try {
            // if doesn't exist in cache, scrape and add to cache
            if (byESG) {
              if (Double.parseDouble(companyData[5]) > Double.parseDouble(currCompany.getScore())) {
                //if not cached, cache
                if(!companiesCache.containsKey(curLoopURL)) {
                  Company newCompany = new Company(companyData);
                  newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
                  companiesCache.put(curLoopURL, newCompany);
                  System.out.println("caching: "+curLoopURL);
                }
                companyList.add(companiesCache.get(curLoopURL));
              }
            } else {
              //if not cached, cache
              if(!companiesCache.containsKey(curLoopURL)) {
                Company newCompany = new Company(companyData);
                newCompany.setUniqueWords(scraper.getText(newCompany.getCompanyURL()));
                companiesCache.put(curLoopURL, newCompany);
              }
              companyList.add(companiesCache.get(curLoopURL));
            }
          } catch (UserFriendlyException e) { // companies with failed scrapes not included in rec
            continue;
          }

      }
      firstRow = false; // set after first row
    }
    SimilarityAlgorithm graph = new SimilarityAlgorithm();
    return graph.findSimilarities(companyList, currCompany, byESG);
  }
}
