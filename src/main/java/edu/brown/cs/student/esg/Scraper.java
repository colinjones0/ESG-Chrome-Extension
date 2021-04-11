package edu.brown.cs.student.esg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Class that handles web-scraping.
 */
public class Scraper {

  public Scraper() {

  }

  /**
   * Finds the company of the page that the user is on.
   * @param url - url taken from url.
   */
  public String findCompany(String url) {
    try {
//      System.out.println("hi");
//      Document doc = Jsoup.connect(url).get();
//      Elements test = doc.select("title");
//      String testText = test.text();
//      System.out.println(testText);
      /* Query the database to see if the url is in it. Take company name from there
      otherwise return that company is not found or something.
       */
    } catch (IOException e) {
      System.out.println("Caught IOexcepction"); // fix handling later
    }
    return null;
  }
}