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
   * dfsdf.
   */
    public HashMap<String, Integer> getText(String url) throws UserFriendlyException {
      try {
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
          throw new UserFriendlyException("Couldn't connect to website for scraping");
      }
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