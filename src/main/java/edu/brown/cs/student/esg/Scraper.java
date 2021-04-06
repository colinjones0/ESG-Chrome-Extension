package edu.brown.cs.student.esg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Class that hanldes webscraping.
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
      Document doc = Jsoup.connect(url).get();
      Elements test = doc.select("div.masthead");
    } catch (IOException e) {
      System.out.println("Caught IOexcepction"); // fix handling later
    }

  }
}