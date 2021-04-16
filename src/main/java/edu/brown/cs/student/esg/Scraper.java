package edu.brown.cs.student.esg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class that handles web-scraping.
 */
public class Scraper {
  private HashSet<String> commonWords;

  public Scraper() {
    loadCommonWords();
  }

  /**
   * Finds the company of the page that the user is on.
   * @param url - url taken from url.
   */
  public String findCompany(String url) {
//    try {
////      System.out.println("hi");
////      Document doc = Jsoup.connect(url).get();
////      Elements test = doc.select("title");
////      String testText = test.text();
////      System.out.println(testText);
//      /* Query the database to see if the url is in it. Take company name from there
//      otherwise return that company is not found or something.
//       */
//    } catch (IOException e) {
//      System.out.println("Caught IOexcepction"); // fix handling later
//    }
    return null;
  }

  /**
   * dfsdf.
   */
    public HashMap<String, Integer> getText(String url) {
      try {
        //TODO: privacy on scraping, must not crash. Rotate IP address
        Document doc = Jsoup.connect(url).get();
        Elements test = doc.select("*"); // selects all
        String testText = test.text();
        //maybe use regex
        StringTokenizer tokenizer = new StringTokenizer((testText));
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        while (tokenizer.hasMoreTokens()) {
          String currWord = tokenizer.nextToken();
          if (!commonWords.contains(currWord)) { // add all non common words
            if (!uniqueWords.containsKey(currWord)) {
              uniqueWords.put(currWord, 1);
            } else {
              int oldVal = uniqueWords.get(currWord);
              int newVal = oldVal++;
              uniqueWords.replace(currWord, oldVal, newVal);
            }
          }
        }
        return uniqueWords;
      } catch (IOException e) {
        System.out.println("Caught IOexcepction for " + url); // fix handling later
      }
      return null;
    }

  /**
   * maybe factor out, doesn't need to happen every time
   */
  public void loadCommonWords () {
    commonWords = new HashSet<>();
    Parser parser = new Parser();
    List<String[]> commonWordsList = parser.parseCSV(new File("data/common_words.csv"));
    for (String[] word: commonWordsList) {
      commonWords.add(word[0]);
    }
  }

}