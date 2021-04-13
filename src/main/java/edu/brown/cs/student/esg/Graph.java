package edu.brown.cs.student.esg;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Graph {

  public Graph(List<NewCompany> companies) {
    buildGraph(companies);
  }

  public void buildGraph(List<NewCompany> companies) {
    // check for empty
    NewCompany root = companies.remove(0);
    root.setWeight(0.0);
    HashMap<String, Integer> rootWords = root.getUniqueWords();
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
  }
}
