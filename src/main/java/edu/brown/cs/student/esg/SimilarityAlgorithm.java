package edu.brown.cs.student.esg;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Determines how similar companies are to each other based on how many words
 * their website's home pages have in common.
 */
public class SimilarityAlgorithm {
  private static final int ESG_BONUS_DIVIDER = 25;
  private static final int DATA_COLS = 9;
  private static final int SEVENTH_INDEX = 7;

  /**
   * Constructor.
   */
  public SimilarityAlgorithm() {
  }

  /**
   * Finds the similarity between companies by determining a similarity weight.
   * @param companies - list of companies with better ESG scores
   * @param currCompany - the company whose website the user is on
   * @return String[][] with the data to be returned to the front end
   */
  public String[][] findSimilarities(List<Company> companies, Company currCompany) {
    currCompany.setWeight(0.0);
    HashMap<String, Integer> rootWords = currCompany.getUniqueWords();
    /* set weights */
    for (Company company: companies) {
      AtomicReference<Double> similarityWeight = new AtomicReference<>(0.0);
      HashMap<String, Integer> companyWords = company.getUniqueWords();
      companyWords.forEach((key, value) -> {
        if (rootWords.containsKey(key)) { // they have the word in common
          /* find the number of words they have in common and add to weight */
          if (rootWords.get(key) < companyWords.get(key)) {
            similarityWeight.updateAndGet(v -> v + rootWords.get(key));
          } else {
            similarityWeight.updateAndGet(v -> v + companyWords.get(key));
          }
        }
      });
      company.setWeight(similarityWeight.get());

      /* Taking ESG scores into account for the weight*/
      Double esgBonus = Math.floor(Double.parseDouble(company.getScore()) / ESG_BONUS_DIVIDER);
      company.setWeight(company.getWeight() + esgBonus);

    }
    String[][] returnData = new String[4][DATA_COLS];
    ScoreComparator sc = new ScoreComparator();
    companies.sort(sc); // sort based off of similarity weight
    int numSuggestions;
    int listSize = companies.size();
    /* find how many companies there are to recommend (usually 3) */
    if (listSize > 3) {
      numSuggestions = 3;
    } else {
      numSuggestions = listSize;
    }
    /* set data of recommendations for return */
    for (int i = 0; i < numSuggestions; i++) {
      returnData[i][0] = companies.get(i).getCompanyName();
      returnData[i][1] = companies.get(i).getTicker();
      returnData[i][2] = companies.get(i).getCompanyURL();
      returnData[i][3] = companies.get(i).getPointPercentage();
      returnData[i][4] = companies.get(i).getScore();
      returnData[i][5] = companies.get(i).getEnvResource();
      returnData[i][6] = companies.get(i).getSocResource();
      returnData[i][SEVENTH_INDEX] = companies.get(i).getGovResource();
    }
    /* set data of currCompany to return */
    returnData[3][0] = currCompany.getCompanyName();
    returnData[3][1] = currCompany.getTicker();
    returnData[3][2] = currCompany.getCompanyURL();
    returnData[3][3] = currCompany.getPointPercentage();
    returnData[3][4] = currCompany.getScore();
    returnData[3][5] = currCompany.getEnvResource();
    returnData[3][6] = currCompany.getSocResource();
    returnData[3][SEVENTH_INDEX] = currCompany.getGovResource();
    return returnData;
  }
}

