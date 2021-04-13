package edu.brown.cs.student.esg;

import com.google.common.base.Ticker;

import java.util.HashMap;

public class Company {

    private String companyPermID;
    private String ticker;
    private String companyName;
    private String industryName;
    private String taxonomyType;
    private String taxonomyPermID;
    private String taxonomyName;
    private String score;
    private HashMap<String, Integer> uniqueWords;

    public Company(String[] values) {
        this.parseValues(values);
    }

    // should have 8 values
    private void parseValues(String[] values) {
        companyPermID = values[0];
        ticker = values[1];
        companyName = values[2];
        industryName = values[3];
        taxonomyType = values[4];
        taxonomyPermID = values[5];
        taxonomyName = values[6];
        score = values[7];
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
}