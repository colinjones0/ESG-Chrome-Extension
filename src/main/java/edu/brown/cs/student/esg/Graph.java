package edu.brown.cs.student.esg;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Graph {

  public Graph() {
  }

  public String[][] buildGraph(List<Company> companies, Company currCompany) {
    // check for empty
    currCompany.setWeight(0.0);
    HashMap<String, Integer> rootWords = currCompany.getUniqueWords();
    /* set weights */
    for (Company company: companies) {
      AtomicReference<Double> weight = new AtomicReference<>(0.0);
      HashMap<String, Integer> companyWords = company.getUniqueWords();
      companyWords.forEach((key, value) -> {
        if (rootWords.containsKey(key)) { // they have the word in common
          /* find the number of words they have in common and add to weight */
          if (rootWords.get(key) < companyWords.get(key)) {
            weight.updateAndGet(v -> v + rootWords.get(key));
          } else {
            weight.updateAndGet(v -> v + companyWords.get(key));
          }
        }
      });
      company.setWeight(weight.get());

      /* Taking ESG scores into account for the weight*/
      Double esgBonus = Math.floor(Double.parseDouble(company.getScore()) / 25);
      company.setWeight(company.getWeight() + esgBonus);

    }
    String[][] returnData = new String[4][9];
    ScoreComparator sc = new ScoreComparator();
    companies.sort(sc); // check if high scores sorted first or low scores
    //make constant for number of suggestions
    for (int i = 0; i < 3; i ++) {
      // check for less esg scores
      returnData[i][0] = companies.get(i).getCompanyName();
      returnData[i][1] = companies.get(i).getTicker();
      returnData[i][2] = companies.get(i).getCompanyURL();
      returnData[i][3] = companies.get(i).getPointPercentage();
      returnData[i][4] = companies.get(i).getScore();
      returnData[i][5] = companies.get(i).getEnvResource();
      returnData[i][6] = companies.get(i).getSocResource();
      returnData[i][7] = companies.get(i).getGovResource();
      returnData[i][8] = companies.get(i).getImagePath();

    }
    returnData[3][0] = currCompany.getCompanyName();
    returnData[3][1] = currCompany.getTicker();
    returnData[3][2] = currCompany.getCompanyURL();
    returnData[3][3] = currCompany.getPointPercentage();
    returnData[3][4] = currCompany.getScore();
    returnData[3][5] = currCompany.getEnvResource();
    returnData[3][6] = currCompany.getSocResource();
    returnData[3][7] = currCompany.getGovResource();
    returnData[3][8] = currCompany.getImagePath();

    return returnData;
    // maybe mak a bracket system? log/exponential
  }
}
