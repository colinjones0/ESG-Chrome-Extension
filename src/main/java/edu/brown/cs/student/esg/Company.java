package edu.brown.cs.student.esg;

import com.google.common.base.Ticker;

import java.util.HashMap;

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

    private HashMap<String, Integer> uniqueWords;

    public Company(String[] values) {
        this.parseValues(values);
    }

    // should have 8 values
    private void parseValues(String[] values) {
        companyName = values[1];
        ticker = values[2];
        companyURL = values[3];
        pointPercentage = values[4];
        score = values[5];
        envResource = values[6];
        socResource = values[7];
        govResource = values[8];
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

    public String getCompanyPermID() {
        return companyPermID;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public String getPointPercentage() {
        return pointPercentage;
    }

    public String getEnvResource() {
        return envResource;
    }

    public String getSocResource() {
        return socResource;
    }

    public String getGovResource() {
        return govResource;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double newWeight) {
        weight = newWeight;
    }

    public HashMap<String, Integer> getUniqueWords() {
        return uniqueWords;
    }
}