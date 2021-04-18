package edu.brown.cs.student.esg;

import java.util.Comparator;

/**
 * Comparator for company scores.
 */
public class ScoreComparator implements Comparator<Company> {

  /**
   * compares the scores of companies, such that a list can be sorted
   * with this method.
   */
  @Override
  public int compare(Company o1, Company o2) {
    double esgScore1 = o1.getWeight();
    double esgScore2 = o2.getWeight();
    if (esgScore1 > esgScore2) {
      return -1;
    } else {
      return 1;
    }
  }
}
