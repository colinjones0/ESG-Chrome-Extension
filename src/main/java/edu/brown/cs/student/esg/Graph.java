package edu.brown.cs.student.esg;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Graph {

  public Graph() {
  }

  public String[][] buildGraph(List<NewCompany> companies, NewCompany currCompany) {
    // check for empty
    currCompany.setWeight(0.0);
    HashMap<String, Integer> rootWords = currCompany.getUniqueWords();
    /* set weights */
    for (NewCompany company: companies) {
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
      System.out.println(company.getWeight());
    }
    String[][] returnData = new String[4][3];
    ScoreComparator sc = new ScoreComparator();
    companies.sort(sc); // check if high scores sorted first or low scores
    //make constant for number of suggestions
    for (int i = 0; i < 3; i ++) {
      String score = companies.get(i).getScore();
      String companyName = companies.get(i).getCompanyName();
      String url = companies.get(i).getUrl();
      returnData[i][0] = score;
      returnData[i][1] = companyName;
      returnData[i][2] = url;
    }
    returnData[4][0] = currCompany.getScore();
    returnData[4][1] = currCompany.getCompanyName();
    returnData[4][2] = currCompany.getUrl();
    return returnData;
    // maybe mak a bracket system? log/exponential
  }
}
