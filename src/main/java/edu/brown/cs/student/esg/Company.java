package edu.brown.cs.student.esg;

import java.util.HashMap;

/**
 * Object to represent a company, holding all the data from the dataset.
 */
public class Company {
  private String companyPermID;
  private String companyName;
  private String ticker;
  private String companyURL;
  private String pointPercentage;
  private String score;
  private String envResource;
  private String socResource;
  private String govResource;
  private Double weight;
  private static final int SOC_RESOURCE_INDEX = 7;
  private static final int GOV_RESOURCE_INDEX = 8;
  private HashMap<String, Integer> uniqueWords;

  /**
   * Constructor parses the values from the dataset.
   * @param values - values from dataset
   */
  public Company(String[] values) {
    this.parseValues(values);
  }

  /**
   * Sets the instance variables based on info from dataset.
   * @param values - values from dataset.
   */
  private void parseValues(String[] values) {
    companyName = values[1];
    ticker = values[2];
    companyURL = values[3];
    pointPercentage = values[4];
    score = values[5];
    envResource = values[6];
    socResource = values[SOC_RESOURCE_INDEX];
    govResource = values[GOV_RESOURCE_INDEX];
  }

  /**
   * Gets the companyName.
   * @return companyName
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Gets the score.
   * @return score
   */
  public String getScore() {
    return score;
  }

  /**
   * Sets the uniqueWords of the company page.
   * @param uniqueWordsData - hashMap from scraping
   */
  public void setUniqueWords(HashMap<String, Integer> uniqueWordsData) {
    uniqueWords = uniqueWordsData;
  }

  /**
   * Gets the company's perm id.
   * @return companyPermID
   */
  public String getCompanyPermID() {
    return companyPermID;
  }

  /**
   * Gets the company's Ticker.
   * @return ticker
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Gets the company's URL.
   * @return companyURL
   */
  public String getCompanyURL() {
    return companyURL;
  }

  /**
   * Gets the percentage of the score from the average.
   * @return pointPercentage.
   */
  public String getPointPercentage() {
    return pointPercentage;
  }

  /**
   * Gets the environmental article.
   * @return envResource
   */
  public String getEnvResource() {
    return envResource;
  }

  /**
   * Returns the social article.
   * @return socResource
   */
  public String getSocResource() {
    return socResource;
  }

  /**
   * Returns the government article.
   * @return govResource
   */
  public String getGovResource() {
    return govResource;
  }

  /**
   * Returns the similarity weight of the company.
   * @return weight
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Sets the similarity weight.
   * @param newWeight - passed in weight to be set to
   */
  public void setWeight(Double newWeight) {
    weight = newWeight;
  }

  /**
   * Gets the unique words of the company.
   * @return uniqueWords
   */
  public HashMap<String, Integer> getUniqueWords() {
    return uniqueWords;
  }
}
