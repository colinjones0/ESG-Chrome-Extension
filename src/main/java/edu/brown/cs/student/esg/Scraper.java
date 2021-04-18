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

  /**
   * Constructor loads common words when the program is run.
   */
  public Scraper() {
    loadCommonWords();
  }

/**
 * This method scrapes all of the text from a given webpage. It then puts all of
 * the unique words (not contained in common words) into a HashMap.
 * @param url - url of the page we are on from frontend
 * @return HashMap<String, Integer> uniqueWords
 */
  public HashMap<String, Integer> getText(String url) throws UserFriendlyException {
    try {
      Document doc = Jsoup.connect(url).get(); // connection. throws IOException on failure
      Elements test = doc.select("*"); // selects all elements on the page
      String testText = test.text(); // converts element to text
      //maybe use regex
      StringTokenizer tokenizer = new StringTokenizer((testText));
      HashMap<String, Integer> uniqueWords = new HashMap<>();
      while (tokenizer.hasMoreTokens()) {
        String currWord = tokenizer.nextToken();
        if (!commonWords.contains(currWord)) { // add all non common words
          if (!uniqueWords.containsKey(currWord)) { // make new key value pair
            uniqueWords.put(currWord, 1);
          } else { // increment value for duplicated words
            int oldVal = uniqueWords.get(currWord);
            int newVal = oldVal++;
            uniqueWords.replace(currWord, oldVal, newVal);
          }
        }
      }
      return uniqueWords;
    } catch (IOException e) {
      throw new UserFriendlyException("Couldn't connect to website for scraping");
    }
  }

  /**
   * Loads the 100 most common words in the English language into a HashSet.
   */
  public void loadCommonWords() {
    commonWords = new HashSet<>();
    Parser parser = new Parser();
    List<String[]> commonWordsList = parser.parseCSV(new File("data/common_words.csv"));
    for (String[] word: commonWordsList) {
      commonWords.add(word[0]);
    }
  }
}
