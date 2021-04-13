package edu.brown.cs.student.esg;

import java.util.HashMap;

public class NewCompany {

    private String companyName;
    private String url;
    private String score;
    private HashMap<String, Integer> uniqueWords;
    private Double weight;

    public NewCompany(String[] values) {
      this.parseValues(values);
    }

    // should have 8 values
    private void parseValues(String[] values) {
      companyName = values[0];
      url = values[1];
      score = values[2];
    }

    public String getCompanyName() {
      return companyName;
    }

    public String getScore() {
      return score;
    }

    public void setUniqueWords(HashMap<String, Integer> uniqueWordsData) {
      uniqueWords = uniqueWordsData;
    }
    public HashMap<String, Integer> getUniqueWords() {
      return uniqueWords;
    }

    public String getUrl() {
      return url;
    }

    public Double getWeight() {
      return weight;
    }

    public void setWeight(Double newWeight) {
      weight = newWeight;
    }
}
